package org.dimhat.example.shiro.spring;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * 自定义实现路径匹配拦截器链
 * @author dimhat
 * @date 2015年12月31日 上午12:21:52
 * @version 1.0
 */
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {
	
	private Logger logger=Logger.getLogger(CustomPathMatchingFilterChainResolver.class);

    private CustomDefaultFilterChainManager customDefaultFilterChainManager;

    public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
        this.customDefaultFilterChainManager = customDefaultFilterChainManager;
        setFilterChainManager(customDefaultFilterChainManager);
    }

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String requestURI = getPathWithinApplication(request);

        //the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
        //as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {

            // If the path does match, then pass on to the subclass implementation for specific checks:
        	logger.info("ready to match path pattern[" + pathPattern + "]");
            if (pathMatches(pathPattern, requestURI)) {
            	logger.info("[ok] Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  "
                        + "Utilizing corresponding filter chain...");
                return customDefaultFilterChainManager.proxy(originalChain, pathPattern);
            }
        }

        return null;
    }
}
