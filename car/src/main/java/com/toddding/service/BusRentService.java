package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.BusRentForm;
import com.toddding.domain.query.BusRentQuery;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 20:31
 */
public interface BusRentService {
    /**
     * 新增出租记录
     * @param form
     * @return
     */
    Result add(BusRentForm form);

    /**
     * 查询出租记录
     * @param query
     * @return
     */
    Result queryPage(BusRentQuery query);
}
