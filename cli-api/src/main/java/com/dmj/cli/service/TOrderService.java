package com.dmj.cli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
public interface TOrderService extends IService<TOrder> {

    BaseResult<Map<String,String>> getCodeUrl(OrderFormRequest orderFormRequest);

    BaseResult<UserInfoAccount> cashPay(OrderFormRequest request);
}
