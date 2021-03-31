package com.toddding.mapper;

import com.toddding.domain.entity.BusRent;
import com.toddding.domain.form.BusRentForm;
import com.toddding.domain.query.BusRentQuery;
import com.toddding.domain.vo.BusRentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusRentMapper {

    /**
     * 新增租车记录
     * @param form
     * @return
     */
    Integer insert(BusRentForm form);

    /**
     * 分页查询租车记录
     * @param query
     * @return
     */
    List<BusRentVo> selectList(BusRentQuery query);

    /**
     * 根据车牌号查询租车记录
     * @param num
     * @return
     */
    BusRentVo selectOneByNum(@Param("num") String num);

    /**
     * 修改出租记录的租车信息
     * @param flag
     * @return
     */
    Integer updateRentInfo(@Param("oldFlag") Integer oldFlag, @Param("flag") Integer flag, @Param("num") String num, @Param("returnTime") String returnTime);
}