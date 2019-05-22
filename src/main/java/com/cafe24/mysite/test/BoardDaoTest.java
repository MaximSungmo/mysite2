package com.cafe24.mysite.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;

public class BoardDaoTest {

	BoardDao dao = new BoardDao();
	
	@Test
	public void insert() throws SQLException{
		List<BoardVo> list =dao.getList(new PageVo(1, 0, 20, 17, 5));
		
		
		for (BoardVo boardVo : list) {
			System.out.println(boardVo.getNo());
		}
		
		
	}
	
}
