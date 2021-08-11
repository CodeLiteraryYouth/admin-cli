package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserLoginLog;
import com.dmj.cli.service.api.UserLoginLogService;
    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 用户登录记录表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/user-login-log")
@Api(tags = "用户登录记录表")
public class UserLoginLogController {
    @Resource
    private UserLoginLogService service;

    @PostMapping("/save")
    public BaseResult<UserLoginLog> save(@RequestBody UserLoginLog entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    public BaseResult<UserLoginLog> update(@RequestBody UserLoginLog entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @GetMapping("/get/{id}")
    public BaseResult<UserLoginLog> select(@PathVariable String id) {
        UserLoginLog data = service.getById(id);
        return BaseResult.success(data);
    }

    @PostMapping("/page")
    public BaseResult<Page<UserLoginLog>> page(@RequestBody Page<UserLoginLog> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

