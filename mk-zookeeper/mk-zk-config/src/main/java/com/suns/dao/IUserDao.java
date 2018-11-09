/*
 * Powered By  sungymf@fansfinancial.com
 * Web Site: https://www.qianshenghua.com
 * Since 2008 - 2018
 */

package com.suns.dao;


import com.suns.module.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserDo> selectUser(Map<String, Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateUserById(UserDo newUserDo);
	
	/**
	 * 新增
	 */
	public int addUser(UserDo newUserDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}
