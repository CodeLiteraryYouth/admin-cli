package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.Product;
import com.dmj.cli.mapper.api.ProductMapper;
import com.dmj.cli.service.api.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-09-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
