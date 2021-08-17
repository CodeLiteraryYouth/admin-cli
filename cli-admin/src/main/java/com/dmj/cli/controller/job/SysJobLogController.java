package com.dmj.cli.controller.job;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.SysJobLog;
import com.dmj.cli.service.ISysJobLogService;
import com.dmj.cli.service.ISysJobService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author zd
 */
@Controller
@RequestMapping("/monitor/jobLog")
@Api(tags = "系统管理-定时任务日志")
public class SysJobLogController extends BaseController {

    @Autowired
    private ISysJobService jobService;

    @Autowired
    private ISysJobLogService jobLogService;


    @PostMapping("/list")
    @ResponseBody
    @ApiOperation("查询定时任务日志列表")
    public BaseResult<PageInfo<List<SysJobLog>>> list(SysJobLog jobLog) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return pageInfoBaseResult(list);
    }

    @PostMapping("/remove")
    @ResponseBody
    @ApiOperation("移除定时任务日志")
    public BaseResult remove(String ids) {
        return success(jobLogService.deleteJobLogByIds(ids));
    }


    @PostMapping("/clean")
    @ResponseBody
    @ApiOperation("清空任务日志")
    public BaseResult clean() {
        jobLogService.cleanJobLog();
        return success();
    }
}
