package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	public boolean insert(PostVo postVo) {
		return 1 == sqlSession.insert("post.insert", postVo);
	}
	public List<PostVo> getList(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("post.list", no);
	}
	public PostVo get(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("post.get", no);
	}
}
