package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * sys_permission
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.SysPermission权限表")
@Data
public class SysPermission implements Serializable {
    /**
     * 权限ID
     */
    @ApiModelProperty(value="权限ID")
    private Integer id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value="权限名称")
    private String title;

    /**
     * 权限图标
     */
    @ApiModelProperty(value="权限图标")
    private String icon;

    /**
     * 权限连接 菜单请求的URL地址
     */
    @ApiModelProperty(value="权限连接 菜单请求的URL地址")
    private String href;

    /**
     * 是否展开 1.展开 0.不展开
     */
    @ApiModelProperty(value="是否展开 1.展开 0.不展开")
    private Boolean spread;

    /**
     * 权限类型 1.菜单 2.按钮
     */
    @ApiModelProperty(value="权限类型 1.菜单 2.按钮")
    private Integer type;

    /**
     * 权限自定义标识
     */
    @ApiModelProperty(value="权限自定义标识")
    private String tag;

    /**
     * 排序 越大越靠前
     */
    @ApiModelProperty(value="排序 越大越靠前")
    private Integer sort;

    /**
     * 父权限ID 默认0 表示一级菜单
     */
    @ApiModelProperty(value="父权限ID 默认0 表示一级菜单")
    private Integer parentId;

    private static final long serialVersionUID = 1L;
}