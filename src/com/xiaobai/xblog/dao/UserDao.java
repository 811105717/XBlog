package com.xiaobai.xblog.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.User;

public interface UserDao {
	public int insertUser(User u);
	public User checkContain(@Param("un")String un,@Param("pwd")String pwd);
	public String findUnById(Integer id);
	public Integer findIdByName(String un);
}
