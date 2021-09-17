package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.BusinessCooperationType;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.BusinessCooperationTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 合作企业类别 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/business/cooperation/type")
@Api(tags = "合作企业类别")
public class BusinessCooperationTypeController extends BaseController {

    @Autowired
    private BusinessCooperationTypeService service;

    @ApiOperation("新增企业合作类别")
    @PostMapping("/save")
    public BaseResult<BusinessCooperationType> save(@RequestBody BusinessCooperationType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改企业合作类别")
    @PostMapping("/update")
    public BaseResult<BusinessCooperationType> update(@RequestBody BusinessCooperationType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除企业合作类别")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @GetMapping("/info/{id}")
    public BaseResult<BusinessCooperationType> info(@PathVariable Long id) {
        BusinessCooperationType data=service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询企业合作类别")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<BusinessCooperationType>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<BusinessCooperationType> list= service.list();
        return pageInfoBaseResult(list);
    }
}

