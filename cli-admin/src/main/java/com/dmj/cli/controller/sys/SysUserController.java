package com.dmj.cli.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.domain.SysUser;
import com.dmj.cli.domain.dto.sys.PasswordDTO;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.query.sys.UserQuery;
import com.dmj.cli.domain.vo.sys.SysUserVO;
import com.dmj.cli.service.sys.SysUserService;
import com.dmj.cli.util.jwt.JwtUtils;
import com.dmj.cli.util.str.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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
@RequestMapping("sys/user")
@Api(tags = "系统管理-用户管理")
public class SysUserController {

    @Resource
    private SysUserService service;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    @GetMapping("/info/{id}")
    public BaseResult<SysUserVO> info(@PathVariable(required = false) Long id) {
        return service.info(id);
    }

    @GetMapping("/info")
    public BaseResult<SysUserVO> info() {
        String userName= jwtUtils.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return BaseResult.fail("token is null");
        }
        Long userId=service.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName,userName)).getId();
        return service.info(userId);
    }

    @ApiOperation("修改密码")
    @PostMapping("/password")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BaseResult password(@RequestBody PasswordDTO passwordDTO) {
        Assert.notNull(passwordDTO.getNewPassword(),"newPassword is null");
        String userName= jwtUtils.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return BaseResult.fail(ResultStatusCode.LOGIN_ERROR);
        }
        String password=passwordEncoder.encode(passwordDTO.getPassword());
        String newPassword=passwordEncoder.encode(passwordDTO.getNewPassword());
        if (!password.equals(newPassword)) {
            return BaseResult.fail("password is error");
        }
        SysUserDTO sysUserDTO=service.getUserByName(userName);
        SysUser sysUser=new SysUser();
        BeanUtil.copyProperties(sysUserDTO,sysUser);
        sysUser.setPassword(newPassword);
        service.updateById(sysUser);
        return BaseResult.success();
    }


    @ApiOperation("删除用户(逻辑删除)")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<String> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }



}

