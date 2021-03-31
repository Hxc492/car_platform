package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 2:32
 */
@Data
public class BusReturnQuery extends Query {
    /**
     * 车牌号
     */
    private String num;
}
