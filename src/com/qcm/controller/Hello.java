package com.qcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/big")
public class Hello {
	@RequestMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	public void init() {
		System.out.println("***************************");
		System.out.println("初始化-控制层-Hello");
	}

}
