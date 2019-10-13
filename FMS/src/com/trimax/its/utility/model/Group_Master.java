package com.trimax.its.utility.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.usermanagement.model.UserDetails;

@Entity
@Table(name="menu_group_master")
public class Group_Master {
	@Id
	@GeneratedValue
	@Column(name="group_id")
	private int group_id;
	@Column(name="group_name")
	private String group_name;
	@Column(name="note")
	private String note;
	@Column(name="created_by")
	private int created_by;
	@Column(name="created_date")
	private java.util.Date created_date;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="updated_date")
	private java.util.Date updated_date;
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="status")
	private String status;
	
	
	
	@Transient
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*@Column(name="page_id")
	private int page_id;*/
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name.trim();
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public java.util.Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(java.util.Date created_date) {
		this.created_date = created_date;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public java.util.Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(java.util.Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}	
	/*public int getPage_id() {		return page_id;
	}
	public void setPage_id(int page_id) {
	this.page_id = page_id;
	}*/
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usergroupid")
	private Set<UserDetails> userDetails;
	
	public Set<UserDetails> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}*/
}
