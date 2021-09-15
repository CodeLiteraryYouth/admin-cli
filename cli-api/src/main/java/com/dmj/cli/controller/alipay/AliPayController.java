package com.dmj.cli.controller.alipay;

import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.OrderConstant;
import com.dmj.cli.common.constant.PayConstant;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;
import com.dmj.cli.entity.AliPayBean;
import com.dmj.cli.service.TOrderService;
import com.dmj.cli.util.str.StringUtils;
import com.ijpay.alipay.AliPayApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zd
 * @date 2021/9/15
 * @apiNote
 **/
@RestController
@RequestMapping("/aliPay")
@Slf4j
public class AliPayController {

    @Resource
    private AliPayBean aliPayBean;

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 1、获取订单信息
     * 2、从redis中获取请求信息判断是充值信息还是商品支付信息
     * 3、修改订单状态
     * @param request
     * @return
     */
    @RequestMapping(value = "/cert_notify_url")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public String certNotifyUrl(HttpServletRequest request) {
        try {
            // 获取支付宝POST过来反馈信息
            Map<String, String> params = AliPayApi.toMap(request);

            for (Map.Entry<String, String> entry : params.entrySet()) {
                log.info(entry.getKey() + " = " + entry.getValue());
            }

            boolean verifyResult = AlipaySignature.rsaCertCheckV1(params, aliPayBean.getAliPayCertPath(), "UTF-8", "RSA2");

            if (verifyResult) {
                String tradeNo=params.get("out_trade_no");
                String reqJson=redisUtils.get(tradeNo).toString();
                if (StringUtils.isNotBlank(reqJson)) {
                    OrderFormRequest formRequest= JSONUtil.toBean(reqJson,OrderFormRequest.class);
                    if (PayConstant.SkuType.RECHARGE.getId() == formRequest.getSkuType()) {
                        //填充充值成功以后用户账户的次数修改等逻辑
                    } else {
                        TOrder tOrder=tOrderService.getOne(Wrappers.<TOrder>lambdaQuery().eq(TOrder::getCode,tradeNo));
                        //防止订单已支付被重复推送
                        if (OrderConstant.OrderStatus.PREPARE.getCode() == tOrder.getStatus()) {
                            tOrder.setPaymentType(PayConstant.TradeChannel.Ali.getCode());
                            tOrder.setStatus(OrderConstant.OrderStatus.PAYMENT.getCode());
                            tOrderService.updateById(tOrder);
                        }
                    }
                }
                redisUtils.del(tradeNo);
                return "success";
            } else {
                log.error("ali pay verify failure");
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.error("支付回调异常",e);
            return "failure";
        }
    }
}
