package org.dimhat.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.dimhat.example.entity.UrlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * shiro过滤器链管理器，路径权限管理
 * @author dimhat
 * @date 2015年12月30日 下午8:20:31
 * @version 1.0
 */
@Service
public class ShiroFilerChainManager {

    @Autowired
    private DefaultFilterChainManager    filterChainManager;

    private Map<String, NamedFilterList> defaultFilterChains; //原来已存在的过滤器链

    @PostConstruct
    public void init() {
        defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
    }

    public void initFilterChains(List<UrlFilter> urlFilters) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if (defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        //2、循环URL Filter 注册filter chain
        for (UrlFilter urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            //注册roles filter
            if (!StringUtils.isEmpty(urlFilter.getRoles())) {
                filterChainManager.addToChain(url, "roles", urlFilter.getRoles());
            }
            //注册perms filter
            if (!StringUtils.isEmpty(urlFilter.getPermissions())) {
                filterChainManager.addToChain(url, "perms", urlFilter.getPermissions());
            }
        }

    }

}
