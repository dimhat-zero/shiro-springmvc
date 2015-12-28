package org.dimhat.example.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 * @author dimhat
 * @date 2015年12月28日 下午11:59:14
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/add")
    @RequiresRoles("系统管理员")
    public String add(Model model) {
        return "success";
    }

    @RequestMapping(value = "/add2")
    @RequiresPermissions("user:add")
    public String do_add(Model model) {
        return "success";
    }
}
