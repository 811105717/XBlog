package com.xiaobai.xblog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaobai.xblog.pojo.Blog;
import com.xiaobai.xblog.pojo.User;
import com.xiaobai.xblog.service.BlogService;
import com.xiaobai.xblog.service.UserService;

/**
 * 主要的controller 负责主要的路由映射
 * 后期的android项目可能会用这个写接口
 * @author xiaobai
 *
 */
@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;

	/**
	 *处理主页数据，并发送到主页显示 
	 * @param model 要显示的数据
	 * @return 页面名称
	 */
	@RequestMapping(value="/index.action")
	public String toIndex(Model model) {
		System.out.println("successfully!!!");
		List<Blog> blogList = blogService.queryAllBlogs();
		model.addAttribute("blogs", blogList);
		return "index";
	}
	
	/**
	 * 处理需要的数据 然后跳转到个人中心
	 * @param model 可能需要一些参数  ？
	 * @return 个人中心页面
	 */
	@RequestMapping(value="/own/center.action")
	public String selfCenter(Model model) {
		
		return "";
	}
	
	/**
	 * 处理数据然后跳转到个人消息中心
	 * @param model 可能需要一些数据
	 * @return 跳转的页面
	 */
	@RequestMapping(value="/own/message.action")
	public String selfMessage(Model model) {
		
		return "";
	}
	
	/**
	 * 根据关键词搜索
	 * @param kwd 关键词
	 * @param model 搜索的数据 
	 * @return 搜索结果页
	 */
	@RequestMapping(value="/search.action")
	public String searching(String kwd, Model model) {
		model.addAttribute("kwd", kwd); //搜索结果页面的搜索框内的值
		List<Blog> results = blogService.searchByKwd(kwd);
		for (Blog blog : results) {
			System.out.println(blog);
		}
		model.addAttribute("results",results);
		return "result";
	}
	
	/**
	 * 用户登录接口 返回json结果 
	 * @param un 用户名
	 * @param pwd 密码
	 * @return 若成功返回json 中 result 为true  否则为 false  
	 */
	@ResponseBody
	@RequestMapping(value="/login.action")
	public Map<String,Object> login(String un,String pwd,HttpSession session) {
		boolean res = userService.checkUser(un,pwd);
		Map<String,Object> map = new HashMap<>();
		if(res) {
			session.setAttribute("_LOGIN_USER_", un);
			map.put("result", true);
			map.put("un", un);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	/**
	 * 用户注册接口 返回json结果
	 * @param u 映射的用户
	 * @return 若成功 返回json中 result 为true 否则为 false
	 */
	@ResponseBody
	@RequestMapping(value="/register.action")
	public Map<String,Object> register(User u,HttpSession session){
		u.setRegisterdate(new SimpleDateFormat("yyyy-MM-dd/:HH:mm:ss").format(new Date()));
		int res = userService.adduser(u);
		Map<String,Object> map = new HashMap<String, Object>();
		if(res>0) {
			session.setAttribute("_LOGIN_USER_", u.getUsername());
			map.put("un", u.getUsername());
			map.put("result",true);
		}
		else {
			map.put("result",false);
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/logout.action")
	public Map<String,Object> logout(HttpSession session){
		Map<String,Object> map = new HashMap<>();
		try {
			session.removeAttribute("_LOGIN_USER_");
			map.put("result", true);
		}catch(Exception e) {
			map.put("result", false);
		}
		return map;
	}
	
}
