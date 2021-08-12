package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("user_coll_log")
@ApiModel(value="UserCollLog对象", description="")
public class UserCollLog implements Serializable {

    private static final long serialVersionUID=1L;
    
        @TableId(value = "id", type = IdType.AUTO)
                private Integer id;

        @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
            private Integer userId;

        @ApiModelProperty(value = "资源、课程等ID")
    @TableField("collect_id")
            private Integer collectId;

        @ApiModelProperty(value = "收藏类型(resources:资源 course:课程)")
    @TableField("collect_type")
            private String collectType;

        @ApiModelProperty(value = "收藏时间")
    @TableField("collect_time")
            private LocalDateTime collectTime;
    
    
}
