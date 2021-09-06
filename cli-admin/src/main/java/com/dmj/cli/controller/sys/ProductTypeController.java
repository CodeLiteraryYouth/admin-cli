package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.ProductType;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.api.ProductTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/product-type")
@Api(tags = "作品管理-作品标签")
public class ProductTypeController extends BaseController {

    @Autowired
    private ProductTypeService service;

    @ApiOperation("新增作品标签")
    @PostMapping("/save")
    public BaseResult<ProductType> save(@RequestBody ProductType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改作品标签")
    @PutMapping("/update")
    public BaseResult<ProductType> update(@RequestBody ProductType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除作品标签")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("删除作品标签详情")
    @GetMapping("/get/{id}")
    public BaseResult<ProductType> select(@PathVariable Long id) {
        ProductType data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询作品标签")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<ProductType>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<ProductType> list= service.list();
        return pageInfoBaseResult(list);
    }
}

