package com.dmj.cli.core;

import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.util.ServletUtils;

/**
 * 表格数据处理
 * 
 * @author zd
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(GlobalConstants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(GlobalConstants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(GlobalConstants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(GlobalConstants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
