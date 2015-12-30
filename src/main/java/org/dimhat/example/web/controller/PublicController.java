package org.dimhat.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/public")
public class PublicController {

	/**
	 * 不需要登录即可访问 /public/**=anon
	 */
	@RequestMapping(value="info",method=RequestMethod.GET)
	public String info(){
		return "public/info";
	}
}
