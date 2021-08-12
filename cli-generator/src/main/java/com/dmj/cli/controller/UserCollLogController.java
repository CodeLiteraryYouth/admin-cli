package com.dmj.cli.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.service.api.UserCollLogService;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/user-coll-log")
@Api(tags = "收藏记录")
public class UserCollLogController {

    @Autowired
    private UserCollLogService service;


}

