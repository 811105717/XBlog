package com.xiaobai.xblog.pojo;

import java.io.Serializable;

public class Pic implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String path;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
