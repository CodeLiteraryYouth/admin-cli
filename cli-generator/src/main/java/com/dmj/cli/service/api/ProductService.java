package com.dmj.cli.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.query.api.ProductQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-09-06
 */
public interface ProductService extends IService<Product> {

    List<Product> page(ProductQuery query);
}
