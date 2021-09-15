package com.dmj.cli.domain.dto.pay;

import cn.hutool.json.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayRequest implements Serializable {

    /**
     * 支付主体
     */
    private String body;

    /**
     * 支付金额（单位：元）
     */
    private String totalAmount;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 支付超时时间
     */
    private String timeOut;

    /**
     * 支付扩展参数（例如storeId、红包等支付参数）
     */
    private JSONObject extras;
}
