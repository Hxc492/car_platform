package com.toddding.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toddding.domain.entity.SysUser;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 16:25
 */
@Data
public class SysUserVO extends SysUser {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}
