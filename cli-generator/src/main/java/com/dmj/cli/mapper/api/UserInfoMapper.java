package com.dmj.cli.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountItemVO;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<CountItemVO> listCountItem(CountQuery query);
}
