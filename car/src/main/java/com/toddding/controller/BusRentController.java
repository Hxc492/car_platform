package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.BusRentForm;
import com.toddding.domain.query.BusRentQuery;
import com.toddding.service.BusRentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:出租记录表
 * @Author: hxc
 * @Date: 2021/3/10 20:31
 */
@RestController
@RequestMapping("rent")
public class BusRentController {

    @Autowired
    private BusRentService busRentService;

    @ApiOperation(value = "添加租车记录")
    @RequestMapping("add.do")
    public Result add(BusRentForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return busRentService.add(form);
    }

    @ApiOperation(value = "分页查询租车记录")
    @RequestMapping("page.do")
    public Result page(BusRentQuery query){
        return busRentService.queryPage(query);
    }



}
