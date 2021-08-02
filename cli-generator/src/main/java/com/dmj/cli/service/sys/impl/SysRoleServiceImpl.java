package com.dmj.cli.service.sys.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.SysRolePermission;
import com.dmj.cli.domain.dto.SysPermissionDTO;
import com.dmj.cli.domain.dto.SysRoleDTO;
import com.dmj.cli.domain.query.RoleQuery;
import com.dmj.cli.domain.vo.SysRoleVO;
import com.dmj.cli.mapper.sys.SysPermissionMapper;
import com.dmj.cli.mapper.sys.SysRoleMapper;
import com.dmj.cli.mapper.sys.SysRolePermissionMapper;
import com.dmj.cli.service.sys.SysRoleService;
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

    @Override
    public List<SysPermissionDTO> listPermRoles() {
        return sysPermissionMapper.listPermissionRole();
    }

    @Override
    public boolean refreshPermRolesRules() {
        redisTemplate.delete(Arrays.asList(GlobalConstants.URL_PERM_ROLES_KEY,GlobalConstants.BTN_PERM_ROLES_KEY));
        List<SysPermissionDTO> permissions = this.listPermRoles();
        if (CollectionUtil.isNotEmpty(permissions)) {
            // 初始化URL【权限->角色(集合)】规则
            List<SysPermissionDTO> urlPermList = permissions.stream()
                    .filter(item -> StrUtil.isNotBlank(item.getPermissionUrl()))
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(urlPermList)) {
                Map<String, List<String>> urlPermRoles = new HashMap<>();
                urlPermList.stream().forEach(item -> {
                    String perm = item.getPermissionUrl();
                    List<String> roles = item.getRoles();
                    urlPermRoles.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.URL_PERM_ROLES_KEY, urlPermRoles);
            }
            // 初始化URL【按钮->角色(集合)】规则
            List<SysPermissionDTO> btnPermList = permissions.stream()
                    .filter(item -> StrUtil.isNotBlank(item.getPermissionStr()))
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(btnPermList)) {
                Map<String, List<String>> btnPermRoles = CollectionUtil.newHashMap();
                btnPermList.stream().forEach(item -> {
                    String perm = item.getPermissionStr();
                    List<String> roles = item.getRoles();
                    btnPermRoles.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.BTN_PERM_ROLES_KEY, btnPermRoles);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult insertRole(SysRoleDTO sysRoleDTO) {
        Assert.notNull(sysRoleDTO,"bad request");
        SysRole sysRole=new SysRole();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRole.setCreateTime(LocalDateTime.now());
        sysRoleMapper.insert(sysRole);
        if (Objects.nonNull(sysRoleDTO.getPermissionIds()) && !sysRoleDTO.getPermissionIds().isEmpty()) {
            for (Integer permissionId: sysRoleDTO.getPermissionIds()) {
                SysRolePermission sysRolePermission=new SysRolePermission();
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(permissionId);
                sysRolePermissionMapper.insert(sysRolePermission);
            }
            refreshPermRolesRules();
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
        sysRole.setUpdateTime(LocalDateTime.now());
        sysRoleMapper.updateById(sysRole);
        if (Objects.nonNull(sysRoleDTO.getPermissionIds()) && !sysRoleDTO.getPermissionIds().isEmpty()) {
            sysRolePermissionMapper.delete(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId,sysRole.getId()));
            for (Integer permissionId:sysRoleDTO.getPermissionIds()) {
                SysRolePermission sysRolePermission=new SysRolePermission();
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(permissionId);
                sysRolePermissionMapper.insert(sysRolePermission);
            }
            refreshPermRolesRules();
        }
        return BaseResult.success();
    }
}
