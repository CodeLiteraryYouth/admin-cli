package com.dmj.cli.domain.dto.pay;

import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zd
 * @date 2021/9/15
 * @apiNote
 **/
@Data
public class OrderFormRequest implements Serializable {

    @NonNull
    @ApiModelProperty(value = "商品id")
    private Long skuId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @NonNull
    @ApiModelProperty(value = "1:资源 2:课程 3:视频 4:充值")
    private Integer skuType;

    @NonNull
    @ApiModelProperty(value = "商品原单价(单位：分)")
    private BigDecimal price;

    @NotNull
    @ApiModelProperty(value = "实际商品单价(单位：分)")
    private BigDecimal actualPrice;

    @NotNull
    @ApiModelProperty(value = "购买商品实际数量")
    private Integer num;

    @ApiModelProperty(value = "支付主体")
    private String body;

    @ApiModelProperty(value = "支付超时时间")
    private String timeOut;

    @ApiModelProperty(value = "支付扩展参数(json格式 例如{'storeId': 'A10010'})")
    private JSONObject extras;
}
