/*
 * Powered By  sungymf@fansfinancial.com
 * Web Site: https://www.qianshenghua.com
 * Since 2008 - 2018
 */

package com.suns.mysql.mapper;

/**
 * @author  sungymf@fansfinancial
 * @version 1.0
 * @since 1.0
 */


import com.suns.mysql.module.LockDo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ILockDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public LockDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<LockDo> selectLock(Map<String, Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateLockById(LockDo newLockDo);
	
	/**
	 * 新增
	 */
	public int addLock(LockDo newLockDo);
	
	/**
	 * 删除
	 */
	public int deleteById(String id);

}
