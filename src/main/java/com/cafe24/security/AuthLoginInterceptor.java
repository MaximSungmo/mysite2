package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
		/*톰캣의 어플리케이션 컨텍스트에서 웹어플리케이션 컨텍스트에 해당하는 정보를 가진 서블릿 컨텍스트 가져온 뒤 핸들러맵핑된 userService를 가져와서 주입시켜줌 */ 
		ApplicationContext ac =
				WebApplicationContextUtils.
				getWebApplicationContext(request.getServletContext());
	
		UserService userService = ac.getBean(UserService.class);
		
		UserVo userVo = new UserVo();
		userVo.setEmail(email);
		userVo.setPassword(password);
		
		UserVo authUser = userService.getUser(userVo);
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		// session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		
		return true;
	}

}
