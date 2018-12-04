package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobai.xblog.dao.MessageDao;
import com.xiaobai.xblog.pojo.Message;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	
	@Transactional
	@Override
	public int addMessage(Message m) {
		return  messageDao.addMessage(m);
	}
	@Override
	public List<Message> getUserMessage(Integer uid) {
		return messageDao.getAllUserMessageById(uid);
	}
	
	@Transactional
	@Override
	public int setReaded(Integer id) {
		return messageDao.setReadedById(id);
	}
	
	@Transactional
	@Override
	public int deleteMessageByBlogId(Integer id) {
		return messageDao.deleteMessageByBlogid(id);
	}

}
