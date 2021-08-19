package com.dmj.cli.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import com.dmj.cli.domain.vo.api.UserInfoVO;
import com.dmj.cli.domain.vo.api.VidelLogVO;
import com.dmj.cli.mapper.api.UserCollLogMapper;
import com.dmj.cli.mapper.api.UserVideoLogMapper;
import com.dmj.cli.service.UserService;
import com.dmj.cli.service.api.UserInfoAccountService;
import com.dmj.cli.util.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zd
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserInfoAccountService infoAccountService;

    @Autowired
    private UserCollLogMapper userCollLogMapper;

    @Autowired
    private UserVideoLogMapper userVideoLogMapper;

    @Override
    public BaseResult<UserInfoVO> getUserBySceneId(String sceneId) {
        Assert.notNull(sceneId,"sceneId is null");
        UserInfoVO userInfoVO=new UserInfoVO();
        String user=redisUtils.get(sceneId).toString();
        if (StringUtils.isBlank(user)) {
            return BaseResult.fail(ResultStatusCode.LOGIN_ERROR);
        }
        UserInfo userInfo= JSONUtil.toBean(user,UserInfo.class);
        if (null != userInfo) {
            BeanUtil.copyProperties(userInfo, userInfoVO);
            List<UserInfoAccount> userInfoAccounts=infoAccountService.list(Wrappers.<UserInfoAccount>lambdaQuery().eq(UserInfoAccount::getUserId,userInfo.getId()));
            if (CollectionUtil.isNotEmpty(userInfoAccounts)) {
                userInfoVO.setUserInfoAccounts(userInfoAccounts);
            }
        }
        return BaseResult.success(userInfoVO);
    }

    @Override
    public BaseResult<Map<String, List<CollectInfoVO>>> getCollectInfo(Long userId) {
        Assert.notNull(userId,"userId is null");
        List<CollectInfoVO> collectInfoVOS=userCollLogMapper.listCollectInfos(userId);
        Map<String, List<CollectInfoVO>> result=null;
        if (CollectionUtil.isNotEmpty(collectInfoVOS)) {
            result = collectInfoVOS.stream().collect(Collectors.groupingBy(CollectInfoVO::getType));
        }
        return BaseResult.success(result);
    }

    @Override
    public BaseResult<List<VidelLogVO>> listVideoLog(Long userId) {
        Assert.notNull(userId,"userId is null");
        return BaseResult.success(userVideoLogMapper.listVideoLog(userId));
    }
}
