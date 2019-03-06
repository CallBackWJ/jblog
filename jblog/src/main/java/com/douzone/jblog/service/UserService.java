package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.CommentDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PostDao postDao;
	
	
	
	public boolean join(UserVo userVo) {
		userDao.insert(userVo);
		blogDao.insert(userVo);
		CategoryVo categoryVo=new CategoryVo();
		categoryVo.setName("미분류");
		categoryVo.setDescription("기본 카테고리입니다.");
		categoryVo.setUser_no(userVo.getNo());
		categoryDao.insert(categoryVo);
		PostVo postVo=new PostVo();
		postVo.setTitle("JBLOG 가이드");
		postVo.setContent("JBLOG에서는 자신의 블러그를 간단하게 만드실수 있습니다.");
		postVo.setCategory_no(categoryVo.getNo());
		postDao.insert(postVo);
		CommentVo commentVo=new CommentVo();
		commentVo.setContent("첫 댓글입니다.");
		commentVo.setPost_no(postVo.getNo());
		commentDao.insert(commentVo);
		
		return  true;
		
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.getByIdAndPassword(userVo);
		
	}

	public boolean existId(String id) {
		UserVo userVo = userDao.getById(id);
		return userVo != null;
	}
}
