package com.dmj.cli.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.OrderConstant;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.exception.LoginException;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.TOrderDetail;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;
import com.dmj.cli.domain.dto.pay.PayRequest;
import com.dmj.cli.domain.dto.pay.PayResponse;
import com.dmj.cli.handler.pay.PayContextHandler;
import com.dmj.cli.mapper.sys.TOrderMapper;
import com.dmj.cli.service.TOrderService;
import com.dmj.cli.service.impl.pay.AliPayScan;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult<Map<String, String>> getCodeUrl(OrderFormRequest orderFormRequest) {
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
        String tradeNo= IdUtil.getSnowflake(1, 1).nextIdStr();
        TOrder tOrder=tOrderMapper.selectOne(Wrappers.<TOrder>lambdaQuery().eq(TOrder::getCode,tradeNo));
        if (tOrder != null && OrderConstant.OrderStatus.PREPARE.getCode() != tOrder.getStatus()) {
            return BaseResult.fail(ResultStatusCode.ORDER_PAY_ERROR);
        }
        BigDecimal amount= NumberUtil.mul(orderFormRequest.getActualPrice(),NumberUtil.toBigDecimal(orderFormRequest.getNum()));
        tOrder = TOrder.builder().build()
                .setCode(tradeNo)
                .setCreateTime(LocalDateTime.now())
                .setUserId(userInfo.getId())
                .setAmount(amount);
        PayRequest payRequest=PayRequest.builder().build()
                .setTotalAmount(NumberUtil.toStr(amount))
                .setOutTradeNo(tradeNo)
                .setBody(orderFormRequest.getBody())
                .setTimeOut(orderFormRequest.getTimeOut())
                .setExtras(orderFormRequest.getExtras());
        PayContextHandler aliHandler=new PayContextHandler(new AliPayScan());
        PayResponse aliRes= aliHandler.pay(payRequest);
        if (!aliRes.isSuccess()) {
            return BaseResult.fail(aliRes.getMsg());
        }
        PayContextHandler wxHandler=new PayContextHandler(new WxPayScan());
        PayResponse wxRes= wxHandler.pay(payRequest);
        if (!wxRes.isSuccess()) {
            return BaseResult.fail(wxRes.getMsg());
        }
        tOrderMapper.insert(tOrder);
        TOrderDetail tOrderDetail=TOrderDetail.builder().build()
                .setOrderId(tOrder.getId())
                .setSkuId(orderFormRequest.getSkuId())
                .setSkuType(orderFormRequest.getSkuType())
                .setPrice(orderFormRequest.getActualPrice())
                .setNum(orderFormRequest.getNum());
        tOrderDetailService.save(tOrderDetail);
        Long timeOut=StringUtils.isBlank(orderFormRequest.getTimeOut()) ? 5*60 : getTimeout(orderFormRequest.getTimeOut());
        redisUtils.set(tradeNo, JSONUtil.toJsonStr(orderFormRequest),timeOut);
        JSONObject aliData=(JSONObject)aliRes.getData();
        String aliCodeUrl=aliData.getString("qr_code");
        Map<String,String> wxData=(Map<String, String>) wxRes.getData();
        String wxCodeUrl=wxData.get("code_url");
        Map<String,String> result=new HashMap<>(3);
        result.put("tradeNo",tradeNo);
        result.put("aliCodeUrl",aliCodeUrl);
        result.put("wxCodeUrl",wxCodeUrl);
        return BaseResult.success(result);
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
