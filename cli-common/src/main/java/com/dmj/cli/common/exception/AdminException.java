package com.dmj.cli.common.exception;

import lombok.Data;

/**
 * 自定义管理端异常
 * @author zd
 */
@Data
public class AdminException extends RuntimeException {

    private int code=500;

    private String msg;

    public AdminException(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    public AdminException(String msg) {
        this.msg=msg;
    }
}
