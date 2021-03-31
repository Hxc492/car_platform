package com.toddding.mapper;

import com.toddding.domain.entity.SysPermission;
import com.toddding.domain.form.SysPermissionForm;
import com.toddding.domain.query.SyPermissionQuery;
import com.toddding.domain.vo.SysPermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {

    /**
     * 根据用户id查询用户所有的菜单
     * @param userId
     * @param type
     * @return
     */
    List<SysPermissionVo> selectUserPermission(@Param("userId") Integer userId, @Param("type") Integer type);

    /**
     * 查询所有权限列表
     * @param query
     * @return
     */
    List<SysPermissionVo> selectList(SyPermissionQuery query);

    /**
     * 新增权限
     * @param form
     * @return
     */
    int insert(SysPermissionForm form);

    /**
     * 修改权限信息
     * @param form
     * @return
     */
    Integer update(SysPermissionForm form);

    /**
     * 批量删除权限
     * @param ids
     * @return
     */
    Integer batchDelete(@Param("ids") List<Integer> ids);

    /**
     * 根据id查子容器的所有id
     * @param ids
     * @return
     */
    List<Integer> selectAllChildId(@Param("ids") List<Integer> ids);

    /**
     * 批量删除关系表
     * @param ids
     * @return
     */
    Integer batchDeleteRel(@Param("ids") List<Integer> ids);
}