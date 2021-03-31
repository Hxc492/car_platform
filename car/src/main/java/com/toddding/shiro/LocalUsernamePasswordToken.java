package com.toddding.shiro;

import lombok.Getter;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Description: 自己定义token
 * @Author: hxc
 * @Date: 2021/4/1 1:02
 */
@Getter
public class LocalUsernamePasswordToken extends UsernamePasswordToken {
    /**
     * 用户身份 1 前台用户 2 管理员
     */
    private Integer identity;

    public LocalUsernamePasswordToken(String username,String password,Integer identity){
        super(username,password);
        this.identity=identity;
    }
}
