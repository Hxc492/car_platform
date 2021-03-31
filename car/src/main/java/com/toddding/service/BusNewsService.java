package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.BusNewsForm;
import com.toddding.domain.query.BusNewsQuery;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/12 1:55
 */
public interface BusNewsService {
    /**
     * 分页查询新闻公告
     * @param query
     * @return
     */
    Result queryPage(BusNewsQuery query);

    /**
     * 新增新闻公告
     * @param form
     * @return
     */
    Result insert(BusNewsForm form);

    /**
     * 修改新闻公告
     * @param form
     * @return
     */
    Result update(BusNewsForm form);

    /**
     * 根据id删除新闻公告
     * @param id
     * @return
     */
    Result delete(Integer id);
}
