package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.CourseType;
import com.dmj.cli.service.api.CourseTypeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 课程类型 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
@RestController
@RequestMapping("/course-type")
@Api(tags = "课程类型")
public class CourseTypeController {

    @Autowired
    private CourseTypeService service;

    @ApiOperation("保存课程分类信息")
    @PostMapping("/save")
    public BaseResult<CourseType> save(@RequestBody CourseType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("更新课程分类信息")
    @PutMapping("/update")
    public BaseResult<CourseType> update(@RequestBody CourseType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @ApiOperation("删除课程分类信息")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("查询课程分类详情")
    @GetMapping("/get/{id}")
    public BaseResult<CourseType> select(@PathVariable String id) {
        CourseType data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("查询课程分类详情")
    @PostMapping("/page")
    public BaseResult<Page<CourseType>> page(@RequestBody Page<CourseType> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

