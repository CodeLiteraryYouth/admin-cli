package com.dmj.cli.service.sys.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.query.PermissionQuery;
import com.dmj.cli.domain.vo.sys.SysPermissionVO;
import com.dmj.cli.mapper.sys.SysPermissionMapper;
import com.dmj.cli.service.sys.SysPermissionService;
import com.dmj.cli.service.sys.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public BaseResult<PageInfo<SysPermissionVO>> pagePermission(PermissionQuery query) {
        Assert.notNull(query,"bad request");
        Assert.notNull(query.getPageNo(),"pageNo is null");
        PageHelper.startPage(query.getPageNo(),query.getPageSize());
        List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.listPermission(query);
        PageInfo<SysPermissionVO> pageInfo=new PageInfo<>(sysPermissionVOS);
        return BaseResult.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult updatePermission(SysPermission sysPermission) {
        Assert.notNull(sysPermission,"bad request");
        Assert.notNull(sysPermission.getId(),"primary key is null");
        sysPermissionMapper.updateById(sysPermission);
        refreshUserAuthorities(Long.valueOf(sysPermission.getId()));
        return BaseResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult deletePermission(Long permissionId) {
        Assert.notNull(permissionId,"permissionId is null");
        sysPermissionMapper.deleteById(permissionId);
        refreshUserAuthorities(permissionId);
        return BaseResult.success();
    }

    private void refreshUserAuthorities(Long permissionId) {
        List<Long> userIds=sysPermissionMapper.listUsersByPermissionId(permissionId);
        List<SysUser> sysUsers=sysUserService.listByIds(userIds);
        if (CollectionUtil.isNotEmpty(sysUsers)) {
            List<String> userNames=sysUsers.stream().map(SysUser::getUserName).collect(Collectors.toList());
            sysUserService.refreshUserAuthorities(userNames.stream().toArray(String[]::new));
        }
    }

}
