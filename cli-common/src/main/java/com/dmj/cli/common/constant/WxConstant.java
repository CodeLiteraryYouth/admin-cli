package com.dmj.cli.common.constant;

/**
 * @author zd
 */
public interface WxConstant {

    /**
     * 微信公众号APPID
     */
    String APP_ID="wxb84e88d8717f7173";

    /**
     * 微信公众号的APPSECRET
     */
    String APP_SECRET="f5e9721a1964eeb7e641c6e4967e5285";


    /**
     * access_token的key值
     */
    String SERVER_TOKEN="wylzd950823";

    /**
     * 已关注公众号
     */
    int SUBSCRIBE=1;


    /**
     * 获取access_token的URL
     */
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 获取session_key和openId
     */
    String GET_SESSIONKEY_AND_OPENID="https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 统一下单URL
     */
    String UNIFIED_ORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 退款URL
     */
    String REFUND_URL="https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 获取二维码图片的URL
     */
    String QRCODE_URL="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";



    /**
     * 获取用户信息
     */
    String USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info";

    enum ACTION {
        /**
         * 临时的整型参数值
         */
        QR_SCENE,
        /**
         * 临时的字符串参数值
         */
        QR_STR_SCENE,
        /**
         * 永久的整型参数值
         */
        QR_LIMIT_SCENE,
        /**
         * 永久的字符串参数值
         */
        QR_LIMIT_STR_SCENE;
    }

    enum GRANT_TYPE {
        /**
         * 支付时候使用的编码
         */
        authorization_code,
        /**
         * 获取access_token
         */
        client_credential;
    }

    enum EVENT_TYPE {
        /**
         * 关注
         */
        subscribe,
        /**
         * 取消关注
         */
        unsubscrib,
        /**
         * 浏览
         */
        SCAN;
    }

    enum TRADE_TYPE {
        /**
         * 现金支付
         */
        RECHARGE,
        /**
         * 资源支付
         */
        RESOURCES_PAY,
        /**
         * 课程支付
         */
        COURSE_PAY,
        /**
         * 资源退费
         */
        RESOURCES_REFUND,
        /**
         * 课程退费
         */
        COURSE_REFUND;
    }



}
