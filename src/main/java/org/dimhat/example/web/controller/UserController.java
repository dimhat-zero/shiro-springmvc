package org.dimhat.example.web.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 需要同时有两个权限才能访问
     */
    @RequestMapping(value = "/update")
    @RequiresPermissions(value={"user:view","user:update"},logical=Logical.AND)
    public String showUpdateUserForm(Model model) {
        return "user/update";
    }
}
