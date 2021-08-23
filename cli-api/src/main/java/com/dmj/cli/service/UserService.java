package com.dmj.cli.service;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.query.api.UserPayLogQuery;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import com.dmj.cli.domain.vo.api.UserInfoVO;
import com.dmj.cli.domain.vo.api.UserPayLogVO;
import com.dmj.cli.domain.vo.api.VidelLogVO;

import java.util.List;
import java.util.Map;

/**
 * @author zd
 */
public interface UserService {

    /**
     * 根据场景ID获取用户信息记录
     * @param sceneId
     * @return
     */
     BaseResult<UserInfoVO> getUserBySceneId(String sceneId);


    /**
     * 查询收藏信息
     * @param userId
     * @return
     */
     BaseResult<Map<String, List<CollectInfoVO>>> getCollectInfo(Long userId);

    /**
     * 查询观看记录
     * @param userId
     * @return
     */
     BaseResult<List<VidelLogVO>> listVideoLog(Long userId);

    /**
     * 查询用户支付记录
     * @param query
     * @return
     */
     BaseResult<List<UserPayLogVO>> listPayLogs(UserPayLogQuery query);
}
