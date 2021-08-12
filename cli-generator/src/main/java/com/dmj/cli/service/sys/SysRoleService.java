package com.dmj.cli.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.dto.sys.SysRoleDTO;
import com.dmj.cli.domain.query.sys.RoleQuery;
import com.dmj.cli.domain.vo.sys.SysRoleVO;
import com.github.pagehelper.PageInfo;

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

    BaseResult insertRole(SysRoleDTO sysRoleDTO);

    BaseResult updateRole(SysRoleDTO sysRoleDTO);

    BaseResult deleteRole(Long roleId);
}
