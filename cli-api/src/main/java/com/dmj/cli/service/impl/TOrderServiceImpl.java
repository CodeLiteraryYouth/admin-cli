package com.dmj.cli.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.OrderConstant;
import com.dmj.cli.common.constant.PayConstant;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.exception.LoginException;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.TOrderDetail;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;
import com.dmj.cli.domain.dto.pay.PayRequest;
import com.dmj.cli.domain.dto.pay.PayResponse;
import com.dmj.cli.handler.pay.PayContextHandler;
import com.dmj.cli.mapper.sys.TOrderMapper;
import com.dmj.cli.service.TOrderService;
import com.dmj.cli.service.api.UserInfoAccountService;
import com.dmj.cli.service.impl.pay.WxPayScan;
import com.dmj.cli.service.sys.TOrderDetailService;
import com.dmj.cli.util.ServletUtils;
import com.dmj.cli.util.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private TOrderDetailService tOrderDetailService;

    @Autowired
    private UserInfoAccountService accountService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult<Map<String, String>> getCodeUrl(OrderFormRequest orderFormRequest) {
        BaseResult<TOrder> orderInfo=buildOrderInfo(orderFormRequest);
        if (orderInfo.getCode() != 200 ) {
            return BaseResult.fail(orderInfo);
        }
        TOrder tOrder=orderInfo.getData();
        PayRequest payRequest=PayRequest.builder().build()
                .setTotalAmount(NumberUtil.toStr(tOrder.getAmount()))
                .setOutTradeNo(tOrder.getCode())
                .setBody(orderFormRequest.getBody())
                .setTimeOut(orderFormRequest.getTimeOut())
                .setExtras(orderFormRequest.getExtras());
        //PayContextHandler aliHandler=new PayContextHandler(new AliPayScan());
        //PayResponse aliRes= aliHandler.pay(payRequest);
        //if (!aliRes.isSuccess()) {
        //    return BaseResult.fail(aliRes.getMsg());
        //}
        PayContextHandler wxHandler=new PayContextHandler(new WxPayScan());
        PayResponse wxRes= wxHandler.pay(payRequest);
        if (!wxRes.isSuccess()) {
            return BaseResult.fail(wxRes.getMsg());
        }

        Long timeOut=StringUtils.isBlank(orderFormRequest.getTimeOut()) ? 5*60 : getTimeout(orderFormRequest.getTimeOut());
        redisUtils.set(tOrder.getCode(), JSONUtil.toJsonStr(orderFormRequest),timeOut);
        //JSONObject aliData=(JSONObject)aliRes.getData();
        //String aliCodeUrl=aliData.getString("qr_code");
        Map<String,String> wxData=(Map<String, String>) wxRes.getData();
        String wxCodeUrl=wxData.get("code_url");
        Map<String,String> result=new HashMap<>(3);
        result.put("tradeNo",tOrder.getCode());
        //result.put("aliCodeUrl",aliCodeUrl);
        result.put("wxCodeUrl",wxCodeUrl);
        return BaseResult.success(result);
    }

    /**
     * 1、判断用户账户是否存在
     * 2、判断账户余额是否充足
     * 3、完成账户的扣减并更改订单状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult<UserInfoAccount> cashPay(OrderFormRequest orderFormRequest) {
        BaseResult<TOrder> orderInfo=buildOrderInfo(orderFormRequest);
        if (orderInfo.getCode() != 200 ) {
            return BaseResult.fail(orderInfo);
        }
        TOrder tOrder=orderInfo.getData();
        Integer skuType = orderFormRequest.getSkuType();
        UserInfoAccount account=accountService.getOne(Wrappers.<UserInfoAccount>lambdaQuery()
                .eq(Objects.nonNull(orderFormRequest.getUserId()),UserInfoAccount::getUserId,orderFormRequest.getUserId())
                .eq(skuType != null,UserInfoAccount::getAccountType,skuType));
        if (account == null) {
            return BaseResult.fail("不存在该账户，请前去创建新账户");
        }
        account.setAccountAmount(NumberUtil.sub(account.getAccountAmount(),orderFormRequest.getActualPrice()));
        account.setAccountNum(NumberUtil.sub(account.getAccountNum(),orderFormRequest.getNum()).longValue());
        accountService.updateById(account);
        //防止订单已支付被重复推送
        if (OrderConstant.OrderStatus.PREPARE.getCode() == tOrder.getStatus()) {
            tOrder.setPaymentType(PayConstant.TradeChannel.Cash.getCode());
            tOrder.setStatus(OrderConstant.OrderStatus.PAYMENT.getCode());
            //生成支付流水号
            String payNo=IdUtil.getSnowflake().nextIdStr();
            tOrder.setTradeNo(payNo);
            tOrderMapper.updateById(tOrder);
        }
        return BaseResult.success(account);
    }

    private BaseResult<TOrder> buildOrderInfo(OrderFormRequest orderFormRequest) {
        HttpServletRequest request= ServletUtils.getRequest();
        String token = request.getHeader(AuthConstants.LOGIN_TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            throw new LoginException("当前用户未登录，请前去登录");
        }
        Long sceneId=Long.valueOf(token);
        String user= (String) redisUtils.get(sceneId.toString());
        if (StringUtils.isBlank(user)) {
            throw new LoginException("当前用户未登录，请前去登录");
        }
        UserInfo userInfo= JSONUtil.toBean(user,UserInfo.class);
        String tradeNo= IdUtil.getSnowflake().nextIdStr();
        TOrder tOrder=tOrderMapper.selectOne(Wrappers.<TOrder>lambdaQuery().eq(TOrder::getCode,tradeNo));
        if (tOrder != null && OrderConstant.OrderStatus.PREPARE.getCode() != tOrder.getStatus()) {
            return BaseResult.fail(ResultStatusCode.ORDER_PAY_ERROR);
        }
        BigDecimal amount= NumberUtil.mul(orderFormRequest.getActualPrice(),NumberUtil.toBigDecimal(orderFormRequest.getNum()));
        tOrder = TOrder.builder().build()
                .setCode(tradeNo)
                .setCreateTime(DateUtil.date())
                .setUserId(userInfo.getId())
                .setAmount(amount);
        tOrderMapper.insert(tOrder);
        TOrderDetail tOrderDetail=TOrderDetail.builder().build()
                .setOrderId(tOrder.getId())
                .setSkuId(orderFormRequest.getSkuId())
                .setSkuType(orderFormRequest.getSkuType())
                .setPrice(orderFormRequest.getActualPrice())
                .setNum(orderFormRequest.getNum());
        tOrderDetailService.save(tOrderDetail);
        return BaseResult.success(tOrder);
    }

    /**
     * 获取超时时间
     * m：分钟
     * h：小时
     * d：天
     * @param timeOut
     * @return
     */
    private Long getTimeout(String timeOut) {
        char c=timeOut.charAt(timeOut.length()-1);
        Long t=Long.valueOf(timeOut.substring(0,timeOut.indexOf(c)));
        switch (c) {
            case 'm':
                return t*60;
            case 'h':
                return t*60*60;
            case 'd':
                return t*60*60*24;
            default:
                return 5L;
        }
    }

}
