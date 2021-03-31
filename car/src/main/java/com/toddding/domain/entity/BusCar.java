package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * bus_car
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.BusCar车辆表")
@Data
public class BusCar implements Serializable {
    /**
     * 汽车ID
     */
    @ApiModelProperty(value="汽车ID")
    private Integer id;

    /**
     * 车牌号
     */
    @ApiModelProperty(value="车牌号")
    private String num;

    /**
     * 类型  1 轿车  2 SUV  3 跑车
     */
    @ApiModelProperty(value="类型  1 轿车  2 SUV  3 跑车")
    private Integer type;

    /**
     * 颜色
     */
    @ApiModelProperty(value="颜色")
    private String color;

    /**
     * 价格
     */
    @ApiModelProperty(value="价格")
    private Integer price;

    /**
     * 出租金额
     */
    @ApiModelProperty(value="出租金额")
    private Integer rentPrice;

    /**
     * 押金
     */
    @ApiModelProperty(value="押金")
    private Integer deposit;

    /**
     * 出租状态 1 未出租  2 已出租
     */
    @ApiModelProperty(value="出租状态 1 未出租  2 已出租")
    private Integer isRent;

    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String descp;

    /**
     * 汽车图片
     */
    @ApiModelProperty(value="汽车图片")
    private String img;

    /**
     * 版本号
     */
    @ApiModelProperty(value="版本号")
    private Integer version;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}