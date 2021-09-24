package com.dmj.cli.annotation.login;

import java.lang.annotation.*;

/**
 * 客户端注册登录注解
 * @author Administrator
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

    String userId() default "";
}
