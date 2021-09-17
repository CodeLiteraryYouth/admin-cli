package com.dmj.cli.domain.vo.sys;

import com.dmj.cli.domain.BusinessCooperation;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/17
 * @apiNote
 **/
@Data
public class BusinessCooperationVO extends BusinessCooperation implements Serializable {

    private String typeName;
}
