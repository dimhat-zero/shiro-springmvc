package org.dimhat.example.entity;

import java.sql.Timestamp;

/**
 * 用户表
 * @author dimhat
 * @date 2015年12月28日 下午10:36:00
 * @version 1.0
 */
public class User {

    private Long      id;

    private String    username;

    private String    password;

    private Boolean   locked;

    private Timestamp lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", username=").append(username).append(", password=")
            .append(password).append(", locked=").append(locked).append(", lastLogin=").append(lastLogin).append("]");
        return builder.toString();
    }

}
