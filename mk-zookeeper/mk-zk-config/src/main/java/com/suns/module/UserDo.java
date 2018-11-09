/*
 * Powered By  sungymf@fansfinancial.com
 * Web Site: https://www.qianshenghua.com
 * Since 2008 - 2018
 */

package com.suns.module;

import org.apache.ibatis.type.Alias;

@Alias("userDo")
public class UserDo  implements java.io.Serializable{	
	
	//columns START
	private Integer id;
	private String userName;
	private String pwd;
	//columns END
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String value) {
		this.pwd = value;
	}

}

