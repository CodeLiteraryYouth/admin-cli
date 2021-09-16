package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.BaseQuery;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.CourseType;
import com.dmj.cli.service.api.CourseTypeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程类型 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
@RestController
@RequestMapping("/course/type")
@Api(tags = "课程类型")
public class CourseTypeController extends BaseController {

    @Autowired
    private CourseTypeService service;

    @ApiOperation("保存课程分类信息")
    @PostMapping("/save")
    public BaseResult<CourseType> save(@RequestBody CourseType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("更新课程分类信息")
    @PostMapping("/update")
    public BaseResult<CourseType> update(@RequestBody CourseType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @ApiOperation("删除课程分类信息")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("查询课程分类详情")
    @GetMapping("/info/{id}")
    public BaseResult<CourseType> select(@PathVariable Long id) {
        CourseType data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("查询课程分类详情")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<CourseType>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<CourseType> courseTypes=service.list(Wrappers.<CourseType>lambdaQuery()
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),CourseType::getTypeName,query.getSearchVal()));
        return pageInfoBaseResult(courseTypes);
    }
}

