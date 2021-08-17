package com.dmj.cli.controller;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.DownloadType;
import com.dmj.cli.domain.SoftwareDownload;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.DownloadTypeService;
import com.dmj.cli.service.sys.SoftwareDownloadService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 软件下载 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/software-download")
@Api(tags = "软件下载")
public class SoftwareDownloadApiController extends BaseController {

    @Autowired
    private SoftwareDownloadService service;

    @Autowired
    private DownloadTypeService downloadTypeService;


    @ApiOperation("获取下载类型")
    @GetMapping("/type/list")
    public BaseResult<List<DownloadType>> listDownloadType() {
        return BaseResult.success(downloadTypeService.list());
    }

    @ApiOperation("获取软件下载详情")
    @GetMapping("/get/{id}")
    public BaseResult<SoftwareDownload> select(@PathVariable Long id) {
        SoftwareDownload data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页获取软件下载信息")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<SoftwareDownload>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<SoftwareDownload> list= service.list();
        return pageInfoBaseResult(list);
    }
}

