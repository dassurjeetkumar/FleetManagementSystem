package com.trimax.its.utility.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="menu_page_master")
public class Page_Master {
	@Id
	@GeneratedValue
	@Column(name="page_id")
	private int page_id;
	
	@Transient
	private String  parentname;
	
	

	
	@Transient
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public int getPage_id() {
		return page_id;
	}
	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name.trim();
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	@Column(name="page_name")
	private String page_name;
	@Column(name="path")
	private String path;
	@Column(name="status")
	private String status;
	@Column(name="deleted_status")
	private int deleted_status;
	@Column(name="created_by")
	private int created_by;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="updated_date")
	private Date updated_date;
	
	
	@Column(name="parent_id")
	private int parent_id;
	
	
	@Column(name="level")
	private int level;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Column(name="sequence")
	private int sequence;
	
	@Column(name="page_type")
	private String page_type;

	public String getPage_type() {
		return page_type;
	}
	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}
	

}
