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
	 * ������Ҫ������ Ȼ����ת����������
	 * @param model ������ҪһЩ����  ��
	 * @return ��������ҳ��
	 */
	@RequestMapping(value="/own/center.action")
	public String selfCenter(Model model) {
		
		return "";
	}
	
	/**
	 * ��������Ȼ����ת��������Ϣ����
	 * @param model ������ҪһЩ����
	 * @return ��ת��ҳ��
	 */
	@RequestMapping(value="/own/message.action")
	public String selfMessage(Model model) {
		
		return "";
	}
	
	/**
	 * ���ݹؼ�������
	 * @param kwd �ؼ���
	 * @param model ���������� 
	 * @return �������ҳ
	 */
	@RequestMapping(value="/search.action")
	public String searching(String kwd, Model model) {
		model.addAttribute("kwd", kwd); //�������ҳ����������ڵ�ֵ
		List<Blog> results = blogService.searchByKwd(kwd);
		for (Blog blog : results) {
			System.out.println(blog);
		}
		model.addAttribute("results",results);
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
