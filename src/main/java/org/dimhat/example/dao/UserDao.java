package org.dimhat.example.dao;

import java.util.Set;

import org.dimhat.example.entity.User;

/**
 * 用户DAO
 * @author dimhat
 * @date 2015年12月29日 下午10:55:54
 * @version 1.0
 */
public interface UserDao {

    User save(User user);

    void update(User user);

    void delete(Long userId);

    /**
     * 关联角色
     * @param userId
     * @param roleIds  
     */
    void correlateRoles(Long userId, Long... roleIds);

    /**
     * 取消关联角色
     * @param userId
     * @param roleIds  
     */
    void uncorrelateRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
