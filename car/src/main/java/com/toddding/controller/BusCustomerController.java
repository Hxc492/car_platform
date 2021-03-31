package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.BusCustomerForm;
import com.toddding.domain.query.BusCustomerQuery;
import com.toddding.service.BusCustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 21:53
 */
@RestController
@RequestMapping("customer")
public class BusCustomerController {

    @Autowired
    private BusCustomerService busCustomerService;

    @ApiOperation(value = "客户列表分页查询")
    @RequestMapping("page.do")
    public Result page(BusCustomerQuery query) {
        return busCustomerService.queryPage(query);
    }

    @ApiOperation(value = "新增客户")
    @RequestMapping("add.do")
    public Result add(BusCustomerForm form) {
        //进行数据格式校验
        ValidatorUtil.validator(form);
        return busCustomerService.add(form);
    }

    @ApiOperation(value = "修改客户信息")
    @RequestMapping("update.do")
    public Result update(BusCustomerForm form) {
        //进行数据格式校验
        ValidatorUtil.validator(form);
        return busCustomerService.update(form);
    }

    @ApiOperation(value = "导出符合条件的客户信息")
    @RequestMapping("export.do")
    public void export(BusCustomerQuery query, HttpServletResponse response) throws Exception {
        busCustomerService.exportCustomer(query,response);
    }

    @ApiOperation(value = "导入客户信息")
    @RequestMapping("import.do")
    public Object customerImport(@RequestParam("customers") MultipartFile file) throws Exception {
        return busCustomerService.batchAddCustomer(file);
    }

    @ApiOperation(value = "禁用客户")
    @RequestMapping("disable.do")
    public Object disable(Integer id) {
        return busCustomerService.disableById(id);
    }

}
