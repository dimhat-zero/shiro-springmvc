package org.dimhat.example.dao;

import org.dimhat.example.entity.Permission;

/**
 * 权限DAO
 * @author dimhat
 * @date 2015年12月29日 下午11:11:13
 * @version 1.0
 */
public interface PermissionDao {

    Permission save(Permission permission);

    void update(Permission permission);

    void delete(Long permissionId);
}
