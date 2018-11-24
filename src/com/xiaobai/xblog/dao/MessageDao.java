package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Message;

public interface MessageDao {
	public int addMessage(Message m);
	public List<Message> getAllUserMessageById(Integer id);
	public int setReadedById(Integer id);
}
