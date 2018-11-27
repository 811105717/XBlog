package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Message;

public interface MessageDao {
	/**
	 * ����µ���Ϣ
	 * @param m ����Ϣ
	 * @return ��ӽ�� 
	 */
	public int addMessage(Message m);
	/**
	 * �����û�id��ȡ������Ϣ 
	 * @param id �û�id
	 * @return ������Ϣ   û�з���null
	 */
	public List<Message> getAllUserMessageById(Integer id);
	/**
	 * ������ϢΪ�Ѷ�
	 * @param id �Ѷ���message ID
	 * @return ���½��  1/0
	 */
	public int setReadedById(Integer id);
	/**
	 * ɾ����Ϣ
	 * @param id ��Ϣ��blog id
	 * @return
	 */
	public int deleteMessageByBlogid(Integer id);
}
