package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Message;

public interface MessageDao {
	/**
	 * 添加新的消息
	 * @param m 新消息
	 * @return 添加结果 
	 */
	public int addMessage(Message m);
	/**
	 * 根据用户id获取所有消息 
	 * @param id 用户id
	 * @return 所有消息   没有返回null
	 */
	public List<Message> getAllUserMessageById(Integer id);
	/**
	 * 设置消息为已读
	 * @param id 已读的message ID
	 * @return 更新结果  1/0
	 */
	public int setReadedById(Integer id);
	/**
	 * 删除消息
	 * @param id 消息的blog id
	 * @return
	 */
	public int deleteMessageByBlogid(Integer id);
}
