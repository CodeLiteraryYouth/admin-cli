package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-09-26
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("uv_count")
@ApiModel(value="UvCount对象", description="")
public class UvCount implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "日期")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "统计数")
    @TableField("uv_num")
    private Long uvNum;
    
    
}
