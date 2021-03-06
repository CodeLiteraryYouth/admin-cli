package com.dmj.cli.security;

import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.util.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * @author Administrator
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String url=httpServletRequest.getRequestURI();

        if (AuthConstants.LOGIN_URL.equals(url) && GlobalConstants.POST_METHOD.equals(httpServletRequest.getMethod())) {
            try {
                validate(httpServletRequest);
            } catch (CaptchaException e) {
                loginFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(HttpServletRequest httpServletRequest) throws CaptchaException {
        String code=httpServletRequest.getParameter("code");
        String token=httpServletRequest.getParameter("uuid");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(token)) {
            throw new CaptchaException("验证错误，请重新输入");
        }
        if (!code.equals(redisUtils.hget(GlobalConstants.CAPTCHA,token))) {
            throw new CaptchaException("验证错误，请重新输入");
        }
        redisUtils.hdel(GlobalConstants.CAPTCHA,token);
    }
}
