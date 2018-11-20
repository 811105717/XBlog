package com.xiaobai.xblog.service;

import java.util.List;

import com.xiaobai.xblog.pojo.Blog;

public interface BlogService {
	public List<Blog> queryAllBlogs();
	public List<Blog> searchByKwd(String kwd);
}
