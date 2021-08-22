package com.dmj.cli.domain.vo.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dmj.cli.domain.Course;
import com.dmj.cli.domain.Resources;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserPayLogVO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

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
    private Long tradeId;

    @ApiModelProperty(value = "交易金额(单位:分)")
    @TableField("trade_amount")
    private BigDecimal tradeAmount;

    @ApiModelProperty(value = "会员交易次数")
    @TableField("trade_num")
    private Long tradeNum;

    @ApiModelProperty(value = "交易时间")
    @TableField("trade_time")
    private LocalDateTime tradeTime;

    private Resources resources;

    private Course course;
}
