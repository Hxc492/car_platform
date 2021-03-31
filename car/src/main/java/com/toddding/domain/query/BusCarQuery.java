package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 14:40
 */
@Data
public class BusCarQuery extends Query {

    /**
     * 车牌号
     */
    private String num;
    /**
     * 车型
     */
    private Integer type;
    /**
     * 颜色
     */
    private String color;
    /**
     * 最低价格
     */
    private Integer minPrice;
    /**
     * 最高价格
     */
    private Integer maxPrice;
    /**
     * 最低租车金额
     */
    private Integer minRentPrice;
    /**
     * 最高租车金额
     */
    private Integer maxRentPrice;
    /**
     * 出租状态 1未出租 2已出租
     */
    private Integer isRent;
    /**
     * 描述
     */
    private String descp;


}
