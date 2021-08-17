package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.api.UserWorkQuery;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserWork;
import com.dmj.cli.service.api.UserWorkService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学员作业 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/user-work")
@Api(tags = "学员作业")
public class UserWorkController extends BaseController {

    @Autowired
    private UserWorkService service;

    @ApiOperation("上传学员作业")
    @PostMapping("/save")
    public BaseResult<UserWork> save(@RequestBody UserWork entity) {
        Assert.notNull(entity,"bad request");
        Assert.notNull(entity.getJobId(),"jobId is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改学员作业")
    @PutMapping("/update")
    public BaseResult<UserWork> update(@RequestBody UserWork entity) {
        Assert.notNull(entity,"bad request");
        Assert.notNull(entity.getId(),"id is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除学员作业")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("查看学员作业详情")
    @GetMapping("/get/{id}")
    public BaseResult<UserWork> select(@PathVariable Long id) {
        UserWork data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询周练学员作业详情")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<UserWork>>> page(@ModelAttribute UserWorkQuery query) {
        startPage();
        List<UserWork> userWorks=service.list(new LambdaQueryWrapper<UserWork>().eq(UserWork::getJobId,query.getJobId()));
        return pageInfoBaseResult(userWorks);
    }
}

