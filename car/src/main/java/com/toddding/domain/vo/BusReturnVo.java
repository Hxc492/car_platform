package com.toddding.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toddding.domain.entity.BusReturn;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 2:37
 */
@Data
public class BusReturnVo extends BusReturn {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
