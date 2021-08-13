package com.dmj.cli.handler;

import cn.hutool.json.JSONUtil;
import com.dmj.cli.common.exception.LoginException;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.util.ServletUtils;
import com.dmj.cli.util.str.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录拦截器
 * @author zd
 */
@Aspect
@Component
public class LoginAspect {

    private final static String LOGIN_TOKEN_KEY="Login-Token";

    @Autowired
    private RedisUtils redisUtils;


    @Pointcut("@annotation(com.dmj.cli.annotation.login.Login)")
    public void point() {

    }

    @Before("point()")
    public void loginFilter(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request= ServletUtils.getRequest();
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            throw new LoginException("当前用户未登录，请前去登录");
        }
        Long sceneId=Long.valueOf(token);
        String user= (String) redisUtils.get(sceneId.toString());
        if (StringUtils.isBlank(user)) {
            throw new LoginException("当前用户未登录，请前去登录");
        }
        UserInfo userInfo= JSONUtil.toBean(user,UserInfo.class);
        if (!userInfo.getLoginStatus()) {
            throw new LoginException("当前用户未登录，请前去登录");
        }
    }
}
