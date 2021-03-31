package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.SysRoleForm;
import com.toddding.domain.query.SysRoleQuery;
import com.toddding.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 3:17
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @ApiOperation("分页查询角色列表")
    @RequestMapping("page.do")
    public Result page(SysRoleQuery query){
        return roleService.queryPage(query);
    }

    @ApiOperation("新增角色")
    @RequestMapping("add.do")
    public Result add(SysRoleForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return roleService.add(form);
    }

    @ApiOperation("修改角色信息")
    @RequestMapping("update.do")
    public Result update(SysRoleForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return roleService.update(form);
    }

    @ApiOperation("查询所有角色信息")
    @RequestMapping("all.do")
    public Result all(){
        return roleService.queryAll();
    }

    @ApiOperation("根据用户id查询所有角色")
    @RequestMapping("userRoles.do")
    public Result userRoles(@RequestParam("userId")Integer userId){
        return roleService.queryUserRoles(userId);
    }

    @ApiOperation("根据用户id设置用户角色")
    @RequestMapping("setRole.do")
    public Result setRole(@RequestParam("userId")Integer userId, @RequestParam("roleId")List<Integer> roleIds){
        return roleService.insertUserRoles(userId,roleIds);
    }

    @ApiOperation("根据角色查询所有的权限id")
    @RequestMapping("permissionIds.do")
    public Result permissionIds(@RequestParam("id")Integer id){
        return roleService.queryRolePermissionIds(id);
    }

    @ApiOperation("设置权限id")
    @RequestMapping("setRolePermission.do")
    public Result permissionIds(@RequestParam("roleId")Integer roleId,@RequestParam("permissionId")List<Integer> permissionId){
        return roleService.addRolePermission(roleId,permissionId);
    }


}
