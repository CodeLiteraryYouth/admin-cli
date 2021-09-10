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
 * 周练
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("practices_job")
@ApiModel(value="PracticesJob对象", description="周练")
public class PracticesJob extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "周练标题")
    @TableField("job_title")
    private String jobTitle;

    @ApiModelProperty(value = "周练描述")
    @TableField("job_desc")
    private String jobDesc;

    @ApiModelProperty(value = "周练标签")
    @TableField("job_tag")
    private String jobTag;

    @ApiModelProperty(value = "周练内容")
    @TableField("job_content")
    private String jobContent;

    @ApiModelProperty(value = "作品数量")
    @TableField("job_num")
    private Integer jobNum;

    @ApiModelProperty(value = "周练状态(1:学习中 2:已结束)")
    @TableField("job_status")
    private Integer jobStatus;

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    @ApiModelProperty(value = "浏览量")
    @TableField("view_num")
    private Long viewNum;

    
}
