package org.dimhat.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dimhat.example.dao.UserDao;
import org.dimhat.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户DAO实现类
 * @author dimhat
 * @date 2015年12月29日 下午11:14:34
 * @version 1.0
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * @see org.dimhat.example.dao.UserDao#save(org.dimhat.example.entity.User)
     */
    @Override
    public User save(final User user) {
        final String sql = "insert into sys_user(username,password,locked) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {//使用预处理语句

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement psst = con.prepareStatement(sql, new String[] { "id" });
                    int idx = 1;
                    psst.setString(idx++, user.getUsername());
                    psst.setString(idx++, user.getPassword());
                    psst.setBoolean(idx++, user.getLocked());
                    return psst;
                }
            }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#update(org.dimhat.example.entity.User)
     */
    @Override
    public void update(User user) {
        String sql = "update sys_user set username=?,password=?,locked=?,last_login=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getLocked(), user.getLastLogin(),
            user.getId());
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#delete(java.lang.Long)
     */
    @Override
    public void delete(Long userId) {
        String sql = "delete from sys_user where id=?";
        jdbcTemplate.update(sql, userId);
    }

    /**
     * 用户是否已经拥有该角色
     * @param userId
     * @param roleId
     * @return 已拥有为true，未拥有为false
     */
    private boolean exist(Long userId, Long roleId) {
        String sql = "select count(1) from sys_user_role where user_id=? and role_id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId);
        return count != 0;
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#correlateRoles(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void correlateRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into sys_user_role(user_id,role_id) values(?,?)";
        for (Long roleId : roleIds) {
            if (!exist(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#uncorrelateRoles(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void uncorrelateRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete from sys_user_role where userId=? and role_id=?";
        for (Long roleId : roleIds) {
            if (exist(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findOne(java.lang.Long)
     */
    @Override
    public User findOne(Long userId) {
        String sql = "select * from sys_user where id = ?";
        return jdbcTemplate.queryForObject(sql, User.class, userId);
        //return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userId);
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username) {
        String sql = "select * from sys_user where username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findRoles(java.lang.String)
     */
    @Override
    public Set<String> findRoles(String username) {
        String sql = "select role from sys_user u,sys_role r,sys_user_role ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        List<String> roleList = jdbcTemplate.queryForList(sql, String.class, username);
        return new HashSet<>(roleList);
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findPermissions(java.lang.String)
     */
    @Override
    public Set<String> findPermissions(String username) {
        String sql = "select permission from sys_user u,sys_role r,sys_resource p,sys_user_role ur,sys_role_resource rp";
        sql += " where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.resource_id";
        List<String> permissionList = jdbcTemplate.queryForList(sql, String.class, username);
        return new HashSet<>(permissionList);
    }

}
