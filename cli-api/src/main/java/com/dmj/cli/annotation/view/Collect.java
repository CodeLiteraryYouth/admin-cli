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
     * 收藏类别(resources:资源 video:视频)
     * @return
     */
    String type() default "resources";
}
