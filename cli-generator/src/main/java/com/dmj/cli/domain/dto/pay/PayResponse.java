package com.dmj.cli.domain.dto.pay;

import com.dmj.cli.common.constant.BaseResult;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
public class PayResponse extends BaseResult implements Serializable {

    public boolean isSuccess() {
        return this.code==200;
    }


    public PayResponse(int code, String msg, Object data) {
        super(code, msg, data);
    }
}
