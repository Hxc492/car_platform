package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.BusCarForm;
import com.toddding.domain.query.BusCarQuery;
import com.toddding.service.BusCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 14:15
 */
@RestController
@RequestMapping("car")
public class BusCarController {

    @Autowired
    private BusCarService busCarService;

    /**
     * 分页查询汽车
     * @param query
     * @return
     */
    @RequestMapping("page.do")
    public Result page(BusCarQuery query){
        return busCarService.queryPage(query);
    }

    /**
     * 新增汽车
     * @param form
     * @return
     */
    @RequestMapping("add.do")
    public Result page(BusCarForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return busCarService.add(form);
    }

}
