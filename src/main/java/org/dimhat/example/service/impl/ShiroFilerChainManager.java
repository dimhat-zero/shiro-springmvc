package org.dimhat.example.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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

	private Logger logger = Logger.getLogger(ShiroFilerChainManager.class);
	
    @Autowired
    private DefaultFilterChainManager    filterChainManager;

    private Map<String, NamedFilterList> defaultFilterChains; //原来已存在的过滤器链

    @PostConstruct
    public void init() {
        defaultFilterChains = new LinkedHashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
        //下面是日志
        Iterator<Entry<String, NamedFilterList>> iterator = filterChainManager.getFilterChains().entrySet().iterator();
        logger.info("[ save default filter chains ... ]");
        while(iterator.hasNext()){
        	Entry<String, NamedFilterList> entry = iterator.next();
        	StringBuffer sb=new StringBuffer();
        	for(Object obj:entry.getValue().toArray()){
        		sb.append(obj+",");
        	}
        	logger.info("default filter chains["+entry.getKey()+":"+sb.substring(0, sb.length()-1)+"]");
        }
    }

    /**
     * chains eg:keys:/static/**, value:authc,user,roles[admin] 
     */
    public void initFilterChains(List<UrlFilter> urlFilters) {
        //1、首先删除以前老的filter chain并注册默认的
    	logger.info("[ clear filter chains... ]");
        filterChainManager.getFilterChains().clear();
        
        logger.info("[ add custom filter chains... ]");
        //2、循环URL Filter 注册filter chain
        for (UrlFilter urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            //注册roles filter
            if (!StringUtils.isEmpty(urlFilter.getRoles())) {
            	logger.info("add filter roles chian [url="+url+",roles="+urlFilter.getRoles()+"]");
                filterChainManager.addToChain(url, "roles", urlFilter.getRoles());
            }
            //注册perms filter
            if (!StringUtils.isEmpty(urlFilter.getPermissions())) {
            	logger.info("add filter perms chian [url="+url+",perms="+urlFilter.getPermissions()+"]");
                filterChainManager.addToChain(url, "perms", urlFilter.getPermissions());
            }
        }
        
        logger.info("[ add default filter chains... ]");
        if (defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
        logger.info("[ finish init filter chians with custom... ]");
    }

}
