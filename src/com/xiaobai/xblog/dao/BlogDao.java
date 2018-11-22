package com.xiaobai.xblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.Blog;

public interface BlogDao {
	public List<Blog> getBlogS();
	public List<Blog> queryBlogsByKwd(String kwd);
	public Blog getBlogById(Integer id);
	public int getBlogUpCountById(Integer id);
	public int updateUpCount(@Param("val")Integer val,@Param("id") Integer id);
	public int getBlogDownCountById(Integer id);
	public int updateDownCount(@Param("val")Integer val,@Param("id") Integer id);
	public int addNewBlog(Blog blog);
}
