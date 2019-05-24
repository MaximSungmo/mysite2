package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="", method=RequestMethod.GET )
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getContentsList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String insert(
			@RequestParam(value="name", required=true, defaultValue="" ) String name,
			@RequestParam(value="password", required=true, defaultValue="" ) String password,
			@RequestParam(value="contents", required=true, defaultValue="") String contents
	) {
		guestbookService.writeContent(new GuestbookVo(name, password, contents));
		return "redirect:/guestbook";	
	}
	
//	@RequestMapping(value="/delete", method=RequestMethod.GET)
//	public String delete(
//			@RequestParam(value="no") Long no,
//			Model model
//	) {
//		model.addAttribute("no", no);
//		return "guestbook/delete";
//	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(
			@ModelAttribute(value="gvo") GuestbookVo vo
	) {
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			@RequestParam(value="no") Long no,
			@RequestParam(value="password") String password
	) {
		guestbookService.deleteContent(new GuestbookVo(no, password));
		return "redirect:/guestbook";
	}
	
	
	
}
