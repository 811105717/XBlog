package com.xiaobai.xblog.service;

import java.util.List;

import com.xiaobai.xblog.pojo.Message;

public interface MessageService {
	public int addMessage(Message m);
	public List<Message> getUserMessage(Integer uid);
	public int setReaded(Integer id);
	public int deleteMessageByBlogId(Integer id);
}
