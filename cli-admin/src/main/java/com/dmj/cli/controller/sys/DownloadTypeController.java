package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.DownloadType;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.service.sys.DownloadTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 下载类型 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/download/type")
@Api(tags = "下载类型")
public class DownloadTypeController extends BaseController {

    @Autowired
    private DownloadTypeService service;

    @ApiOperation("新增下载类型")
    @PostMapping("/save")
    public BaseResult<DownloadType> save(@RequestBody DownloadType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改下载类型")
    @PostMapping("/update")
    public BaseResult<DownloadType> update(@RequestBody DownloadType entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除下载类型")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @GetMapping("info/{id}")
    public BaseResult<DownloadType> info(@PathVariable Long id) {
        DownloadType data=service.getById(id);
        return BaseResult.success(data);
    }

    @ApiOperation("分页获取下载类型")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<DownloadType>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<DownloadType> list= service.list();
        return pageInfoBaseResult(list);
    }
}

