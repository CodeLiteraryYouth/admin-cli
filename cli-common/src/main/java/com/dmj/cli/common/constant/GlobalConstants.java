package com.dmj.cli.common.constant;

/**
 * @author zd
 */
public interface GlobalConstants {

    Integer STATUS_YES = 1;
    Integer STATUS_NO = 0;

    String DEFAULT_USER_PASSWORD = "123456";

    String ROOT_ROLE_CODE = "ROOT";

    String URL_PERM_ROLES_KEY = "system:perm_roles_rule:url:";
    String BTN_PERM_ROLES_KEY = "system:perm_roles_rule:btn:";

    String APP_API_PATTERN="/*/app-api/**";

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


}
