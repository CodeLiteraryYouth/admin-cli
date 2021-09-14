package com.dmj.cli.common.constant;

import com.dmj.cli.common.enums.ResultStatusCode;

/**
 * @author zd
 */
public class BaseResult<T> {

    /**
     * 返回的代码，200表示成功，其他表示失败
     */
    protected int code;
    /**
     * 成功或失败时返回的错误信息
     */
    protected String msg;
    /**
     * 成功时返回的数据信息
     */
    protected T data;

    public BaseResult(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(ResultStatusCode resultStatusCode, T data){
        this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
    }

    public BaseResult(int code, String msg){
        this(code, msg, null);
    }

    public BaseResult(ResultStatusCode resultStatusCode){
        this(resultStatusCode, null);
    }


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
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public static BaseResult success() {
        return new BaseResult(ResultStatusCode.OK);
    }

    public static BaseResult success(Object data) {
        return new BaseResult(ResultStatusCode.OK,data);
    }

    public static boolean isSuccess(int code) {
        return code==200;
    }

    public static BaseResult fail() {
        return new BaseResult(ResultStatusCode.SYSTEM_ERR);
    }

    public static BaseResult fail(int code,String msg) {
        return new BaseResult(code, msg);
    }

    public static BaseResult fail(Object data) {
        return new BaseResult(ResultStatusCode.SYSTEM_ERR,data);
    }

    public static BaseResult fail(ResultStatusCode resultStatusCode) {
        return new BaseResult(resultStatusCode,null);
    }
}
