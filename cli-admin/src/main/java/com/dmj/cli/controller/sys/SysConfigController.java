package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.SysConfig;
import com.dmj.cli.service.sys.SysConfigService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 系统配置信息表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-10
 */
@RestController
@RequestMapping("/sys/config")
@Api(tags = "系统配置信息表")
public class SysConfigController extends BaseController {
    @Resource
    private SysConfigService service;

    @PostMapping("/save")
    public BaseResult<SysConfig> save(@RequestBody SysConfig entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    public BaseResult<SysConfig> update(@RequestBody SysConfig entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @GetMapping("/get/{id}")
    public BaseResult<SysConfig> select(@PathVariable String id) {
        SysConfig data = service.getById(id);
        return BaseResult.success(data);
    }

    @PostMapping("/list")
    public BaseResult<Page<SysConfig>> page(@RequestBody Page<SysConfig> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

