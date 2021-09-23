package com.dmj.cli.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.annotation.view.Collect;
import com.dmj.cli.annotation.view.Favour;
import com.dmj.cli.annotation.view.View;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.BusinessCooperation;
import com.dmj.cli.domain.BusinessCooperationType;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.BusinessCooperationService;
import com.dmj.cli.service.sys.BusinessCooperationTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 企业合作 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/business-cooperation")
@Api(tags = "企业合作")
public class BusinessCooperationApiController extends BaseController {

    @Autowired
    private BusinessCooperationService service;

    @Autowired
    private BusinessCooperationTypeService typeService;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("查询合作列表")
    @GetMapping("/type/list")
    public BaseResult<List<BusinessCooperationType>> typeList() {
        return BaseResult.success(typeService.list());
    }

    @View(type = "BusinessCooperation")
    @ApiOperation("查询企业合作详情")
    @GetMapping("/get/{id}")
    public BaseResult<BusinessCooperation> select(@PathVariable Long id) {
        BusinessCooperation data = service.getById(id);
        buildNum(data);
        return BaseResult.success(data);
    }

    @Favour(type = "Business")
    @ApiOperation("点赞")
    @GetMapping("/favour")
    public BaseResult favour(@RequestParam Long id) {
        return BaseResult.success();
    }

    @Collect(type = "business")
    @ApiOperation("收藏")
    @GetMapping("/collect")
    public BaseResult collect(@RequestParam Long id,@RequestParam Long userId) {
        return BaseResult.success();
    }

    @ApiOperation("分页查询企业合作详情")
    @GetMapping("/page")
    public BaseResult<PageInfo<List<BusinessCooperation>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<BusinessCooperation> list= service.list(Wrappers.<BusinessCooperation>lambdaQuery()
                .eq(Objects.nonNull(query.getTypeId()),BusinessCooperation::getTypeId,query.getTypeId())
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),BusinessCooperation::getTitle,query.getSearchVal()));
        list.forEach(this::buildNum);
        return pageInfoBaseResult(list);
    }


    private void buildNum(BusinessCooperation data) {
        Double viewNum = redisUtils.score(GlobalConstants.VIEW_NUM, data.getId().toString());
        Double collectNum = redisUtils.score(GlobalConstants.COLLECT_NUM, data.getId().toString());
        Double favourNUm = redisUtils.score(GlobalConstants.FAVOUR_NUM, data.getId().toString());
        data.setViewNum(viewNum == null ? 0L : viewNum.longValue());
        data.setCollectNum(collectNum == null ? 0L : collectNum.longValue());
        data.setFavourNum(favourNUm == null ? 0L : favourNUm.longValue());
    }
}

