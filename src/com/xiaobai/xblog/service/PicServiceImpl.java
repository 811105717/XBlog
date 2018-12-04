package com.xiaobai.xblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobai.xblog.dao.PicDao;
import com.xiaobai.xblog.pojo.Pic;

@Service
public class PicServiceImpl implements PicService {

	@Autowired
	private PicDao picDao;
	@Override
	public List<Pic> getpicByUid(Integer id) {
		return picDao.getPicsByUid(id);
	}
	@Override
	public int addPic(Pic pic) {
		return picDao.addnewPic(pic);
	}
	

}
