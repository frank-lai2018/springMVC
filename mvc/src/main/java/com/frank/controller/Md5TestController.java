package com.frank.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("md5")
@Controller
public class Md5TestController {

	@RequestMapping(value="/springMd5",method = RequestMethod.POST)
	public String springMd5(String str,Map<String,String> map) {
		map.put("res", this.toSpringMd5(str));
		return "md5";
	}
	
	private String toSpringMd5(String str) {
		String md5 = "";
		md5 = DigestUtils.md5DigestAsHex(str.getBytes());
		
		return md5;
	}
}
