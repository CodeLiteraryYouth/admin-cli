package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.api.ProductService;
import com.dmj.cli.service.api.ProductTypeService;
import com.dmj.cli.util.str.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ProductTypeService productTypeService;

    @ApiOperation("上传作品")
    @PostMapping("/save")
    public BaseResult save(@RequestBody Product entity) {
        service.save(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("审批作品、屏蔽作品、排序作品")
    @PostMapping("/update")
    public BaseResult<Product> update(@RequestBody Product entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除作品")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("获取作品详情")
    @GetMapping("/info/{id}")
    public BaseResult<Product> select(@PathVariable Long id) {
        Product data = service.getById(id);
        data.setTypeName(productTypeService.getById(data.getTypeId()).getTypeName());
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询作品列表")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<Product>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<Product> list= service.list(Wrappers.<Product>lambdaQuery()
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),Product::getTitle,query.getSearchVal())
                .eq(Objects.nonNull(query.getTypeId()),Product::getTypeId,query.getTypeId()));
        list.forEach(product -> {
            product.setTypeName(productTypeService.getById(product.getTypeId()).getTypeName());
        });
        return pageInfoBaseResult(list);
    }
}

