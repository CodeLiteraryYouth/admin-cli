package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author zd
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_config")
@ApiModel(value="SysConfig对象", description="系统配置信息表")
public class SysConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "key")
    @TableField("param_key")
    private String paramKey;

    @ApiModelProperty(value = "value")
    @TableField("param_value")
    private String paramValue;

    @ApiModelProperty(value = "状态   0：隐藏   1：显示")
    @TableField("status")
    private Integer status;

    private String remark;
    
    
}
