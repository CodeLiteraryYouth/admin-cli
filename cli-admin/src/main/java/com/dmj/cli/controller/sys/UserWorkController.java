package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.UserWork;
import com.dmj.cli.domain.query.api.UserWorkQuery;
import com.dmj.cli.service.api.UserWorkService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 学员作业 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/user/work")
@Api(tags = "学员作业")
public class UserWorkController extends BaseController {

    @Autowired
    private UserWorkService service;

    @ApiOperation("上传学员作业")
    @PostMapping("/save")
    public BaseResult<UserWork> save(@RequestBody UserWork entity) {
        Assert.notNull(entity.getJobId(),"jobId is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改学员作业")
    @PostMapping("/update")
    public BaseResult<UserWork> update(@RequestBody UserWork entity) {
        Assert.notNull(entity.getId(),"id is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除学员作业")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("查看学员作业详情")
    @GetMapping("/info/{id}")
    public BaseResult<UserWork> select(@PathVariable Long id) {
        UserWork data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询周练学员作业详情")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<UserWork>>> page(@ModelAttribute UserWorkQuery query) {
        startPage();
        List<UserWork> userWorks=service.list(Wrappers.<UserWork>lambdaQuery()
                .eq(UserWork::getJobId,query.getJobId())
                .eq(Objects.nonNull(query.getUserId()),UserWork::getUserId,query.getUserId())
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),UserWork::getWorkTitle,query.getSearchVal()));
        return pageInfoBaseResult(userWorks);
    }
}

