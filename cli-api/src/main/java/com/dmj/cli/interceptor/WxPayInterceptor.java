package com.dmj.cli.interceptor;

import com.dmj.cli.controller.wxpay.AbstractWxPayApiController;
import com.ijpay.wxpay.WxPayApiConfigKit;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WxPayInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) {
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (!(controller instanceof AbstractWxPayApiController)) {
                throw new RuntimeException("控制器需要继承 AbstractWxPayApiController");
            }
            WxPayApiConfigKit.setThreadLocalWxPayApiConfig(((AbstractWxPayApiController) controller).getApiConfig());
            return true;
        }
        return false;
    }
}