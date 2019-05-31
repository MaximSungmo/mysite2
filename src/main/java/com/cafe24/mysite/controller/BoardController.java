package com.cafe24.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String list(@RequestParam(value = "p") int p, @RequestParam(value = "kwd") String kwd, Model model) {
		final int CONTENT_PER_PAGE = 5;
		int totalCount = boardService.getTotalContentCount();
		PageVo pageVo = new PageVo(p, CONTENT_PER_PAGE, totalCount);
		List<BoardVo> list = boardService.getBoardList(pageVo);
		model.addAttribute("list", list);
		model.addAttribute("pageVo", pageVo);
		System.out.println(pageVo);
		return "/board/list";
	}

	@RequestMapping("/search")
	public String search(@RequestParam(value = "kwd") String kwd, Model model) {
		model.addAttribute("kwd", kwd);
		return "/board/list";
	}

	@Auth(role = Auth.Role.USER)
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@ModelAttribute(value = "boardvo") BoardVo boardvo) {
		System.out.println(boardvo);
		return "/board/write";
	}

//	@RequestMapping(value = "/write", method = RequestMethod.POST)
//	public String write(@RequestParam(value = "user_no") Long user_no,
//			@RequestParam(value = "title") @Valid String title, @RequestParam(value = "content") @Valid String content,
//			@RequestParam(value = "group_no") Long group_no, @RequestParam(value = "order_no") Long order_no,
//			@RequestParam(value = "depth") Long depth, BindingResult result, Model model) {
//
//		BoardVo boardVo = new BoardVo(user_no, title, content, group_no, order_no, depth);
//		Boolean vo = boardService.writeContent(boardVo);
//		System.out.println(boardVo + ":vo");
//		model.addAttribute("no", boardVo);
//		Long no = boardVo.getNo();
//		System.out.println(no + ":no");
//		if (result.hasErrors()) {
//			model.addAllAttributes(result.getModel());
//			return "board/write";
//		}
//		return "redirect:/board/view?no=" + no;
//	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute(value = "boardvo") @Valid BoardVo boardVo, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/write";
		}
		boardService.writeContent(boardVo);
		Long no = boardVo.getNo();
		System.out.println(boardVo);
		return "redirect:/board/view?no=" + boardVo.getNo();
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "no") Long no, Model model) {
		BoardVo vo = boardService.viewContent(new BoardVo(no));
		model.addAttribute("vo", vo);
		return "/board/view";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam(value = "no") Long no, Model model) {
		model.addAttribute("no", no);
		return "/board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(@RequestParam(value = "no") Long no, @RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content, Model model) {
		boardService.updateContent(new BoardVo(no, title, content));
		return "redirect:/board/modify";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "no") Long no, Model model) {
		model.addAttribute("no", no);
		return "/board/delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "no") Long no, @RequestParam(value = "password") String password) {
		boardService.deleteContent(new BoardVo(no));

		return "redirect:/board";
	}

}
