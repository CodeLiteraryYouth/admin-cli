package com.dmj.cli.controller;


import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.OrderConstant;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;
import com.dmj.cli.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @Login
    @ApiOperation("获取微信和支付宝二维码")
    @PostMapping("/codeUrl")
    public BaseResult<Map<String,String>> save(@RequestBody OrderFormRequest entity) {
        return service.getCodeUrl(entity);
    }

    @Login
    @ApiOperation("现金支付")
    @PostMapping("/cashPay")
    public BaseResult<UserInfoAccount> cashPay(@RequestBody OrderFormRequest request) {
        return service.cashPay(request);
    }

    @Login
    @ApiOperation("轮询订单是否已支付")
    @GetMapping("/info/{tradeNo}")
    public BaseResult info(@PathVariable String tradeNo) {
        Assert.notNull(tradeNo,"tradeNo is null");
        TOrder tOrder=service.getOne(Wrappers.<TOrder>lambdaQuery().eq(TOrder::getCode,tradeNo));
        if (OrderConstant.OrderStatus.PAYMENT.getCode() ==tOrder.getStatus()) {
            return BaseResult.success();
        }
        return BaseResult.fail();
    }

}

