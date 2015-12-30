package org.dimhat.example.entity;

import java.io.Serializable;

/**
 * 访问资源需要权限，资源等价于权限
 * @author dimhat
 * @date 2015年12月29日 下午10:50:31
 * @version 1.0
 */
public class Resource implements Serializable {

    private Long         id;

    private String       permission;

    private String       description;

    private Boolean      available = Boolean.TRUE;

    private ResourceType type      = ResourceType.menu; //资源类型

    private Long         parentId;                     //父编号

    private String       parentIds;                    //父编号路径，pid/id/

    private String       url;                          //资源路径，菜单才有

    public static enum ResourceType {
        menu("菜单"), button("按钮");

        private final String info;

        private ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /** 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((available == null) ? 0 : available.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((permission == null) ? 0 : permission.hashCode());
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
        Resource other = (Resource) obj;
        if (available == null) {
            if (other.available != null)
                return false;
        } else if (!available.equals(other.available))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (permission == null) {
            if (other.permission != null)
                return false;
        } else if (!permission.equals(other.permission))
            return false;
        return true;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Permission [id=").append(id).append(", permission=").append(permission)
            .append(", description=").append(description).append(", available=").append(available).append("]");
        return builder.toString();
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 是否是根节点
     * @return  父节点为0为true，否则为false
     */
    public boolean isRootNode() {
        return parentId == 0;
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
}
