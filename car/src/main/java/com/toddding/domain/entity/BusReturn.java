package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * bus_return
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.BusReturn还车记录表")
@Data
public class BusReturn implements Serializable {
    /**
     * 还车记录ID
     */
    @ApiModelProperty(value="还车记录ID")
    private Integer id;

    /**
     * 车牌号
     */
    @ApiModelProperty(value="车牌号")
    private String num;

    /**
     * 出租记录ID
     */
    @ApiModelProperty(value="出租记录ID")
    private Integer rentId;

    /**
     * 租车天数
     */
    @ApiModelProperty(value="租车天数")
    private Integer rentDays;

    /**
     * 还车时间
     */
    @ApiModelProperty(value="还车时间")
    private String returnTime;

    /**
     * 租金
     */
    @ApiModelProperty(value="租金")
    private Integer rentPrice;

    /**
     * 赔付金额
     */
    @ApiModelProperty(value="赔付金额")
    private Integer payMoney;

    /**
     * 问题
     */
    @ApiModelProperty(value="问题")
    private String problem;

    /**
     * 总金额
     */
    @ApiModelProperty(value="总金额")
    private Integer totalMoney;

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

    private static final long serialVersionUID = 1L;
}