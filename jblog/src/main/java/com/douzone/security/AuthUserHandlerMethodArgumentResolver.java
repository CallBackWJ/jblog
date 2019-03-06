package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver  implements HandlerMethodArgumentResolver{

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {
		// TODO Auto-generated method stub
		
		if(supportsParameter(parameter)==false)
		{
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//@AuthUser 가 붙어 있고 type이 UserVo
		HttpServletRequest request=(HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session=request.getSession();
		if(session ==null)
		{
			return null;
		}
		
		
		return session.getAttribute("authuser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		AuthUser authUser=parameter.getParameterAnnotation(AuthUser.class);
		
		if(authUser ==null)
		{
			return false;
		}
		//파라미터 타입이 userVo인지 확인
		if(parameter.getParameterType().equals(UserVo.class)==false)
		{
			return false;
		}
		
		return true;
	}

}
