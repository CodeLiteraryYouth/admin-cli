package com.dmj.cli.controller.job;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.exception.TaskException;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.SysJob;
import com.dmj.cli.service.ISysJobService;
import com.dmj.cli.util.CronUtils;
import com.dmj.cli.util.str.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 * 
 * @author zd
 */
@RestController
@RequestMapping("/monitor/job")
@Api(tags = "系统管理-定时任务")
public class SysJobController extends BaseController {

    @Autowired
    private ISysJobService jobService;


    @GetMapping("/list")
    @ApiOperation("获取任务列表")
    public BaseResult<PageInfo<List<SysJob>>> list(@ModelAttribute SysJob job) {
        startPage();
        List<SysJob> list = jobService.selectJobList(job);
        return pageInfoBaseResult(list);
    }

    @DeleteMapping("/delete")
    @ApiOperation("移除任务")
    public BaseResult remove(List<String> list) throws SchedulerException {
        String ids = StringUtils.join(list,",");
        jobService.deleteJobByIds(ids);
        return success();
    }

    /**
     * 任务调度状态修改
     */
    @PostMapping("/changeStatus")
    @ApiOperation("改变任务状态")
    public BaseResult changeStatus(@RequestBody SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return BaseResult.success(jobService.changeStatus(newJob));
    }

    /**
     * 任务调度立即执行一次
     */
    @PostMapping("/run")
    @ApiOperation("立即执行任务")
    public BaseResult run(@RequestBody SysJob job) throws SchedulerException {
        jobService.run(job);
        return success();
    }

    @PostMapping("/add")
    @ApiOperation("增加任务")
    public BaseResult addSave(@Validated @RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return fail("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), GlobalConstants.LOOKUP_RMI)) {
            return fail("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { GlobalConstants.HTTP, GlobalConstants.HTTPS })) {
            return fail("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用");
        }
        return BaseResult.success(jobService.insertJob(job));
    }

    /**
     * 修改保存调度
     */

    @PostMapping("/edit")
    @ApiOperation("编辑任务")
    public BaseResult editSave(@Validated @RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return fail("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), GlobalConstants.LOOKUP_RMI)) {
            return fail("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { GlobalConstants.HTTP, GlobalConstants.HTTPS })) {
            return fail("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用");
        }
        return BaseResult.success(jobService.updateJob(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ApiOperation("校验Cron表达式")
    public boolean checkCronExpressionIsValid(@RequestBody SysJob job) {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }
}
