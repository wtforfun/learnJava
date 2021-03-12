/*
 * 文 件 名:  ChannelUser.java
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
 * 客户平台用户
 * <功能详细描述>
 * 
 * @author  ETOC-ChenChao
 * @version  [版本号, Jun 27, 2019]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChannelUser implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 6730387959783651452L;
	
	private String id;
	
	private String loginName;
	
	private String userName;
	
	private String familyName;
	
	private String firstName;
	
	private String channelId;
	
	private Channel chanelInfo;
	
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

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Channel getChanelInfo() {
		return chanelInfo;
	}

	public void setChanelInfo(Channel chanelInfo) {
		this.chanelInfo = chanelInfo;
	}
	
	@Override
	public String toString() {
		return loginName;
	}

	/**
	 * 渠道，即代理商
	 * <一句话功能简述>
	 * <功能详细描述>
	 * 
	 * @author  ETOC-ChenChao
	 * @version  [版本号, Jun 27, 2019]
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	public class Channel implements Serializable {
		/**
		 * 注释内容
		 */
		private static final long serialVersionUID = 2759573689830219356L;

		private String channelId;
		
		private String channelNo;
		
		private String channelParentNo;
		
		private String proxyModelId;
		
		private String channelName;

		public String getChannelId() {
			return channelId;
		}

		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}

		public String getChannelNo() {
			return channelNo;
		}

		public void setChannelNo(String channelNo) {
			this.channelNo = channelNo;
		}

		public String getChannelParentNo() {
			return channelParentNo;
		}

		public void setChannelParentNo(String channelParentNo) {
			this.channelParentNo = channelParentNo;
		}

		public String getProxyModelId() {
			return proxyModelId;
		}

		public void setProxyModelId(String proxyModelId) {
			this.proxyModelId = proxyModelId;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		
	}

}
