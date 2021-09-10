package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.SysDict;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.SysDictService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-10
 */
@RestController
@RequestMapping("/sys/dict")
@Api(tags = "数据字典表")
public class SysDictController extends BaseController {

    @Resource
    private SysDictService service;

    @ApiOperation("添加数据字典值")
    @PostMapping("/save")
    public BaseResult<SysDict> save(@RequestBody SysDict entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改数据字典值")
    @PutMapping("/update")
    public BaseResult<SysDict> update(@RequestBody SysDict entity) {
        Assert.notNull(entity.getId(),"id is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除数据字典")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("查询数据字典详情")
    @GetMapping("/get/{id}")
    public BaseResult<SysDict> select(@PathVariable Long id) {
        SysDict data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询数据字典详情")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<SysDict>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<SysDict> sysDicts = service.list();
        return pageInfoBaseResult(sysDicts);
    }
}

