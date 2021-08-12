package com.dmj.cli.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.SysUserRole;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.query.sys.UserQuery;
import com.dmj.cli.domain.vo.sys.SysUserVO;
import com.dmj.cli.mapper.sys.SysPermissionMapper;
import com.dmj.cli.mapper.sys.SysRoleMapper;
import com.dmj.cli.mapper.sys.SysUserMapper;
import com.dmj.cli.mapper.sys.SysUserRoleMapper;
import com.dmj.cli.service.sys.SysUserService;
import com.dmj.cli.util.str.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public SysUserDTO getUserByName(String userName) {
        SysUserDTO sysUserDTO=null;
        Object value=redisUtils.get(AuthConstants.CACHE_AUTHORITIES+":"+userName);
        if (null== value) {
            sysUserDTO = sysUserMapper.getUserByName(userName);
            if (null == sysUserDTO) {
                List<Long> permissionIds = sysUserMapper.listMenuIdsByUserId(sysUserDTO.getId());
                List<SysPermission> sysPermissions = sysPermissionMapper.selectBatchIds(permissionIds);
                List<String> permissionStrs = sysPermissions.stream().map(SysPermission::getPermissionStr).collect(Collectors.toList());
                sysUserDTO.setAuthorities(permissionStrs);
            }
            redisUtils.set(AuthConstants.CACHE_AUTHORITIES+":"+userName,sysUserDTO);
        } else {
            sysUserDTO= (SysUserDTO) redisUtils.get(AuthConstants.CACHE_AUTHORITIES+":"+userName);
        }
       return sysUserDTO;
    }

    @Override
    public void refreshUserAuthorities(String... users) {
        if (StringUtils.isNotEmpty(users)) {
            for (String userName : users) {
                SysUserDTO sysUserDTO = sysUserMapper.getUserByName(userName);
                if (null == sysUserDTO) {
                    List<Long> permissionIds = sysUserMapper.listMenuIdsByUserId(sysUserDTO.getId());
                    List<SysPermission> sysPermissions = sysPermissionMapper.selectBatchIds(permissionIds);
                    List<String> permissionStrs = sysPermissions.stream().map(SysPermission::getPermissionStr).collect(Collectors.toList());
                    sysUserDTO.setAuthorities(permissionStrs);
                }
                redisUtils.set(AuthConstants.CACHE_AUTHORITIES+":"+userName,sysUserDTO);
            }
        }
    }

    @Override
    public BaseResult<PageInfo<SysUserVO>> pageUserList(UserQuery userQuery) {
        Assert.notNull(userQuery,"bad request");
        Assert.notNull(userQuery.getPageNo(),"pageNo is null");
        Assert.notNull(userQuery.getPageSize(),"pageSize is null");
        PageHelper.startPage(userQuery.getPageNo(),userQuery.getPageSize());
        List<SysUserVO> sysUserVOS=sysUserMapper.listSysUser(userQuery);
        PageInfo<SysUserVO> pageInfo=new PageInfo<SysUserVO>(sysUserVOS);
        return BaseResult.success(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult insertUser(SysUserDTO sysUserDTO) {
        Assert.notNull(sysUserDTO,"bad request");
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        sysUserMapper.insert(sysUser);
        if (Objects.nonNull(sysUserDTO.getRoles()) && !sysUserDTO.getRoles().isEmpty()) {
            for (String roleCode: sysUserDTO.getRoles()) {
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                LambdaQueryWrapper<SysRole> queryWrapper=new LambdaQueryWrapper<SysRole>();
                SysRole sysRole=sysRoleMapper.selectOne(queryWrapper.eq(SysRole::getRoleCode,roleCode));
                if (Objects.nonNull(sysRole)) {
                    sysUserRole.setRoleId(sysRole.getId());
                }
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
        return BaseResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult updateUser(SysUserDTO sysUserDTO) {
        Assert.notNull(sysUserDTO,"bad request");
        Assert.notNull(sysUserDTO.getId(),"id is null");
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        sysUserMapper.updateById(sysUser);
        if (Objects.nonNull(sysUserDTO.getRoles()) && !sysUserDTO.getRoles().isEmpty()) {
            sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,sysUser.getId()));
            for (String roleCode: sysUserDTO.getRoles()) {
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                LambdaQueryWrapper<SysRole> queryWrapper=new LambdaQueryWrapper<SysRole>();
                SysRole sysRole=sysRoleMapper.selectOne(queryWrapper.eq(SysRole::getRoleCode,roleCode));
                if (Objects.nonNull(sysRole)) {
                    sysUserRole.setRoleId(sysRole.getId());
                }
                sysUserRoleMapper.insert(sysUserRole);
            }
            //更新权限缓存
            refreshUserAuthorities(sysUserDTO.getUserName());
        }
        return BaseResult.success();
    }
}
