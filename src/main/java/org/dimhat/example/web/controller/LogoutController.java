package org.dimhat.example.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping(method=RequestMethod.GET)
	public String doGet(HttpServletRequest request,String ref){
		SecurityUtils.getSubject().logout();
		if("toolbar".equals(ref)){
			String referer = request.getHeader("referer");
			System.out.println(referer+"退出成功");
			return "redirect:"+referer;
		}
		return "redirect:";
	}
}
