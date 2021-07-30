package com.dmj.cli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.SysUserRole;
import com.dmj.cli.domain.dto.SysUserDTO;
import com.dmj.cli.domain.query.UserQuery;
import com.dmj.cli.domain.vo.SysUserVO;
import com.dmj.cli.service.SysUserService;
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

    @Override
    public SysUserDTO getUserByName(String userName) {
        return sysUserMapper.getUserByName(userName);
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
        sysUser.setCreateTime(LocalDateTime.now());
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
        sysUser.setUpdateTime(LocalDateTime.now());
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
        }
        return BaseResult.success();
    }
}
