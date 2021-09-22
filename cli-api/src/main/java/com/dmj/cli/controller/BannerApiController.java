package com.dmj.cli.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Banner;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.sys.BannerQuery;
import com.dmj.cli.service.sys.BannerService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/banner")
@Api(tags = "广告位管理")
public class BannerApiController extends BaseController {

    @Autowired
    private BannerService service;


    @ApiOperation("分页查询广告位")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<Banner>>> page(@ModelAttribute BannerQuery query) {
        startPage();
        List<Banner> list= service.list(Wrappers.<Banner>lambdaQuery()
                .eq(Objects.isNull(query.getLocation()),Banner::getLocation,query.getLocation())
                .eq(Objects.isNull(query.getType()),Banner::getType,query.getType()));
        return pageInfoBaseResult(list);
    }

}

