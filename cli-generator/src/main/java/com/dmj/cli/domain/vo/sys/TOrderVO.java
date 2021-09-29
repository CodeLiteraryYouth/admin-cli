package com.dmj.cli.domain.vo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zd
 * @date 2021/9/29
 * @apiNote
 **/
@Data
public class TOrderVO implements Serializable {

    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    private String userName;

    @ApiModelProperty(value = "订单号")
    private String code;

    @ApiModelProperty(value = "订单总价格")
    private BigDecimal amount;

    @ApiModelProperty(value = "1:现金 2:微信 3:支付宝")
    private Integer paymentType;

    @ApiModelProperty(value = "1:待付款 2:已付款 3:已超时 4:审核中 5:已退款")
    private Integer status;

    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "支付交易流水号")
    private String tradeNo;

    @ApiModelProperty(value = "商品id")
    private Long skuId;

    private String title;

    @ApiModelProperty(value = "1:资源 2:课程")
    private Integer skuType;

    @ApiModelProperty(value = "购买商品实际数量")
    private Long num;
}
