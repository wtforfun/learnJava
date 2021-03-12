/*
 * 文 件 名:  User.java
 * 版    权:  ETOC 信息技术有限公司, Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ETOC-ChenChao
 * 修改时间:  Jun 27, 2019
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package org.channel.common.vo;

import java.io.Serializable;

/**
 * 管理平台用户
 * <功能详细描述>
 * 
 * @author  ETOC-ChenChao
 * @version  [版本号, Jun 27, 2019]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class User implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -901177658330292498L;
	
	private String id;
	
	private String loginName;
	
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return loginName;
	}

}
