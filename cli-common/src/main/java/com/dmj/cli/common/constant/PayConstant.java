package com.dmj.cli.common.constant;

/**
 * @author zd
 * @date 2021/9/14
 * @apiNote
 **/
public interface PayConstant {

    /**
     * 支付宝成功编码
     */
    String Ali_Success_Code="10000";

    /**
     * 支付宝成功消息
     */
    String getAli_Success_Msg="Success";

    enum SkuType {

        RESOURCES(1,"资源"),
        COURSE(2,"课程"),
        VIDEO(3,"视频"),
        RECHARGE(4,"充值");

        private int id;
        private String type;

        SkuType(int id,String type) {
            this.id=id;
            this.type=type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    enum TradeChannel {
        Wx(1),
        Ali(2),
        Cash(3);


        private int code;

        TradeChannel(int code) {
            this.code=code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /**
     * 交易方式
     */
    enum TradeType {
        WxScanPay,
        WxAppPay,
        WxWebPay,
        AliScanPay,
        AliWebPay,
        AliAppPay
    }
}
