package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Blog;

public interface BlogDao {
	public List<Blog> getBlogS();
	public List<Blog> queryBlogsByKwd(String kwd);
}
