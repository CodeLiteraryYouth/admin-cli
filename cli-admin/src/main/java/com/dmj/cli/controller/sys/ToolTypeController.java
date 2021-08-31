package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.ToolType;
import com.dmj.cli.service.sys.ToolTypeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import java.util.List;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.BaseQuery;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/tool-type")
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
    @PutMapping("/update")
    public BaseResult<ToolType> update(@RequestBody ToolType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @ApiOperation("删除工具类型")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("分页查询工具类型")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<ToolType>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<ToolType> list= service.list();
        return pageInfoBaseResult(list);
    }
}

