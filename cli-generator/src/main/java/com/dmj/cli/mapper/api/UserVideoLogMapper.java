package com.dmj.cli.mapper.api;

import com.dmj.cli.domain.UserVideoLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.vo.api.VideoLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
public interface UserVideoLogMapper extends BaseMapper<UserVideoLog> {

    /**
     * 查询观看记录
     * @param userId
     * @return
     */
    List<VideoLogVO> listVideoLog(@Param("userId") Long userId);
}
