package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.service.api.UserInfoService;
    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/user-info")
@Api(tags = "用户信息表")
public class UserInfoController {
    @Resource
    private UserInfoService service;

    @PostMapping("/save")
    public BaseResult<UserInfo> save(@RequestBody UserInfo entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    public BaseResult<UserInfo> update(@RequestBody UserInfo entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @GetMapping("/get/{id}")
    public BaseResult<UserInfo> select(@PathVariable String id) {
        UserInfo data = service.getById(id);
        return BaseResult.success(data);
    }

    @PostMapping("/page")
    public BaseResult<Page<UserInfo>> page(@RequestBody Page<UserInfo> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

