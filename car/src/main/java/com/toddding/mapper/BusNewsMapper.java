package com.toddding.mapper;

import com.toddding.domain.entity.BusNews;
import com.toddding.domain.form.BusNewsForm;
import com.toddding.domain.query.BusNewsQuery;
import com.toddding.domain.vo.BusNewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusNewsMapper {

    /**
     * 分页查询新闻公告
     * @param query
     * @return
     */
    List<BusNewsVo> selectList(BusNewsQuery query);

    /**
     * 新增新闻公告
     * @param form
     * @return
     */
    int insert(BusNewsForm form);

    /**
     * 修改新闻公告
     * @param form
     * @return
     */
    Integer update(BusNewsForm form);

    /**
     * 根据id删除新闻公告
     * @param id
     * @return
     */
    Integer delete(@Param("id") Integer id);
}