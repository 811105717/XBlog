package com.xiaobai.xblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaobai.xblog.pojo.Blog;

public interface BlogDao {
	/**
	 * ��ȡ�����е�blog
	 * @return list�洢blog
	 */
	public List<Blog> getBlogS();
	/**
	 * ���ݹؼ��ֲ�blog
	 * @param kwd �ؼ���
	 * @return �鵽��blog  û�з���null
	 */
	public List<Blog> queryBlogsByKwd(@Param("val")String kwd);
	/**
	 * ͨ��ID��ȡblog
	 * @param id 
	 * @return �õ���blog  û�з���null
	 */
	public Blog getBlogById(Integer id);
	/**
	 * ����ID��ȡblog�ĵ�����
	 * @param id 
	 * @return �õ��ĵ����� 
	 */
	public int getBlogUpCountById(Integer id);
	/**
	 * ���µ�����
	 * @param val �µ�ֵ
	 * @param id blog��id
	 * @return ���½�� 1/0
	 */
	public int updateUpCount(@Param("val")Integer val,@Param("id") Integer id);
	/**
	 * ����ID��ȡ�����
	 * @param id 
	 * @return �õ��ĵ����
	 */
	public int getBlogDownCountById(Integer id);
	/**
	 * ���µ����
	 * @param val �µ�ֵ
	 * @param id  blog��ID
	 * @return ���½�� 1/0
	 */
	public int updateDownCount(@Param("val")Integer val,@Param("id") Integer id);
	/**
	 * ����µ�blog
	 * @param blog �µ�blog
	 * @return ��ӽ��  1/0
	 */
	public int addNewBlog(Blog blog);
	/**
	 * ����blog��ID��ȡblog������ID
	 * @param id blog��ID
	 * @return ����ID
	 */
	public int getUidByBlogId(Integer id);
	/**
	 * �����û�ID��ȡ���û�����blog
	 * @param id �û�ID 
	 * @return �õ���blog û�з���null
	 */
	public List<Blog> getBlogsByUid(Integer id);
	/**
	 * ����blog��IDɾ��blog
	 * @param id blog��ID
	 * @return ɾ����� 1/0 
	 */
	public int deleteBlogById(Integer id);
	
	/**
	 *	���²���
	 * @param blog
	 * @return
	 */
	public int updateBlog(Blog blog);
}
