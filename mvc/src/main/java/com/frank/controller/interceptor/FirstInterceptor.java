package com.frank.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FirstInterceptor implements HandlerInterceptor{

	//控制器方法執行之前執行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("FirstInterceptor ---> preHandle");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	//控制器方法執行之後執行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("FirstInterceptor ---> postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	//在視圖渲染之後執行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("FirstInterceptor ---> afterCompletion");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
