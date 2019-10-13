package com.trimax.its.utility.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_user_group")
public class UserGroup {
	@Id
	@GeneratedValue
	@Column(name="ugid")
	private int ugid;
	public int getUgid() {
		return ugid;
	}
	public void setUgid(int ugid) {
		this.ugid = ugid;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	@Column(name="user_id")
    private int user_id;
	@Column(name="group_id")
    private int group_id;
	
	@Column(name="status")
    private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
