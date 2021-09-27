package com.dmj.cli.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountItemVO;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
public interface UserInfoService extends IService<UserInfo> {

    List<CountItemVO> listItemVOS(CountQuery query);

}
