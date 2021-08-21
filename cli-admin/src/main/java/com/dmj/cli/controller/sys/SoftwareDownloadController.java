package com.dmj.cli.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SoftwareDownload;
import com.dmj.cli.service.sys.SoftwareDownloadService;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import java.util.List;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.BaseQuery;

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
public class SoftwareDownloadController extends BaseController {
    @Autowired
    private SoftwareDownloadService service;

    @ApiOperation("上传软件下载信息")
    @PostMapping("/save")
    public BaseResult<SoftwareDownload> save(@RequestBody SoftwareDownload entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("更新软件下载信息")
    @PutMapping("/update")
    public BaseResult<SoftwareDownload> update(@RequestBody SoftwareDownload entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除软件下载信息")
    @DeleteMapping("/delete/{id}")
    public BaseResult delete(@PathVariable Long id) {
        service.removeById(id);
        return BaseResult.success();
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
