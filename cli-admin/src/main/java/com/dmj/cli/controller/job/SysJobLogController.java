package com.dmj.cli.controller.job;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.controller.BaseController;
import com.dmj.cli.domain.SysJobLog;
import com.dmj.cli.service.ISysJobLogService;
import com.dmj.cli.service.ISysJobService;
import com.github.pagehelper.PageInfo;
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
public class SysJobLogController extends BaseController {

    @Autowired
    private ISysJobService jobService;

    @Autowired
    private ISysJobLogService jobLogService;


    @PostMapping("/list")
    @ResponseBody
    public BaseResult<PageInfo<List<SysJobLog>>> list(SysJobLog jobLog) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return pageInfoBaseResult(list);
    }

    @PostMapping("/remove")
    @ResponseBody
    public BaseResult remove(String ids) {
        return success(jobLogService.deleteJobLogByIds(ids));
    }


    @PostMapping("/clean")
    @ResponseBody
    public BaseResult clean() {
        jobLogService.cleanJobLog();
        return success();
    }
}
