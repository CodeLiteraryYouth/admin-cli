package com.dmj.cli.service.api.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.UserRechargeInfo;
import com.dmj.cli.domain.dto.pay.OrderFormRequest;
import com.dmj.cli.mapper.api.UserInfoAccountMapper;
import com.dmj.cli.service.api.UserInfoAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.service.api.UserRechargeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
@Service
@Slf4j
public class UserInfoAccountServiceImpl extends ServiceImpl<UserInfoAccountMapper, UserInfoAccount> implements UserInfoAccountService {

    @Autowired
    private UserInfoAccountService userInfoAccountService;

    @Autowired
    private UserRechargeInfoService userRechargeInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult updateUserAccount(OrderFormRequest request,String payNo,Integer tradeType) {
        if (Objects.isNull(request.getUserId())) {
            return BaseResult.fail(ResultStatusCode.LOGIN_ERROR);
        }
        UserInfoAccount account=userInfoAccountService.getOne(Wrappers.<UserInfoAccount>lambdaQuery()
                .eq(UserInfoAccount::getUserId,request.getUserId())
                .eq(UserInfoAccount::getAccountType,request.getSkuType()));
        if (Objects.isNull(account)) {
            account=new UserInfoAccount();
            account.setUserId(request.getUserId());
            account.setAccountType(request.getSkuType());
            account.setAccountAmount(request.getActualPrice());
            account.setAccountNum(request.getNum());
            account.setCreateTime(DateUtil.date());
            account.setCreator(request.getUserId().toString());
            userInfoAccountService.save(account);
        } else {
            account.setAccountAmount(request.getActualPrice());
            account.setAccountNum(request.getNum());
            account.setUpdateTime(DateUtil.date());
            account.setUpdater(request.getUserId().toString());
            userInfoAccountService.updateById(account);
        }
        UserRechargeInfo userRechargeInfo=new UserRechargeInfo();
        userRechargeInfo.setUserId(request.getUserId());
        userRechargeInfo.setRechargeNum(request.getNum());
        userRechargeInfo.setRechargeType(request.getSkuType());
        userRechargeInfo.setRechargeAmount(request.getActualPrice());
        userRechargeInfo.setTradeTime(LocalDateTime.now());
        userRechargeInfo.setPayNo(payNo);
        userRechargeInfo.setTradeType(tradeType);
        userRechargeInfoService.save(userRechargeInfo);
        return BaseResult.success();
    }
}
