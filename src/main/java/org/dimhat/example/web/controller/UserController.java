package org.dimhat.example.web.controller;


import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger logger=Logger.getLogger(UserController.class);

    /**
     * 需要同时有两个权限才能访问
     */
    @RequestMapping(value = "/update")
    @RequiresPermissions(value={"user:view","user:update"},logical=Logical.AND)
    public String showUpdateUserForm(Model model) {
        return "user/update";
    }
    
    @RequestMapping(value = "/{id}/delete")
    public String deleteUser(@PathVariable("id")Long id) {
    	Subject currentUser = SecurityUtils.getSubject();    
    	//找到这个路径所需的角色和权限进行代码校验
    	
    	if (currentUser.isPermitted("user:delete")) {    
    	    //有资源权限  
    		logger.info("有权限");
    		
    		
    	} else {    
    	    //没有权限  
    		logger.info("没有权限");
    	}   
        return "redirect:/";
    }
}
