package org.dimhat.example.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.dimhat.example.dao.UrlFilterDao;
import org.dimhat.example.entity.UrlFilter;
import org.dimhat.example.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 动态URL权限控制，
 * 在进行新增、修改、删除时会调用initFilterChain来重新初始化Shiro的URL拦截器链
 * @author dimhat
 * @date 2015年12月30日 下午11:53:05
 * @version 1.0
 */
@Service
@Transactional
public class UrlFilterServiceImpl implements UrlFilterService {

    @Autowired
    private UrlFilterDao           urlFilterDao;

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

    @Override
    public UrlFilter createUrlFilter(UrlFilter urlFilter) {
        urlFilter = urlFilterDao.save(urlFilter);
        initFilterChain();
        return urlFilter;
    }

    @Override
    public void updateUrlFilter(UrlFilter urlFilter) {
        urlFilterDao.update(urlFilter);
        initFilterChain();
    }

    @Override
    public void deleteUrlFilter(Long urlFilterId) {
        urlFilterDao.delete(urlFilterId);
        initFilterChain();
    }

    @Override
    public UrlFilter findOne(Long urlFilterId) {
        return urlFilterDao.findOne(urlFilterId);
    }

    @Override
    public List<UrlFilter> findAll() {
        return urlFilterDao.findAll();
    }

    /**
     * 初始化过滤器链
     */
    @PostConstruct
    public void initFilterChain() {
        shiroFilerChainManager.initFilterChains(findAll());
    }
}
