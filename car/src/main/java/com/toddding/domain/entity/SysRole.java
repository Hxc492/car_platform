package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * sys_role
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.SysRole角色表")
@Data
public class SysRole implements Serializable {
    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value="角色名称")
    private String name;

    /**
     * 角色标识
     */
    @ApiModelProperty(value="角色标识")
    private String tag;

    /**
     * 角色描述
     */
    @ApiModelProperty(value="角色描述")
    private String descp;

    private static final long serialVersionUID = 1L;
}