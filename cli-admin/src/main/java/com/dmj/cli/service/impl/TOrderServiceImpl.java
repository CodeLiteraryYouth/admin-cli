package com.dmj.cli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmj.cli.domain.Course;
import com.dmj.cli.domain.Resources;
import com.dmj.cli.domain.TOrder;
import com.dmj.cli.domain.query.sys.TOrderQuery;
import com.dmj.cli.domain.vo.sys.TOrderVO;
import com.dmj.cli.mapper.sys.TOrderMapper;
import com.dmj.cli.service.TOrderService;
import com.dmj.cli.service.api.CourseService;
import com.dmj.cli.service.api.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ResourcesService resourcesService;


    @Override
    public List<TOrderVO> listOrder(TOrderQuery query) {
        List<TOrderVO> tOrders=tOrderMapper.listOrder(query);
        tOrders.forEach(item -> {
            switch (item.getSkuType()) {
                case 1:
                    Resources resources=resourcesService.getById(item.getSkuId());
                    item.setTitle(resources.getTitle());
                    break;
                case 2:
                    Course course=courseService.getById(item.getSkuId());
                    item.setTitle(course.getCourseTitle());
                    break;
            }
        });
        return tOrders;
    }
}
