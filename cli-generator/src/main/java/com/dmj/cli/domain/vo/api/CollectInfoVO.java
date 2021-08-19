package com.dmj.cli.domain.vo.api;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏信息
 * @author zd
 */
@Data
public class CollectInfoVO implements Serializable {

    private Long id;

    private String type;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String collDesc;

    @ApiModelProperty(value = "收藏时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime time;

    @ApiModelProperty(value = "封面地址")
    private String coverUrl;
}
