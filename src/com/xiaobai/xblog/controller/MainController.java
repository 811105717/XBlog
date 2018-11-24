package com.xiaobai.xblog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaobai.xblog.pojo.Blog;
import com.xiaobai.xblog.pojo.Common;
import com.xiaobai.xblog.pojo.User;
import com.xiaobai.xblog.service.BlogService;
import com.xiaobai.xblog.service.CommonService;
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
	@Autowired
	private CommonService commonServce;
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
	 * 根据关键词搜索
	 * @param kwd 关键词
	 * @param model 搜索的数据 
	 * @return 搜索结果页
	 */
	@RequestMapping(value="/search.action")
	public String searching(String kwd, Model model) {
		if(!kwd.trim().equals("")) {
			model.addAttribute("kwd", kwd); //搜索结果页面的搜索框内的值
			List<Blog> results = blogService.searchByKwd(kwd);
			for (Blog blog : results) {
				System.out.println(blog);
			}
			model.addAttribute("results",results);
		}
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
	
	/**
	 * 用户退出处理 返回退出结果
	 * @param session  用来删除保存在session中的用户信息
	 * @return  操作结果 成功  result 为 ture  失败为false
	 */
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
	
	/**
	 * 根据id获得博客并跳转到展示页进行展示
	 * @param id 博文的id号
	 * @param model 查询结果保存在里面
	 * @return 跳转页面名字
	 */
	@RequestMapping(value="/blog.action")
	public String gotoBlog(Integer id,Model model) {
		Blog blog = blogService.queryBlogById(id);
		if(blog==null) {
			return "error";
		}
		List<Common> commons = commonServce.getAllCommonByBlogId(id);
		for (Common common : commons) {  //为每一条评论添加作者名字
			common.setAuthorname(userService.findUserNameById(common.getUserid()));
		}
		String author = userService.findUserNameById(blog.getUserid());
		model.addAttribute("commons", commons);
		model.addAttribute("author", author);
		model.addAttribute("blog", blog);
		return "showblog";
	}
	
	/**
	 * 更新点赞数
	 * @param id 博文id
	 * @return  更新结果 true false  
	 */
	@ResponseBody
	@RequestMapping(value="/upcount.action")
	public Map<String,Object> upBlogAction(Integer id){
		Map<String,Object> map = new HashMap<>();
		int res = blogService.doup(id);
		if(res>0) {
			map.put("result", true);
			map.put("count", res);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	/**
	 * 更新点踩数 
	 * @param id  博文id
	 * @return 更新结果  true false 
	 */
	@ResponseBody
	@RequestMapping(value="/downcount.action")
	public Map<String,Object> downBlogAction(Integer id){
		Map<String,Object> map = new HashMap<>();
		int res = blogService.dodown(id);
		if(res>0) {
			map.put("result", true);
			map.put("count", res);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	/**
	 * 更新评论点赞数
	 * @param id 评论条目id
	 * @return 是否成功  
	 */
	@ResponseBody
	@RequestMapping(value="/zancommon.action")
	public Map<String,Object> comZan(Integer id){
		Map<String,Object> map = new HashMap<>();
		int res = commonServce.doUp(id);
		if(res>0) {
			map.put("result", true);
			map.put("count", res);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/caicommon.action")
	public Map<String,Object> comCai(Integer id){
		Map<String,Object> map = new HashMap<>();
		int res = commonServce.doDown(id);
		if(res>0) {
			map.put("result", true);
			map.put("count", res);
		}
		else {
			map.put("result", false);
		}
		return map;
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
	 * 写新博客  默认走这一条路径 转发到写博客的页面  方便拦截器拦截
	 * @return 写博客页面的地址
	 */
	@RequestMapping(value="/own/newblog.action")
	public String writeNewBlog(HttpServletRequest request) {
		//do nothing here  only forward 
		return "newblog";
	}
	
	/**
	 * 发布新的文章 
	 * @param text  文章文本编码
	 * @param session session 用来获取用户名
 	 * @return 结果 true false
	 */
	@ResponseBody
	@RequestMapping(value="/own/addblog.action")
	public Map<String,Object> sendBlog(String tittle,String tag,String text,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		Integer uid = userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")); //肯定能查到 除非非法访问 
		Blog blog = new Blog();
		blog.setBlogmain(text);
		blog.setTag(tag);
		blog.setCreatedate(new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").format(new Date()));
		blog.setUserid(uid);
		blog.setBlogtittle(tittle);
		blog.setUpcount(0);
		blog.setDowncount(0);
		int res = blogService.addNewBlog(blog);
		if(res>0) {
			blog.setId(res);
			map.put("result", true);
			map.put("id", blog.getReturnkey());
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	
}
