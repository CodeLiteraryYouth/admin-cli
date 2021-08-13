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
 * 课程
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
@ApiModel(value="Course对象", description="课程")
public class Course extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型ID")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty(value = "课程标题")
    @TableField("course_title")
    private String courseTitle;

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "课程文案")
    @TableField("text")
    private String text;

    @ApiModelProperty(value = "课程链接")
    @TableField("course_url")
    private String courseUrl;

    @ApiModelProperty(value = "课程类型(TEXT:文案类型 URL:链接类型)")
    @TableField("course_type")
    private String courseType;

    @ApiModelProperty(value = "false: free true: VIP")
    @TableField("free")
    private Boolean free;

}
