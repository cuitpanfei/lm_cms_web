package com.lm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	// 默认登录页面
	@RequestMapping(value = "/loginPage")
	public String loginPage() {
		System.out.println("index");
		return "login";
	}

}
