package com.xiaobai.xblog.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.xiaobai.xblog.dao.PicDao;
import com.xiaobai.xblog.pojo.Blog;
import com.xiaobai.xblog.pojo.Common;
import com.xiaobai.xblog.pojo.Message;
import com.xiaobai.xblog.pojo.Pic;
import com.xiaobai.xblog.pojo.User;
import com.xiaobai.xblog.service.BlogService;
import com.xiaobai.xblog.service.CommonService;
import com.xiaobai.xblog.service.MessageService;
import com.xiaobai.xblog.service.PicService;
import com.xiaobai.xblog.service.TagService;
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
	@Autowired
	private MessageService messageService;
	@Autowired
	private TagService tagService;
	@Autowired
	private PicService picService;
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
		//添加消息
		Message m = new Message();
		m.setUid(blogService.getUidByBlogId(id));
		m.setBlogid(id);
		m.setMess("您的博文收到了一个赞！");
		messageService.addMessage(m);
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
		//添加消息
		Message m = new Message();
		m.setUid(blogService.getUidByBlogId(id));
		m.setBlogid(id);
		m.setMess("有人踩了你的博文！快看看吧！！");
		messageService.addMessage(m);
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
	public Map<String,Object> comZan(Integer id,Integer blogid){
		Map<String,Object> map = new HashMap<>();
		int res = commonServce.doUp(id);
		//添加消息
		Message m = new Message();
		m.setUid(commonServce.getUidByCommonId(id));
		m.setBlogid(blogid);
		m.setMess("有人赞了你的评论！！！");
		messageService.addMessage(m);
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
	 * @param id 评论条目id
	 * @return 更新结果 
	 */
	@ResponseBody
	@RequestMapping(value="/caicommon.action")
	public Map<String,Object> comCai(Integer id,Integer blogid){
		Map<String,Object> map = new HashMap<>();
		int res = commonServce.doDown(id);
		//添加消息
		Message m = new Message();
		m.setUid(commonServce.getUidByCommonId(id));
		m.setBlogid(blogid);
		m.setMess("有人踩了你的评论！");
		messageService.addMessage(m);
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
	 * 发表评论
	 * @param id  文章id
	 * @param common 评论内容
	 * @param session 检查登陆用户使用
	 * @return 评论结果
	 */
	@ResponseBody
	@RequestMapping(value="/own/sendcommon.action")
	public Map<String,Object> sentCommon(Integer id,String common,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		String secur = HtmlUtils.htmlEscape(common); //防止xss攻击
		int uid = userService.getUidByName((String)(session.getAttribute("_LOGIN_USER_")));
		Common c = new Common();
		c.setBlogid(id); c.setUserid(uid); c.setCommon(secur);
		c.setAuthorname((String)session.getAttribute("_LOGIN_USER_"));
		c.setDowncount(0); c.setUpcount(0); 
		c.setDate(new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").format(new Date()));
		int res = commonServce.sentNewCommon(c);
		//添加消息
		Message m = new Message();
		m.setUid(blogService.getUidByBlogId(id));
		m.setBlogid(id);
		m.setMess("您收到了新的评论！");
		messageService.addMessage(m);
		
		if(res>0) {
			map.put("result", true);
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
	public String selfCenter(HttpSession session,Model model) {
		User u = userService.getUserById(userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")));
		List<Blog> blogs = blogService.getUserBlogsByUid(u.getId());
		model.addAttribute("user", u);
		model.addAttribute("blogs", blogs);
		return "usercenter";
	}
	
	@ResponseBody
	@RequestMapping(value="/own/updateprofile.action")
	public Map<String,Object> updateUserProfile(String pass,String address,String email,String tel,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User u = new User();
		u.setAddress(address); u.setEmail(email); u.setPassword(pass); u.setTel(tel);
		u.setId(userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")));
		int res = userService.updateUser(u);
		if(res>0) {
			map.put("result", true);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	/**
	 * 处理数据然后跳转到个人消息中心
	 * @param model 可能需要一些数据
	 * @return 跳转的页面
	 */
	@RequestMapping(value="/own/message.action")
	public String selfMessage(Model model,HttpSession session) {
		List<Message> messages = messageService.getUserMessage(userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")));
		model.addAttribute("messages", messages);
		return "mymessage";
	}
	
	/**
	 * 获取可用的所有tag 然后跳转到写博客地址
	 * @return 写博客页面的地址
	 */
	@RequestMapping(value="/own/newblog.action")
	public String writeNewBlog(Model model) {
		model.addAttribute("tags",tagService.getAllTag());
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
	
	/**
	 * 删除一条博客 
	 * @param id 要删除的博客的id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/own/delete.action")
	public Map<String,Object> deleteBlog(Integer id) {
		Map<String,Object> map = new HashMap<>();
		messageService.deleteMessageByBlogId(id);
		commonServce.deleteCommonByBlogid(id);
		int res = blogService.deleteBlogById(id);
		if(res>0) {
			map.put("result", true);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	@RequestMapping(value="/own/updateblog.action")
	public String updateBlog(Integer id,Model model) {
		Blog blog = blogService.queryBlogById(id);
		System.out.println(blog);
		model.addAttribute("blog",blog);
		model.addAttribute("tags",tagService.getAllTag());
		return "updateblog";
	}
	
	/**
	 * 设定消息已读 当用户点击某一条消息时触发
	 * @param id 被点击的消息的id
	 */
	@RequestMapping(value="/own/isread.action")
	public void setRead(Integer id) {
		messageService.setReaded(id);
	}
	
	/**
	 * 更新博文 
	 * @param tittle 标题
	 * @param tag 标签
	 * @param text 文章
	 * @param session 取出用户
	 * @return 结果 
	 */
	@ResponseBody
	@RequestMapping(value="/own/startupdate.action")
	public Map<String,Object> startUpdateBlog(Integer id,String tittle,String tag,String text,HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		Integer uid = userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")); //肯定能查到 除非非法访问 
		Blog blog = new Blog();
		blog.setId(id);
		blog.setBlogmain(text);
		blog.setTag(tag);
		blog.setCreatedate(new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").format(new Date()));
		blog.setUserid(uid);
		blog.setBlogtittle(tittle);
		blog.setUpcount(0);
		blog.setDowncount(0);
		int res = blogService.UpdateBlog(blog);
		if(res>0) {
			blog.setId(res);
			map.put("result", true);
			map.put("id", id);
		}
		else {
			map.put("result", false);
		}
		return map;
	}
	
	/**
	 * 获取当前用户的图片  并返回
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/own/piccontrol.action")
	public String picControl(Model model,HttpSession session) {
		Integer uid = userService.getUidByName((String)session.getAttribute("_LOGIN_USER_"));
		List<Pic> piclist = picService.getpicByUid(uid);
		model.addAttribute("imglist", piclist);
		return "imglist";
	}
	
	@RequestMapping(value="/own/uploadimg.action")
	public String uploadPic(@RequestParam MultipartFile getimg,HttpSession session,Model model) {
		if(getimg!=null) {
			try {
				String orginal = getimg.getOriginalFilename();
				String picpath = "D:\\MyProject\\XBlog\\WebContent\\userImg\\"; //路径写死 注意修改
				String newFileName = UUID.randomUUID()+orginal.substring(orginal.lastIndexOf("."));
				String newfilepath = picpath+newFileName;
				File file = new File(newfilepath);
				getimg.transferTo(file);
				Pic p = new Pic();
				p.setUid(userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")));
				p.setPath(newFileName);
				int res = picService.addPic(p);
				if(res>0) {
					model.addAttribute("info", "添加图片成功");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return "forward:/own/piccontrol.action";
	}
}
