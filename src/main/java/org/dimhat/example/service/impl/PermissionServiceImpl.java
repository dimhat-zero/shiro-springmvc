package org.dimhat.example.service.impl;

import org.dimhat.example.dao.PermissionDao;
import org.dimhat.example.entity.Permission;
import org.dimhat.example.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限服务实现类
 * @author dimhat
 * @date 2015年12月30日 上午12:52:10
 * @version 1.0
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /** 
     * @see org.dimhat.example.service.PermissionService#createPermission(org.dimhat.example.entity.Permission)
     */
    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.save(permission);
    }

    /** 
     * @see org.dimhat.example.service.PermissionService#updatePermission(org.dimhat.example.entity.Permission)
     */
    @Override
    public void updatePermission(Permission permission) {
        permissionDao.update(permission);
    }

    /** 
     * @see org.dimhat.example.service.PermissionService#deletePermission(java.lang.Long)
     */
    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.delete(permissionId);
    }

}
