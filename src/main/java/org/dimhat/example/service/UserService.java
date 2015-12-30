package org.dimhat.example.service;

import java.util.Set;

import org.dimhat.example.entity.User;

/**
 * 用户服务接口
 * @author dimhat
 * @date 2015年12月30日 上午12:28:36
 * @version 1.0
 */
public interface UserService {

    User register(User user);

    User login(String username, String password);

    void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    void correlateRoles(Long userId, Long... roleIds);

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    void uncorrelateRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}
