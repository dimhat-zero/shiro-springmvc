package org.dimhat.example.service;

import java.util.List;

import org.dimhat.example.entity.Role;

/**
 * 角色服务接口
 * @author dimhat
 * @date 2015年12月30日 上午12:33:16
 * @version 1.0
 */
public interface RoleService {

    Role createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void correlatePermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void uncorrelatePermissions(Long roleId, Long... permissionIds);

    List<Role> findAll();

    Role findOne(Long id);
}
