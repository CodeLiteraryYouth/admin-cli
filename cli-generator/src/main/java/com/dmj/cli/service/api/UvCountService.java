package com.dmj.cli.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.UvCount;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountItemVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-09-26
 */
public interface UvCountService extends IService<UvCount> {

    List<CountItemVO> listCountItem(CountQuery query);
}
