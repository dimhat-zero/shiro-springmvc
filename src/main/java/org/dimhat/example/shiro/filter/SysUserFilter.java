package org.dimhat.example.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.dimhat.example.Constants;
import org.dimhat.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自动注入user过滤器
 * 此项目用session中的数据，不用这里的数据
 * @author dimhat
 * @date 2015年12月30日 下午9:24:38
 * @version 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
                                                                                                       throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
