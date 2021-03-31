package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 19:47
 */
@Data
public class SyPermissionQuery extends Query {
    /**
     * 权限名称
     */
    private String title;
}
