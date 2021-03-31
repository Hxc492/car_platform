package com.toddding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.CodeMsg;
import com.toddding.common.Constant;
import com.toddding.common.Result;
import com.toddding.domain.entity.SysUser;
import com.toddding.domain.form.SysUserForm;
import com.toddding.domain.query.SysUserQuery;
import com.toddding.domain.vo.SysUserVO;
import com.toddding.mapper.SysUserMapper;
import com.toddding.service.SysUserService;
import com.toddding.utils.Md5HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/8 16:17
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public Result queryUser(String username, String password) {
        SysUser sysUser=sysUserMapper.selectUserByUsernameAndPassword(username,password);
        if (sysUser==null){
            return new Result(CodeMsg.USER_USERNAME_PASSWORD_ERROR);
        }
        return new Result(sysUser);
    }

    @Override
    public Result queryPage(SysUserQuery query) {
        //开启分页查询
        Page<SysUserVO> sysUserVOPage = PageHelper.startPage(query.getPage(), query.getLimit());
        //根据参数查询用户列表
        sysUserMapper.selectList(query);
        return new Result(sysUserVOPage.toPageInfo());
    }

    @Override
    public Result add(SysUserForm form) {
        //业务数据校验
        SysUserQuery query=new SysUserQuery();

        //登录名不能重复
        query.setLoginName(form.getLoginName());
        SysUserVO sysUserVO=sysUserMapper.selectUserByNameOrPhoneOrIdCard(query);
        if (sysUserVO != null){
            return new Result(CodeMsg.USER_LOGIN_NAME_EXIST_ERROR);
        }

        //手机号不能重复
        query=new SysUserQuery();
        query.setPhone(form.getPhone());
        sysUserVO=sysUserMapper.selectUserByNameOrPhoneOrIdCard(query);
        if (sysUserVO != null){
            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }

        //身份证号不能重复
        query=new SysUserQuery();
        query.setIdCard(form.getIdCard());
        sysUserVO=sysUserMapper.selectUserByNameOrPhoneOrIdCard(query);
        if (sysUserVO != null){
            return new Result(CodeMsg.USER_ID_CARD_EXIST_ERROR);
        }
        //插入数据
        form.setLoginPassword(Md5HashUtil.encrypt(Constant.DEFAULT_PASSWORD));
        sysUserMapper.insert(form);
        return new Result();
    }

    @Override
    public Result resetPassword(Integer id) {
        sysUserMapper.updatePassword(id,Md5HashUtil.encrypt(Constant.DEFAULT_PASSWORD));
        return new Result();
    }

    @Override
    public Result updatePassword(Integer id, String newPassword) {
        newPassword=Md5HashUtil.encrypt(newPassword);
        sysUserMapper.updatePassword(id,newPassword);
        return new Result();
    }
}
