package com.xiaobai.xblog.service;

import com.xiaobai.xblog.pojo.User;

public interface UserService {
	public int adduser(User u);
	public boolean checkUser(String un,String pwd);
	public String findUserNameById(Integer id);
	public Integer getUidByName(String un);
}
