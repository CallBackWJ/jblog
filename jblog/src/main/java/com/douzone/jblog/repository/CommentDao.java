package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CommentDao {
	@Autowired
	private SqlSession sqlSession;
	public boolean insert(CommentVo commentVo) {
		return 1 == sqlSession.insert("comment.insert", commentVo);
	}
	public List<CommentVo> getList(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("comment.list", no);
	}
	public CommentVo get(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("comment.get", no);
	}
}
