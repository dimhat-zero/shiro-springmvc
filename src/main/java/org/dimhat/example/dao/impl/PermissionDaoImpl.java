package org.dimhat.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dimhat.example.dao.PermissionDao;
import org.dimhat.example.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限DAO实现类
 * @author dimhat
 * @date 2015年12月29日 下午11:15:29
 * @version 1.0
 */
@Repository
@Transactional
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * @see org.dimhat.example.dao.PermissionDao#save(org.dimhat.example.entity.Permission)
     */
    @Override
    public Permission save(final Permission permission) {
        final String sql = "insert into sys_permissions(permission,description,available) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psst = con.prepareStatement(sql, new String[] { "id" });
                int idx = 1;
                psst.setString(idx++, permission.getPermission());
                psst.setString(idx++, permission.getDescription());
                psst.setBoolean(idx++, permission.getAvailable());
                return psst;
            }
        }, keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    /** 
     * @see org.dimhat.example.dao.PermissionDao#update(org.dimhat.example.entity.Permission)
     */
    @Override
    public void update(Permission permission) {
        String sql = "update sys_permissions set permission=?,description=?,available=? where id=?";
        jdbcTemplate.update(sql, permission.getPermission(), permission.getDescription(), permission.getAvailable(),
            permission.getId());
    }

    /** 
     * @see org.dimhat.example.dao.PermissionDao#delete(java.lang.Long)
     */
    @Override
    public void delete(Long permissionId) {
        String sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, permissionId);
    }

}
