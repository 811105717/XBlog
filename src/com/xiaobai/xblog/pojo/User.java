package com.xiaobai.xblog.pojo;

import java.io.Serializable;
/**
 * ��Ӧ���ݿ���е� x_user��
 * �������շ�����
 * @author xiaobai
 *
 */
public class User implements Serializable{
    //�����л�ID=1
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String registerdate;
	private String email;
	private String address;
	private String tel;
	private String sex;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", registerdate=" + registerdate
				+ ", email=" + email + ", address=" + address + ", tel=" + tel + ", sex=" + sex + "]";
	}
	
}
