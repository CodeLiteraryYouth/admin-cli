package com.dmj.cli.service.sys.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.SysRolePermission;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.dto.sys.SysRoleDTO;
import com.dmj.cli.domain.query.sys.RoleQuery;
import com.dmj.cli.domain.vo.sys.SysRoleVO;
import com.dmj.cli.mapper.sys.SysPermissionMapper;
import com.dmj.cli.mapper.sys.SysRoleMapper;
import com.dmj.cli.mapper.sys.SysRolePermissionMapper;
import com.dmj.cli.service.sys.SysRoleService;
import com.dmj.cli.service.sys.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public BaseResult<PageInfo<SysRoleVO>> pageRole(RoleQuery roleQuery) {
        Assert.notNull(roleQuery,"bad request");
        Assert.notNull(roleQuery.getPageNo(),"pageNo is null");
        PageHelper.startPage(roleQuery.getPageNo(),roleQuery.getPageSize());
        List<SysRoleVO> sysRoleVOS=sysRoleMapper.listRole(roleQuery);
        PageInfo<SysRoleVO> pageInfo=new PageInfo<>(sysRoleVOS);
        return BaseResult.success(pageInfo);
    }

    private void refreshUserAuthorities(Long roleId) {
        Assert.notNull(roleId,"roleId is null");
        List<Long> userIds=sysRoleMapper.listUsersByRoleId(roleId);
        if (CollectionUtil.isNotEmpty(userIds)) {
            List<SysUser> sysUsers = sysUserService.listByIds(userIds);
            List<String> userNames = sysUsers.stream().map(SysUser::getUserName).collect(Collectors.toList());
            sysUserService.refreshUserAuthorities(userNames.stream().toArray(String[]::new));
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult insertRole(SysRoleDTO sysRoleDTO) {
        Assert.notNull(sysRoleDTO,"bad request");
        SysRole sysRole=new SysRole();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRole.setRoleCode(GlobalConstants.ROLE_PREFIX+sysRoleDTO.getRoleCode());
        sysRoleMapper.insert(sysRole);
        if (Objects.nonNull(sysRoleDTO.getPermissionIds()) && !sysRoleDTO.getPermissionIds().isEmpty()) {
            for (Integer permissionId: sysRoleDTO.getPermissionIds()) {
                SysRolePermission sysRolePermission=new SysRolePermission();
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(permissionId);
                sysRolePermissionMapper.insert(sysRolePermission);
            }
        }
        return BaseResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult updateRole(SysRoleDTO sysRoleDTO) {
        Assert.notNull(sysRoleDTO,"bad request");
        Assert.notNull(sysRoleDTO.getId(),"id is null");
        SysRole sysRole=new SysRole();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRole.setRoleCode(GlobalConstants.ROLE_PREFIX+sysRoleDTO.getRoleCode());
        sysRoleMapper.updateById(sysRole);
        if (Objects.nonNull(sysRoleDTO.getPermissionIds()) && !sysRoleDTO.getPermissionIds().isEmpty()) {
            sysRolePermissionMapper.delete(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId,sysRole.getId()));
            for (Integer permissionId:sysRoleDTO.getPermissionIds()) {
                SysRolePermission sysRolePermission=new SysRolePermission();
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(permissionId);
                sysRolePermissionMapper.insert(sysRolePermission);
            }
            refreshUserAuthorities(Long.valueOf(sysRoleDTO.getId()));
        }
        return BaseResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult deleteRole(Long roleId) {
        Assert.notNull(roleId,"roleId is null");
        int res=sysRoleMapper.deleteById(roleId);
        if (res > 0) {
            refreshUserAuthorities(roleId);
        }
        return BaseResult.success();
    }
}
