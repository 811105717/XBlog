package com.xiaobai.xblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.Common;

public interface CommonDao {
	public List<Common> queryCommonsByBid(Integer id);
	public int getUpById(Integer id);
	public int setUpById(@Param("val")Integer val,@Param("id")Integer id);
	public int getDownById(Integer id);
	public int setDownById(@Param("val")Integer val,@Param("id")Integer id);
	public int sentNewCommon(Common c);
	public int getUidByComId(Integer id);
}
