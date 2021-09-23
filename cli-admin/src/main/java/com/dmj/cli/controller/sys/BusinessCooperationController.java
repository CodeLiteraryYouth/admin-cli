package com.dmj.cli.controller.sys;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.BusinessCooperation;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.domain.vo.sys.BusinessCooperationVO;
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
@RequestMapping("/business/cooperation")
@Api(tags = "企业合作")
public class BusinessCooperationController extends BaseController {

    @Autowired
    private BusinessCooperationService service;

    @Autowired
    private BusinessCooperationTypeService typeService;

    @ApiOperation("新增企业合作详情")
    @PostMapping("/save")
    public BaseResult<BusinessCooperation> save(@RequestBody BusinessCooperation entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("修改企业合作详情")
    @PostMapping("/update")
    public BaseResult<BusinessCooperation> update(@RequestBody BusinessCooperation entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @ApiOperation("删除企业合作详情")
    @DeleteMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return BaseResult.success();
    }

    @ApiOperation("查询企业合作详情")
    @GetMapping("/info/{id}")
    public BaseResult<BusinessCooperationVO> select(@PathVariable Long id) {
        BusinessCooperation data = service.getById(id);
        BusinessCooperationVO vo=new BusinessCooperationVO();
        BeanUtil.copyProperties(data,vo);
        vo.setTypeName(typeService.getById(data.getTypeId()).getTypeName());
        return BaseResult.success(vo);
    }

    @ApiOperation("分页查询企业合作详情")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<BusinessCooperation>>> page(@ModelAttribute BaseQuery query) {
        startPage();
        List<BusinessCooperation> list= service.list(Wrappers.<BusinessCooperation>lambdaQuery()
                .eq(Objects.nonNull(query.getTypeId()),BusinessCooperation::getTypeId,query.getTypeId())
                .likeRight(StringUtils.isNotBlank(query.getSearchVal()),BusinessCooperation::getTitle,query.getSearchVal()));
        return pageInfoBaseResult(list);
    }
}

