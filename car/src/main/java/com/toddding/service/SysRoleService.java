package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.SysRoleForm;
import com.toddding.domain.query.SysRoleQuery;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 3:17
 */
public interface SysRoleService {
    /**
     * 分页查询角色列表
     * @param query
     * @return
     */
    Result queryPage(SysRoleQuery query);

    /**
     * 新增角色
     * @param form
     * @return
     */
    Result add(SysRoleForm form);

    /**
     * 修改角色信息
     * @param form
     * @return
     */
    Result update(SysRoleForm form);

    /**
     * 查询所有角色信息
     * @return
     */
    Result queryAll();

    /**
     * 根据用户id查询所有对应角色
     * @param userId
     * @return
     */
    Result queryUserRoles(Integer userId);

    /**
     * 根据用户id设置用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    Result insertUserRoles(Integer userId, List<Integer> roleIds);

    /**
     * 查询用户所有角色的标识
     * @param id
     * @return
     */
    List<String> queryUserRolesTags(Integer id);

    /**
     * 根据角色查询权限id
     * @param id
     * @return
     */
    Result queryRolePermissionIds(Integer id);

    /**
     * 设置角色权限id
     * @param roleId
     * @param permissionId
     * @return
     */
    Result addRolePermission(Integer roleId, List<Integer> permissionId);
}
