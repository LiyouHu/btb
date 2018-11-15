package com.btb.protal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//执行handler方法之前执行此方法
		HttpSession session = request.getSession();
		String accessPath = request.getRequestURL().toString();
		if(session.getAttribute("loginUser")!= null) {	//从session中取得user
			//已经登录
			//放行
			return true ; 
		}
		//经过Controller ,响应到jsp
		//表示从cookie中结算购物车 ； 提前登录此属性不会设置
		session.setAttribute("flag", "cookie");
		response.sendRedirect("/login.html?callback="+accessPath);
		//返回false拦截，返回true拦截；	
		return false;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		//handler执行之后，modleAndView 返回之前执行
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//modelAndView执行之后执行，用于异常处理
		
	}

	

}
