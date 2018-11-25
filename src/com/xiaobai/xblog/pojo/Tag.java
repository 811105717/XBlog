package com.xiaobai.xblog.pojo;

import java.io.Serializable;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String tag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", tag=" + tag + "]";
	}
	
}
