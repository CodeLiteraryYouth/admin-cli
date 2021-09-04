package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 企业合作
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("business_cooperation")
@ApiModel(value="BusinessCooperation对象", description="企业合作")
public class BusinessCooperation extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("type_id")
    private Long typeId;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "描述")
    @TableField("desc")
    private String desc;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "合作标签")
    @TableField("tags")
    private String tags;

    @ApiModelProperty(value = "浏览数")
    @TableField("view_num")
    private Integer viewNum;

    @ApiModelProperty(value = "点赞数")
    @TableField("favour_num")
    private Integer favourNum;

    @ApiModelProperty(value = "收藏数")
    @TableField("collect_num")
    private Integer collectNum;

    
}
