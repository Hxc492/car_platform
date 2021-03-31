package com.toddding.shiro;

import com.toddding.domain.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @Description: 对 用户 角色 权限包装类
 * @Author: hxc
 * @Date: 2021/3/7 22:31
 */
@Data
public class ActiveUser {
    /**
     * 当前认证用户
     */
    private SysUser sysUser;
    /**
     * 用户真实名称
     */
    private String realname;
    /**
     * 用户所有的角色
     */
    private List<String> roles;
    /**
     * 用户所有的权限
     */
    private List<String> permissions;
}
