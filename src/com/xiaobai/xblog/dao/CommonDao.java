package com.xiaobai.xblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.Common;

public interface CommonDao {
	/**
	 * 根据blog的ID查询该blog的所有评论
	 * @param id blog 的 ID
	 * @return list集合的评论  没有返回null
	 */
	public List<Common> queryCommonsByBid(Integer id);
	/**
	 * 通过ID获取点赞数
	 * @param id common的ID
	 * @return 点赞数 
	 */
	public int getUpById(Integer id);
	/**
	 * 更新点赞数
	 * @param val 新的值
	 * @param id common的ID
	 * @return 更新结果 1/0 
	 */
	public int setUpById(@Param("val")Integer val,@Param("id")Integer id);
	/**
	 * 获取点踩数
	 * @param id common的ID
	 * @return 点踩数
	 */
	public int getDownById(Integer id);
	/**
	 * 更新点踩数
	 * @param val 新的值
	 * @param id common的ID
	 * @return 更新结果 1/0
	 */
	public int setDownById(@Param("val")Integer val,@Param("id")Integer id);
	/**
	 * 发表新的评论
	 * @param c 评论 
	 * @return 更新结果 1 / 0
	 */
	public int sentNewCommon(Common c);
	/**
	 * 通过评论ID获得该评论的作者ID
	 * @param id  评论id
	 * @return 作者ID
	 */
	public int getUidByComId(Integer id);
	/**
	 * 通过id 删除评论
	 * @param id 要删的评论id
	 * @return 删除结果 1/0
	 */
	public int deleteCommonByBlogId(Integer id);
}
