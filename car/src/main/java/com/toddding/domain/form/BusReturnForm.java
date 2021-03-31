package com.toddding.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 0:48
 */
@Data
public class BusReturnForm {
    /**
     * 还车记录ID
     */
    @ApiModelProperty(value="还车记录ID")
    private Integer id;

    /**
     * 车牌号
     */
    @ApiModelProperty(value="车牌号")
    @NotEmpty(message = "车牌号不能为空")
    @Length(min = 7,max = 8,message = "车牌号为7-8位")
    private String num;

    /**
     * 出租记录ID
     */
    @ApiModelProperty(value="出租记录ID")
    @NotNull(message = "出租记录ID不能为空")
    private Integer rentId;

    /**
     * 出租记录ID
     */
    private Integer rentDays;

    /**
     * 还车时间
     */
    @ApiModelProperty(value="还车时间")
    @NotEmpty(message = "还车时间不能为空")
    private String returnTime;

    /**
     * 租金
     */
    @ApiModelProperty(value="租金")
    private Integer rentPrice;

    /**
     * 赔付金额
     */
    @ApiModelProperty(value="赔付金额")
    @NotNull(message = "赔付金额不能为空")
    @Min(value = 0,message = "赔付金额最少为0")
    private Integer payMoney;

    /**
     * 问题
     */
    @ApiModelProperty(value="问题")
    private String problem;

    /**
     * 总金额
     */
    @ApiModelProperty(value="总金额")
    private Integer totalMoney;

    /**
     * 业务员ID
     */
    @ApiModelProperty(value="业务员ID")
    private Integer userId;

}
