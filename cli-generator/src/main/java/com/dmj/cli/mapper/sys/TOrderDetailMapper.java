package com.dmj.cli.mapper.sys;

import com.dmj.cli.domain.TOrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.vo.api.PayLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
public interface TOrderDetailMapper extends BaseMapper<TOrderDetail> {

    List<PayLogVO> listPayLog(@Param("userId") Long userId,@Param("skuType") Long skuType);
}
