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
import java.util.Date;

/**
 * <p>
 * 软件下载
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("software_download")
@ApiModel(value="SoftwareDownload对象", description="软件下载")
public class SoftwareDownload implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "下载类型ID")
    @TableField("type_id")
    private Long typeId;

    @ApiModelProperty(value = "软件名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "软件描述")
    @TableField("download_desc")
    private String downloadDesc;

    @ApiModelProperty(value = "软件版本")
    @TableField("version")
    private String version;

    @ApiModelProperty(value = "软件发布时间")
    @TableField("publish_time")
    private Date publishTime;

    @ApiModelProperty(value = "软件大小")
    @TableField("size")
    private String size;

    @ApiModelProperty(value = "软件下载信息")
    @TableField("content")
    private String content;
    
    
}
