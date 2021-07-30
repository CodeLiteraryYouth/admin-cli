package com.dmj.cli.security;

import com.alibaba.fastjson.JSON;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.enums.ResultStatusCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zd
 * @description: 用户未登录时返回给前端的数据
 */
@Component
public class ResponseAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.getWriter().write(JSON.toJSONString(BaseResult.fail(ResultStatusCode.LOGIN_ERROR)));
    }
}
