package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.ToolType;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.ToolTypeService;
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
@RequestMapping("/tool/type")
@Api(tags = "工具类型维护")
public class ToolTypeController extends BaseController {

    @Autowired
    private ToolTypeService service;

    @ApiOperation("新增工具类型")
    @PostMapping("/save")
    public BaseResult<ToolType> save(@RequestBody ToolType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改工具类型")
    @PostMapping("/update")
    public BaseResult<ToolType> update(@RequestBody ToolType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @ApiOperation("删除工具类型")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @GetMapping("/info/{id}")
    public BaseResult<ToolType> info(@PathVariable Long id) {
        ToolType data=service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询工具类型")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<ToolType>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<ToolType> list= service.list();
        return pageInfoBaseResult(list);
    }
}

