package com.toddding.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toddding.domain.entity.BusNews;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/12 2:04
 */
@Data
public class BusNewsVo extends BusNews {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
