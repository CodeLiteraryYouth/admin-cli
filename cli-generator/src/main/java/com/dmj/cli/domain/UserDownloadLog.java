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
@TableName("user_download_log")
@ApiModel(value="UserDownloadLog对象", description="")
public class UserDownloadLog implements Serializable {

    private static final long serialVersionUID=1L;
    
        @TableId(value = "id", type = IdType.AUTO)
                private Integer id;

        @ApiModelProperty(value = "下载ID")
    @TableField("download_id")
            private Integer downloadId;

        @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
            private Integer userId;

        @ApiModelProperty(value = "下载时间")
    @TableField("download_time")
            private LocalDateTime downloadTime;
    
    
}
