package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountVO;
import com.dmj.cli.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zd
 * @date 2021/9/27
 * @apiNote
 **/
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;


    @GetMapping("/count")
    public BaseResult<CountVO> count(@ModelAttribute CountQuery query) {
        return indexService.info(query);
    }
}
