package com.dmj.cli.controller.sys;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.PracticesJob;
import com.dmj.cli.domain.query.api.PracticesJobQuery;
import com.dmj.cli.service.api.PracticesJobService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 周练 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/practices/job")
@Api(tags = "周练")
public class PracticesJobController extends BaseController {

    @Autowired
    private PracticesJobService service;

    @ApiOperation("上传周练")
    @PostMapping("/save")
    public BaseResult<PracticesJob> save(@RequestBody PracticesJob entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改周练")
    @PostMapping("/update")
    public BaseResult<PracticesJob> update(@RequestBody PracticesJob entity) {
        Assert.notNull(entity.getId(),"id is null");
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除周练")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("获取周练详情")
    @GetMapping("/info/{id}")
    public BaseResult<PracticesJob> select(@PathVariable Long id) {
        PracticesJob data = service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页查询周练")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<PracticesJob>>> page(@ModelAttribute PracticesJobQuery query) {
        startPage();
        List<PracticesJob> list=service.list(Wrappers.<PracticesJob>lambdaQuery()
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),PracticesJob::getJobTitle,query.getSearchVal()));
        return pageInfoBaseResult(list);
    }
}

