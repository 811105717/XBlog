package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobai.xblog.dao.TagDao;
import com.xiaobai.xblog.pojo.Tag;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagDao tagDao;
	public List<Tag> getAllTag(){
		return tagDao.getAllTag();
	}
}
