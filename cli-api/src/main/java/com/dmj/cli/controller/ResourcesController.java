package com.dmj.cli.controller;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.query.api.ResourcesQuery;
import com.dmj.cli.domain.vo.api.ResourcesVO;
import com.dmj.cli.service.api.ResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zd
 */
@RestController
@RequestMapping("/resources")
@Api(tags = "资源列表")
public class ResourcesController {

    @Autowired
    private ResourcesService service;

    @ApiOperation("获取资源详情")
    @GetMapping("/get/{id}")
    public BaseResult<ResourcesVO> select(@PathVariable Long id) {
        return service.getResourcesById(id);
    }

    @ApiOperation("获取资源列表")
    @GetMapping("/list")
    public BaseResult<List<ResourcesVO>> list(@ModelAttribute ResourcesQuery query) {
        return service.listResources(query);
    }

    @ApiOperation("收藏")
    @GetMapping("collect")
    public BaseResult collect(@RequestParam Long userId,@RequestParam Long resourcesId) {
        return BaseResult.success();
    }

    @ApiOperation("下载")
    @GetMapping("download")
    public BaseResult download(@RequestParam Long userId,@RequestParam Long resourcesId) {
        return BaseResult.success();
    }

}
