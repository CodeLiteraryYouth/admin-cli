package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.dto.SysRoleDTO;
import com.dmj.cli.domain.query.RoleQuery;
import com.dmj.cli.domain.vo.SysRoleVO;
import com.dmj.cli.service.sys.SysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/sys-role")
@Api(tags = "系统管理-角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService service;

    @ApiOperation("查询角色列表")
    public BaseResult<PageInfo<SysRoleVO>> page(@ModelAttribute RoleQuery query) {
        return service.pageRole(query);
    }

    @ApiOperation("新增角色")
    @PostMapping("/save")
    public BaseResult<SysRole> save(@RequestBody SysRoleDTO entity) {
       return service.insertRole(entity);
    }

    @ApiOperation("修改角色")
    @PutMapping("/update")
    public BaseResult<SysRole> update(@RequestBody SysRoleDTO entity) {
        return service.updateRole(entity);
    }


    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
       return service.deleteRole(id);
    }

}

