package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.query.sys.PermissionQuery;
import com.dmj.cli.domain.vo.sys.SysPermissionVO;
import org.apache.ibatis.annotations.Param;

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

    List<SysPermission> listByRoleId(@Param("roleId") Long roleId);

    List<Long> listPermissionIdsByUserId(@Param("userId") Long userId);

    List<Long> listUsersByPermissionId(@Param("permissionId") Long permissionId);
}
