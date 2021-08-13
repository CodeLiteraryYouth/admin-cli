package com.dmj.cli.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.service.api.UserDownloadLogService;
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
@RequestMapping("/user-download-log")
@Api(tags = "下载记录")
public class UserDownloadLogController {

    @Autowired
    private UserDownloadLogService service;

}

