package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login()
	{
		return "user/login";
	}
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join()
	{
		
		return "user/join";
	}
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		
		if(userService.join(userVo)){
			return "user/joinsuccess";
		}
		
		return "user/join";
	}
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkId(@RequestParam(value="id", required=true, defaultValue="") String id)
	{
		boolean exist=userService.existId(id);
		
		return JSONResult.success(exist);
		
	}
	
}
