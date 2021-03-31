package com.toddding.domain.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 21:13
 */
@Data
public class SysPermissionForm {
    private Integer id;
    @NotNull(message = "父权限ID不能为空")
    private Integer parentId;
    @NotEmpty(message = "权限名称不能为空")
    @Length(max = 10,message = "权限名称最多10个字符")
    private String title;
    @Length(max = 20,message = "权限图标最多20个字符")
    private String icon;
    @Length(max = 100,message = "权限链接最多100个字符")
    private String href;
    @Range(min = 0,max = 1,message = "是否展开只能为0或1")
    private Integer spread;
    @NotNull(message = "父权限id不能为空")
    @Range(min = 1,max = 2,message = "权限类型只能为1或2")
    private Integer type;
    @NotEmpty(message = "权限标识不能为空")
    @Length(max = 20,message = "权限标识最多20个字符")
    private String tag;
    private Integer sort;
}
