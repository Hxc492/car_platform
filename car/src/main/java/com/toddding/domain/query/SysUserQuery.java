package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:用户查询参数类
 * @Author: hxc
 * @Date: 2021/3/9 16:14
 */
@Data
public class SysUserQuery extends Query {
    /**
     * 用户姓名
     */
    private String realname;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 登录名
     */
    private String loginName;
}
