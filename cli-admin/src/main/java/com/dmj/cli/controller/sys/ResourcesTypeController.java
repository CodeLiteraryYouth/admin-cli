package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.ResourcesType;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.api.ResourcesTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/resources/type")
@Api(tags = "资源类型管理")
public class ResourcesTypeController {

    @Resource
    private ResourcesTypeService service;

    @ApiOperation("新增资源标签分类")
    @PostMapping("/save")
    public BaseResult<ResourcesType> save(@RequestBody ResourcesType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改资源标签分类")
    @PostMapping("/update")
    public BaseResult<ResourcesType> update(@RequestBody ResourcesType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @GetMapping("/info/{id}")
    public BaseResult<ResourcesType> info(@PathVariable Long id) {
        ResourcesType data=service.getById(id);
        return BaseResult.success(data);
    }


    @ApiOperation("删除标签分类")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("查询所有标签")
    @GetMapping("/list")
    public BaseResult<List<ResourcesType>> list(BaseQuery query) {
        List<ResourcesType> resourcesTypes=service.list();
        return BaseResult.success(resourcesTypes);
    }
}

