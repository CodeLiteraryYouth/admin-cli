package com.dmj.cli.controller.sys;


import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.sys.TOrderQuery;
import com.dmj.cli.domain.vo.sys.TOrderVO;
import com.dmj.cli.service.TOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
@RestController
@RequestMapping("order")
@Api(tags = "订单")
public class TOrderController extends BaseController {

    @Autowired
    private TOrderService service;

    @GetMapping("/list")
    public BaseResult<PageInfo<List<TOrderVO>>> list(@ModelAttribute TOrderQuery query) {
        startPage();
        List<TOrderVO> tOrderVOS=service.listOrder(query);
        return pageInfoBaseResult(tOrderVOS);
    }

}

