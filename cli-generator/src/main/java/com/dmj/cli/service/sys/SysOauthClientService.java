package com.dmj.cli.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysOauthClient;
import com.dmj.cli.domain.query.sys.OauthClientQuery;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysOauthClientService extends IService<SysOauthClient> {

    BaseResult<PageInfo<SysOauthClient>> pageOauthClient(OauthClientQuery query);
}
