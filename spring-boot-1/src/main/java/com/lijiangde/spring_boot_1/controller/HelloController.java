package com.lijiangde.spring_boot_1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("hello2")
public class HelloController {
	@RequestMapping("")
	public String hello() {
		return "SpringBoot，hello演示";
	}
}
