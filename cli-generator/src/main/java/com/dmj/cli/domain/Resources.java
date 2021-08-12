package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resources")
@ApiModel(value="Resources对象", description="")
public class Resources extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "资源编号")
    @TableField("resources_no")
    private String resourcesNo;

    @ApiModelProperty(value = "资源大小")
    @TableField("resources_size")
    private Integer resourcesSize;

    @ApiModelProperty(value = "资源格式")
    @TableField("resources_format")
    private String resourcesFormat;

    @ApiModelProperty(value = "资源用途")
    @TableField("resources_use")
    private String resourcesUse;

    @ApiModelProperty(value = "文本地址")
    @TableField("text")
    private String text;

    @ApiModelProperty(value = "资源地址")
    @TableField("resources_url")
    private String resourcesUrl;

    @ApiModelProperty(value = "false:free true:VIP")
    @TableField("free")
    private Boolean free;

}
