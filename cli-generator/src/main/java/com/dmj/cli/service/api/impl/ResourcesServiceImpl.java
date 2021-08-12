package com.dmj.cli.service.api.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Resources;
import com.dmj.cli.domain.ResourcesTypeMiddle;
import com.dmj.cli.domain.dto.sys.ResourcesDTO;
import com.dmj.cli.domain.query.api.ResourcesQuery;
import com.dmj.cli.domain.vo.api.ResourcesVO;
import com.dmj.cli.mapper.api.ResourcesMapper;
import com.dmj.cli.mapper.api.ResourcesTypeMiddleMapper;
import com.dmj.cli.service.api.ResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private ResourcesTypeMiddleMapper resourcesTypeMiddleMapper;


    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult insert(ResourcesDTO resourcesDTO) {
        Assert.notNull(resourcesDTO,"bad request");
        Resources resources=new Resources();
        BeanUtil.copyProperties(resourcesDTO,resources);
        resourcesMapper.insert(resources);
        if (CollectionUtil.isNotEmpty(resourcesDTO.getTypeIds())) {
            for (Long typeId:resourcesDTO.getTypeIds()) {
                ResourcesTypeMiddle resourcesTypeMiddle=new ResourcesTypeMiddle();
                resourcesTypeMiddle.setResourcesId(resources.getId());
                resourcesTypeMiddle.setTypeId(typeId);
                resourcesTypeMiddleMapper.insert(resourcesTypeMiddle);
            }
        }
        return BaseResult.success(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult update(ResourcesDTO resourcesDTO) {
        Assert.notNull(resourcesDTO,"bad request");
        Assert.notNull(resourcesDTO.getId(),"id is null");
        Resources resources=new Resources();
        BeanUtil.copyProperties(resourcesDTO,resources);
        resourcesMapper.updateById(resources);
        if (CollectionUtil.isNotEmpty(resourcesDTO.getTypeIds())) {
            resourcesTypeMiddleMapper.delete(new LambdaQueryWrapper<ResourcesTypeMiddle>().eq(ResourcesTypeMiddle::getResourcesId,resources.getId()));
            for (Long typeId:resourcesDTO.getTypeIds()) {
                ResourcesTypeMiddle resourcesTypeMiddle=new ResourcesTypeMiddle();
                resourcesTypeMiddle.setResourcesId(resources.getId());
                resourcesTypeMiddle.setTypeId(typeId);
                resourcesTypeMiddleMapper.insert(resourcesTypeMiddle);
            }
        }
        return BaseResult.success(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult delete(Long id) {
        Assert.notNull(id,"bad request");
        resourcesMapper.deleteById(id);
        resourcesTypeMiddleMapper.delete(new LambdaQueryWrapper<ResourcesTypeMiddle>().eq(ResourcesTypeMiddle::getResourcesId,id));
        return BaseResult.success();
    }

    @Override
    public BaseResult<List<ResourcesVO>> listResources(ResourcesQuery query) {
        List<ResourcesVO> resourcesVOS=resourcesMapper.listResources(query);
        return BaseResult.success(resourcesVOS);
    }

    @Override
    public BaseResult<ResourcesVO> getResourcesById(Long id) {
        Assert.notNull(id,"id is null");
        ResourcesVO resourcesVO=resourcesMapper.getResourcesById(id);
        return BaseResult.success(resourcesVO);
    }
}
