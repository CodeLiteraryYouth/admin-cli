package com.dmj.cli.handle;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 在操作数据库的时候根据语句拦截，帮我们自动补充被拦截的语句的属性
 */
@Component
public class DataMetaObjectHandler implements MetaObjectHandler {
    //在执行insert语句的时候被拦截操作的
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("createTime", DateUtil.date(),metaObject);
        this.setFieldValByName("creator", SecurityContextHolder.getContext().getAuthentication().getName(),metaObject);
    }
    //修改语句
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",DateUtil.date(),metaObject);
        this.setFieldValByName("updater", SecurityContextHolder.getContext().getAuthentication().getName(),metaObject);
    }
}

