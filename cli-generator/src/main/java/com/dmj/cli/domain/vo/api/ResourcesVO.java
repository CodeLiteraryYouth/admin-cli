package com.dmj.cli.domain.vo.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dmj.cli.domain.BaseEntity;
import com.dmj.cli.domain.ResourcesType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-08-12
 */
@Data
public class ResourcesVO extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "浏览次数")
    private Long viewNum;

    @ApiModelProperty(value = "收藏次数")
    private Long collectNum;

    private List<ResourcesType> resourcesTypes;

}
