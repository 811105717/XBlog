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
	@Override
	public int doUp(Integer id) {
		int cur = commonDao.getUpById(id);
		commonDao.setUpById(cur+1,id);
		return commonDao.getUpById(id);
	}
	@Override
	public int doDown(Integer id) {
		int cur = commonDao.getDownById(id);
		commonDao.setDownById(cur+1,id);
		return commonDao.getDownById(id);
	}
	@Override
	public int sentNewCommon(Common c) {
		return commonDao.sentNewCommon(c);
	}
	@Override
	public int getUidByCommonId(Integer id) {
		return commonDao.getUidByComId(id);
	}

}
