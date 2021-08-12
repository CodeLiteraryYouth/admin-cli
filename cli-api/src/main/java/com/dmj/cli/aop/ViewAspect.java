package com.dmj.cli.aop;

import com.dmj.cli.annotation.view.Collect;
import com.dmj.cli.annotation.view.Download;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zd
 */
@Slf4j
@Aspect
@Component
public class ViewAspect {

    @Autowired
    private RedisUtils redisUtils;

    @Pointcut("@annotation(com.dmj.cli.annotation.view.*)")
    public void point() {
    }


    @After("point()")
    public void viewFilter(JoinPoint point) throws Throwable {
        Object params = point.getArgs()[0];

        if (params != null) {
            Long id= (Long) params;
            handleFilter(id,point);
        }
    }

    private void handleFilter(Long id,JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        View view=method.getAnnotation(View.class);

        Collect collect=method.getAnnotation(Collect.class);

        Download download=method.getAnnotation(Download.class);

        Double data=1.0;

        if (view != null) {
            data=redisUtils.score(GlobalConstants.VIEW_NUM,id.toString());
            redisUtils.zsIncr(GlobalConstants.VIEW_NUM,id.toString(),data);
        }
        if (collect != null) {
            data=redisUtils.score(GlobalConstants.COLLECT_NUM,id.toString());
            redisUtils.zsIncr(GlobalConstants.COLLECT_NUM,id.toString(),data);
        }
        if (download != null) {
            data=redisUtils.score(GlobalConstants.DOWNLOAD_NUM,id.toString());
            redisUtils.zsIncr(GlobalConstants.DOWNLOAD_NUM,id.toString(),data);
        }

    }
}
