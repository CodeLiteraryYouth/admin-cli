package com.dmj.cli.common.constant;

/**
 * @author zd
 */
public interface GlobalConstants {

    String FAIL="fail";

    String SUCCESS="success";

    /**
     * 当前记录起始索引
     */
    String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    String ORDER_BY_COLUMN = "orderByColumn";

    String IS_ASC = "isAsc";

    /**
     * RMI 远程方法调用
     */
    String LOOKUP_RMI = "rmi://";

    /**
     * http请求
     */
    String HTTP = "http://";

    /**
     * https请求
     */
    String HTTPS = "https://";

    /**
     * 验证码缓存key值
     */
    String CAPTCHA="captcha_key";

    /**
     * POST请求
     */
    String POST_METHOD="POST";


    /**
     * SpringSecurity权限前缀
     */
    String ROLE_PREFIX="ROLE_";

    /**
     * 简体
     */
    String ZH_CN="zh_CN";

    /**
     * 浏览量key值
     */
    String VIEW_NUM="view_num";

    /**
     * 收藏量
     */
    String COLLECT_NUM="collect_num";

    /**
     * 下载量
     */
    String DOWNLOAD_NUM="download_num";

    /**
     * 资源
     */
    String RESOURCES="resources";

    /**
     * 视频
     */
    String VIDEO="video";


    enum LOGIN_TYPE {
        /**
         * 微信登录
         */
        WX,
        /**
         * 邮箱登录
         */
        EMAIL;
    }
}
