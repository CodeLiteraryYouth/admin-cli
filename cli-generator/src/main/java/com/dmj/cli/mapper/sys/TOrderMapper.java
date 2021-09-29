package com.dmj.cli.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.query.sys.TOrderQuery;
import com.dmj.cli.domain.vo.sys.TOrderVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    List<TOrderVO> listOrder(TOrderQuery query);
}
