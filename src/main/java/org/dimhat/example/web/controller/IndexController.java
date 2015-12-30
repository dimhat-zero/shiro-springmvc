package org.dimhat.example.web.controller;

import org.dimhat.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index*")
public class IndexController {

	@RequestMapping(method=RequestMethod.GET)
	public String index(@ModelAttribute("user")User user){
		return "index";
	}
}
