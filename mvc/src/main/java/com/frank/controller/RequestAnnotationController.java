package com.frank.controller;


import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/annotation")
@Controller
public class RequestAnnotationController {
	
	@RequestMapping("/cookie")
	public String testCookie(@CookieValue("APPLE") String cookie) {
		System.out.println("cookie:"+cookie);
		return "cookie";
	}
	
	@RequestMapping("/basecookie")
	public String basecookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println("cookie:"+Arrays.toString(cookies));
		Cookie cookie1 = this.getCookie(cookies, "APPLE");
		System.out.println("cookie1:"+cookie1.getValue());
		Cookie cookie2 = this.getCookie(cookies, "APPLE1");
		System.out.println("cookie1:"+cookie2);
		return "cookie";
	}
	
    @RequestMapping("/header")
    public String testParam(@RequestHeader(value = "Cookie", required = true, defaultValue = "haha") String host
            ){
        System.out.println("host:"+host);
        return "header";
    }
	
	private Cookie getCookie(Cookie[] cookies,String name) {
		if(cookies == null || cookies.length == 0) {
			return null;
		}
		Optional<Cookie> findFirst = 
				Arrays.stream(cookies).filter(cookie -> name.equals(cookie.getName())).findFirst();
		if(findFirst.isPresent()) {
			return findFirst.get();
		}
		return null;
	}

}
