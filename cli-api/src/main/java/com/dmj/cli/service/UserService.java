package com.dmj.cli.service;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import com.dmj.cli.domain.vo.api.PayLogVO;
import com.dmj.cli.domain.vo.api.UserInfoVO;
import com.dmj.cli.domain.vo.api.VideoLogVO;

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
     * @param sceneId
     * @return
     */
     BaseResult<Map<String, List<CollectInfoVO>>> getCollectInfo(String sceneId);

    /**
     * 查询观看记录
     * @param sceneId
     * @return
     */
     BaseResult<List<VideoLogVO>> listVideoLog(String sceneId);

    /**
     * 查询支付记录
     * @param skuType
     * @return
     */
     BaseResult<List<PayLogVO>> listPayLog(String sceneId,Long skuType);

}
