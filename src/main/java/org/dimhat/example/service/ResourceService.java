package org.dimhat.example.service;

import java.util.List;

import org.dimhat.example.entity.Resource;

/**
 * 权限服务接口
 * @author dimhat
 * @date 2015年12月30日 上午12:37:16
 * @version 1.0
 */
public interface ResourceService {

    Resource createResource(Resource resource);

    void updateResource(Resource resource);

    void deleteResource(Long resourceId);

    List<Resource> findAll();

    Resource findOne(Long parentId);

}
