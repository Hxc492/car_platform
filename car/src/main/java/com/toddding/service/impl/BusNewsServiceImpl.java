package com.toddding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.Result;
import com.toddding.domain.form.BusNewsForm;
import com.toddding.domain.query.BusNewsQuery;
import com.toddding.mapper.BusNewsMapper;
import com.toddding.service.BusNewsService;
import com.toddding.shiro.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/12 1:55
 */
@Service
public class BusNewsServiceImpl implements BusNewsService {

    @Autowired
    private BusNewsMapper newsMapper;

    @Override
    public Result queryPage(BusNewsQuery query) {
        Page<Object> page = PageHelper.startPage(query.getPage(), query.getLimit());
        newsMapper.selectList(query);
        return new Result(page.toPageInfo());
    }

    @Override
    public Result insert(BusNewsForm form) {
        //获取当前业务员对象
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        //设置业务员id
        form.setUserId(activeUser.getSysUser().getId());
        newsMapper.insert(form);
        return new Result();
    }

    @Override
    public Result update(BusNewsForm form) {
        //获取当前业务员对象
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        //设置业务员id
        form.setUserId(activeUser.getSysUser().getId());
        newsMapper.update(form);
        return new Result();
    }

    @Override
    public Result delete(Integer id) {
        newsMapper.delete(id);
        return new Result();
    }
}
