package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BusinessCooperation;
import com.dmj.cli.service.BusinessCooperationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import java.util.List;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.BaseQuery;

/**
 * <p>
 * 企业合作 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/business-cooperation")
@Api(tags = "企业合作")
public class BusinessCooperationController extends BaseController {

    @Autowired
    private BusinessCooperationService service;

    @ApiOperation("新增企业合作详情")
    @PostMapping("/save")
    public BaseResult<BusinessCooperation> save(@RequestBody BusinessCooperation entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改企业合作详情")
    @PutMapping("/update")
    public BaseResult<BusinessCooperation> update(@RequestBody BusinessCooperation entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除企业合作详情")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("查询企业合作详情")
    @GetMapping("/get/{id}")
    public BaseResult<BusinessCooperation> select(@PathVariable Long id) {
        BusinessCooperation data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询企业合作详情")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<BusinessCooperation>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<BusinessCooperation> list= service.list();
        return pageInfoBaseResult(list);
    }
}

