package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author zd
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value="SysDict对象", description="数据字典表")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "字典类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "字典码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "字典值")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "扩展字段值")
    @TableField("ext_value")
    private String extValue;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    
}
