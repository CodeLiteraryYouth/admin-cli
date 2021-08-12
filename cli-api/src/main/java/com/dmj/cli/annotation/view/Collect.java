package com.dmj.cli.annotation.view;

import java.lang.annotation.*;

/**
 * 统计用户的收藏量
 * @author zd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Collect {

    /**
     * 统计收藏次数的id
     * @return
     */
    String id() default "";

    /**
     * 收藏的用户ID
     * @return
     */
    long userId() default 0L;
}
