package org.dimhat.example.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.dimhat.example.entity.User;
import org.dimhat.example.service.PasswordHelper;
import org.springframework.stereotype.Component;

/**
 * 密码加密实现类
 * @author dimhat
 * @date 2015年12月30日 上午12:43:18
 * @version 1.0
 */
@Component
public class PasswordHelperImpl implements PasswordHelper {

    private String    algorithmName  = "md5";
    private final int hashIterations = 2;

    /** 
     * @see org.dimhat.example.service.PasswordHelper#encryptPassword(org.dimhat.example.entity.User)
     */
    @Override
    public void encryptPassword(User user) {
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), hashIterations).toHex();
        user.setPassword(newPassword);
    }

}
