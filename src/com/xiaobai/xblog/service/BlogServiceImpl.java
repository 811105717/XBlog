package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	public Blog queryBlogById(Integer id) {
		return blogDao.getBlogById(id);
	}
	
	@Transactional
	@Override
	public int doup(Integer id) {
		int cur = blogDao.getBlogUpCountById(id);
		if(blogDao.updateUpCount(cur+1,id)>0) {
			return blogDao.getBlogUpCountById(id); //得到新的点赞数
		}
		else {
			return 0; //失败
		}
	}
	
	@Transactional
	@Override
	public int dodown(Integer id) {
		int cur = blogDao.getBlogDownCountById(id);
		if(blogDao.updateDownCount(cur+1,id)>0) {
			return blogDao.getBlogDownCountById(id); //得到新的点赞数
		}
		else {
			return 0; //失败
		}
	}
	
	@Transactional
	@Override
	public int addNewBlog(Blog blog) {
		return blogDao.addNewBlog(blog);
	}
	
	@Override
	public int getUidByBlogId(Integer id) {
		return blogDao.getUidByBlogId(id);
	}
	
	@Override
	public List<Blog> getUserBlogsByUid(Integer id) {
		return blogDao.getBlogsByUid(id);
	}
	
	@Transactional
	@Override
	public int deleteBlogById(Integer id) {
		return blogDao.deleteBlogById(id);
	}
	
	@Transactional
	@Override
	public int UpdateBlog(Blog blog) {
		return blogDao.updateBlog(blog);
	}
	
}
