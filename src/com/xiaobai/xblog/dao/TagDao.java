package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Tag;

public interface TagDao {
	/**
	 * ��ȡ�����е�tag
	 * @return ���е�tag  û�з��� null
	 */
	public List<Tag> getAllTag();
}
