package com.trimax.its.inventoryticketpasstype.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="InventoryTicket_PassType")
public class InventoryTicketPassType {

	@Id
	@GeneratedValue
	@Column(name="inventorypass_id")
	private int inventorypass_id;
	
	@Column(name="day_month")
	private String day_month;
	
	@Column(name="pass_type")
	private int pass_type;
	
	@Column(name="status")
	private String status;
	
	@Column(name="deleted_status") 
	private int deleted_status;
	@Column(name="created_by")
	
	private String created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
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
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	public int getInventorypass_id() {
		return inventorypass_id;
	}
	public void setInventorypass_id(int inventorypass_id) {
		this.inventorypass_id = inventorypass_id;
	}
	public String getDay_month() {
		return day_month.trim();
	}
	public void setDay_month(String day_month) {
		this.day_month = day_month.trim();
	}
	public int getPass_type() {
		return pass_type;
	}
	public void setPass_type(int pass_type) {
		this.pass_type = pass_type;
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
}
