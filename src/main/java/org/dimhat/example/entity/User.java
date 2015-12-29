package org.dimhat.example.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户
 * @author dimhat
 * @date 2015年12月28日 下午10:36:00
 * @version 1.0
 */
public class User implements Serializable {

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

    /** 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
        result = prime * result + ((locked == null) ? 0 : locked.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastLogin == null) {
            if (other.lastLogin != null)
                return false;
        } else if (!lastLogin.equals(other.lastLogin))
            return false;
        if (locked == null) {
            if (other.locked != null)
                return false;
        } else if (!locked.equals(other.locked))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
