package org.dimhat.example.dao;

import java.util.List;

import org.dimhat.example.entity.Resource;

/**
 * 权限DAO
 * @author dimhat
 * @date 2015年12月29日 下午11:11:13
 * @version 1.0
 */
public interface ResourceDao {

    Resource save(Resource resource);

    void update(Resource resource);

    /**
     * 删除自己和所有子权限
     * @param resourceId  
     */
    void delete(Long resourceId);

    /**
     * 找到所有权限
     * @return  根据parentIds+id路径升序
     */
    List<Resource> findAll();

    Resource findOne(Long resourceId);
}
