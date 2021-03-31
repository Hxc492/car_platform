package com.toddding.domain.query;

import lombok.Data;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 3:23
 */
@Data
public class SysRoleQuery extends Query {
    /**
     * 角色名
     */
    private String name;
}
