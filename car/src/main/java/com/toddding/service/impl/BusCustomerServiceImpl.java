package com.toddding.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.domain.form.BusCustomerForm;
import com.toddding.domain.query.BusCustomerQuery;
import com.toddding.domain.vo.BusCustomerVO;
import com.toddding.mapper.BusCustomerMapper;
import com.toddding.service.BusCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 21:56
 */
@Service
public class BusCustomerServiceImpl implements BusCustomerService {

    @Autowired
    private BusCustomerMapper busCustomerMapper;
    @Override
    public Result queryPage(BusCustomerQuery query) {
        Page<BusCustomerVO> customerVOPage = PageHelper.startPage(query.getPage(), query.getLimit());
        busCustomerMapper.selectList(query);
        return new Result(customerVOPage.toPageInfo());
    }

    @Override
    public Result add(BusCustomerForm form) {
        //业务数据校验
        BusCustomerQuery busCustomerQuery=new BusCustomerQuery();

        //手机号唯一
        busCustomerQuery.setPhone(form.getPhone());
        BusCustomerVO busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        if (busCustomerVO!=null){
            return new Result(CodeMsg.CUSTOMER_PHONE_EXIST_ERROR);
        }

        //身份证唯一
        busCustomerQuery=new BusCustomerQuery();
        busCustomerQuery.setIdCard(form.getIdCard());
        busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        if (busCustomerVO!=null){
            return new Result(CodeMsg.CUSTOMER_ID_CARD_EXIST_ERROR);
        }

        //驾驶证唯一
        busCustomerQuery=new BusCustomerQuery();
        busCustomerQuery.setDrivingLicense(form.getDrivingLicense());
        busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        if (busCustomerVO!=null){
            return new Result(CodeMsg.CUSTOMER_DRIVING_LICENSE_EXIST_ERROR);
        }

        //新增客户
        busCustomerMapper.insert(form);

        return new Result();
    }

    @Override
    public Result update(BusCustomerForm form) {
        //业务数据校验
        BusCustomerQuery busCustomerQuery=new BusCustomerQuery();
        BusCustomerVO busCustomerVO=null;

        //身份证唯一
        busCustomerQuery=new BusCustomerQuery();
        busCustomerQuery.setIdCard(form.getIdCard());
        busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        /**
         * 若身份证号相同
         *   1、历史数据 id一致
         *   2、其他数据 id不一致
         */
        if (busCustomerVO!=null && !busCustomerVO.getId().equals(form.getId())){
            return new Result(CodeMsg.CUSTOMER_ID_CARD_EXIST_ERROR);
        }

        //驾驶证唯一
        busCustomerQuery=new BusCustomerQuery();
        busCustomerQuery.setDrivingLicense(form.getDrivingLicense());
        busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        /**
         * 若驾驶证号相同
         *   1、历史数据 id一致
         *   2、其他数据 id不一致
         */
        if (busCustomerVO!=null && !busCustomerVO.getId().equals(form.getId())){
            return new Result(CodeMsg.CUSTOMER_DRIVING_LICENSE_EXIST_ERROR);
        }

        //手机号唯一
        busCustomerQuery.setPhone(form.getPhone());
        busCustomerVO = busCustomerMapper.selectByIdCardOrPhoneOrDrivingLicense(busCustomerQuery);
        /**
         * 若手机号相同
         *   1、历史数据 id一致
         *   2、其他数据 id不一致
         */
        if (busCustomerVO!=null && !busCustomerVO.getId().equals(form.getId())){
            return new Result(CodeMsg.CUSTOMER_PHONE_EXIST_ERROR);
        }

        //更新客户信息
        busCustomerMapper.update(form);

        return new Result();
    }

    @Override
    public void exportCustomer(BusCustomerQuery query, HttpServletResponse response) throws Exception {
        //与客户端相关的输出流
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //客户信息.xls是弹出下载对话框的文件名，不能为中文，中文需自行编码
        String fileName= URLEncoder.encode("客户信息.xls","utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //符合条件的所有客户
        List<BusCustomerVO> busCustomerVOList = busCustomerMapper.selectList(query);
        //将客户转化为Excel数据流
        //通过工具创建writer，m默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("id","编号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("phone", "手机号");
        writer.addHeaderAlias("province", "省");
        writer.addHeaderAlias("city", "市");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("drivingLicense", "驾驶证号");
        writer.addHeaderAlias("idCard", "身份证号");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("state", "状态");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("updateTime", "修改时间");
        //将数据输出
        //将Excel数据流输出给浏览器
        writer.write(busCustomerVOList,true);
        writer.flush(os,true);
    }

    @Override
    public Result batchAddCustomer(MultipartFile file) throws Exception {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        System.out.println(file.getInputStream().toString());
        //将数据转化对象
        //解析Excel文件
        List<List<Object>> vosList = reader.read(1, reader.getRowCount());
        List<BusCustomerVO> busCustomerVOs=new ArrayList<>();
        //循环遍历转化文件
        for (List<Object> list : vosList) {
            BusCustomerVO vo=new BusCustomerVO();
            for (int i=0;i<list.size();i++){
                //字段不为空
                if (list.get(i)!=null && list.get(i)!=""){
                    String value=String.valueOf(list.get(i));
                    switch (i){
                        case 0: vo.setId(Integer.parseInt(value));
                            break;
                        case 1: vo.setName(value);
                            break;
                        case 2:vo.setPhone(value);
                            break;
                        case 3:vo.setProvince(value);
                            break;
                        case 4:vo.setCity(value);
                            break;
                        case 5:vo.setAddress(value);
                            break;
                        case 6:vo.setDrivingLicense(value);
                            break;
                        case 7:vo.setIdCard(value);
                            break;
                        case 8:vo.setSex(Integer.parseInt(value));
                            break;
                        case 9:vo.setState(Integer.parseInt(value));
                            break;
                        default:
                            break;
                    }
                }
            }
            busCustomerVOs.add(vo);
        }
        //批量添加
        busCustomerMapper.batchInsert(busCustomerVOs);
        return new Result();
    }

    @Override
    public Result disableById(Integer id) {
        int result = busCustomerMapper.updateStateById(id);
        if (result == 0){
            return new Result(CodeMsg.CUSTOMER_AlREADY_DISABLED);
        }
        return new Result(CodeMsg.SUCCESS);
    }
}
