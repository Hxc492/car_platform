package com.toddding.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toddding.domain.entity.BusRent;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 23:21
 */
@Data
public class BusRentVo extends BusRent {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

}
