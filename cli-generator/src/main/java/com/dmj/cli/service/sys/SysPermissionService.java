package com.dmj.cli.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.query.sys.PermissionQuery;
import com.dmj.cli.domain.vo.sys.SysPermissionVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysPermissionService extends IService<SysPermission> {

    BaseResult<List<SysPermissionVO>> pagePermission(PermissionQuery query);

    BaseResult<SysPermissionVO> getPermissionById(Long id);

    BaseResult<Map<String,Object>> listUserPermissions(String userName);

    BaseResult updatePermission(SysPermission sysPermission);

    BaseResult deletePermission(Long permissionId);

}
