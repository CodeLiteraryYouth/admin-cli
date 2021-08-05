package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.dto.SysUserDTO;
import com.dmj.cli.domain.query.UserQuery;
import com.dmj.cli.domain.vo.SysUserVO;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUserDTO getUserByName(@Param("userName") String userName);

    List<Long> listMenuIdsByUserId(@Param("userId") Integer userId);

    List<SysUserVO> listSysUser(UserQuery userQuery);
}
