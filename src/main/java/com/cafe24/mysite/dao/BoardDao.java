package com.cafe24.mysite.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.PageVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList(PageVo pageVo) {
		List<BoardVo> result = sqlSession.selectList("board.getList", pageVo);
		return result;
	};
	
	public int getTotalContentCount() {
		int totalCount = sqlSession.selectOne("board.getTotalContentCount");
		return totalCount;
	}
	

	public BoardVo viewContent(BoardVo vo) {
		BoardVo result = sqlSession.selectOne("board.viewcontent", vo);
		return result;
	};
	
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return 1== count;
	};

	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.updatecontent", vo);
		return 1 == count;
	};

	public Boolean delete(BoardVo vo) {
		int count = sqlSession.delete("board.delete",vo);
		return 1 == count;
	};

}
