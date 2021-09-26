package com.dmj.cli.domain.vo.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zd
 * @date 2021/9/26
 * @apiNote
 **/
@Data
public class PayLogVO implements Serializable {

    @ApiModelProperty(value = "订单id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "商品id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "1:资源 2:课程")
    @TableField("sku_type")
    private Integer skuType;

    @ApiModelProperty(value = "商品原价")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "实际支付价格")
    @TableField("actual_price")
    private BigDecimal actualPrice;

    @ApiModelProperty(value = "购买商品实际数量")
    @TableField("num")
    private Long num;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面地址")
    private String coverUrl;

    @ApiModelProperty(value = "支付时间")
    private Date payDate;
}
