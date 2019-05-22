package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guest.delete", vo);		
		return 1 == count;
	}
	
	public Boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guest.insert", vo);
		return 1 == count;
	}	
	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> result = sqlSession.selectList("guest.getList");
		return result;
	}	
	
}
