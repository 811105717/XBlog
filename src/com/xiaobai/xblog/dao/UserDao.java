package com.xiaobai.xblog.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.User;

public interface UserDao {
	/**
	 * 添加用户
	 * @param u 用户
	 * @return 添加结果 1 / 0
	 */
	public int insertUser(User u);
	/**
	 * 用于登陆时检查用户是否存在
	 * @param un 用户名
	 * @param pwd 密码
	 * @return 检查结果  成功返回该用户  失败返回null
	 */
	public User checkContain(@Param("un")String un,@Param("pwd")String pwd);
	/**
	 * 根据ID获取用户名
	 * @param id 用户的id
	 * @return 用户名 没有返回null
	 */
	public String findUnById(Integer id);
	/**
	 * 通过用户名查找用户id
	 * @param un 用户名
	 * @return 查询结果  没有返回null
	 */
	public Integer findIdByName(String un);
	/**
	 * 根据id获取用户 （如果只想查询用户名使用 findunbyid ）
	 * @param id 用户的id  
	 * @return 用户 没有返回null
	 */
	public User findUserById(Integer id);
	/**
	 * 更新用户信息
	 * @param u 新用户
	 * @return 更新结果 1 / 0
	 */
	public int UpdateUser(User u);
}
