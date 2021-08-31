package com.dmj.cli.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.Tool;
import com.dmj.cli.domain.ToolType;
import com.dmj.cli.domain.query.sys.ToolQuery;
import com.dmj.cli.service.sys.ToolService;
import com.dmj.cli.service.sys.ToolTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zd
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/tool")
@Api(tags = "工具")
public class ToolApiController extends BaseController {

    @Autowired
    private ToolTypeService toolTypeService;

    @Autowired
    private ToolService toolService;


    @ApiOperation("查询工具类型")
    @GetMapping("/tyoe/list")
    public BaseResult<List<ToolType>> listType() {
        return BaseResult.success(toolTypeService.list());
    }


    @ApiOperation("查询工具列表")
    @GetMapping("/list")
    public BaseResult<List<Tool>> listTool(@ModelAttribute ToolQuery query) {
        List<Tool> list=null;
        if (query.getTypeId() != null) {
            list = toolService.list(Wrappers.<Tool>lambdaQuery().eq(Tool::getTypeId,query.getTypeId()));
        } else {
            list = toolService.list();
        }
        return BaseResult.success(list);
    }
}
