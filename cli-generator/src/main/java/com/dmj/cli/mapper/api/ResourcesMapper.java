package com.dmj.cli.mapper.api;

import com.dmj.cli.domain.Resources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.query.api.ResourcesQuery;
import com.dmj.cli.domain.vo.api.ResourcesVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
public interface ResourcesMapper extends BaseMapper<Resources> {

    List<ResourcesVO> listResources(ResourcesQuery query);

    ResourcesVO getResourcesById(Long id);
}
