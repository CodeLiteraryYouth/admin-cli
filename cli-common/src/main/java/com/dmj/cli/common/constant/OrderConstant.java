package com.dmj.cli.common.constant;

/**
 * @author zd
 * @date 2021/9/15
 * @apiNote
 **/
public interface OrderConstant {

    /**
     * 订单状态
     */
    enum OrderStatus {
        //待付款
        PREPARE(1),
        //已付款
        PAYMENT(2),
        //付款超时
        TIMEOUT(3),
        //退款审核
        REVIEW(4),
        //已退款
        REFUND(5);
        private int code;

        private OrderStatus(int code) {
            this.code=code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
