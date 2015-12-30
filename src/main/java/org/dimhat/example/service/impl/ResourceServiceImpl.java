package org.dimhat.example.service.impl;

import java.util.List;

import org.dimhat.example.dao.ResourceDao;
import org.dimhat.example.entity.Resource;
import org.dimhat.example.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限服务实现类
 * @author dimhat
 * @date 2015年12月30日 上午12:52:10
 * @version 1.0
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    /** 
     * @see org.dimhat.example.service.ResourceService#createResource(org.dimhat.example.entity.Resource)
     */
    @Override
    public Resource createResource(Resource resource) {
        return resourceDao.save(resource);
    }

    /** 
     * @see org.dimhat.example.service.ResourceService#updateResource(org.dimhat.example.entity.Resource)
     */
    @Override
    public void updateResource(Resource resource) {
        resourceDao.update(resource);
    }

    /** 
     * @see org.dimhat.example.service.ResourceService#deleteResource(java.lang.Long)
     */
    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.delete(resourceId);
    }

    /** 
     * @see org.dimhat.example.service.ResourceService#findAll()
     */
    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    /** 
     * @see org.dimhat.example.service.ResourceService#findOne(java.lang.Long)
     */
    @Override
    public Resource findOne(Long parentId) {
        return resourceDao.findOne(parentId);
    }

}
