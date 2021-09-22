package com.dmj.cli.service.impl.pay;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.dto.pay.PayRequest;
import com.dmj.cli.domain.dto.pay.PayResponse;
import com.dmj.cli.domain.dto.pay.RefundResponse;
import com.dmj.cli.service.PayService;
import com.dmj.cli.service.TOrderService;
import com.dmj.cli.service.api.UserInfoAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

/**
 * @author zd
 * @date 2021/9/22
 * @apiNote 现金支付
 **/
@Service
@Slf4j
public class CashPay implements PayService {

    @Autowired
    private UserInfoAccountService accountService;

    @Autowired
    private TOrderService orderService;

    /**
     * 1、判断用户账户是否存在
     * 2、判断账户余额是否充足
     * 3、完成账户的扣减并更改订单状态
     * @param params
     * @return
     */
    @Override
    @Transactional
    public PayResponse pay(PayRequest params) {
        JSONObject extras=params.getExtras();
        if (extras == null) {
            return (PayResponse) PayResponse.fail("账户类型为空");
        }
        Integer skuType = extras.getInt("skuType");
        UserInfoAccount account=accountService.getOne(Wrappers.<UserInfoAccount>lambdaQuery()
                .eq(Objects.nonNull(params.getUserId()),UserInfoAccount::getUserId,params.getUserId())
                .eq(Objects.nonNull(skuType),UserInfoAccount::getAccountType,skuType));
        if (account == null) {
            return (PayResponse) PayResponse.fail("不存在该账户，请前去创建新账户");
        }
        account.setAccountAmount(NumberUtil.sub(NumberUtil.toStr(account.getAccountAmount()),params.getTotalAmount()));
        //account.setAccountNum(NumberUtil.sub(account.getAccountNum(),params.get));
        return null;
    }

    @Override
    public RefundResponse refund(Map<String, String> params) {
        return null;
    }
}
