package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Course;
import com.dmj.cli.service.api.CourseService;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
@RestController
@RequestMapping("/course")
@Api(tags = "课程")
public class CourseController {

    @Autowired
    private CourseService service;

    @ApiOperation("保存课程")
    @PostMapping("/save")
    public BaseResult<Course> save(@RequestBody Course entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改课程")
    @PutMapping("/update")
    public BaseResult<Course> update(@RequestBody Course entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("获取课程详情")
    @GetMapping("/get/{id}")
    public BaseResult<Course> select(@PathVariable String id) {
        Course data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("查询课程分页信息")
    @PostMapping("/page")
    public BaseResult<Page<Course>> page(@RequestBody Page<Course> page) {
        page = service.page(page);
        return BaseResult.success(page);
    }
}

