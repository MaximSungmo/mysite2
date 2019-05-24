package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUesrHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	

	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		/*authUser 값을 처리하지 않음으로써 return하여 dispatcherServlet이 처리하도록 넘김*/
		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		
		HttpSession session = request.getSession();
		
		if(session ==null) {
			return null;
		}
		
		return session.getAttribute("authUser");
	}
	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		// @AuthUser가 붙어 있지 않는 상태
		if(authUser == null) {
			return false;
		}
		
		
		// parameter type이 UserVo가 맞는 지 확인하기
		if(parameter.getParameterType().equals(UserVo.class)==false) {
			return false;
		}
		return true;
	}
}
