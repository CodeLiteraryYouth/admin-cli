package com.dmj.cli.service.impl.pay;

import com.dmj.cli.controller.wxpay.AbstractWxPayApiController;
import com.dmj.cli.domain.dto.pay.PayRequest;
import com.dmj.cli.domain.dto.pay.PayResponse;
import com.dmj.cli.domain.dto.pay.RefundResponse;
import com.dmj.cli.entity.WxPayBean;
import com.dmj.cli.service.PayService;
import com.dmj.cli.util.ServletUtils;
import com.dmj.cli.util.str.StringUtils;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
@Slf4j
public class WxPayScan extends AbstractWxPayApiController implements PayService {

    @Resource
    WxPayBean wxPayBean;

    private String notifyUrl;
    private String refundNotifyUrl;


    @Override
    public WxPayApiConfig getApiConfig() {
        WxPayApiConfig apiConfig;

        try {
            apiConfig = WxPayApiConfigKit.getApiConfig(wxPayBean.getAppId());
        } catch (Exception e) {
            apiConfig = WxPayApiConfig.builder()
                    .appId(wxPayBean.getAppId())
                    .mchId(wxPayBean.getMchId())
                    .partnerKey(wxPayBean.getPartnerKey())
                    .certPath(wxPayBean.getCertPath())
                    .domain(wxPayBean.getDomain())
                    .build();
        }
        notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
        refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        return apiConfig;
    }

    @Override
    public PayResponse pay(PayRequest params) {
        String totalAmount=params.getTotalAmount();
        if (StringUtils.isBlank(totalAmount)) {
            return (PayResponse) PayResponse.fail("支付金额不能为空");
        }
        String outTradeNo=params.getOutTradeNo();
        if (StringUtils.isBlank(outTradeNo)) {
            return (PayResponse) PayResponse.fail("订单号不能为空");
        }
        String ip = IpKit.getRealIp(ServletUtils.getRequest());
        if (StringUtils.isBlank(ip)) {
            ip = "127.0.0.1";
        }
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> payParams = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body(StringUtils.isBlank(params.getBody()) ? "产品支付" : params.getBody())
                .attach(params.getExtras().getStr("attach"))
                .out_trade_no(outTradeNo)
                .total_fee(totalAmount)
                .spbill_create_ip(ip)
                .notify_url(notifyUrl)
                .trade_type(TradeType.NATIVE.getTradeType())
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, payParams);
        log.info("微信统一下单:" + xmlResult);

        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
            return (PayResponse) PayResponse.fail("error:" + returnMsg);
        }
        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            return (PayResponse) PayResponse.fail("error:" + returnMsg);
        }
        return (PayResponse) PayResponse.success(result);
    }

    @Override
    public RefundResponse refund(Map<String, String> params) {
        return null;
    }


}
