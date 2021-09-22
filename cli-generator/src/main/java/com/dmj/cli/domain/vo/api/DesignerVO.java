package com.dmj.cli.domain.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/22
 * @apiNote
 **/
@Data
public class DesignerVO implements Serializable {

    @ApiModelProperty(value = "用户唯一标识")
    private String sceneId;

    @ApiModelProperty(value = "设计师姓名")
    private String nickName;

    @ApiModelProperty(value = "设计师头像")
    private String avatarUrl;
}
