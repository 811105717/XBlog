package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobai.xblog.dao.MessageDao;
import com.xiaobai.xblog.pojo.Message;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	@Override
	public int addMessage(Message m) {
		return  messageDao.addMessage(m);
	}
	@Override
	public List<Message> getUserMessage(Integer uid) {
		return messageDao.getAllUserMessageById(uid);
	}
	@Override
	public int setReaded(Integer id) {
		return messageDao.setReadedById(id);
	}
	@Override
	public int deleteMessageByBlogId(Integer id) {
		return messageDao.deleteMessageByBlogid(id);
	}

}
