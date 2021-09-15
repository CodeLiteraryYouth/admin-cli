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
import java.time.LocalDateTime;

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
@TableName("t_order")
@ApiModel(value="TOrder对象", description="")
public class TOrder implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "订单号")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "订单总价格")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "1:现金 2:微信 3:支付宝")
    @TableField("payment_type")
    private Integer paymentType;

    @ApiModelProperty(value = "1:待付款 2:已付款 3:已超时 4:审核中 5:已退款")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "订单创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "支付交易流水号")
    @TableField("trade_no")
    private String tradeNo;
    
    
}
