package com.xiaobai.xblog.pojo;

import java.io.Serializable;
/**
 * ��Ӧ���ݿ���x_common��
 * �������շ�����
 * @author xiaobai
 *
 */
public class Common implements Serializable {
	//��ϵ�л�ID=1
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer blogid;
	private String common;
	private Integer userid;
	private String date;
	private Integer upcount;
	private Integer downcount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlogid() {
		return blogid;
	}
	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getUpcount() {
		return upcount;
	}
	public void setUpcount(Integer upcount) {
		this.upcount = upcount;
	}
	public Integer getDowncount() {
		return downcount;
	}
	public void setDowncount(Integer downcount) {
		this.downcount = downcount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Common [id=" + id + ", blogid=" + blogid + ", common=" + common + ", userid=" + userid + ", date="
				+ date + ", upcount=" + upcount + ", downcount=" + downcount + "]";
	}
	
}
