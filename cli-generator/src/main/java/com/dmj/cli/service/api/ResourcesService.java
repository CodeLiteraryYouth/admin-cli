package com.dmj.cli.service.api;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Resources;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.dto.sys.ResourcesDTO;
import com.dmj.cli.domain.query.api.ResourcesQuery;
import com.dmj.cli.domain.vo.api.ResourcesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
public interface ResourcesService extends IService<Resources> {

    BaseResult insert(ResourcesDTO resourcesDTO);

    BaseResult update(ResourcesDTO resourcesDTO);

    BaseResult delete(List<Long> id);

    List<ResourcesVO> listResources(ResourcesQuery query);

    BaseResult<ResourcesVO> getResourcesById(Long id);

}
