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
 * 用户支付记录表
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_pay_log")
@ApiModel(value="UserPayLog对象", description="用户支付记录表")
public class UserPayLog implements Serializable {

    private static final long serialVersionUID=1L;
    
        @TableId(value = "id", type = IdType.AUTO)
                private Integer id;

        @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
            private Integer userId;

        @ApiModelProperty(value = "支付方式(CASH:现金支付 MEMBER:会员支付)")
    @TableField("pay_type")
            private String payType;

        @ApiModelProperty(value = "支付流水号")
    @TableField("pay_no")
            private String payNo;

        @ApiModelProperty(value = "交易类型(RECHARGE:会员充值 RESOURCES_PAY:资源支付 COURSE_PAY:课程支付 RESOURCES_REFUND:资源退款 COURSE_REFUND:课程退款 )")
    @TableField("trade_type")
            private String tradeType;

        @ApiModelProperty(value = "交易ID")
    @TableField("trade_id")
            private Integer tradeId;

        @ApiModelProperty(value = "交易金额(单位:分)")
    @TableField("trade_amount")
            private Integer tradeAmount;

        @ApiModelProperty(value = "会员交易次数")
    @TableField("trade_num")
            private Integer tradeNum;

        @ApiModelProperty(value = "交易时间")
    @TableField("trade_time")
            private LocalDateTime tradeTime;

        @ApiModelProperty(value = "交易商品是否属于VIP")
    @TableField("trade_vip")
            private Boolean tradeVip;
    
    
}
