package com.dmj.cli.controller;

import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.annotation.view.Collect;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Course;
import com.dmj.cli.domain.CourseType;
import com.dmj.cli.domain.query.api.CourseQuery;
import com.dmj.cli.service.api.CourseService;
import com.dmj.cli.service.api.CourseTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zd
 */
@RestController
@RequestMapping("/course")
@Api(tags = "课程查询")
public class CourseApiController extends BaseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    @ApiOperation("查询所有课程分类")
    @GetMapping("/type/list")
    public BaseResult<List<CourseType>> list() {
        return BaseResult.success(courseTypeService.list());
    }

    @View(type = "Course")
    @ApiOperation("获取课程详情")
    @GetMapping("/get/{id}")
    public BaseResult<Course> select(@PathVariable Long id) {
        Course data = courseService.getById(id);
        return BaseResult.success(data);
    }



    @ApiOperation("获取课程分组列表")
    @GetMapping("/list")
    public BaseResult<Map<String, List<Course>>> list(@ModelAttribute CourseQuery query) {
        return courseService.listCourse(query);
    }

    @Login
    @ApiOperation("观看视频")
    @GetMapping("/watch")
    public BaseResult watch(@RequestParam Long id) {
        return courseService.watchVideo(id, getSceneId());
    }

    @Login
    @Collect(type = "course")
    @ApiOperation("收藏")
    @GetMapping("collect")
    public BaseResult collect(@RequestParam Long resourcesId,@RequestParam Long userId) {
        return BaseResult.success();
    }
}
