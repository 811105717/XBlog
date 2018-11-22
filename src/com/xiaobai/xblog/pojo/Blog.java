package com.xiaobai.xblog.pojo;

import java.io.Serializable;
/**
 * 对应数据库中x_blog的表
 * 不采用驼峰命名
 * @author xiaobai
 *
 */
public class Blog implements Serializable {
	//可序列化序号ID=1
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userid;
	private String blogtittle;
	private String blogmain;
	private String createdate;
	private String tag;
	private Integer upcount;
	private Integer downcount;
	private Integer returnkey;
	
	public Integer getReturnkey() {
		return returnkey;
	}
	public void setReturnkey(Integer returnkey) {
		this.returnkey = returnkey;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getBlogtittle() {
		return blogtittle;
	}
	public void setBlogtittle(String blogtittle) {
		this.blogtittle = blogtittle;
	}
	public String getBlogmain() {
		return blogmain;
	}
	public void setBlogmain(String blogmain) {
		this.blogmain = blogmain;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
		return "Blog [id=" + id + ", userid=" + userid + ", blogtittle=" + blogtittle + ", blogmain=" + blogmain
				+ ", createdate=" + createdate + ", tag=" + tag + ", upcount=" + upcount + ", downcount=" + downcount
				+ "]";
	}
	
}
