package com.dmj.cli.controller.sys;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Course;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.domain.vo.sys.CourseVO;
import com.dmj.cli.service.api.CourseService;
import com.dmj.cli.service.api.CourseTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
public class CourseController extends BaseController {

    @Autowired
    private CourseService service;

    @Autowired
    private CourseTypeService typeService;

    @ApiOperation("保存课程")
    @PostMapping("/save")
    public BaseResult<Course> save(@RequestBody Course entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改课程")
    @PostMapping("/update")
    public BaseResult<Course> update(@RequestBody Course entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("获取课程详情")
    @GetMapping("/info/{id}")
    public BaseResult<CourseVO> select(@PathVariable Long id) {
        Course data = service.getById(id);
        CourseVO courseVO=new CourseVO();
        BeanUtil.copyProperties(data,courseVO);
        courseVO.setTypeName(typeService.getById(data.getTypeId()).getTypeName());
        return BaseResult.success(data);
    }

    @ApiOperation("查询课程分页信息")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<CourseVO>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<Course> courses=service.list(Wrappers.<Course>lambdaQuery()
                .eq(Objects.nonNull(query.getTypeId()),Course::getTypeId,query.getTypeId())
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),Course::getCourseTitle,query.getSearchVal()));
        PageInfo<List<Course>> pageInfo=pageInfoBaseResult(courses).getData();
        PageInfo<List<CourseVO>> courseVOS=new PageInfo<>();
        BeanUtil.copyProperties(pageInfo,courseVOS);
        courseVOS.getList().get(0).forEach(item -> {
            item.setTypeName(typeService.getById(item.getTypeId()).getTypeName());
        });
        return BaseResult.success(courseVOS);
    }
}

