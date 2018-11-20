package com.xiaobai.xblog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonAlias;
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
		System.out.println(kwd);
		return "";
	}
	
	/**
	 * �û���¼�ӿ� ����json��� 
	 * @param un �û���
	 * @param pwd ����
	 * @return ���ɹ�����json �� result Ϊtrue  ����Ϊ false  
	 */
	@ResponseBody
	@RequestMapping(value="/login.action")
	public Map<String,String> login(String un,String pwd) {
		Map<String,String> map = new HashMap<>();
		map.put("result", "success");
		map.put("un", un);
		map.put("pwd", pwd);
		return map;
	}
	
	/**
	 * �û�ע��ӿ� ����json���
	 * @param u ӳ����û�
	 * @return ���ɹ� ����json�� result Ϊtrue ����Ϊ false
	 */
	@ResponseBody
	@RequestMapping(value="/register.action")
	public Map<String,String> register(User u){
		Map<String,String> map = new HashMap<String, String>();
		map.put("result", "true");
		map.put("un", u.getUsername());
		return map;
	}
	
}
