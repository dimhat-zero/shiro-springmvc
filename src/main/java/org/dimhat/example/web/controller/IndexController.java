package org.dimhat.example.web.controller;

import org.apache.log4j.Logger;
import org.dimhat.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	private Logger logger=Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("user") User user) {
    	logger.info("get request[/] and user is "+user);
        return "index";
    }
    
}
