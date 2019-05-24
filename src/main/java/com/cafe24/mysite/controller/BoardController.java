package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.PageVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@RequestMapping("")
	public String list(
			@RequestParam(value="p") int p,
			Model model
			) {	
		
		final int CONTENT_PER_PAGE = 5;
		int totalCount = boardService.getTotalContentCount();
		PageVo pageVo= new PageVo(p, CONTENT_PER_PAGE, totalCount);
		List<BoardVo> list = boardService.getBoardList(pageVo);
		model.addAttribute("list", list);
		model.addAttribute("pageVo", pageVo);
		System.out.println(pageVo);
		return "/board/list";
	}

	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/write", method= RequestMethod.GET)
	public String write() {		
		return "/board/write";
	}
	
	@RequestMapping(value = "/write", method= RequestMethod.POST)
	public String write(
			@RequestParam(value="user_no") Long user_no,
			@RequestParam(value="title") @Valid  String title,
			@RequestParam(value="content") @Valid  String content,
			Model model
			) {
		BoardVo boardVo = new BoardVo(user_no, title, content);
		Boolean vo = boardService.writeContent(boardVo);
		System.out.println(vo+":vo");
		model.addAttribute("no", boardVo);
		Long no =boardVo.getNo();
		System.out.println(no+":no");
		return "redirect:/board/view?no="+no;
	}
	
	@RequestMapping(value= "/view", method = RequestMethod.GET)
	public String view(
			@RequestParam(value="no") Long no,
			Model model
			) {
		BoardVo vo = boardService.viewContent(new BoardVo(no));
		model.addAttribute("vo", vo);
		return "/board/view";
	}

	
	@RequestMapping(value = "/modify", method = RequestMethod.GET )
	public String modify(
			@RequestParam(value="no") Long no,
			Model model
	) {
		BoardVo vo = boardService.viewContent(new BoardVo(no));
		model.addAttribute("vo", vo);
		return "/board/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST )
	public String modify(
			@RequestParam(value="board_no") Long no,
			@RequestParam(value="title") String title,
			@RequestParam(value="content") String content,
			Model model
	) {
		boardService.updateContent(new BoardVo(no, title, content));
		return "redirect:/board/modify";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam(value="no") Long no,
			Model model
	) {
		model.addAttribute("no", no);
		return "/board/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			@RequestParam(value="no") Long no,
			@RequestParam(value="password") String password
	) {
			boardService.deleteContent(new BoardVo(no));
		
		return "redirect:/board";
	}
	
}


