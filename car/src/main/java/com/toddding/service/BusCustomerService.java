package com.toddding.service;

import com.toddding.common.Result;
import com.toddding.domain.form.BusCustomerForm;
import com.toddding.domain.query.BusCustomerQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 21:55
 */
public interface BusCustomerService {
    /**
     * 分页查询客户信息
     * @param query
     * @return
     */
    Result queryPage(BusCustomerQuery query);

    /**
     * 新增客户信息
     * @param form
     * @return
     */
    Result add(BusCustomerForm form);

    /**
     * 更新客户信息
     * @param form
     * @return
     */
    Result update(BusCustomerForm form);

    /**
     * 导出客户信息
     * @param query
     * @param response
     * @return
     */
    void exportCustomer(BusCustomerQuery query, HttpServletResponse response) throws Exception;

    /**
     * 导入客户信息
     * 将上传的excel文件中的客户信息插入到数据库中
     * @param file
     */
    Result batchAddCustomer(MultipartFile file) throws Exception;

    /**
     * 根据用户id禁用用户
     * @param id
     * @return
     */
    Result disableById(Integer id);
}
