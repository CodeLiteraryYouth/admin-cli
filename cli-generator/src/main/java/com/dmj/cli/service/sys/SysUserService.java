package com.dmj.cli.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.query.sys.UserQuery;
import com.dmj.cli.domain.vo.sys.SysUserVO;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysUserService extends IService<SysUser> {

    SysUserDTO getUserByName(String userName);

    /**
     * 刷信用户权限
     * @param users
     */
    void refreshUserAuthorities(String... users);

    BaseResult<PageInfo<SysUserVO>> pageUserList(UserQuery userQuery);

    BaseResult insertUser(SysUserDTO sysUserDTO);

    BaseResult updateUser(SysUserDTO sysUserDTO);


}
