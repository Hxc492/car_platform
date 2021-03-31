package com.toddding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.domain.form.BusCarForm;
import com.toddding.domain.query.BusCarQuery;
import com.toddding.domain.query.Query;
import com.toddding.domain.vo.BusCarVo;
import com.toddding.mapper.BusCarMapper;
import com.toddding.service.BusCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 14:14
 */
@Service
public class BusCarServiceImpl implements BusCarService {

    @Autowired
    private BusCarMapper busCarMapper;

    @Override
    public Result queryPage(BusCarQuery query) {
        Page<BusCarVo> page = PageHelper.startPage(query.getPage(), query.getLimit());
        busCarMapper.selectList(query);
        return new Result(page.toPageInfo());
    }

    @Override
    public Result add(BusCarForm form) {
        //进行业务数据校验
        //车牌号不能重复
        BusCarVo busCarVo = busCarMapper.selectOneByNum(form.getNum());
        if (busCarVo != null){
            return new Result(CodeMsg.CAR_NUM_EXIST_ERROR);
        }
        //将车辆信息添加到数据库
        busCarMapper.insert(form);
        return new Result();
    }
}
