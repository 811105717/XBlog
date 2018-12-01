package com.xiaobai.xblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.Blog;

public interface BlogDao {
	/**
	 * 获取所设有的blog
	 * @return list存储blog
	 */
	public List<Blog> getBlogS();
	/**
	 * 根据关键字查blog
	 * @param kwd 关键字
	 * @return 查到的blog  没有返回null
	 */
	public List<Blog> queryBlogsByKwd(@Param("val")String kwd);
	/**
	 * 通过ID获取blog
	 * @param id 
	 * @return 得到的blog  没有返回null
	 */
	public Blog getBlogById(Integer id);
	/**
	 * 根据ID获取blog的点赞数
	 * @param id 
	 * @return 得到的点赞数 
	 */
	public int getBlogUpCountById(Integer id);
	/**
	 * 更新点赞数
	 * @param val 新的值
	 * @param id blog的id
	 * @return 更新结果 1/0
	 */
	public int updateUpCount(@Param("val")Integer val,@Param("id") Integer id);
	/**
	 * 根据ID获取点踩数
	 * @param id 
	 * @return 得到的点踩数
	 */
	public int getBlogDownCountById(Integer id);
	/**
	 * 更新点踩数
	 * @param val 新的值
	 * @param id  blog的ID
	 * @return 更新结果 1/0
	 */
	public int updateDownCount(@Param("val")Integer val,@Param("id") Integer id);
	/**
	 * 添加新的blog
	 * @param blog 新的blog
	 * @return 添加结果  1/0
	 */
	public int addNewBlog(Blog blog);
	/**
	 * 根据blog的ID获取blog的作者ID
	 * @param id blog的ID
	 * @return 作者ID
	 */
	public int getUidByBlogId(Integer id);
	/**
	 * 根据用户ID获取该用户所有blog
	 * @param id 用户ID 
	 * @return 得到的blog 没有返回null
	 */
	public List<Blog> getBlogsByUid(Integer id);
	/**
	 * 根据blog的ID删除blog
	 * @param id blog的ID
	 * @return 删除结果 1/0 
	 */
	public int deleteBlogById(Integer id);
	
	/**
	 *	更新博客
	 * @param blog
	 * @return
	 */
	public int updateBlog(Blog blog);
}
