package com.toddding.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 3:36
 */
@Data
public class SysRoleForm {
    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value="角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 20,message = "角色名称最多20个字符")
    private String name;

    /**
     * 角色标识
     */
    @ApiModelProperty(value="角色标识")
    @NotEmpty(message = "角色标识不能为空")
    @Length(max = 20,message = "角色标识最多20个字符")
    private String tag;

    /**
     * 角色描述
     */
    @ApiModelProperty(value="角色描述")
    @NotEmpty(message = "角色描述不能为空")
    @Length(max = 100,message = "角色描述最多100个字符")
    private String descp;
}
