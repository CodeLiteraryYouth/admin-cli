package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.api.ProductService;
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
@RequestMapping("/product")
@Api(tags = "作品管理-作品")
public class ProductController extends BaseController {

    @Autowired
    private ProductService service;


    @ApiOperation("审批作品、屏蔽作品、排序作品")
    @PutMapping("/update")
    public BaseResult<Product> update(@RequestBody Product entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除作品")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("获取作品详情")
    @GetMapping("/get/{id}")
    public BaseResult<Product> select(@PathVariable Long id) {
        Product data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询作品列表")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<Product>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<Product> list= service.list();
        return pageInfoBaseResult(list);
    }
}

