package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo userVo) {
		return 1 == sqlSession.insert("user.insert", userVo);
	}
	
	public UserVo getByIdAndPassword(UserVo userVo)
	{
		return sqlSession.selectOne("user.getByIdAndPassword", userVo);
		
	}

	public UserVo getById(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.getById", id);
	}


}
