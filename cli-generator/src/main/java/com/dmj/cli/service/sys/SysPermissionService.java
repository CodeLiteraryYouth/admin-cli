package com.dmj.cli.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.query.PermissionQuery;
import com.dmj.cli.domain.vo.sys.SysPermissionVO;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysPermissionService extends IService<SysPermission> {

    BaseResult<PageInfo<SysPermissionVO>> pagePermission(PermissionQuery query);

    BaseResult updatePermission(SysPermission sysPermission);

    BaseResult deletePermission(Long permissionId);

}
