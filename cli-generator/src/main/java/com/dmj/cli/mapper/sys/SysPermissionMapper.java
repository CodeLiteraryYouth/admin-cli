package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.dto.SysPermissionDTO;
import com.dmj.cli.domain.query.PermissionQuery;
import com.dmj.cli.domain.vo.SysPermissionVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermissionVO> listPermission(PermissionQuery query);

}
