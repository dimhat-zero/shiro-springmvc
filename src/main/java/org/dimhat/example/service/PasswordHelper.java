package org.dimhat.example.service;

import org.dimhat.example.entity.User;

/**
 * TODO
 * @author dimhat
 * @date 2015年12月30日 上午12:42:21
 * @version 1.0
 */
public interface PasswordHelper {

    void encryptPassword(User user);

}
