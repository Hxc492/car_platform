package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 21:57
 */
@Data
public class BusCustomerQuery extends Query {
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户状态 1 正常 2 禁用
     */
    private Integer state;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 驾驶证号
     */
    private String drivingLicense;
}
