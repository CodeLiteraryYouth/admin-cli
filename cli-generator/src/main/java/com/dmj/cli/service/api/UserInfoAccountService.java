package com.dmj.cli.service.api;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserInfoAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
public interface UserInfoAccountService extends IService<UserInfoAccount> {

    /**
     * 更新用户账户
     * @param request
     * @return
     */
    BaseResult updateUserAccount(OrderFormRequest request,String payNo,Integer tradetype);

}
