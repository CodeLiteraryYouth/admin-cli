package com.dmj.cli.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.annotation.view.Favour;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
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
 * @author zd
 */
@RestController
@RequestMapping("/user-work")
@Api(tags = "学员作业")
public class UserWorkApiController extends BaseController {

    @Autowired
    private UserWorkService service;

    @Autowired
    private RedisUtils redisUtils;

    @Login
    @ApiOperation("上传学员作业")
    @PostMapping("/save")
    public BaseResult<UserWork> save(@RequestBody UserWork entity) {
        Assert.notNull(entity,"bad request");
        Assert.notNull(entity.getJobId(),"jobId is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @View(type = "UserWork")
    @ApiOperation("查看学员作业详情")
    @GetMapping("/get/{id}")
    public BaseResult<UserWork> select(@PathVariable Long id) {
        UserWork data = service.getById(id);
        buildNum(data);
        return BaseResult.success(data);
    }

    @Favour(type = "UserWork")
    @ApiOperation("点赞")
    @GetMapping("/favour")
    public BaseResult favour(@RequestParam Long id) {
        return BaseResult.success();
    }

    @ApiOperation("分页查询周练学员作业详情")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<UserWork>>> page(@ModelAttribute UserWorkQuery query) {
        startPage();
        List<UserWork> userWorks=service.list(Wrappers.<UserWork>lambdaQuery()
                .eq(Objects.nonNull(query.getJobId()),UserWork::getJobId,query.getJobId())
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),UserWork::getWorkTitle,query.getSearchVal()));
        userWorks.forEach(this::buildNum);
        return pageInfoBaseResult(userWorks);
    }

    private void buildNum(UserWork data) {
        Double viewNum = redisUtils.score(GlobalConstants.VIEW_NUM, data.getId().toString());
        Double favourNUm = redisUtils.score(GlobalConstants.FAVOUR_NUM, data.getId().toString());
        data.setViewNum(viewNum == null ? 0L : viewNum.longValue());
        data.setFavourNum(favourNUm == null ? 0L : favourNUm.longValue());
    }
}
