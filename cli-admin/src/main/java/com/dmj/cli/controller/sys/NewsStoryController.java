package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.NewsStory;
import com.dmj.cli.service.sys.NewsStoryService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import java.util.List;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.BaseQuery;

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
public class NewsStoryController extends BaseController {

    @Autowired
    private NewsStoryService service;

    @ApiOperation("添加新闻事迹")
    @PostMapping("/save")
    public BaseResult<NewsStory> save(@RequestBody NewsStory entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改新闻事迹")
    @PutMapping("/update")
    public BaseResult<NewsStory> update(@RequestBody NewsStory entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除新闻事迹")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }


    @GetMapping("/get/{id}")
    public BaseResult<NewsStory> select(@PathVariable Long id) {
        NewsStory data = service.getById(id);
        return BaseResult.success(data);
    }

    @GetMapping("/page")
    public BaseResult<PageInfo<List<NewsStory>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<NewsStory> newsStories=service.listNewsStory(query);
        return pageInfoBaseResult(newsStories);
    }
}

