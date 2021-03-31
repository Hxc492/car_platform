package com.toddding.common;

/**
 * @Description: 常量接口
 * @Author: hxc
 * @Date: 2021/3/7 22:36
 */
public interface Constant {
    /**
     * MD5加密的盐
     */
    String MD5_SALT="car rental";
    /**
     * 系统异常码 ：数据校验不通过
     */
    Integer PARAM_CHECK_ERROR=4001;
    /**
     * 默认的登录密码
     */
    String DEFAULT_PASSWORD="123456";
    /**
     * 保存上传文件的文件夹
     */
    String UPLOAD_FOLDER="upload";
    /**
     * 车辆状态 未出租
     */
    Integer CAR_RENT_NOT=1;
    /**
     * 车辆状态 已出租
     */
    Integer CAR_RENT_ED=2;
    /**
     * 出租记录状态 未还车
     */
    Integer RENT_RETURN_NOT=1;
    /**
     * 出租记录状态 已还车
     */
    Integer RENT_RETURN_ED=2;
    /**
     * 权限类型 菜单
     */
    Integer PERMISSION_TYPE_MENU = 1;
    /**
     * 权限类型 按钮
     */
    Integer PERMISSION_TYPE_BUTTON = 2;
    /**
     * 一级菜单
     */
    Integer MENU_LV1=0;
}
