package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.BusReturnForm;
import com.toddding.domain.query.BusReturnQuery;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 0:46
 */
public interface BusReturnService {
    /**
     * 新增换车记录
     * @param form
     * @return
     */
    Result add(BusReturnForm form);

    /**
     * 分页查询还车记录
     * @param query
     * @return
     */
    Result queryPage(BusReturnQuery query);
}
