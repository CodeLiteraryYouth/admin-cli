package com.dmj.cli.annotation.view;

import java.lang.annotation.*;

/**
 * @author zd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Favour {

    String type() default "";
}
