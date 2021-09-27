package com.dmj.cli.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.query.api.ProductQuery;
import com.dmj.cli.domain.vo.api.CountItemVO;
import com.dmj.cli.domain.vo.api.DesignerVO;
import com.dmj.cli.mapper.api.ProductMapper;
import com.dmj.cli.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<CountItemVO> listCountItem(CountQuery query) {
        return productMapper.listCountItem(query);
    }

    @Override
    public List<Product> page(ProductQuery query) {
        return productMapper.listProducts(query);
    }

    @Override
    public List<DesignerVO> listDesigner() {
        return productMapper.listDesigner();
    }
}
