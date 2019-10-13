package com.trimax.its.utility.model;

import java.util.Date;
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
@Table(name="menu_role_master")
public class Role {

	@Id
	@GeneratedValue
	@Column(name="role_id")
	
	private int role_id;
	@Column(name="role_name")
	private String role_name;
	
	@Column(name="status")
	private String status;
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="created_date")
	private Date created_date;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Transient
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRole_name() {
		return role_name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Date getCreated_date() {
		return created_date;
	}
	
	public Date getUpdated_date() {
		return updated_date;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name.trim();
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserDetails> userDetails;

	public Set<UserDetails> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	
	
	
}
