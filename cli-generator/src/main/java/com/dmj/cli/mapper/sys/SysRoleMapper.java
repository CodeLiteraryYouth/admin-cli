package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.query.RoleQuery;
import com.dmj.cli.domain.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleVO> listRole(RoleQuery roleQuery);
}
