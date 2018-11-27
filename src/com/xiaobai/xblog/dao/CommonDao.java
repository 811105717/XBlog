package com.xiaobai.xblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.Common;

public interface CommonDao {
	/**
	 * ����blog��ID��ѯ��blog����������
	 * @param id blog �� ID
	 * @return list���ϵ�����  û�з���null
	 */
	public List<Common> queryCommonsByBid(Integer id);
	/**
	 * ͨ��ID��ȡ������
	 * @param id common��ID
	 * @return ������ 
	 */
	public int getUpById(Integer id);
	/**
	 * ���µ�����
	 * @param val �µ�ֵ
	 * @param id common��ID
	 * @return ���½�� 1/0 
	 */
	public int setUpById(@Param("val")Integer val,@Param("id")Integer id);
	/**
	 * ��ȡ�����
	 * @param id common��ID
	 * @return �����
	 */
	public int getDownById(Integer id);
	/**
	 * ���µ����
	 * @param val �µ�ֵ
	 * @param id common��ID
	 * @return ���½�� 1/0
	 */
	public int setDownById(@Param("val")Integer val,@Param("id")Integer id);
	/**
	 * �����µ�����
	 * @param c ���� 
	 * @return ���½�� 1 / 0
	 */
	public int sentNewCommon(Common c);
	/**
	 * ͨ������ID��ø����۵�����ID
	 * @param id  ����id
	 * @return ����ID
	 */
	public int getUidByComId(Integer id);
	/**
	 * ͨ��id ɾ������
	 * @param id Ҫɾ������id
	 * @return ɾ����� 1/0
	 */
	public int deleteCommonByBlogId(Integer id);
}
