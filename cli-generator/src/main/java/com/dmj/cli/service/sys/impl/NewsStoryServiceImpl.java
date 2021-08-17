package com.dmj.cli.service.sys.impl;

import com.dmj.cli.domain.NewsStory;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.mapper.sys.NewsStoryMapper;
import com.dmj.cli.service.sys.NewsStoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 新闻事迹 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Service
public class NewsStoryServiceImpl extends ServiceImpl<NewsStoryMapper, NewsStory> implements NewsStoryService {

    @Autowired
    private NewsStoryMapper newsStoryMapper;

    @Override
    public List<NewsStory> listNewsStory(BaseQuery query) {
        return newsStoryMapper.listNewsStory(query);
    }
}
