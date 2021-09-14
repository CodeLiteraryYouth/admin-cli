package com.dmj.cli.service;

import com.dmj.cli.entity.pay.PayRequest;
import com.dmj.cli.entity.pay.PayResponse;
import com.dmj.cli.entity.pay.RefundResponse;

import java.util.Map;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
public interface PayService {

    /**
     * 支付接口
     * @param params
     * @return
     */
    PayResponse pay(PayRequest params);

    /**
     * 退款接口
     * @param params
     * @return
     */
    RefundResponse refund(Map<String,String> params);
}
