package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.SysPermissionForm;
import com.toddding.domain.query.SyPermissionQuery;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 16:28
 */
public interface SysPermissionService {
    /**
     * 获取当前用户的左侧菜单
     * @return
     */
    Result currentLeftMenu();

    /**
     * 根据用户
     * @return
     */
    List<String> queryUserPermissionTags(Integer id);

    /**
     * 查询所有的权限数据
     * @return
     */
    Result queryAll();

    /**
     * 分页查询权限数据
     * @param query
     * @return
     */
    Result queryPage(SyPermissionQuery query);

    /**
     * 新增权限
     * @param form
     * @return
     */
    Result add(SysPermissionForm form);

    /**
     * 修改权限信息
     * @param form
     * @return
     */
    Result update(SysPermissionForm form);

    /**
     * 删除权限
     * @param id
     * @return
     */
    Result delete(Integer id);
}
