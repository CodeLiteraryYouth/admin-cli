package com.dmj.cli.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.query.api.ProductQuery;
import com.dmj.cli.domain.vo.api.DesignerVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-09-06
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> listProducts(ProductQuery query);

    List<DesignerVO> listDesigner();
}
