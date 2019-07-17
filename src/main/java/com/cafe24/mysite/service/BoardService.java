package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.PageVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getBoardList(PageVo pageVo) {
		return boardDao.getList(pageVo); 
	}
	
	public BoardVo viewContent(BoardVo vo) {
		return boardDao.viewContent(vo);
	}
	
	public Boolean updateContent(BoardVo vo) {
		return boardDao.update(vo);
	}

	public Boolean writeContent(BoardVo vo) {
		System.out.println("db 들가기 전 vo : "+ vo);
		return boardDao.insert(vo);
	}
	public Boolean replyContent(BoardVo vo) {
		System.out.println("reply+ db 들가기 전 vo : "+ vo);
		return boardDao.reply_insert(vo);
	}
	
	public boolean deleteContent(BoardVo boardVo) {
		return boardDao.delete(boardVo);
	}
	
	public int getTotalContentCount() {
		return boardDao.getTotalContentCount();
	}
	
}

	
	