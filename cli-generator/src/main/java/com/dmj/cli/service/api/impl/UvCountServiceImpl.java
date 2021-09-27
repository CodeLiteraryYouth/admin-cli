package com.dmj.cli.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.domain.UvCount;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountItemVO;
import com.dmj.cli.mapper.api.UvCountMapper;
import com.dmj.cli.service.api.UvCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-09-26
 */
@Service
public class UvCountServiceImpl extends ServiceImpl<UvCountMapper, UvCount> implements UvCountService {

    @Autowired
    private UvCountMapper uvCountMapper;

    @Override
    public List<CountItemVO> listCountItem(CountQuery query) {
        return uvCountMapper.listCountItem(query);
    }
}
