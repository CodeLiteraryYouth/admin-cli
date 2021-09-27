package com.dmj.cli.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.Product;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountVO;
import com.dmj.cli.service.IndexService;
import com.dmj.cli.service.api.ProductService;
import com.dmj.cli.service.api.UserInfoService;
import com.dmj.cli.service.api.UvCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zd
 * @date 2021/9/27
 * @apiNote
 **/
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    private static final String TODAY_SQL="date_format(create_time,'%Y-%m-%d') = ";

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UvCountService uvCountService;

    @Autowired
    private ProductService productService;

    @Override
    public BaseResult<CountVO> info(CountQuery query) {
        CountVO countVO=new CountVO();
        String today= DateUtil.today();
        Integer registerNum=userInfoService.count(Wrappers.<UserInfo>lambdaQuery()
                .apply(TODAY_SQL+ today));
        countVO.setRegisterNum(registerNum);
        Long uvNum=redisUtils.pfCount(today);
        countVO.setUvNum(uvNum == null ? 0 : uvNum.intValue());
        Integer articleNum=productService.count(Wrappers.<Product>lambdaQuery()
                .eq(Product::getIsArticle,0)
                .apply(TODAY_SQL+ today));
        countVO.setArticleNum(articleNum);
        Integer productNum=productService.count(Wrappers.<Product>lambdaQuery()
                .eq(Product::getIsArticle,1)
                .apply(TODAY_SQL+ today));
        countVO.setProductNum(productNum);
        if (query.getType() == 0 || query.getType() == 1) {
            countVO.setItemVOS(productService.listCountItem(query));
        } else if (query.getType() == 2) {
            countVO.setItemVOS(uvCountService.listCountItem(query));
        } else {
            countVO.setItemVOS(userInfoService.listItemVOS(query));
        }
        return BaseResult.success(countVO);
    }
}
