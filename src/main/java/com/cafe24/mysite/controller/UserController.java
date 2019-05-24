package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {	
		return "user/join";
	}
	/*UserVo 에 Valid 체크를 위하여 어노테이션 표시, BindingResult 생성*/ 
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model 
			) {
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
			/*AllAttributes 는 map으로 생성, addAttribute 는 여러번 돌아서 add해야함 */
			model.addAllAttributes(result.getModel());
			
			return "user/join";
		}
		
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
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(
			@AuthUser UserVo authUser,
			Model model
	) {
		UserVo userVo = (UserVo) userService.getUser(authUser.getNo());
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
