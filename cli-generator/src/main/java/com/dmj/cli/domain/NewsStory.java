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
 * 新闻事迹
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("news_story")
@ApiModel(value="NewsStory对象", description="新闻事迹")
public class NewsStory extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "描述")
    @TableField("desc")
    private String desc;

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "浏览数")
    @TableField("view_num")
    private Long viewNum;

    @ApiModelProperty(value = "新闻内容")
    @TableField("content")
    private String content;
    
    
}
