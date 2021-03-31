package com.toddding.mapper;

import com.toddding.domain.entity.BusReturn;
import com.toddding.domain.form.BusReturnForm;
import com.toddding.domain.query.BusReturnQuery;
import com.toddding.domain.vo.BusReturnVo;

import java.util.List;

public interface BusReturnMapper {

    /**
     * 新增还车记录
     * @param form
     * @return
     */
    int insert(BusReturnForm form);

    /**
     * 分页查询还车记录
     * @param query
     * @return
     */
    List<BusReturnVo> selectList(BusReturnQuery query);
}