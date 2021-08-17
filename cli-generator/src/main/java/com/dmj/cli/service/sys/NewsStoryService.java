package com.dmj.cli.service.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.NewsStory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.query.BaseQuery;

import java.util.List;

/**
 * <p>
 * 新闻事迹 服务类
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
public interface NewsStoryService extends IService<NewsStory> {

    List<NewsStory> listNewsStory(BaseQuery query);
}
