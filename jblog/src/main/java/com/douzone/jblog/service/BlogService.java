package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.CommentDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PostDao postDao;
	
	
	public Map<String,Object> getAll(String id, long categoryNo, long postNo) {
		
		 Map<String,Object> map=new HashMap<String,Object>();
		 long userNo=userDao.getById(id).getNo();
		 
		 map.put("id", id);
		 map.put("blog", blogDao.get(userNo));
		 map.put("category", categoryDao.getList(userNo));
		 map.put("postlist", postDao.getList(categoryNo));
		 map.put("post", postDao.get(postNo));
		 map.put("comment", commentDao.getList(postNo));
		 
		return map;
	}


	public Long getDefaultCategory(String id) {
		return categoryDao.getList(userDao.getById(id).getNo()).get(0).getNo();
	}


	public Long getDefaultPost(String id) {
		return postDao.getList(categoryDao.getList(userDao.getById(id).getNo()).get(0).getNo()).get(0).getNo();
	}


	public BlogVo getBlog(String id) {

		return blogDao.get(userDao.getById(id).getNo());
	}


	public void updateBlog(BlogVo vo, String id) {
		vo.setUser_no(userDao.getById(id).getNo());
		blogDao.update(vo);
	}


	public List<CategoryVo> getCategoryList(String id) {
		return categoryDao.getList(userDao.getById(id).getNo());
	}


	public void postWrite(PostVo vo) {
		postDao.insert(vo);
	}


	public long addCategory(CategoryVo vo) {
		categoryDao.insert(vo);
		return vo.getNo();
	}


	public CategoryVo getCategory(long no) {
		return categoryDao.get(no);
	}


	public boolean deleteCategory(Long no) {
		if(postDao.getList(no).size()!=0) return false;
		return categoryDao.delete(no);
	}


	public void commentWrite(CommentVo vo) {
		commentDao.insert(vo);
	}


	public CommentVo getComment(long no) {
		return commentDao.get(no);
	}
	

}
