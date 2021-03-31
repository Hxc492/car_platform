package com.toddding.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.CodeMsg;
import com.toddding.common.Constant;
import com.toddding.common.Result;
import com.toddding.common.exception.BussiException;
import com.toddding.domain.form.BusRentForm;
import com.toddding.domain.query.BusCustomerQuery;
import com.toddding.domain.query.BusRentQuery;
import com.toddding.domain.vo.BusCarVo;
import com.toddding.domain.vo.BusCustomerVO;
import com.toddding.domain.vo.BusRentVo;
import com.toddding.mapper.BusCarMapper;
import com.toddding.mapper.BusCustomerMapper;
import com.toddding.mapper.BusRentMapper;
import com.toddding.service.BusRentService;
import com.toddding.shiro.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 20:31
 */
@Service
public class BusRentServiceImpl implements BusRentService {

    @Autowired
    private BusRentMapper busRentMapper;
    @Autowired
    private BusCustomerMapper busCustomerMapper;
    @Autowired
    private BusCarMapper busCarMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(BusRentForm form) {
        //业务数据校验
        //校验客户是否存在
        //根据身份证号查询客户是否存在
        BusCustomerQuery busCustomerQuery=new BusCustomerQuery();
        busCustomerQuery.setIdCard(form.getIdCard());
        BusCustomerVO busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        if (busCustomerVO==null){
            return new Result(CodeMsg.RENT_CUSTOMER_ID_CARD_ERROR);
        }
        //校验车辆信息
        //根据车牌号查询车辆信息
        BusCarVo busCarVo = busCarMapper.selectOneByNum(form.getNum());
        if (busCarVo.getIsRent().equals(Constant.CAR_RENT_ED)){
            return new Result(CodeMsg.CAR_RENT_ERROR);
        }
        //乐观锁处理并发问题
        //修改车辆状态,若车辆是从未出租状态改变为已出租，则版本号+1
        Integer rows=busCarMapper.updateRentState(busCarVo.getNum(),Constant.CAR_RENT_ED,busCarVo.getIsRent());
        if (rows != 1){
            throw new BussiException(CodeMsg.RENT_FAILED_ERROR.code,CodeMsg.RENT_FAILED_ERROR.msg);
        }

        //将页面输入的出租时间分割为开始时间和结束时间
        String rentTime=form.getRentTime();
        String[] times=rentTime.split("~");
        form.setBeginTime(times[0].trim());
        form.setEndTime(times[1].trim());
        //客户名称
        form.setName(busCustomerVO.getName());
        //设置业务员id
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        form.setUserId(activeUser.getSysUser().getId());
        //新增出租记录
        busRentMapper.insert(form);
        return new Result();
    }

    @Override
    public Result queryPage(BusRentQuery query) {
        Page<BusRentVo> page = PageHelper.startPage(query.getPage(), query.getLimit());
        if (StrUtil.isNotEmpty(query.getBeginTime())){
            String[] times=query.getBeginTime().split("~");
            query.setMinBeginTime(times[0].trim());
            query.setMaxBeginTime(times[1].trim());
        }
        busRentMapper.selectList(query);
        return new Result(page.toPageInfo());
    }
}
