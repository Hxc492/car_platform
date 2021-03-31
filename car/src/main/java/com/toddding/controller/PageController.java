package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.utils.Md5HashUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:负责页面跳转的controller
 *   因为所有的页面都在WEB-INF下面，是无法直接访问的
 *   必须使用服务器的内部转发访问
 * @Author: hxc
 * @Date: 2021/3/8 18:03
 */
@Controller
@RequestMapping("page")
public class PageController {

    @ApiOperation(value = "登录")
    @RequestMapping("login.do")
    public Result login(@RequestParam String loginPhone, @RequestParam String loginPassword,@RequestParam Integer identity) {
        // 对密码加密
        String passwordMd5= Md5HashUtil.encrypt(loginPassword);
        System.out.println(passwordMd5);
        // 使用手机和密码换取token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginPhone, passwordMd5);
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        // 进行认证
        subject.login(usernamePasswordToken);
        return new Result();
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping("logout.do")
    public Result logout() {
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        //跳转到登录页面
        return new Result();
    }

    /**
     * 跳转前台首页
     * @return
     */
    @RequestMapping("home.do")
    public String home(){
        return "/home.jsp";
    }

    /**
     * 跳转后台主页
     * @return
     */
    @RequestMapping("main.do")
    public String main(){
        return "main.jsp";
    }

    /**
     * 跳转后台用户列表
     * @return
     */
    @RequestMapping("user/list.do")
    public String userList(){
        return "user/list.jsp";
    }

    /**
     * 跳转后台客户列表
     * @return
     */
    @RequestMapping("customer/list.do")
    public String customerList(){
        return "customer/list.jsp";
    }

    /**
     * 跳转后台汽车列表
     * @return
     */
    @RequestMapping("car/list.do")
    public String carList(){
        return "car/list.jsp";
    }

    /**
     * 跳转后台租车记录
     * @return
     */
    @RequestMapping("rent/list.do")
    public String rentList(){
        return "rent/list.jsp";
    }

    /**
     * 跳转后台还车记录
     * @return
     */
    @RequestMapping("return/list.do")
    public String returnList(){
        return "return/list.jsp";
    }

     /**
     * 跳转后台角色管理
     * @return
     */
    @RequestMapping("role/list.do")
    public String roleList(){
        return "role/list.jsp";
    }

     /**
     * 跳转后台权限管理
     * @return
     */
    @RequestMapping("perm/list.do")
    public String permList(){
        return "perm/list.jsp";
    }

    /**
     * 跳转后台新闻公告管理
     * @return
     */
    @RequestMapping("news/list.do")
    public String newsList(){
        return "news/list.jsp";
    }

}
