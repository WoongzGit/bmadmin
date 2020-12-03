package com.bmadmin.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/board")
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value="{divn}")
	public String index (@PathVariable String divn) {
		logger.info("index");
		return "board/" + divn;
	}
}
