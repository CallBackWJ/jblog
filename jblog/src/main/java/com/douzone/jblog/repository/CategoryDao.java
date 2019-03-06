package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	public boolean insert(CategoryVo categoryVo) {
		return 1 == sqlSession.insert("category.insert", categoryVo);
	}
	public List<CategoryVo> getList(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("category.list", no);
	}
	public CategoryVo get(long no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("category.get", no);
	}
	public boolean delete(Long no) {
		// TODO Auto-generated method stub
		return 1 == sqlSession.delete("category.delete", no);
	}

}
