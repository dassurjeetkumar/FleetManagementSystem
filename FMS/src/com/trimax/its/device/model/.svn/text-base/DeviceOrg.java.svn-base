package com.trimax.its.device.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_org")
public class DeviceOrg {
	@Id
	@GeneratedValue
	@Column(name="device_org_id")
	private int device_org_id;
	@Column(name="device_id")
	private int device_id;
	@Column(name="org_id")
	private int org_id;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="status")
	private String status;
	
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
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDevice_org_id() {
		return device_org_id;
	}
	public int getDevice_id() {
		return device_id;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setDevice_org_id(int device_org_id) {
		this.device_org_id = device_org_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	

}
