package org.dimhat.example.web.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 未授权控制器
 * 在AuthorizationFilter的onAccessDenied方法中重定向过来的
 * 
 * 如果没有指定unauthorizedUrl则返回401
 * 否则跳转到指定的链接
 * 
 * @author dimhat
 * @date 2015年12月31日 下午4:05:45
 * @version 1.0
 */
@Controller
@RequestMapping("/unauthorized")
public class UnauthorizedController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView doGet(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("unauthorized");
		return mv;
	}
}
