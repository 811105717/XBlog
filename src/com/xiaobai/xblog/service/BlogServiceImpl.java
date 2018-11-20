package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobai.xblog.dao.BlogDao;
import com.xiaobai.xblog.pojo.Blog;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogDao blogDao;
	@Override
	public List<Blog> queryAllBlogs() {
		return blogDao.getBlogS();
	}
	@Override
	public List<Blog> searchByKwd(String kwd) {
		return blogDao.queryBlogsByKwd(kwd);
	}

}
