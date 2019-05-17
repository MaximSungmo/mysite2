package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public boolean deleteContent(GuestbookVo guestbookVo) {
		return guestbookDao.delete(guestbookVo);
	}
	
	public boolean writeContent(GuestbookVo guestbookVo) {
		return guestbookDao.insert(guestbookVo);
		
	}
	
	public List<GuestbookVo> getContentsList() {
		return guestbookDao.getList();
	}
	
	
	
}
