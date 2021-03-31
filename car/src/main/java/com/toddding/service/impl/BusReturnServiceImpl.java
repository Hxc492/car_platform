package com.toddding.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.CodeMsg;
import com.toddding.common.Constant;
import com.toddding.common.Result;
import com.toddding.common.exception.BussiException;
import com.toddding.domain.form.BusReturnForm;
import com.toddding.domain.query.BusReturnQuery;
import com.toddding.domain.vo.BusCarVo;
import com.toddding.domain.vo.BusRentVo;
import com.toddding.domain.vo.BusReturnVo;
import com.toddding.mapper.BusCarMapper;
import com.toddding.mapper.BusRentMapper;
import com.toddding.mapper.BusReturnMapper;
import com.toddding.service.BusReturnService;
import com.toddding.shiro.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 0:46
 */
@Service
public class BusReturnServiceImpl implements BusReturnService {
    @Autowired
    private BusReturnMapper returnMapper;
    @Autowired
    private BusCarMapper carMapper;
    @Autowired
    private BusRentMapper rentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(BusReturnForm form) {
        //业务数据校验
        //查询出租记录
        BusRentVo rentVo = rentMapper.selectOneByNum(form.getNum());
        //如果出租状态为已还车，返回提示
        if (rentVo.getFlag().equals(Constant.RENT_RETURN_ED)){
            return new Result(CodeMsg.RETURN_CAR_ERROR);
        }

        //未还车状态
        //1、修改出租记录的状态 未还车-已还车
        Integer rows=rentMapper.updateRentInfo(rentVo.getFlag(),Constant.RENT_RETURN_ED,form.getNum(),form.getReturnTime());
        if (rows != 1){
            throw new BussiException(CodeMsg.RETURN_RENT_CHANGE_ERROR.code,CodeMsg.RETURN_RENT_CHANGE_ERROR.msg);
        }
        //2、修改车辆状态 已出租-未出租
        BusCarVo carVo = carMapper.selectOneByNum(form.getNum());
        rows = carMapper.updateRentState(carVo.getNum(), Constant.CAR_RENT_NOT, carVo.getIsRent());
        if (rows != 1){
            throw new BussiException(CodeMsg.RETURN_CAR_CHANGE_ERROR.code,CodeMsg.RETURN_CAR_CHANGE_ERROR.msg);
        }
        //3、计算出租的总金额，插入换车记录
        //计算租车天数
        String beginTime=rentVo.getBeginTime()+" 00:00:00";
        String endTime=form.getReturnTime()+" 23:59:59";
        Date begin= DateUtil.parse(beginTime,"yyyy-MM-dd HH:mm:ss");
        Date end= DateUtil.parse(endTime,"yyyy-MM-dd HH:mm:ss");
        int days= (int) DateUtil.betweenDay(begin,end,true)+1;
        if (DateUtil.isSameDay(begin,end)){
            days=1;
        }
        //租车天数
        form.setRentDays(days);
        //计算租金
        int rentPriceTotal=days*carVo.getRentPrice();
        form.setRentPrice(rentPriceTotal);
        //计算总金额
        int totalMoney=rentPriceTotal+form.getPayMoney();
        form.setTotalMoney(totalMoney);
        //获取操作员id
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        form.setUserId(activeUser.getSysUser().getId());
        //新增还车记录
        returnMapper.insert(form);
        return new Result();
    }

    @Override
    public Result queryPage(BusReturnQuery query) {
        Page<BusReturnVo> page = PageHelper.startPage(query.getPage(), query.getLimit());
        returnMapper.selectList(query);
        return new Result(page.toPageInfo());
    }
}
