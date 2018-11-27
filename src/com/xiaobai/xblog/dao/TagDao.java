package com.xiaobai.xblog.dao;

import java.util.List;

import com.xiaobai.xblog.pojo.Tag;

public interface TagDao {
	/**
	 * 获取所所有的tag
	 * @return 所有的tag  没有返回 null
	 */
	public List<Tag> getAllTag();
}
