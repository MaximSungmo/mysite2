package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo",no);
	}

	public UserVo get(String email, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
	}


	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		return 1 == count;
	}

	public Boolean delete(GuestbookVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "delete from guestbook where no=? and password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			int count = pstmt.executeUpdate();
			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
	public boolean update(UserVo vo) {		
		int count = sqlSession.update("user.update", vo);
		return 1 == count;
	}

}
