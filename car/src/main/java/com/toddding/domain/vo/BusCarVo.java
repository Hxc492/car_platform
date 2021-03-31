package com.toddding.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toddding.domain.entity.BusCar;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/10 14:47
 */
@Data
public class BusCarVo extends BusCar {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
