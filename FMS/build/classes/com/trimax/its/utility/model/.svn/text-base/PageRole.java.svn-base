package com.trimax.its.utility.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_page_role")
public class PageRole {
	@Id
	@GeneratedValue
	@Column(name="page_role_id")
	private int page_role_id;
	@Column(name="page_id")
	private int page_id;
	@Column(name="role_id")
	private int role_id;
	@Column(name="status")
	private String status;
	@Column(name="created_by")
	private String created_by;
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	@Column(name="created_date")
	private Date created_date;
	@Column(name="updated_by")
	private String updated_by;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="deleted_status")
	private int deleted_status;
	public int getPage_role_id() {
		return page_role_id;
	}
	public int getPage_id() {
		return page_id;
	}
	
	public String getStatus() {
		return status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setPage_role_id(int page_role_id) {
		this.page_role_id = page_role_id;
	}
	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	
}
