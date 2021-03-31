package com.toddding.domain.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 16:47
 */
@Data
public class BusCarForm {
    @NotEmpty(message = "车牌号不能为空")
    @Length(min = 7,max = 8,message = "车牌号7-8位")
    private String num;
    @NotNull(message = "车型不能为空")
    @Range(min = 1,max = 3,message = "车辆类型只能为1-3")
    private Integer type;
    @NotEmpty(message = "汽车颜色不能为空")
    @Length(max = 10,message = "汽车颜色最多10位字符")
    private String color;
    @NotEmpty(message = "汽车图片不能为空")
    @Length(max = 100,message = "汽车图片地址最多100位字符")
    private String img;
    @NotNull(message = "汽车价格不能为空")
    @Range(min = 1,max = 999999999,message = "汽车价格1-999999999")
    private Integer price;
    @NotNull(message = "汽车租金不能为空")
    @Range(min = 1,max = 999999999,message = "汽车租金1-999999999")
    private Integer rentPrice;
    @NotNull(message = "汽车押金不能为空")
    @Range(min = 1,max = 999999999,message = "汽车押金1-999999999")
    private String deposit;
    @Length(max = 100,message = "汽车描述最多100位字符")
    private String descp;
}
