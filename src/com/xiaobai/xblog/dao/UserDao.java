package com.xiaobai.xblog.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.User;

public interface UserDao {
	/**
	 * ����û�
	 * @param u �û�
	 * @return ��ӽ�� 1 / 0
	 */
	public int insertUser(User u);
	/**
	 * ���ڵ�½ʱ����û��Ƿ����
	 * @param un �û���
	 * @param pwd ����
	 * @return �����  �ɹ����ظ��û�  ʧ�ܷ���null
	 */
	public User checkContain(@Param("un")String un,@Param("pwd")String pwd);
	/**
	 * ����ID��ȡ�û���
	 * @param id �û���id
	 * @return �û��� û�з���null
	 */
	public String findUnById(Integer id);
	/**
	 * ͨ���û��������û�id
	 * @param un �û���
	 * @return ��ѯ���  û�з���null
	 */
	public Integer findIdByName(String un);
	/**
	 * ����id��ȡ�û� �����ֻ���ѯ�û���ʹ�� findunbyid ��
	 * @param id �û���id  
	 * @return �û� û�з���null
	 */
	public User findUserById(Integer id);
	/**
	 * �����û���Ϣ
	 * @param u ���û�
	 * @return ���½�� 1 / 0
	 */
	public int UpdateUser(User u);
}
