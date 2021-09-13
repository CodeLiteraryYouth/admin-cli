package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysPermission;
import com.dmj.cli.domain.query.sys.PermissionQuery;
import com.dmj.cli.domain.vo.sys.SysPermissionVO;
import com.dmj.cli.service.sys.SysPermissionService;
import com.dmj.cli.service.sys.SysRoleService;
import com.dmj.cli.util.jwt.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@RestController
@RequestMapping("sys/menu")
@Api(tags = "系统管理-权限管理")
public class SysPermissionController {

    @Autowired
    private SysPermissionService service;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("权限列表")
    @GetMapping("/list")
    public BaseResult<List<SysPermissionVO>> pageInfoBaseResult(@ModelAttribute PermissionQuery query) {
        return service.pagePermission(query);
    }

    @ApiOperation("查询当前用户菜单和权限列表")
    @GetMapping("/nav")
    public BaseResult<Map<String,Object>> listUserPermission(HttpServletRequest request) {
        return service.listUserPermissions(jwtUtils.getUserName());
    }

    @PostMapping("/save")
    @ApiOperation("新增权限")
    public BaseResult<SysPermission> save(@RequestBody SysPermission entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    @ApiOperation("更新权限")
    public BaseResult<SysPermission> update(@RequestBody SysPermission entity) {
        return service.updatePermission(entity);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除权限")
    public BaseResult delete(@PathVariable Long id) {
       return service.deletePermission(id);
    }

}

