package com.bmadmin.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/login")
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping()
	public String index () {
		logger.info("index");
		return "login/index";
	}
}
