package com.dmj.cli.controller;

import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.PracticesJob;
import com.dmj.cli.domain.query.api.PracticesJobQuery;
import com.dmj.cli.service.api.PracticesJobService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zd
 */
@RestController
@RequestMapping("/practices-job")
@Api(tags = "周练")
public class PracticesJobApiController extends BaseController {

    @Autowired
    private PracticesJobService service;

    @Autowired
    private RedisUtils redisUtils;

    @View(type = "PracticesJob")
    @ApiOperation("获取周练详情")
    @GetMapping("/get/{id}")
    public BaseResult<PracticesJob> select(@PathVariable Long id) {
        PracticesJob data = service.getById(id);
        buildNum(data);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询周练")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<PracticesJob>>> page(@ModelAttribute PracticesJobQuery query) {
        startPage();
        List<PracticesJob> list=service.list();
        list.forEach(this::buildNum);
        return pageInfoBaseResult(list);
    }

    private void buildNum(PracticesJob data) {
        Double viewNum = redisUtils.score(GlobalConstants.VIEW_NUM,data.getId().toString());
        data.setViewNum(viewNum == null ? 0L : viewNum.longValue());
    }

}
