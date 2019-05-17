package com.cafe24.mysite.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler( Exception.class )
	public void handleException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e
	) throws ServletException, IOException {
		//1. 로깅 
		e.printStackTrace();
		//로그가 생기면 FileWriter로 로그파일을 만들어주어야 한다. 
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//LOGGER.error(errors.toString());	
		
		System.out.println(errors.toString());
		
		//2. 안내 페이지 가기 + 정상종료(Response)
		request.setAttribute("uri", request.getRequestURI());
		request.setAttribute("exception", errors);		
		
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);;
		
		
	}
	
	
}
