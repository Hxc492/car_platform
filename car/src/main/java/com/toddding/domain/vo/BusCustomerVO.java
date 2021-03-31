package com.toddding.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toddding.domain.entity.BusCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 22:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomerVO extends BusCustomer {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
