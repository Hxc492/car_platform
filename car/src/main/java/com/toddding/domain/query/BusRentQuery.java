package com.toddding.domain.query;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 23:08
 */
@Data
public class BusRentQuery extends Query {
    /**
     * 车牌号
     */
    private String num;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 出租状态
     */
    private Integer flag;
    /**
     * 开始租车时间
     */
    private String beginTime;
    /**
     * 最小的开始租车时间
     */
    private String minBeginTime;
    /**
     * 最大的开始租车时间
     */
    private String maxBeginTime;

}
