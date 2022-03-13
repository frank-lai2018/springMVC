package com.frank.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String hello(HttpServletResponse res) {
		Cookie cookie = new Cookie("APPLE", "TESTCOOKIE");
		res.addCookie(cookie);
		return "index";
	}
}
