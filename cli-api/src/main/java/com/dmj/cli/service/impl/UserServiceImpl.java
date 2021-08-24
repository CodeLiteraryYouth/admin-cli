package com.dmj.cli.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.WxConstant;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.domain.UserPayLog;
import com.dmj.cli.domain.query.api.UserPayLogQuery;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import com.dmj.cli.domain.vo.api.UserInfoVO;
import com.dmj.cli.domain.vo.api.UserPayLogVO;
import com.dmj.cli.domain.vo.api.VidelLogVO;
import com.dmj.cli.mapper.api.UserCollLogMapper;
import com.dmj.cli.mapper.api.UserVideoLogMapper;
import com.dmj.cli.service.UserService;
import com.dmj.cli.service.api.CourseService;
import com.dmj.cli.service.api.ResourcesService;
import com.dmj.cli.service.api.UserInfoAccountService;
import com.dmj.cli.service.api.UserPayLogService;
import com.dmj.cli.util.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
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

    @Autowired
    private UserPayLogService userPayLogService;

    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private CourseService courseService;

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

    @Override
    public BaseResult<List<UserPayLogVO>> listPayLogs(UserPayLogQuery query) {
        Assert.notNull(query,"bad request");
        Assert.notNull(query.getUserId(),"userId is null");
        Assert.notNull(query.getTradeType(),"tradeType is null");
        List<UserPayLogVO> userPayLogVOS=new ArrayList<>();
        List<UserPayLog> userPayLogs=userPayLogService.list(Wrappers.<UserPayLog>lambdaQuery()
                .eq(UserPayLog::getUserId,query.getUserId())
                .eq(UserPayLog::getTradeType,query.getTradeType()));
        userPayLogs.stream().map(userPayLog -> {
            UserPayLogVO userPayLogVO=new UserPayLogVO();
            BeanUtil.copyProperties(userPayLog,userPayLogVO);
            if (WxConstant.TRADE_TYPE.RESOURCES_PAY.name().equals(query.getTradeType())) {
                userPayLogVO.setResources(resourcesService.getById(userPayLog.getTradeId()));
            } else {
                userPayLogVO.setCourse(courseService.getById(userPayLog.getTradeId()));
            }
            userPayLogVOS.add(userPayLogVO);
            return userPayLog;
        });
        return BaseResult.success(userPayLogVOS);
    }
}
