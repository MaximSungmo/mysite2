package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping({"/", "/main"})
	public String main() {
		return "main/index";
	}
	
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1> 안녕하세요! </h1>";
	}
	
	
	@ResponseBody
	@RequestMapping("/hello2")
	public UserVo hello2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setName("맥심");
		vo.setEmail("max@gmail.com");
		return vo;
	}
}
