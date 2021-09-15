package com.dmj.cli.domain.dto.pay;

import com.dmj.cli.common.constant.BaseResult;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
public class RefundResponse extends BaseResult implements Serializable {

    public RefundResponse(int code, String msg, Object data) {
        super(code, msg, data);
    }
}
