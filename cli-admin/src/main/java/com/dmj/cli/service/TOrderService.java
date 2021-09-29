package com.dmj.cli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.query.sys.TOrderQuery;
import com.dmj.cli.domain.vo.sys.TOrderVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
public interface TOrderService extends IService<TOrder> {

    List<TOrderVO> listOrder(TOrderQuery query);
}
