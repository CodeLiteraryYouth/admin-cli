package com.dmj.cli.task;

import cn.hutool.core.date.DateUtil;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.UvCount;
import com.dmj.cli.service.api.UvCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zd
 * @date 2021/9/26
 * @apiNote
 **/
@Component("uvCount")
public class UVCountTask {

    @Autowired
    private UvCountService uvCountService;

    @Autowired
    private RedisUtils redisUtils;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void save() {
        String key= DateUtil.today();
        Long count=redisUtils.pfCount(key);
        UvCount uvCount=UvCount.builder().build()
                .setUvNum(count)
                .setCreateTime(DateUtil.date());
        uvCountService.save(uvCount);
    }
}
