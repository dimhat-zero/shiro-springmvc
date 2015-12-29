package org.dimhat.example.dao;

import org.dimhat.example.entity.Role;

/**
 * 角色DAO
 * @author dimhat
 * @date 2015年12月29日 下午11:03:10
 * @version 1.0
 */
public interface RoleDao {

    Role save(Role role);

    void update(Role role);

    void delete(Long roleId);

    /**
     * 关联角色和权限
     * @param roleId
     * @param permissionIds  
     */
    void correlatePermissions(Long roleId, Long... permissionIds);

    /**
     * 取消关联角色和权限
     * @param roleId
     * @param permissionIds  
     */
    void uncorrelatePermissions(Long roleId, Long... permissionIds);
}
