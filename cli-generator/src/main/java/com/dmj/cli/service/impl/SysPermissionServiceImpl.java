package com.dmj.cli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.dto.SysPermissionDTO;
import com.dmj.cli.domain.query.PermissionQuery;
import com.dmj.cli.domain.vo.SysPermissionVO;
import com.dmj.cli.service.SysPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

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
    public List<SysPermissionDTO> listPermissionRole() {
        return sysPermissionMapper.listPermissionRole();
    }
}
