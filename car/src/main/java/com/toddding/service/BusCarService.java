package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.BusCarForm;
import com.toddding.domain.query.BusCarQuery;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 14:14
 */
public interface BusCarService {
    /**
     * 分页查询汽车
     * @param query
     * @return
     */
    Result queryPage(BusCarQuery query);

    /**
     * 新增汽车
     * @param form
     * @return
     */
    Result add(BusCarForm form);
}
