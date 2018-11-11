/*
 * Powered By  sungymf@fansfinancial.com
 * Web Site: https://www.qianshenghua.com
 * Since 2008 - 2018
 */

package com.suns.mysql.module;

import org.apache.ibatis.type.Alias;

/**
 * @author  sungymf@fansfinancial
 * @version 1.0
 * @since 1.0
 */

@Alias("lockDo")
public class LockDo  implements java.io.Serializable{	
	
	//columns START
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

