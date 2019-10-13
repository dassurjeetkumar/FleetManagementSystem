package com.trimax.its.pass.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
	@Entity
	@Table(name="pass_duration_type")
	public class PassDurationType {
	@Id
	@GeneratedValue
	@Column(name="pass_duration_id")
	private int pass_duration_id;



	@Column(name="pass_duration_name")
	private String pass_duration_name;
	
	@Column(name="pass_type_id")
	private int pass_type_id;
	
	@Column(name="pass_sub_type_id")
	private int pass_sub_type_id;
	
	@Column(name="pass_start_date")
	private String pass_start_date;
	
	@Column(name="pass_expiry_date")
	private String pass_expiry_date;

	@Column(name="status")
	private String status;

	@Column(name="remarks")
	private String remarks;

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
	
	
	
	
	public int getPass_type_id() {
		return pass_type_id;
	}

	public void setPass_type_id(int pass_type_id) {
		this.pass_type_id = pass_type_id;
	}

	public int getPass_sub_type_id() {
		return pass_sub_type_id;
	}

	public void setPass_sub_type_id(int pass_sub_type_id) {
		this.pass_sub_type_id = pass_sub_type_id;
	}

	public String getPass_start_date() {
		return pass_start_date;
	}

	public void setPass_start_date(String pass_start_date) {
		this.pass_start_date = pass_start_date;
	}

	

	public String getPass_expiry_date() {
		return pass_expiry_date;
	}

	public void setPass_expiry_date(String pass_expiry_date) {
		this.pass_expiry_date = pass_expiry_date;
	}

	public int getPass_duration_id() {
		return pass_duration_id;
	}

	public void setPass_duration_id(int pass_duration_id) {
		this.pass_duration_id = pass_duration_id;
	}

	public String getPass_duration_name() {
		return pass_duration_name;
	}

	public void setPass_duration_name(String pass_duration_name) {
		this.pass_duration_name = pass_duration_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

}
