package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Common;

public interface CommonDao {
	public List<Common> queryCommonsByBid(Integer id);
}
