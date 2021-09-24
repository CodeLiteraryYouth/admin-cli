package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Tool;
import com.dmj.cli.domain.query.sys.ToolQuery;
import com.dmj.cli.service.sys.ToolService;
import com.dmj.cli.service.sys.ToolTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ToolTypeService typeService;

    @ApiOperation("新增工具")
    @PostMapping("/save")
    public BaseResult<Tool> save(@RequestBody Tool entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改工具")
    @PostMapping("/update")
    public BaseResult<Tool> update(@RequestBody Tool entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除工具")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("查询工具详情")
    @GetMapping("/info/{id}")
    public BaseResult<Tool> select(@PathVariable Long id) {
        Tool data = service.getById(id);
        data.setTypeName(typeService.getById(data.getTypeId()).getTypeName());
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询工具列表")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<Tool>>> page(@ModelAttribute ToolQuery query) {
        startPage();
        List<Tool> list=service.list(Wrappers.<Tool>lambdaQuery()
                .eq(Objects.nonNull(query.getTypeId()),Tool::getTypeId,query.getTypeId())
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),Tool::getToolTitle,query.getSearchVal()));
        list.forEach(item -> {
            item.setTypeName(typeService.getById(item.getTypeId()).getTypeName());
        });
        return pageInfoBaseResult(list);
    }
}

