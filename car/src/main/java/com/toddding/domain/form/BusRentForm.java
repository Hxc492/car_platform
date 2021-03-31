package com.toddding.domain.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 20:35
 */
@Data
public class BusRentForm {
    private Integer id;

    @NotEmpty(message = "车牌号不能为空")
    @Length(min = 7,max = 8,message = "车牌号7-8位")
    private String num;

    @NotNull(message = "车型不能为空")
    @Range(min = 1,max = 3,message = "车型的值1~3")
    private Integer type;

    @NotNull(message = "租金不能为空")
    @Range(min = 1,max = 999999999,message = "租金范围：1~999999999")
    private Integer rentPrice;

    @NotNull(message = "押金不能为空")
    @Range(min = 1,max = 999999999,message = "押金范围：1~999999999")
    private Integer deposit;

    private String name;

    @NotEmpty(message = "身份证号不能为空")
    @Length(min = 18,max = 18,message = "身份证号18位")
    private String idCard;

    @NotEmpty(message = "出租时间不能为空")
    private String rentTime;

    //开始出租时间
    private String beginTime;
    //结束时间
    private String endTime;

    //业务员id
    private Integer userId;
}
