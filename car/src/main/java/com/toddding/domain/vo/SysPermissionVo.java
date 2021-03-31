package com.toddding.domain.vo;

import com.toddding.domain.entity.SysPermission;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 16:35
 */
@Data
public class SysPermissionVo extends SysPermission {
    /**
     * 子菜单
     */
    private List<SysPermissionVo> children;
    /**
     * 前端设置复选框
     */
    private String checkArr = "0";
}
