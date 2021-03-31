package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.BusReturnForm;
import com.toddding.domain.query.BusReturnQuery;
import com.toddding.service.BusReturnService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 0:45
 */
@RestController
@RequestMapping("return")
public class BusReturnController {
    @Autowired
    private BusReturnService busReturnService;

    @ApiOperation("新增换车记录")
    @RequestMapping("add.do")
    public Result add(BusReturnForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return busReturnService.add(form);
    }

    @ApiOperation("分页查询换车记录")
    @RequestMapping("page.do")
    public Result page(BusReturnQuery query){
        return busReturnService.queryPage(query);
    }

}
