package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.SysUserForm;
import com.toddding.domain.query.SysUserQuery;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/8 16:16
 */
public interface SysUserService {
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    Result queryUser(String username, String password);

    /**
     * 分页查询用户列表
     * @param query
     * @return
     */
    Result queryPage(SysUserQuery query);

    /**
     * 新增用户
     * @param form
     * @return
     */
    Result add(SysUserForm form);

    /**
     * 重置密码
     * @param id
     * @return
     */
    Result resetPassword(Integer id);

    /**
     * 根据用户id修改密码
     * @param id
     * @param newPassword
     * @return
     */
    Result updatePassword(Integer id, String newPassword);
}
