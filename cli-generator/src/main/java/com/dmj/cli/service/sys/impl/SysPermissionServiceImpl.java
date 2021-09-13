package com.dmj.cli.service.sys.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.query.sys.PermissionQuery;
import com.dmj.cli.domain.vo.sys.SysPermissionVO;
import com.dmj.cli.mapper.sys.SysPermissionMapper;
import com.dmj.cli.mapper.sys.SysUserMapper;
import com.dmj.cli.service.sys.SysPermissionService;
import com.dmj.cli.service.sys.SysUserService;
import com.dmj.cli.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public BaseResult<List<SysPermissionVO>> pagePermission(PermissionQuery query) {
        List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.listPermission(query);
        List<SysPermissionVO> result=DataTransferUtils.list2Tree(sysPermissionVOS,"id","parentId","children");
        return BaseResult.success(result);
    }

    @Override
    public BaseResult<Map<String, Object>> listUserPermissions(String userName) {
        Assert.notNull(userName,"userName is null");
        SysUserDTO sysUserDTO=sysUserService.getUserByName(userName);
        List<Long> ids=sysPermissionMapper.listPermissionIdsByUserId(sysUserDTO.getId());
        List<SysPermission> sysPermissions=sysPermissionMapper.selectBatchIds(ids);
        List<SysPermissionVO> sysPermissionVOS = new ArrayList<>(sysPermissions.size());
        List<String> perms=null;
        if (CollectionUtil.isNotEmpty(sysPermissions)) {
            sysPermissions.forEach(sysPermission -> {
                SysPermissionVO sysPermissionVO=new SysPermissionVO();
                BeanUtil.copyProperties(sysPermission,sysPermissionVO);
                sysPermissionVOS.add(sysPermissionVO);
            });
            perms=sysPermissions.stream().map(SysPermission::getPermissionStr).collect(Collectors.toList());

        }
        List<SysPermissionVO> menuList=DataTransferUtils.list2Tree(sysPermissionVOS,"id","parentId","children");
        Map<String,Object> result=new HashMap<>(2);
        result.put("menuList",menuList);
        result.put("permissions",perms);
        return BaseResult.success(result);
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
