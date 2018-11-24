package com.xiaobai.xblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String user = (String)request.getSession().getAttribute("_LOGIN_USER_");
		if(null==user||user.trim().equals("")) {
			return false;
		}
		return true;
		
	}
}
