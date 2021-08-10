package com.dmj.cli.handler;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.exception.AdminException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

/**
 * 异常处理器
 * @author zd
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionAdviceHandler {

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
            ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public BaseResult handleHttpMessageNotReadableException(Exception e) {
        log.error("参数解析失败", e);
        if (e instanceof BindException){
            return BaseResult.fail(ResultStatusCode.BAD_REQUEST.getCode(),e.getMessage());
        }
        return BaseResult.fail(ResultStatusCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return BaseResult.fail(ResultStatusCode.METHOD_NOT_ALLOWED);
    }


    /**
     * 500
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResult handleException(Exception e) {
        log.error("服务运行异常", e);
        return BaseResult.fail(ResultStatusCode.SYSTEM_ERR);
    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AdminException.class)
    public BaseResult adminHandleException(AdminException e) {
        return BaseResult.fail(e.getMessage());
    }
}
