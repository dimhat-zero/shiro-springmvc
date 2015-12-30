package org.dimhat.example.web.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	/**
	 * 需要admin角色才能访问
	 */
	@RequiresRoles("admin")
	@RequestMapping(value="manage",method=RequestMethod.GET)
	public String showManagePage(){
		return "admin/manage";
	}
}
