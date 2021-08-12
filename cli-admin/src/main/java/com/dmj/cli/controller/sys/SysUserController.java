package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.query.sys.UserQuery;
import com.dmj.cli.domain.vo.sys.SysUserVO;
import com.dmj.cli.service.sys.SysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/sys-user")
@Api(tags = "系统管理-用户管理")
public class SysUserController {

    @Resource
    private SysUserService service;


    @ApiOperation("查询用户列表")
    @GetMapping("page")
    public BaseResult<PageInfo<SysUserVO>> pageUser(@ModelAttribute UserQuery query) {
        return service.pageUserList(query);
    }

    @ApiOperation("新增用户")
    @PostMapping("/save")
    public BaseResult<SysUser> save(@RequestBody SysUserDTO entity) {
        return service.insertUser(entity);
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public BaseResult<SysUser> update(@RequestBody SysUserDTO entity) {
        return service.updateUser(entity);
    }


    @ApiOperation("删除用户(逻辑删除)")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }


}

