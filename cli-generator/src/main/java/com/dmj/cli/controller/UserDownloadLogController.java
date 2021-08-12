package com.dmj.cli.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserDownloadLog;
import com.dmj.cli.service.api.UserDownloadLogService;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/user-download-log")
@Api(tags = "")
public class UserDownloadLogController {

    @Autowired
    private UserDownloadLogService service;

    @PostMapping("/save")
    public BaseResult<UserDownloadLog> save(@RequestBody UserDownloadLog entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    public BaseResult<UserDownloadLog> update(@RequestBody UserDownloadLog entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @GetMapping("/get/{id}")
    public BaseResult<UserDownloadLog> select(@PathVariable String id) {
        UserDownloadLog data = service.getById(id);
        return BaseResult.success(data);
    }

    @PostMapping("/page")
    public BaseResult<Page<UserDownloadLog>> page(@RequestBody Page<UserDownloadLog> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

