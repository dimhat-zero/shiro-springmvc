package org.dimhat.example.web.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dimhat.example.entity.User;
import org.dimhat.example.service.UserService;
import org.dimhat.example.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登陆控制器
 * @author dimhat
 * @date 2015年12月28日 下午10:33:16
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@ModelAttribute("user") LoginForm user) {
    	System.out.println("get login");
        return "login";
    }

    /**
     * 如果账户密码正确，不会执行这个方法（shiro搞定了）
     *     如果是跳转，则直接到目标页面
     *     如果不是跳转，则转到首页
     * 如果不正确，则会执行
     *     如果验证通过，会执行subject.login(token);
     *     如果验证不通过，直接返回
     */
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(Model model,@Valid @ModelAttribute("user") LoginForm user,BindingResult errors) {
    	System.out.println("post login");
    	if(errors.hasErrors()){//后台表单验证
    		return doGet(user);
    	}
    	
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        System.out.println("开始登录");
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            error = "其他错误：" + e.getMessage();
        }
        System.out.println("登录完成");
        
        if (error != null) {//出错了，返回登录页面
            model.addAttribute("error", error);
            return doGet(user);
        } else {//登录成功
        	User realUser = userService.login(user.getUsername(), user.getPassword());
            subject.getSession().setAttribute("user", realUser);//对象放入session
            System.out.println("登录成功");
            return "success";
        }
    }
}
