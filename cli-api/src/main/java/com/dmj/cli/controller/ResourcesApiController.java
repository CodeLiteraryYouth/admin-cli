package com.dmj.cli.controller;

import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.annotation.view.Collect;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.ResourcesType;
import com.dmj.cli.domain.query.api.ResourcesQuery;
import com.dmj.cli.domain.vo.api.ResourcesVO;
import com.dmj.cli.service.api.ResourcesService;
import com.dmj.cli.service.api.ResourcesTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * @author zd
 */
@RestController
@RequestMapping("/resources")
@Api(tags = "资源列表")
public class ResourcesApiController {

    @Autowired
    private ResourcesService service;

    @Resource
    private ResourcesTypeService typeService;


    @ApiOperation("查询所有资源标签")
    @GetMapping("/type/list")
    public BaseResult<List<ResourcesType>> typeList() {
        List<ResourcesType> resourcesTypes=typeService.list();
        return BaseResult.success(resourcesTypes);
    }

    @View
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

    @Login
    @Collect
    @ApiOperation("收藏")
    @GetMapping("collect")
    public BaseResult collect(@RequestParam Long resourcesId,@RequestParam Long userId) {
        return BaseResult.success();
    }

    @Login
    @ApiOperation("下载")
    @GetMapping("download")
    public BaseResult download(@RequestParam Long resourcesId,@RequestParam Long userId) {
        return BaseResult.success();
    }

}