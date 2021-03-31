package com.toddding.common.handler;

import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.common.exception.BussiException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:全局异常处理器
 * @Author: hxc
 * @Date: 2021/3/7 22:43
 */
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    @ResponseBody
    public Object handlerException(Exception exception){
        exception.printStackTrace();
        //判断是否是程序员自己定义的异常信息
        if(exception instanceof BussiException){
            BussiException bussiException= (BussiException) exception;
            return new Result(bussiException);
        }
        //shiro异常
        if (exception instanceof ShiroException){
            if (exception instanceof UnknownAccountException){
                return new Result(CodeMsg.USER_USERNAME_PASSWORD_ERROR);
            }
            if (exception instanceof AuthenticationException){
                return new Result(CodeMsg.USER_NOT_HAVE_PERMISSION_ERROR);
            }
            return null;
        }
        //可能发生其他异常
        return new Result(CodeMsg.ERROR);
    }
}
