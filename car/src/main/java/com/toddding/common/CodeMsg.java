package com.toddding.common;

/**
 * @Description: 业务码 业务消息枚举
 * @Author: hxc
 * @Date: 2021/3/7 22:32
 */
public enum CodeMsg {
    SUCCESS(200,"执行成功")
    ,ERROR(110,"出错啦")

    //登录跳转主页
    ,JUMP_PAGE_BACKSTAGE_MAIN(201,"跳转到后台主页")
    ,JUMP_PAGE_FRONT_HOME(202,"跳转到前台首页")

    //用户管理
    ,USER_USERNAME_PASSWORD_ERROR(1001,"用户名或密码错误")
    ,USER_LOGIN_NAME_EXIST_ERROR(1002,"用户登录名已被使用")
    ,USER_PHONE_EXIST_ERROR(1003,"用户手机号已被使用")
    ,USER_ID_CARD_EXIST_ERROR(1004,"用户身份证号已被使用")
    ,USER_NOT_HAVE_PERMISSION_ERROR(1005,"用户权限不足")
    ,USER_UPDATE_PASSWORD_ERROR(1006,"修改密码失败，原密码不正确")

    //客户管理
    ,CUSTOMER_ID_CARD_EXIST_ERROR(2001,"客户身份证号已被使用")
    ,CUSTOMER_PHONE_EXIST_ERROR(2002,"客户手机号已被使用")
    ,CUSTOMER_DRIVING_LICENSE_EXIST_ERROR(2003,"客户驾驶证号已被使用")
    ,CUSTOMER_AlREADY_DISABLED(2004,"该用户已被禁用")

    ,CAR_UPLOAD_IMG_ERROR(3001,"车辆图片上传失败" )
    ,CAR_NUM_EXIST_ERROR(3002,"车牌号已被使用")

    //租车管理
    ,RENT_CUSTOMER_ID_CARD_ERROR(4001,"客户身份证号不存在")
    ,CAR_RENT_ERROR(4002,"车辆已出租")
    ,RENT_FAILED_ERROR(4003,"车辆出租失败,车辆信息发生了变化")

    //还车管理
    ,RETURN_CAR_ERROR(5001,"车辆已归还，请勿重复还车")
    , RETURN_RENT_CHANGE_ERROR(5002,"还车失败，出租记录发生了变化")
    ,RETURN_CAR_CHANGE_ERROR(5003,"修改车辆状态失败，车辆状态已发生了变化")
    ;

    //业务码
    public Integer code;
    //业务消息
    public String msg;

    CodeMsg(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
