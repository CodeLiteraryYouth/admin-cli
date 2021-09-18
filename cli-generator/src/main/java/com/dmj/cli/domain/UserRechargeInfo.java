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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户支付记录表
 * </p>
 *
 * @author zd
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_recharge_info")
@ApiModel(value="UserRechargeInfo对象", description="用户支付记录表")
public class UserRechargeInfo implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "1:微信 2:支付宝")
    @TableField("recharge_type")
    private Integer rechargeType;

    @ApiModelProperty(value = "支付流水号")
    @TableField("pay_no")
    private String payNo;

    @ApiModelProperty(value = "1:素材 2:课程")
    @TableField("trade_type")
    private Integer tradeType;

    @ApiModelProperty(value = "充值金额")
    @TableField("recharge_amount")
    private BigDecimal rechargeAmount;

    @ApiModelProperty(value = "充值次数")
    @TableField("recharge_num")
    private Long rechargeNum;

    @ApiModelProperty(value = "交易时间")
    @TableField("trade_time")
    private LocalDateTime tradeTime;
    
    
}
