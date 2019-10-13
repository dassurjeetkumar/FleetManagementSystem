package com.trimax.its.pass.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

	
	@Entity
	@Table(name="pass_purchase_type")
	public class PassPurchaseType {
	@Id
	@GeneratedValue
	@Column(name="pass_purchase_id")
	private int pass_purchase_id;

	@Column(name="pass_purchase_name")
	private String pass_purchase_name;

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

	public int getPass_purchase_id() {
		return pass_purchase_id;
	}

	public void setPass_purchase_id(int pass_purchase_id) {
		this.pass_purchase_id = pass_purchase_id;
	}

	public String getPass_purchase_name() {
		return pass_purchase_name;
	}

	public void setPass_purchase_name(String pass_purchase_name) {
		this.pass_purchase_name = pass_purchase_name;
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

	@Column(name="deleted_status")
	private int deleted_status;

}
