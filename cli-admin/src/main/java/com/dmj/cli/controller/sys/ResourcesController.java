package com.dmj.cli.controller.sys;


import com.dmj.cli.domain.dto.sys.ResourcesDTO;
import com.dmj.cli.domain.query.api.ResourcesQuery;
import com.dmj.cli.domain.vo.api.ResourcesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Resources;
import com.dmj.cli.service.api.ResourcesService;
    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/resources")
@Api(tags = "资源管理")
public class ResourcesController {

    @Resource
    private ResourcesService service;

    @PostMapping("/save")
    @ApiOperation("新增资源")
    public BaseResult<Resources> save(@RequestBody ResourcesDTO entity) {
       return service.insert(entity);
    }

    @PutMapping("/update")
    @ApiOperation("更新资源")
    public BaseResult<Resources> update(@RequestBody ResourcesDTO entity) {
        return service.update(entity);
    }


    @ApiOperation("删除资源")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        return service.delete(id);
    }

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
}

