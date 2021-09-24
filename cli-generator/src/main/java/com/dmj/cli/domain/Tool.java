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
 * 
 * </p>
 *
 * @author zd
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tool")
@ApiModel(value="Tool对象", description="")
public class Tool extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类型id")
    @TableField("type_id")
    private Long typeId;

    @ApiModelProperty(value = "工具名称")
    @TableField("tool_title")
    private String toolTitle;

    @ApiModelProperty(value = "工具描述")
    @TableField("tool_desc")
    private String toolDesc;

    @ApiModelProperty(value = "工具图标")
    @TableField("tool_icon")
    private String toolIcon;

    @ApiModelProperty(value = "工具地址")
    @TableField("tool_url")
    private String toolUrl;

    private String typeName;
    
    
}
