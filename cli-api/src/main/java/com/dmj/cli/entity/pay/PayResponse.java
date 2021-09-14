package com.dmj.cli.entity.pay;

import com.dmj.cli.common.constant.BaseResult;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
public class PayResponse extends BaseResult {

    public PayResponse(int code, String msg, Object data) {
        super(code, msg, data);
    }
}
