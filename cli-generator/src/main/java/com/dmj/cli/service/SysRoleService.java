package com.dmj.cli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.dto.SysPermissionDTO;
import com.dmj.cli.domain.dto.SysRoleDTO;
import com.dmj.cli.domain.query.RoleQuery;
import com.dmj.cli.domain.vo.SysRoleVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysRoleService extends IService<SysRole> {

    BaseResult<PageInfo<SysRoleVO>> pageRole(RoleQuery roleQuery);

    List<SysPermissionDTO> listPermRoles();

    boolean refreshPermRolesRules();

    BaseResult insertRole(SysRoleDTO sysRoleDTO);

    BaseResult updateRole(SysRoleDTO sysRoleDTO);
}
