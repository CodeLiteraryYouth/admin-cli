package com.dmj.cli.controller;


import cn.hutool.core.date.DateUtil;
import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.annotation.view.Favour;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.ProductType;
import com.dmj.cli.domain.query.api.ProductQuery;
import com.dmj.cli.domain.vo.api.DesignerVO;
import com.dmj.cli.service.api.ProductService;
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
@RequestMapping("/product")
@Api(tags = "作品管理-作品")
public class ProductApiController extends BaseController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductTypeService productTypeService;


    @ApiOperation("获取作品标签列表")
    @GetMapping("/type/list")
    public BaseResult<List<ProductType>> listType() {
        return BaseResult.success(productTypeService.list());
    }

    @View
    @ApiOperation("获取作品详情")
    @GetMapping("/get/{id}")
    public BaseResult<Product> select(@PathVariable Long id) {
        Product data = service.getById(id);
        return BaseResult.success(data);
    }

    @Login
    @Favour
    @ApiOperation("点赞")
    @GetMapping("favour")
    public BaseResult favour(@RequestParam Long id,@RequestParam Long userId) {
        return BaseResult.success();
    }

    @Login
    @ApiOperation("上传作品、文章")
    @PostMapping("/save")
    public BaseResult save(@RequestBody Product entity) {
        entity.setCreateTime(DateUtil.date());
        entity.setCreator(getToken());
        service.save(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("分页查询作品列表")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<Product>>> page(@ModelAttribute ProductQuery query) {
        startPage();
        List<Product> list= service.page(query);
        return pageInfoBaseResult(list);
    }

    @ApiOperation("/设计师列表")
    @GetMapping("/designer/list")
    public BaseResult<List<DesignerVO>> listDesigner() {
        return BaseResult.success(service.listDesigner());
    }
}

