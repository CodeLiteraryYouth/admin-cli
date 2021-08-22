package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户账户表
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
@Data
@TableName("user_info_account")
@ApiModel(value="UserInfoAccount对象", description="用户账户表")
public class UserInfoAccount extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "账户类型(VIDEO:观看账户 DOWNLOAD:下载账户)")
    @TableField("account_type")
    private String accountType;

    @ApiModelProperty(value = "账户余额(单位:分)")
    @TableField("account_amount")
    private BigDecimal accountAmount;

    @ApiModelProperty(value = "会员次数")
    @TableField("account_num")
    private Long accountNum;
    
    
}
