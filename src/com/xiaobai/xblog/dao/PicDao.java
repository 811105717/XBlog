package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Pic;

public interface PicDao {
	public List<Pic> getPicsByUid(Integer id);
	public int addnewPic(Pic pic);
}
