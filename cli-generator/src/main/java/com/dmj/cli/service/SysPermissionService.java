package com.dmj.cli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.dto.SysPermissionDTO;
import com.dmj.cli.domain.query.PermissionQuery;
import com.dmj.cli.domain.vo.SysPermissionVO;
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
public interface SysPermissionService extends IService<SysPermission> {

    BaseResult<PageInfo<SysPermissionVO>> pagePermission(PermissionQuery query);

    List<SysPermissionDTO> listPermissionRole();
}
