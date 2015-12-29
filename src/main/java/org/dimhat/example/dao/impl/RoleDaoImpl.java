package org.dimhat.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dimhat.example.dao.RoleDao;
import org.dimhat.example.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色DAO实现类
 * @author dimhat
 * @date 2015年12月29日 下午11:15:01
 * @version 1.0
 */
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * @see org.dimhat.example.dao.RoleDao#save(org.dimhat.example.entity.Role)
     */
    @Override
    public Role save(final Role role) {
        final String sql = "insert into sys_roles(role,description,available) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psst = con.prepareStatement(sql, new String[] { "id" });
                int idx = 1;
                psst.setString(idx++, role.getRole());
                psst.setString(idx++, role.getDescription());
                psst.setBoolean(idx++, role.getAvailable());
                return psst;
            }
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    /** 
     * @see org.dimhat.example.dao.RoleDao#update(org.dimhat.example.entity.Role)
     */
    @Override
    public void update(Role role) {
        String sql = "update sys_roles set role=?,description=?,available=? where id=?";
        jdbcTemplate.update(sql, role.getRole(), role.getDescription(), role.getAvailable(), role.getId());
    }

    /** 
     * @see org.dimhat.example.dao.RoleDao#delete(java.lang.Long)
     */
    @Override
    public void delete(Long roleId) {
        String sql = "delete from sys_roles where id = ?";
        jdbcTemplate.update(sql, roleId);
    }

    private boolean exist(Long roleId, Long permissionId) {
        String sql = "select count(1) from sys_roles_permissions where role_id = ? and permission_id = ?";
        int count = jdbcTemplate.queryForInt(sql, roleId, permissionId);
        return count != 0;
    }

    /** 
     * @see org.dimhat.example.dao.RoleDao#correlatePermissions(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void correlatePermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "insert into sys_roles_permissions(role_id,permission_id) values(?,?)";
        for (Long permissionId : permissionIds) {
            if (!exist(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

    /** 
     * @see org.dimhat.example.dao.RoleDao#uncorrelatePermissions(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void uncorrelatePermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
        for (Long permissionId : permissionIds) {
            if (exist(roleId, permissionId)) {
                jdbcTemplate.update(sql, roleId, permissionId);
            }
        }
    }

}
