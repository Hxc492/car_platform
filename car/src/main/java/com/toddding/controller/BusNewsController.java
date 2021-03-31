package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.BusNewsForm;
import com.toddding.domain.query.BusNewsQuery;
import com.toddding.service.BusNewsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/12 1:54
 */
@RestController
@RequestMapping("news")
public class BusNewsController {

    @Autowired
    private BusNewsService newsService;

    @ApiOperation(value = "分页查询新闻公告")
    @RequestMapping("page.do")
    public Result page(BusNewsQuery query){
        return newsService.queryPage(query);
    }

    @ApiOperation(value = "分页查询新闻公告")
    @RequestMapping("add.do")
    public Result add(BusNewsForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return newsService.insert(form);
    }

    @ApiOperation(value = "分页查询新闻公告")
    @RequestMapping("update.do")
    public Result update(BusNewsForm form){
        //数据格式校验
        ValidatorUtil.validator(form);
        return newsService.update(form);
    }

    @ApiOperation(value = "删除新闻公告")
    @RequestMapping("delete.do")
    public Result delete(Integer id){
        return newsService.delete(id);
    }


}
