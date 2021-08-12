package com.dmj.cli.annotation.view;

import java.lang.annotation.*;

/**
 * 统计下载次数
 * @author zd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Download {

    /**
     * 统计下载次数的id
     * @return
     */
    String id() default "";

    /**
     * 下载的用户ID
     * @return
     */
    long userId() default 0L;
}
