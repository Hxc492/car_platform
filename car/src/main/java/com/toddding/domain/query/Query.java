package com.toddding.domain.query;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Description: 所有查询参数的基类
 * @Author: hxc
 * @Date: 2021/3/9 16:11
 */
@Data
public abstract class Query {
    /**
     * 页码
     */
    private Integer page=1;
    /**
     * 每页的条数
     */
    private Integer limit=10;
}
