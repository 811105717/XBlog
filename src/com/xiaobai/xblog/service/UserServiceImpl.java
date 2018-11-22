package com.xiaobai.xblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobai.xblog.dao.UserDao;
import com.xiaobai.xblog.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public int adduser(User u) {
		return userDao.insertUser(u);
	}
	@Override
	public boolean checkUser(String un, String pwd) {
		if(userDao.checkContain(un,pwd)!=null) {
			return true;
		}
		return false;
	}
	@Override
	public String findUserNameById(Integer id) {
		return userDao.findUnById(id);
	}
	@Override
	public Integer getUidByName(String un) {
		return userDao.findIdByName(un);
	}

}
