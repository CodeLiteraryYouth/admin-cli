package com.dmj.cli.security;

import com.alibaba.fastjson.JSON;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.util.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 请求处理成功处理器
 * @author Administrator
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=httpServletResponse.getOutputStream();
        String userName=authentication.getName();
        //生成JWT，并放到请求头中
        String token= jwtUtils.generateToken(userName);
        httpServletResponse.setHeader(jwtUtils.getHeader(),token);
        BaseResult result=BaseResult.success(token);
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
