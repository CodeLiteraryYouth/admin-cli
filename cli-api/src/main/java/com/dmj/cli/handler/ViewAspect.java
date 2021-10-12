package com.dmj.cli.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.annotation.view.Collect;
import com.dmj.cli.annotation.view.Download;
import com.dmj.cli.annotation.view.Favour;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.UserCollLog;
import com.dmj.cli.domain.UserDownloadLog;
import com.dmj.cli.service.api.UserCollLogService;
import com.dmj.cli.service.api.UserDownloadLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author zd
 */
@Slf4j
@Aspect
@Component
public class ViewAspect {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserCollLogService userCollLogService;

    @Autowired
    private UserDownloadLogService userDownloadLogService;


    @Pointcut("@annotation(com.dmj.cli.annotation.view.Collect) " +
            "|| @annotation(com.dmj.cli.annotation.view.View)" +
            "|| @annotation(com.dmj.cli.annotation.view.Download)"+
            "|| @annotation(com.dmj.cli.annotation.view.Favour)")
    public void viewPoint() {
    }


    @After("viewPoint()")
    public void viewFilter(JoinPoint point) throws Throwable {
        Object params = point.getArgs()[0];
        Long userId=null;
        if (point.getArgs().length>1) {
            Object param=point.getArgs()[1];
            if (param != null) {
                userId = (Long) param;
            }
        }
        Long id=null;
        if (params != null) {
            id= (Long) params;

        }
        handleFilter(id,userId,point);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    protected void handleFilter(Long id,Long userId,JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        View view=method.getAnnotation(View.class);

        Collect collect=method.getAnnotation(Collect.class);

        Download download=method.getAnnotation(Download.class);

        Favour favour=method.getAnnotation(Favour.class);

        if (favour != null) {
            Double viewData=redisUtils.score(GlobalConstants.FAVOUR_NUM,id.toString());
            redisUtils.zsIncr(GlobalConstants.FAVOUR_NUM,id.toString(),viewData);
        }

        if (view != null) {
            Double viewData=redisUtils.score(GlobalConstants.VIEW_NUM,id.toString());
            redisUtils.zsIncr(GlobalConstants.VIEW_NUM,id.toString(),viewData);
        }
        if (collect != null) {
            Double collData=redisUtils.score(GlobalConstants.COLLECT_NUM,id.toString());
            redisUtils.zsIncr(GlobalConstants.COLLECT_NUM,id.toString(),collData);
            UserCollLog userCollLog=userCollLogService.getOne(Wrappers.<UserCollLog>lambdaQuery()
                    .eq(UserCollLog::getCollectId,id)
                    .eq(UserCollLog::getUserId,userId));
            if (userCollLog == null) {
                userCollLog = new UserCollLog();
                userCollLog.setUserId(userId);
                userCollLog.setCollectId(id);
                userCollLog.setCollectTime(LocalDateTime.now());
                userCollLog.setCollectType(collect.type());
                userCollLogService.save(userCollLog);
            } else {
                userCollLogService.remove(Wrappers.<UserCollLog>lambdaQuery()
                        .eq(UserCollLog::getCollectId,id)
                        .eq(UserCollLog::getUserId,userId));
            }
        }
        if (download != null) {
            UserDownloadLog userDownloadLog = userDownloadLogService.getOne(Wrappers.<UserDownloadLog>lambdaQuery()
                    .eq(UserDownloadLog::getDownloadId,id)
                    .eq(UserDownloadLog::getUserId,userId));
            if (userDownloadLog == null) {
                userDownloadLog = new UserDownloadLog();
                userDownloadLog.setDownloadId(id);
                userDownloadLog.setUserId(userId);
                userDownloadLog.setDownloadTime(LocalDateTime.now());
                userDownloadLogService.save(userDownloadLog);
            } else {
                userDownloadLogService.remove(Wrappers.<UserDownloadLog>lambdaQuery()
                        .eq(UserDownloadLog::getDownloadId,id)
                        .eq(UserDownloadLog::getUserId,userId));
            }
        }

    }
}
