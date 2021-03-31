package com.toddding.shiro;

import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.domain.entity.SysUser;
import com.toddding.service.SysPermissionService;
import com.toddding.service.SysRoleService;
import com.toddding.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: 认证鉴权器
 * @Author: hxc
 * @Date: 2021/3/7 19:37
 */
public class MyAuthortion extends AuthorizingRealm {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysPermissionService permissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        char[] pwd = usernamePasswordToken.getPassword();
        String password=String.valueOf(pwd);
        //根据用户名和密码查询用户
        Result result= userService.queryUser(username,password);
        //根据用户名和密码没有查询到数据，则直接返回null
        if (!result.getCode().equals(CodeMsg.SUCCESS.code)){
            return null;
        }
        SysUser sysUser= (SysUser) result.getData();
        String realname=sysUser.getRealname();

        //根据 用户ID 查询用户所有角色标识
        List<String> rolesTags = roleService.queryUserRolesTags(sysUser.getId());
        //根据 用户ID 查询用户所有权限标识
        List<String> permissionTags = permissionService.queryUserPermissionTags(sysUser.getId());

        ActiveUser activeUser=new ActiveUser();
        activeUser.setSysUser(sysUser);
        activeUser.setRealname(realname);
        activeUser.setRoles(rolesTags);
        activeUser.setPermissions(permissionTags);
        //shiro自己校验密码
        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(activeUser,password,realname);
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiveUser activeUser= (ActiveUser) principalCollection.getPrimaryPrincipal();
        List<String> roles = activeUser.getRoles();
        List<String> permissions = activeUser.getPermissions();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

}
