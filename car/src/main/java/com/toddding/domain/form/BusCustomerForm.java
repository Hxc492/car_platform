package com.toddding.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:接收客户提交的更新数据
 * @Author: hxc
 * @Date: 2021/3/9 21:58
 */
@Data
public class BusCustomerForm {
    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID")
    private Integer id;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    @NotEmpty(message = "客户名称不能为空")
    @Length(min= 2,max = 15,message = "客户名称6-15位字符")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    @NotEmpty(message = "手机号不能为空")
    @Length(min= 11,max = 11,message = "手机号11位字符")
    private String phone;

    /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号")
    @NotEmpty(message = "身份证号不能为空")
    @Length(min= 18,max = 18,message = "身份证号18位字符")
    private String idCard;

    /**
     * 驾驶证号
     */
    @ApiModelProperty(value="驾驶证号")
    @NotEmpty(message = "驾驶证号不能为空")
    @Length(min= 18,max = 18,message = "驾驶证号18位字符")
    private String drivingLicense;

    /**
     * 性别  1 男  2 女
     */
    @ApiModelProperty(value="性别  1 男  2 女")
    @NotNull(message = "性别不能为空")
    @Range(min= 1,max = 2,message = "性别只能为男或女")
    private Integer sex;

    /**
     * 状态  1 正常  2 禁用
     */
    @ApiModelProperty(value="状态  1 正常  2 禁用")
    @NotNull(message = "状态不能为空")
    @Range(min= 1,max = 2,message = "性别只能为正常或禁用")
    private Integer state;

    /**
     * 省
     */
    @ApiModelProperty(value="省")
    @NotEmpty(message = "省不能为空")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value="市")
    @NotEmpty(message = "市不能为空")
    private String city;

    /**
     * 地址
     */
    @ApiModelProperty(value="地址")
    @NotEmpty(message = "地址不能为空")
    @Length(max = 100,message = "地址6最多100个字符")
    private String address;

}
