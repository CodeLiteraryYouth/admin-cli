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
 * 学员作业
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_work")
@ApiModel(value="UserWork对象", description="学员作业")
public class UserWork extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "周练ID")
    @TableField("job_id")
    private Long jobId;

    @TableField(exist = false)
    private String jobName;

    @ApiModelProperty(value = "作业标题")
    @TableField("work_title")
    private String workTitle;

    @ApiModelProperty(value = "作业描述")
    @TableField("work_desc")
    private String workDesc;

    @ApiModelProperty(value = "作业内容")
    @TableField("work_content")
    private String workContent;

    @ApiModelProperty(value = "0:审批中 1:审批通过")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "浏览数")
    @TableField("view_num")
    private Long viewNum;

    @ApiModelProperty(value = "点赞数")
    @TableField("favour_num")
    private Long favourNum;

    @ApiModelProperty(value = "封面地址")
    @TableField("cover_url")
    private String coverUrl;

    
}
