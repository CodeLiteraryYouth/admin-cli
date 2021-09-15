package com.dmj.cli.handler.pay;

import com.dmj.cli.domain.dto.pay.PayRequest;
import com.dmj.cli.domain.dto.pay.PayResponse;
import com.dmj.cli.service.PayService;

/**
 * @author zd
 * @date 2021/9/15
 * @apiNote
 **/
public class PayContextHandler {

    private PayService payService;

    public PayContextHandler(PayService payService) {
        this.payService=payService;
    }

    public PayResponse pay(PayRequest payRequest) {
        return payService.pay(payRequest);
    }
}
