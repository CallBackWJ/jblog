package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		UserVo temp=new UserVo();
		temp.setId(request.getParameter("id"));
		temp.setPassword(request.getParameter("password"));
		
		UserVo vo=userService.login(temp);
		if(vo==null)
		{
			//인증실패
			request.setAttribute("result", "fail");
			response.sendRedirect(request.getContextPath()+"/user/login?result=fail");
			
			return false;
		}
		//인증성공 -> 인증처리
		HttpSession session=request.getSession(true);
		session.setAttribute("authuser", vo);
		
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}

	
}
