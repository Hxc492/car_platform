package com.toddding.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:接收用户提交的更新数据
 * @Author: hxc
 * @Date: 2021/3/9 18:18
 */
@Data
public class SysUserForm {
    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Integer id;

    /**
     * 登录名
     */
    @ApiModelProperty(value="登录名")
    @NotEmpty(message = "loginName:登录名不能为空")
    @Length(min= 6,max = 15,message = "loginName:登录名6-15位字符")
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
    @NotEmpty(message = "phone:手机号不能为空")
    @Length(min= 11,max = 11,message = "phone:手机号11位字符")
    private String phone;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    @NotEmpty(message = "真实姓名：不能为空")
    @Length(max = 20,message = "真实姓名：最多20位字符")
    private String realname;

    /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号")
    @NotEmpty(message = "身份证号：不能为空")
    @Length(min= 18,max = 18,message = "身份证号：18位字符")
    private String idCard;

    /**
     * 性别  1 男  2 女
     */
    @ApiModelProperty(value="性别  1 男  2 女")
    @NotNull(message = "性别：不能为空")
    @Range(min= 1,max = 2,message = "性别：只能为男(1)或女(2)")
    private Integer sex;

    /**
     * 地址
     */
    @ApiModelProperty(value="地址")
    @NotEmpty(message = "地址：不能为空")
    @Length(max = 100,message = "地址：最多100个字符")
    private String address;

    /**
     * 图像
     */
    @ApiModelProperty(value="图像")
    private String img;
}
