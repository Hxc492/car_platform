package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.SysPermissionForm;
import com.toddding.domain.query.SyPermissionQuery;
import com.toddding.service.SysPermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 16:29
 */
@RestController
@RequestMapping("permission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService permissionService;

    @ApiOperation(value = "获取当前用户的左侧菜单")
    @RequestMapping("leftMenu.do")
    public Result getUserMenu(){
        return permissionService.currentLeftMenu();
    }

    @ApiOperation(value = "所有的权限数据")
    @RequestMapping("all.do")
    public Result all(){
        return permissionService.queryAll();
    }

    @ApiOperation(value = "分页查询权限列表数据")
    @RequestMapping("page.do")
    public Result page(SyPermissionQuery query){
        return permissionService.queryPage(query);
    }

    @ApiOperation(value = "新增权限")
    @RequestMapping("add.do")
    public Result add(SysPermissionForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return permissionService.add(form);
    }

    @ApiOperation(value = "修改权限")
    @RequestMapping("update.do")
    public Result update(SysPermissionForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return permissionService.update(form);
    }

    @ApiOperation(value = "删除权限")
    @RequestMapping("delete.do")
    public Result delete(Integer id){
        return permissionService.delete(id);
    }


}
