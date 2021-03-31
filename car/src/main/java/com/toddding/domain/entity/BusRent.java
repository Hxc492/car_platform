package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * bus_rent
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.BusRent出租记录表")
@Data
public class BusRent implements Serializable {
    /**
     * 出租记录ID
     */
    @ApiModelProperty(value="出租记录ID")
    private Integer id;

    /**
     * 车牌号
     */
    @ApiModelProperty(value="车牌号")
    private String num;

    /**
     * 车型  1 轿车 2 SUV  3跑车
     */
    @ApiModelProperty(value="车型  1 轿车 2 SUV  3跑车")
    private Integer type;

    /**
     * 租金
     */
    @ApiModelProperty(value="租金")
    private Integer rentPrice;

    /**
     * 押金
     */
    @ApiModelProperty(value="押金")
    private Integer deposit;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    private String name;

    /**
     * 客户身份证号
     */
    @ApiModelProperty(value="客户身份证号")
    private String idCard;

    /**
     * 计划租车开始时间
     */
    @ApiModelProperty(value="计划租车开始时间")
    private String beginTime;

    /**
     * 计划租车的结束时间
     */
    @ApiModelProperty(value="计划租车的结束时间")
    private String endTime;

    /**
     * 状态  1 未还车  2 已还车
     */
    @ApiModelProperty(value="状态  1 未还车  2 已还车")
    private Integer flag;

    /**
     * 业务员ID
     */
    @ApiModelProperty(value="业务员ID")
    private Integer userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}