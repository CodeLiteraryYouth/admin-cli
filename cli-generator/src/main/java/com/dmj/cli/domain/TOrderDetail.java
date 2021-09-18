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
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-09-15
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_detail")
@ApiModel(value="TOrderDetail对象", description="")
public class TOrderDetail implements Serializable {

    private static final long serialVersionUID=1L;
    
    @ApiModelProperty(value = "订单id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "商品id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "1:资源 2:课程 3:视频")
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
    
    
}
