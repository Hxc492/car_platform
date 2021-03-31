package com.toddding.mapper;

import com.toddding.domain.entity.BusCar;
import com.toddding.domain.form.BusCarForm;
import com.toddding.domain.query.BusCarQuery;
import com.toddding.domain.vo.BusCarVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCarMapper {

    /**
     * 根据条件查询车辆列表
     * @param query
     * @return
     */
    List<BusCarVo> selectList(BusCarQuery query);

    /**
     * 根据车牌号查询汽车信息
     * @param num
     */
    BusCarVo selectOneByNum(@Param("num") String num);

    /**
     * 新增车辆信息
     * @param form
     * @return
     */
    Integer insert(BusCarForm form);

    /**
     * 修改汽车出租状态
     * @param num
     * @param isRent
     * @return
     */
    Integer updateRentState(@Param("num") String num, @Param("isRent") Integer isRent, @Param("state") Integer state);
}