package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.ProductType;
import com.dmj.cli.mapper.api.ProductTypeMapper;
import com.dmj.cli.service.api.ProductTypeService;
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
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

}
