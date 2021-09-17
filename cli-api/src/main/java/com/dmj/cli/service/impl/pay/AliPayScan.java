package com.dmj.cli.service.impl.pay;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.dmj.cli.common.constant.PayConstant;
import com.dmj.cli.controller.alipay.AbstractAliPayApiController;
import com.dmj.cli.domain.dto.pay.PayRequest;
import com.dmj.cli.domain.dto.pay.PayResponse;
import com.dmj.cli.domain.dto.pay.RefundResponse;
import com.dmj.cli.entity.AliPayBean;
import com.dmj.cli.service.PayService;
import com.dmj.cli.util.str.StringUtils;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
@Slf4j
public class AliPayScan extends AbstractAliPayApiController implements PayService {

    @Resource
    private AliPayBean aliPayBean;

    /**
     * 证书模式
     */
    private final static String NOTIFY_URL = "/aliPay/cert_notify_url";
    /**
     * 证书模式
     */
    private final static String RETURN_URL = "/aliPay/cert_return_url";

    @Override
    public AliPayApiConfig getApiConfig() throws AlipayApiException {
        AliPayApiConfig aliPayApiConfig;
        try {
            aliPayApiConfig = AliPayApiConfigKit.getApiConfig(aliPayBean.getAppId());
        } catch (Exception e) {
            aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(aliPayBean.getAppId())
                    .setAliPayPublicKey(aliPayBean.getPublicKey())
                    .setAppCertPath(aliPayBean.getAppCertPath())
                    .setAliPayCertPath(aliPayBean.getAliPayCertPath())
                    .setAliPayRootCertPath(aliPayBean.getAliPayRootCertPath())
                    .setCharset("UTF-8")
                    .setPrivateKey(aliPayBean.getPrivateKey())
                    .setServiceUrl(aliPayBean.getServerUrl())
                    .setSignType("RSA2")
                    // 普通公钥方式
                    //.build();
                    // 证书模式
                    .buildByCert();

        }
        return aliPayApiConfig;
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
        String storeId=params.getExtras().getStr("storeId");
        String notifyUrl = aliPayBean.getDomain() + NOTIFY_URL;
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(StringUtils.isBlank(params.getBody()) ? "支付主体" : params.getBody());
        //分转换为元
        model.setTotalAmount(NumberUtil.toStr(NumberUtil.div(totalAmount,"100")));
        model.setStoreId(storeId);
        model.setTimeoutExpress(StringUtils.isBlank(params.getTimeOut()) ? "5m" : params.getTimeOut());
        model.setOutTradeNo(outTradeNo);
        try {
            String resultStr = AliPayApi.tradePrecreatePayToResponse(model, notifyUrl).getBody();
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            JSONObject response=jsonObject.getJSONObject("alipay_trade_precreate_response");
            String code = response.getString("code");
            if (PayConstant.Ali_Success_Code.equals(code)) {
                return (PayResponse) PayResponse.success(response);
            }
        } catch (Exception e) {
            log.error("微信扫码支付异常",e);
            return (PayResponse) PayResponse.fail(e.getMessage());

        }
        return (PayResponse) PayResponse.fail();
    }

    @Override
    public RefundResponse refund(Map<String, String> params) {
        return null;
    }
}
