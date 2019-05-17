package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {	
		return "user/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {	
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
			@RequestParam(value="email", required=true, defaultValue="") String email,
			@RequestParam(value="password", required=true, defaultValue="") String password,
			HttpSession session,
			Model model
	) {
		UserVo authUser = userService.getUser(new UserVo(email, password));
		
		if(authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		// Session 처리 
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(
			HttpSession session, 
			Model model
	) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("gender") String gender,
			@RequestParam("no") long no,
			Model model
	) {		
		
		
		userService.update(new UserVo(no, name, password, gender));
		return "redirect:/user/update";
	}
	
	
}
