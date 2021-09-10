package com.dmj.cli.controller.sys;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Tool;
import com.dmj.cli.domain.query.sys.ToolQuery;
import com.dmj.cli.service.sys.ToolService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/tool")
@Api(tags = "工具")
public class ToolController extends BaseController {

    @Autowired
    private ToolService service;

    @ApiOperation("新增工具")
    @PostMapping("/save")
    public BaseResult<Tool> save(@RequestBody Tool entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改工具")
    @PutMapping("/update")
    public BaseResult<Tool> update(@RequestBody Tool entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除工具")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("查询工具详情")
    @GetMapping("/get/{id}")
    public BaseResult<Tool> select(@PathVariable Long id) {
        Tool data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询工具列表")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<Tool>>> page(@ModelAttribute ToolQuery query) {
        Assert.notNull(query,"bad request");
        Assert.notNull(query.getPageNum(),"pageNo is null");
        Assert.notNull(query.getPageSize(),"pageSize is null");
        startPage();
        List<Tool> list=null;
        if (query.getTypeId() != null) {
             list = service.list(Wrappers.<Tool>lambdaQuery().eq(Tool::getTypeId,query.getTypeId()));
        } else {
             list = service.list();
        }
        return pageInfoBaseResult(list);
    }
}

