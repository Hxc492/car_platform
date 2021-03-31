package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/12 2:00
 */
@Data
public class BusNewsQuery extends Query {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
}
