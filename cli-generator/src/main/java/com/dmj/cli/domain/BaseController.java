package com.dmj.cli.domain;

import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.core.PageDomain;
import com.dmj.cli.core.TableSupport;
import com.dmj.cli.sql.SqlUtil;
import com.dmj.cli.util.ServletUtils;
import com.dmj.cli.util.str.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zd
 */
public class BaseController {


    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            if (StringUtils.isEmpty(orderBy)) {
                PageHelper.startPage(pageNum, pageSize);
            } else {
                PageHelper.startPage(pageNum,pageSize,orderBy);
            }
        }
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    protected String getToken() {
        return getRequest().getHeader(AuthConstants.LOGIN_TOKEN_KEY);
    }

    protected <T> BaseResult<PageInfo<List<T>>> pageInfoBaseResult(List<T> list) {
        PageInfo<T> pageInfo=new PageInfo<T>(list);
        return BaseResult.success(pageInfo);
    }


    protected BaseResult success() {
        return BaseResult.success();
    }

    protected BaseResult fail() {
        return BaseResult.fail();
    }

    protected BaseResult success(Object msg) {
        return BaseResult.success(msg);
    }

    protected BaseResult fail(Object msg) {
        return BaseResult.fail(msg);
    }
}
