package com.dmj.cli.controller;


import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.NewsStory;
import com.dmj.cli.domain.PracticesJob;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.NewsStoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新闻事迹 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/news-story")
@Api(tags = "新闻事迹")
public class NewsStoryApiController extends BaseController {

    @Autowired
    private NewsStoryService service;

    @Autowired
    private RedisUtils redisUtils;

    @View(type = "NewsStory")
    @ApiOperation("查看新闻事迹详情")
    @GetMapping("/get/{id}")
    public BaseResult<NewsStory> select(@PathVariable Long id) {
        NewsStory data = service.getById(id);
        buildNum(data);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询新闻事迹列表")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<NewsStory>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<NewsStory> newsStories=service.listNewsStory(query);
        newsStories.forEach(this::buildNum);
        return pageInfoBaseResult(newsStories);
    }

    private void buildNum(NewsStory data) {
        Double viewNum = redisUtils.score(GlobalConstants.VIEW_NUM,data.getId().toString());
        data.setViewNum(viewNum == null ? 0L : viewNum.longValue());
    }
}

