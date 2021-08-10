package com.dmj.cli.controller.sys;


import com.dmj.cli.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysDict;
import com.dmj.cli.service.sys.SysDictService;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-10
 */
@RestController
@RequestMapping("/sys-dict")
@Api(tags = "数据字典表")
public class SysDictController extends BaseController {
    @Resource
    private SysDictService service;

    @PostMapping("/save")
    public BaseResult<SysDict> save(@RequestBody SysDict entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    public BaseResult<SysDict> update(@RequestBody SysDict entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @GetMapping("/get/{id}")
    public BaseResult<SysDict> select(@PathVariable String id) {
        SysDict data = service.getById(id);
        return BaseResult.success(data);
    }

    @PostMapping("/page")
    public BaseResult<Page<SysDict>> page(@RequestBody Page<SysDict> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

