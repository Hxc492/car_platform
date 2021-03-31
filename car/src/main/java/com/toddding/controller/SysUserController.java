package com.toddding.controller;

import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.entity.SysUser;
import com.toddding.domain.form.SysUserForm;
import com.toddding.domain.query.SysUserQuery;
import com.toddding.service.SysUserService;
import com.toddding.shiro.ActiveUser;
import com.toddding.utils.Md5HashUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/8 17:01
 */
@RestController
@RequestMapping("sysuser")
@Api(tags = "用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户列表分页查询")
    @RequestMapping("page.do")
    public Result page(SysUserQuery query) {
        return sysUserService.queryPage(query);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping("add.do")
    public Result add(SysUserForm form) {
        //进行数据格式校验
        ValidatorUtil.validator(form);
        return sysUserService.add(form);
    }

    @ApiOperation(value = "重置密码")
    @RequestMapping("reset.do")
    public Result reset(Integer id) {
        return sysUserService.resetPassword(id);
    }

    @ApiOperation(value = "修改当前用户密码")
    @RequestMapping("updatePassword.do")
    public Result updatePassword(String password,String newPassword) {
        //判断输入的原密码是否与实际密码一致
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        SysUser sysUser = activeUser.getSysUser();
        //将输入的原密码加密
        password = Md5HashUtil.encrypt(password);
        //将加密后的原密码与实际密码对比
        if (!password.equals(sysUser.getLoginPassword())){
            return new Result(CodeMsg.USER_UPDATE_PASSWORD_ERROR);
        }
        //修改密码
        Result rs = sysUserService.updatePassword(sysUser.getId(),newPassword);
        if (rs.getCode().equals(CodeMsg.SUCCESS.code)){
            //退出当前登录
            subject.logout();
        }
        return rs;
    }



}
