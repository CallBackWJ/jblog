package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable String id, @PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, ModelMap map) {

		
		long categoryNo = (pathNo1.isPresent()) ? pathNo1.get() : blogService.getDefaultCategory(id);
		long postNo = (pathNo2.isPresent()) ? pathNo2.get() : blogService.getDefaultPost(id); 

		map.addAllAttributes( blogService.getAll(id, categoryNo, postNo));
		return "blog/blog-main";
	}
	
	
	

	@RequestMapping(value="/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable String id,Model model) {
		
		model.addAttribute("blog", blogService.getBlog(id));
		return "blog/blog-admin-basic";
	}
	
	
	
	@RequestMapping(value="/admin/basic", method = RequestMethod.POST)
	public String blogUpdate(BlogVo vo,
			@RequestParam(value="file") MultipartFile file,
			@PathVariable String id) {
		
		String profile=fileuploadService.restore(file);
		if(profile.equals(""))
		{
			profile=blogService.getBlog(id).getLogo();
		}
		vo.setLogo(profile);
		blogService.updateBlog(vo,id); 
		return "redirect:/"+id+"/admin/basic";
	}

	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable String id,Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", blogService.getCategoryList(id));
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping("/admin/category/api")
	public JSONResult createCategory(CategoryVo vo)
	{
		System.out.println("=================vo:::"+vo);
		long no=blogService.addCategory(vo);
		System.out.println("blogService.getCategory(no)===="+blogService.getCategory(no));
		return JSONResult.success(blogService.getCategory(no));
		
	}
	@ResponseBody
	@RequestMapping("/admin/category/api/delete")
	public JSONResult deleteCategory(Long no)
	{
		
		boolean result=blogService.deleteCategory(no);
		return JSONResult.success(result);
		
	}
	
	@ResponseBody
	@RequestMapping("/admin/comment/api")
	public JSONResult comment(CommentVo vo)
	{
		blogService.commentWrite(vo);
		return JSONResult.success(blogService.getComment(vo.getNo()));
		
	}
	
	@RequestMapping(value="/admin/write" , method = RequestMethod.GET)
	public String adminWrite(@PathVariable String id,Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("category", blogService.getCategoryList(id));
		return "blog/blog-admin-write";
	}
	@RequestMapping(value="/admin/write" , method = RequestMethod.POST)
	public String adminWrite(@PathVariable String id,PostVo vo) {
		blogService.postWrite(vo);
		return "redirect:/"+id+"/admin/write";
	}
	

}
