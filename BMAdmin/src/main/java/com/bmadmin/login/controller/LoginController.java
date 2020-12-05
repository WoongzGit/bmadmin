package com.bmadmin.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value= {"", "admin/", "admin", "admin/login", "admin/login/", "admin/login/login.html"})
	public String index () {
		logger.info("index");
		return "login/login";
	}
}
