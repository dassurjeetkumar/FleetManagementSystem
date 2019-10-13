package com.trimax.its.device.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name="maintenance_type")
public class MaintenanceType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maintenance_type_id	;
	
	@Column 
	private String maintenance_type;

	
	@Column
	private String notes;
	
	@Column
	private String status;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;

	public int getMaintenance_type_id() {
		return maintenance_type_id;
	}

	public void setMaintenance_type_id(int maintenance_type_id) {
		this.maintenance_type_id = maintenance_type_id;
	}

	public String getMaintenance_type() {
		return maintenance_type.trim();
	}

	public void setMaintenance_type(String maintenance_type) {
		this.maintenance_type = maintenance_type.trim();
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
