package org.dimhat.example.service.impl;

import org.dimhat.example.dao.RoleDao;
import org.dimhat.example.entity.Role;
import org.dimhat.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色服务实现类
 * @author dimhat
 * @date 2015年12月30日 上午12:50:11
 * @version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /** 
     * @see org.dimhat.example.service.RoleService#createRole(org.dimhat.example.entity.Role)
     */
    @Override
    public Role createRole(Role role) {
        return roleDao.save(role);
    }

    /** 
     * @see org.dimhat.example.service.RoleService#updateRole(org.dimhat.example.entity.Role)
     */
    @Override
    public void updateRole(Role role) {
        roleDao.update(role);
    }

    /** 
     * @see org.dimhat.example.service.RoleService#deleteRole(java.lang.Long)
     */
    @Override
    public void deleteRole(Long roleId) {
        roleDao.delete(roleId);
    }

    /** 
     * @see org.dimhat.example.service.RoleService#correlatePermissions(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void correlatePermissions(Long roleId, Long... permissionIds) {
        roleDao.correlatePermissions(roleId, permissionIds);
    }

    /** 
     * @see org.dimhat.example.service.RoleService#uncorrelatePermissions(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void uncorrelatePermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelatePermissions(roleId, permissionIds);
    }

}
