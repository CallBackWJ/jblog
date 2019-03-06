package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo userVo) {
		return 1 == sqlSession.insert("blog.insert", userVo);
	}

	public BlogVo get(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("blog.select", no);
	}

	public void update(BlogVo vo) {
		// TODO Auto-generated method stub
		System.out.println("======================================="+vo);
		sqlSession.update("blog.update", vo);
	}

}
