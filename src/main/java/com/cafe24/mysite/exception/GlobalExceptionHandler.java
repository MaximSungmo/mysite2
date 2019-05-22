package com.cafe24.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

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
//		LOGGER.error(errors.toString());	
		
		System.out.println(errors.toString());
		
		
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
			//JSON 응답 
			response.setStatus(HttpServletResponse.SC_OK);
			JSONResult jsonresult = JSONResult.fail(errors.toString());
			String result = new ObjectMapper().writeValueAsString(jsonresult);
			
			System.out.println(result);
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
			
		}else {
			//2. 안내 페이지 가기 + 정상종료(Response)
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors);		
			
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}		
	}
	
	
}
