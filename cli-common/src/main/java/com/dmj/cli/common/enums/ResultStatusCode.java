package com.dmj.cli.common.enums;

public enum ResultStatusCode {
    OK(200, "OK"),

    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),
    BAD_REQUEST(400, "参数解析失败"),
    INVALID_TOKEN(401, "无效的授权码"),
    INVALID_CLIENTID(402, "无效的密钥"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    SYSTEM_ERR(500, "服务器运行异常"),

    NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
    LOGINED_IN(10001, "该用户已登录"),
    LOGIN_ERROR(10002,"用户未登录，请前去登录"),
    SHIRO_ERROR(10003, "登录异常"),
    UNAUTHO_ERROR(10004, "您没有该权限"),
    USER_LOCKED(10005,"用户已锁定，请联系管理员"),
    TOKEN_ERROR(10006,"获取token异常"),
    BIND_PHONE(10010, "请绑定手机号"),

    ORDER_PAY_ERROR(20000,"该订单已失效，请重新生成订单");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ResultStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
