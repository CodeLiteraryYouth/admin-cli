package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.query.sys.RoleQuery;
import com.dmj.cli.domain.vo.sys.SysRoleVO;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleVO> listRole(RoleQuery roleQuery);

    List<Long> listUsersByRoleId(@Param("roleIds") List<Long> roleIds);
}
