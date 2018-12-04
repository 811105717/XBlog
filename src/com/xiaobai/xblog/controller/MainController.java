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
 * ��Ҫ��controller ������Ҫ��·��ӳ��
 * ���ڵ�android��Ŀ���ܻ������д�ӿ�
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
	 *������ҳ���ݣ������͵���ҳ��ʾ 
	 * @param model Ҫ��ʾ������
	 * @return ҳ������
	 */
	@RequestMapping(value="/index.action")
	public String toIndex(Model model) {
		System.out.println("successfully!!!");
		List<Blog> blogList = blogService.queryAllBlogs();
		model.addAttribute("blogs", blogList);
		return "index";
	}
	
	
	/**
	 * ���ݹؼ�������
	 * @param kwd �ؼ���
	 * @param model ���������� 
	 * @return �������ҳ
	 */
	@RequestMapping(value="/search.action")
	public String searching(String kwd, Model model) {
		if(!kwd.trim().equals("")) {
			model.addAttribute("kwd", kwd); //�������ҳ����������ڵ�ֵ
			List<Blog> results = blogService.searchByKwd(kwd);
			for (Blog blog : results) {
				System.out.println(blog);
			}
			model.addAttribute("results",results);
		}
		return "result";
	}
	
	/**
	 * �û���¼�ӿ� ����json��� 
	 * @param un �û���
	 * @param pwd ����
	 * @return ���ɹ�����json �� result Ϊtrue  ����Ϊ false  
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
	 * �û�ע��ӿ� ����json���
	 * @param u ӳ����û�
	 * @return ���ɹ� ����json�� result Ϊtrue ����Ϊ false
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
	 * �û��˳����� �����˳����
	 * @param session  ����ɾ��������session�е��û���Ϣ
	 * @return  ������� �ɹ�  result Ϊ ture  ʧ��Ϊfalse
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
	 * ����id��ò��Ͳ���ת��չʾҳ����չʾ
	 * @param id ���ĵ�id��
	 * @param model ��ѯ�������������
	 * @return ��תҳ������
	 */
	@RequestMapping(value="/blog.action")
	public String gotoBlog(Integer id,Model model) {
		Blog blog = blogService.queryBlogById(id);
		if(blog==null) {
			return "error";
		}
		List<Common> commons = commonServce.getAllCommonByBlogId(id);
		for (Common common : commons) {  //Ϊÿһ�����������������
			common.setAuthorname(userService.findUserNameById(common.getUserid()));
		}
		String author = userService.findUserNameById(blog.getUserid());
		model.addAttribute("commons", commons);
		model.addAttribute("author", author);
		model.addAttribute("blog", blog);
		return "showblog";
	}
	
	/**
	 * ���µ�����
	 * @param id ����id
	 * @return  ���½�� true false  
	 */
	@ResponseBody
	@RequestMapping(value="/upcount.action")
	public Map<String,Object> upBlogAction(Integer id){
		Map<String,Object> map = new HashMap<>();
		int res = blogService.doup(id);
		//�����Ϣ
		Message m = new Message();
		m.setUid(blogService.getUidByBlogId(id));
		m.setBlogid(id);
		m.setMess("���Ĳ����յ���һ���ޣ�");
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
	 * ���µ���� 
	 * @param id  ����id
	 * @return ���½��  true false 
	 */
	@ResponseBody
	@RequestMapping(value="/downcount.action")
	public Map<String,Object> downBlogAction(Integer id){
		Map<String,Object> map = new HashMap<>();
		int res = blogService.dodown(id);
		//�����Ϣ
		Message m = new Message();
		m.setUid(blogService.getUidByBlogId(id));
		m.setBlogid(id);
		m.setMess("���˲�����Ĳ��ģ��쿴���ɣ���");
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
	 * �������۵�����
	 * @param id ������Ŀid
	 * @return �Ƿ�ɹ�  
	 */
	@ResponseBody
	@RequestMapping(value="/zancommon.action")
	public Map<String,Object> comZan(Integer id,Integer blogid){
		Map<String,Object> map = new HashMap<>();
		int res = commonServce.doUp(id);
		//�����Ϣ
		Message m = new Message();
		m.setUid(commonServce.getUidByCommonId(id));
		m.setBlogid(blogid);
		m.setMess("��������������ۣ�����");
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
	 * ���µ���� 
	 * @param id ������Ŀid
	 * @return ���½�� 
	 */
	@ResponseBody
	@RequestMapping(value="/caicommon.action")
	public Map<String,Object> comCai(Integer id,Integer blogid){
		Map<String,Object> map = new HashMap<>();
		int res = commonServce.doDown(id);
		//�����Ϣ
		Message m = new Message();
		m.setUid(commonServce.getUidByCommonId(id));
		m.setBlogid(blogid);
		m.setMess("���˲���������ۣ�");
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
	 * ��������
	 * @param id  ����id
	 * @param common ��������
	 * @param session ����½�û�ʹ��
	 * @return ���۽��
	 */
	@ResponseBody
	@RequestMapping(value="/own/sendcommon.action")
	public Map<String,Object> sentCommon(Integer id,String common,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		String secur = HtmlUtils.htmlEscape(common); //��ֹxss����
		int uid = userService.getUidByName((String)(session.getAttribute("_LOGIN_USER_")));
		Common c = new Common();
		c.setBlogid(id); c.setUserid(uid); c.setCommon(secur);
		c.setAuthorname((String)session.getAttribute("_LOGIN_USER_"));
		c.setDowncount(0); c.setUpcount(0); 
		c.setDate(new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").format(new Date()));
		int res = commonServce.sentNewCommon(c);
		//�����Ϣ
		Message m = new Message();
		m.setUid(blogService.getUidByBlogId(id));
		m.setBlogid(id);
		m.setMess("���յ����µ����ۣ�");
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
	 * ������Ҫ������ Ȼ����ת����������
	 * @param model ������ҪһЩ����  ��
	 * @return ��������ҳ��
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
	 * ��������Ȼ����ת��������Ϣ����
	 * @param model ������ҪһЩ����
	 * @return ��ת��ҳ��
	 */
	@RequestMapping(value="/own/message.action")
	public String selfMessage(Model model,HttpSession session) {
		List<Message> messages = messageService.getUserMessage(userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")));
		model.addAttribute("messages", messages);
		return "mymessage";
	}
	
	/**
	 * ��ȡ���õ�����tag Ȼ����ת��д���͵�ַ
	 * @return д����ҳ��ĵ�ַ
	 */
	@RequestMapping(value="/own/newblog.action")
	public String writeNewBlog(Model model) {
		model.addAttribute("tags",tagService.getAllTag());
		return "newblog";
	}
	
	/**
	 * �����µ����� 
	 * @param text  �����ı�����
	 * @param session session ������ȡ�û���
 	 * @return ��� true false
	 */
	@ResponseBody
	@RequestMapping(value="/own/addblog.action")
	public Map<String,Object> sendBlog(String tittle,String tag,String text,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		Integer uid = userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")); //�϶��ܲ鵽 ���ǷǷ����� 
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
	 * ɾ��һ������ 
	 * @param id Ҫɾ���Ĳ��͵�id
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
	 * �趨��Ϣ�Ѷ� ���û����ĳһ����Ϣʱ����
	 * @param id ���������Ϣ��id
	 */
	@RequestMapping(value="/own/isread.action")
	public void setRead(Integer id) {
		messageService.setReaded(id);
	}
	
	/**
	 * ���²��� 
	 * @param tittle ����
	 * @param tag ��ǩ
	 * @param text ����
	 * @param session ȡ���û�
	 * @return ��� 
	 */
	@ResponseBody
	@RequestMapping(value="/own/startupdate.action")
	public Map<String,Object> startUpdateBlog(Integer id,String tittle,String tag,String text,HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		Integer uid = userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")); //�϶��ܲ鵽 ���ǷǷ����� 
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
	 * ��ȡ��ǰ�û���ͼƬ  ������
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
				String picpath = "D:\\MyProject\\XBlog\\WebContent\\userImg\\"; //·��д�� ע���޸�
				String newFileName = UUID.randomUUID()+orginal.substring(orginal.lastIndexOf("."));
				String newfilepath = picpath+newFileName;
				File file = new File(newfilepath);
				getimg.transferTo(file);
				Pic p = new Pic();
				p.setUid(userService.getUidByName((String)session.getAttribute("_LOGIN_USER_")));
				p.setPath(newFileName);
				int res = picService.addPic(p);
				if(res>0) {
					model.addAttribute("info", "���ͼƬ�ɹ�");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return "forward:/own/piccontrol.action";
	}
}
