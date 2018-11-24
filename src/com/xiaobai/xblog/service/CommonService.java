package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaobai.xblog.pojo.Common;
@Service
public interface CommonService {
	public List<Common> getAllCommonByBlogId(Integer id);
	public int doUp(Integer id);
	public int doDown(Integer id);
	public int sentNewCommon(Common c);
}
