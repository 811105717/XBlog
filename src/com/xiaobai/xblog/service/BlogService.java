package com.xiaobai.xblog.service;

import java.util.List;

import com.xiaobai.xblog.pojo.Blog;

public interface BlogService {
	public List<Blog> queryAllBlogs();
	public List<Blog> searchByKwd(String kwd);
	public Blog queryBlogById(Integer id);
	public int doup(Integer id);
	public int dodown(Integer id);
	public int addNewBlog(Blog blog);
	public int getUidByBlogId(Integer id);
	public List<Blog> getUserBlogsByUid(Integer id);
	public int deleteBlogById(Integer id);
}
