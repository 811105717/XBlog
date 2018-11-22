package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobai.xblog.dao.CommonDao;
import com.xiaobai.xblog.pojo.Common;
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonDao commonDao;
	@Override
	public List<Common> getAllCommonByBlogId(Integer id) {
		return commonDao.queryCommonsByBid(id);
	}

}
