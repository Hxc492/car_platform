package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * bus_customer
 * @author 
 */
@ApiModel(value="客户表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomer implements Serializable {
    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID")
    private Integer id;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    private String name;

    /**
     * 客户电话
     */
    @ApiModelProperty(value="客户电话")
    private String phone;

    /**
     * 省
     */
    @ApiModelProperty(value="省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value="市")
    private String city;

    /**
     * 客户地址
     */
    @ApiModelProperty(value="客户地址")
    private String address;

    /**
     * 驾驶证号
     */
    @ApiModelProperty(value="驾驶证号")
    private String drivingLicense;

    /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号")
    private String idCard;

    /**
     * 性别 1 男 2女
     */
    @ApiModelProperty(value="性别 1 男 2女")
    private Integer sex;

    /**
     * 状态 1 正常 2 禁用
     */
    @ApiModelProperty(value="状态 1 正常 2 禁用")
    private Integer state;

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