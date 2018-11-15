package com.btb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/index")
	public String index() {
		return "index" ;
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		//跳转到jsp的页面
		return page;
	}
	
	
}
