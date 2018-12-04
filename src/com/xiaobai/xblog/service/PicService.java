package com.xiaobai.xblog.service;

import java.util.List;

import com.xiaobai.xblog.pojo.Pic;

public interface PicService {
	public List<Pic> getpicByUid(Integer id);
	public int addPic(Pic pic);
}
