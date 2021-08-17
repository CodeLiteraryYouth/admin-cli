package com.dmj.cli.mapper.sys;

import com.dmj.cli.domain.NewsStory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.query.BaseQuery;

import java.util.List;

/**
 * <p>
 * 新闻事迹 Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
public interface NewsStoryMapper extends BaseMapper<NewsStory> {

    List<NewsStory> listNewsStory(BaseQuery query);
}
