package org.dimhat.example.service.impl;

import java.util.List;
import java.util.Set;

import org.dimhat.example.dao.UserDao;
import org.dimhat.example.entity.User;
import org.dimhat.example.exception.MyException;
import org.dimhat.example.service.PasswordHelper;
import org.dimhat.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现类
 * @author dimhat
 * @date 2015年12月30日 上午12:39:18
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao        userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    /** 
     * @see org.dimhat.example.service.UserService#register(org.dimhat.example.entity.User)
     */
    @Override
    public User register(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.save(user);
    }

    
    @Override
    public User login(String username,String password){
    	User user = userDao.findByUsername(username);
    	if(user==null){
    		throw new MyException("找不到用户");
    	}
    	String encryptPassword = user.getPassword();
    	user.setPassword(password);
    	passwordHelper.encryptPassword(user);
    	if(encryptPassword.equals(user.getPassword())){
    		return user;
    	}
    	throw new MyException("密码不匹配");
    }

    /** 
     * @see org.dimhat.example.service.UserService#changePassword(java.lang.Long, java.lang.String)
     */
    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    /** 
     * @see org.dimhat.example.service.UserService#correlateRoles(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void correlateRoles(Long userId, Long... roleIds) {
        userDao.correlateRoles(userId, roleIds);
    }

    /** 
     * @see org.dimhat.example.service.UserService#uncorrelateRoles(java.lang.Long, java.lang.Long[])
     */
    @Override
    public void uncorrelateRoles(Long userId, Long... roleIds) {
        userDao.uncorrelateRoles(userId, roleIds);
    }

    /** 
     * @see org.dimhat.example.service.UserService#findOne(java.lang.Long)
     */
    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    /** 
     * @see org.dimhat.example.service.UserService#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /** 
     * @see org.dimhat.example.service.UserService#findRoles(java.lang.String)
     */
    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    /** 
     * @see org.dimhat.example.service.UserService#findPermissions(java.lang.String)
     */
    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }


	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}


	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}


	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}


	@Override
	public void deleteUser(Long id) {
		userDao.delete(id);
	}

}
