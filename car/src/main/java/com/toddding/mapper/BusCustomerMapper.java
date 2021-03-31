package com.toddding.mapper;

import com.toddding.domain.entity.BusCustomer;
import com.toddding.domain.form.BusCustomerForm;
import com.toddding.domain.query.BusCustomerQuery;
import com.toddding.domain.vo.BusCustomerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCustomerMapper {

    /**
     * 根据条件查询客户列表
     * @param query
     * @return
     */
    List<BusCustomerVO> selectList(BusCustomerQuery query);

    /**
     * 根据身份证号/手机号/驾驶证号查询客户
     * @param busCustomerQuery
     * @return
     */
    BusCustomerVO selectByIdCardOrPhoneOrDrivingLicense(BusCustomerQuery busCustomerQuery);

    /**
     * 新增客户
     * @param form
     * @return
     */
    Integer insert(BusCustomerForm form);

    /**
     * 更新客户信息
     * @param form
     */
    Integer update(BusCustomerForm form);

    /**
     * 批量添加客户信息
     * @param list
     * @return
     */
    Integer batchInsert(@Param("customers") List<BusCustomerVO> list);

    /**
     * 更新用户的状态-禁用用户
     * @param id
     * @return
     */
    Integer updateStateById(@Param("id") Integer id);
}