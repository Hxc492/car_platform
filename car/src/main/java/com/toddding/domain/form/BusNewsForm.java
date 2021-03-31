package com.toddding.domain.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/12 2:10
 */
@Data
public class BusNewsForm {
    private Integer id;
    @NotEmpty(message = "标题不能为空")
    @Length(max = 30,message = "标题最多30个字符")
    private String title;
    @NotEmpty(message = "内容不能为空")
    @Length(max = 200,message = "内容最多200个字符")
    private String content;
    private Integer userId;
}
