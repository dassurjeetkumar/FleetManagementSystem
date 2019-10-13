/* Document   : SessionDetailsDao.java
    Created on : May 18, 2014, 11:05:01 AM
    Author     : manojv
*/
package com.trimax.its.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session_details")
public class SessionDetails {
	@Id
	@GeneratedValue(strategy =GenerationType.TABLE)
	@Column(name="session_id")
	private int sessionId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="html_session_id")
	private String htmlSessionId;
	
	@Column(name="login_date")
	private String loginDate;
	
	@Column(name="logout_date")
	private String logoutDate;
	
	@Column(name="update_date")
	private String updateDate;
	
	@Column(name="status")
	private String status;

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHtmlSessionId() {
		return htmlSessionId;
	}

	public void setHtmlSessionId(String htmlSessionId) {
		this.htmlSessionId = htmlSessionId;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
