package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.query.sys.UserQuery;
import com.dmj.cli.domain.vo.sys.SysUserVO;
import com.dmj.cli.service.sys.SysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/sys/user")
@Api(tags = "系统管理-用户管理")
public class SysUserController {

    @Resource
    private SysUserService service;


    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public BaseResult<PageInfo<SysUserVO>> pageUser(@ModelAttribute UserQuery query) {
        return service.pageUserList(query);
    }

    @ApiOperation("新增用户")
    @PostMapping("/save")
    public BaseResult save(@RequestBody SysUserDTO entity) {
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        return service.insertUser(entity);
    }

    @ApiOperation("修改用户")
    @PostMapping("/update")
    public BaseResult update(@RequestBody SysUserDTO entity) {
        return service.updateUser(entity);
    }

    @GetMapping("/info")
    public BaseResult<SysUserVO> info(@RequestParam Long id) {
        return service.info(id);
    }


    @ApiOperation("删除用户(逻辑删除)")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<String> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }


}

