package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysRole;
import com.dmj.cli.domain.dto.sys.SysRoleDTO;
import com.dmj.cli.domain.query.sys.RoleQuery;
import com.dmj.cli.domain.vo.sys.SysRoleVO;
import com.dmj.cli.service.sys.SysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "系统管理-角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService service;

    @ApiOperation("查询角色列表")
    @GetMapping("/list")
    public BaseResult<PageInfo<SysRoleVO>> page(@ModelAttribute RoleQuery query) {
        return service.pageRole(query);
    }

    @ApiOperation("新增角色")
    @PostMapping("/save")
    public BaseResult<SysRole> save(@RequestBody SysRoleDTO entity) {
       return service.insertRole(entity);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public BaseResult<SysRole> update(@RequestBody SysRoleDTO entity) {
        return service.updateRole(entity);
    }

    @GetMapping("/select")
    public BaseResult<List<SysRole>> select() {
        return BaseResult.success(service.list());
    }

    @GetMapping("/info/{id}")
    public BaseResult<SysRoleVO> info(@PathVariable Long id) {
        return service.info(id);
    }


    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
       return service.deleteRole(ids);
    }

}

