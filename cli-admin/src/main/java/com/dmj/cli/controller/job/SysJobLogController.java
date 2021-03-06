package com.dmj.cli.controller.job;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.SysJobLog;
import com.dmj.cli.service.ISysJobLogService;
import com.dmj.cli.service.ISysJobService;
import com.dmj.cli.util.str.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author zd
 */
@RestController
@RequestMapping("/monitor/jobLog")
@Api(tags = "系统管理-定时任务日志")
public class SysJobLogController extends BaseController {

    @Autowired
    private ISysJobService jobService;

    @Autowired
    private ISysJobLogService jobLogService;


    @GetMapping("/list")
    @ApiOperation("查询定时任务日志列表")
    public BaseResult<PageInfo<List<SysJobLog>>> list(@ModelAttribute SysJobLog jobLog) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return pageInfoBaseResult(list);
    }

    @DeleteMapping("/delete")
    @ApiOperation("移除定时任务日志")
    public BaseResult remove(@RequestBody List<String> list) {
        String ids = StringUtils.join(list,",");
        return success(jobLogService.deleteJobLogByIds(ids));
    }

    @PostMapping("/clean")
    @ApiOperation("清空任务日志")
    public BaseResult clean() {
        jobLogService.cleanJobLog();
        return success();
    }
}
