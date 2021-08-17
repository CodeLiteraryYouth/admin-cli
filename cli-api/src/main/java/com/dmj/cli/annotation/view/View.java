package com.dmj.cli.annotation.view;

import java.lang.annotation.*;

/**
 * 统计浏览次数
 * @author zd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface View {

    /**
     * 统计浏览量的类型
     * @return
     */
    String type() default "";
}
