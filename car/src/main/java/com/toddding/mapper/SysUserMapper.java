package com.toddding.mapper;

import com.github.pagehelper.Page;
import com.toddding.domain.entity.SysUser;
import com.toddding.domain.form.SysUserForm;
import com.toddding.domain.query.SysUserQuery;
import com.toddding.domain.vo.SysUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    SysUser selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据参数查询用户列表
     * @param query
     * @return
     */
    List<SysUserVO> selectList(SysUserQuery query);

    /**
     * 根据登录名/手机号/身份证号查询用户
     * @param query
     * @return
     */
    SysUserVO selectUserByNameOrPhoneOrIdCard(SysUserQuery query);

    /**
     * 新增用户数据
     * @param form
     * @return
     */
    Integer insert(SysUserForm form);

    Integer updatePassword(@Param("id") Integer id, @Param("loginPassword") String loginPassword);
}