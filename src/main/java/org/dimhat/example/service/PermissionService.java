package org.dimhat.example.service;

import org.dimhat.example.entity.Permission;

/**
 * 权限服务接口
 * @author dimhat
 * @date 2015年12月30日 上午12:37:16
 * @version 1.0
 */
public interface PermissionService {

    Permission createPermission(Permission permission);

    void updatePermission(Permission permission);

    void deletePermission(Long permissionId);

}
