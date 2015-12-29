package org.dimhat.example.entity;

import java.io.Serializable;

/**
 * 角色权限关系
 * @author dimhat
 * @date 2015年12月29日 下午10:53:20
 * @version 1.0
 */
public class RolePermission implements Serializable {

    private Long roleId;

    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /** 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
        RolePermission other = (RolePermission) obj;
        if (permissionId == null) {
            if (other.permissionId != null)
                return false;
        } else if (!permissionId.equals(other.permissionId))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        return true;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RolePermission [roleId=").append(roleId).append(", permissionId=").append(permissionId)
            .append("]");
        return builder.toString();
    }

}
