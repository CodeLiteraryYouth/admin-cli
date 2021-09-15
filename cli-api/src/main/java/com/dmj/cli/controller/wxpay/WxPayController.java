package com.dmj.cli.controller.wxpay;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.OrderConstant;
import com.dmj.cli.common.constant.PayConstant;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;
import com.dmj.cli.service.TOrderService;
import com.dmj.cli.util.str.StringUtils;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApiConfigKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zd
 * @date 2021/9/15
 * @apiNote
 **/
@RestController
@RequestMapping("/wxPay")
@Slf4j
public class WxPayController {

    @Autowired
    private TOrderService tOrderService;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 异步通知
     */
    @RequestMapping(value = "/payNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        log.info("支付通知=" + xmlMsg);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

        String returnCode = params.get("return_code");

        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 注意此处签名方式需与统一下单的签名类型一致
        if (WxPayKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPartnerKey(), SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                // 更新订单信息
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
                            tOrder.setPaymentType(PayConstant.TradeChannel.Wx.getCode());
                            tOrder.setStatus(OrderConstant.OrderStatus.PAYMENT.getCode());
                            tOrderService.updateById(tOrder);
                        }
                    }
                }
                redisUtils.del(tradeNo);
                // 发送通知等
                Map<String, String> xml = new HashMap<String, String>(2);
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return WxPayKit.toXml(xml);
            }
        }
        return null;
    }
}
