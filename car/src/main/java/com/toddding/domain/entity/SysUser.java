package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys_user
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.SysUser用户表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Integer id;

    /**
     * 登录名
     */
    @ApiModelProperty(value="登录名")
    private String loginName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value="登录密码")
    private String loginPassword;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String realname;

    /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号")
    private String idCard;

    /**
     * 性别  1 男  2 女
     */
    @ApiModelProperty(value="性别  1 男  2 女")
    private Integer sex;

    /**
     * 地址
     */
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * 图像
     */
    @ApiModelProperty(value="图像")
    private String img;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}