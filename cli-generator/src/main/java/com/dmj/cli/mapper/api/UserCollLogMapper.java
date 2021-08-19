package com.dmj.cli.mapper.api;

import com.dmj.cli.domain.UserCollLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
public interface UserCollLogMapper extends BaseMapper<UserCollLog> {

    List<CollectInfoVO> listCollectInfos(@Param("userId") Long userId);


}
