package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.SysOauthClient;
import com.dmj.cli.domain.query.sys.OauthClientQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
public interface SysOauthClientMapper extends BaseMapper<SysOauthClient> {

    List<SysOauthClient> listOauthClients(OauthClientQuery query);
}
