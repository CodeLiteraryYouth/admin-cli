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
 * @since 2021-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product")
@ApiModel(value="Product对象", description="")
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类型ID")
    @TableField("type_id")
    private Long typeId;

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "作品说明")
    @TableField("product_desc")
    private String productDesc;

    @ApiModelProperty(value = "图片地址")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "视频地址")
    @TableField("video_url")
    private String videoUrl;

    @ApiModelProperty(value = "文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "0:原创 1:转载")
    @TableField("copyright")
    private Integer copyright;

    @ApiModelProperty(value = "0:优雅白 1:高级黑")
    @TableField("match_color")
    private Integer matchColor;

    @ApiModelProperty(value = "0:文章 1:作品")
    @TableField("is_article")
    private Integer isArticle;

    @ApiModelProperty(value = "浏览数")
    @TableField("view_num")
    private Long viewNum;

    @ApiModelProperty(value = "点赞数")
    @TableField("favour_num")
    private Long favourNum;

    @ApiModelProperty(value = "通过理由")
    @TableField("pass_reason")
    private String passReason;

    @ApiModelProperty(value = "false:不屏蔽 true:屏蔽")
    @TableField("is_block")
    private Boolean isBlock;

    @ApiModelProperty(value = "false:不热门 true:热门")
    @TableField("is_hot")
    private Boolean isHot;

    @ApiModelProperty(value = "false:未通过 true:已通过")
    @TableField("is_pass")
    private Boolean isPass;

    @ApiModelProperty(value = "作品排序")
    @TableField("sort")
    private Long sort;

    private String typeName;
    
    
}
