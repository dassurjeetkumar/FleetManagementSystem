package com.trimax.its.vehicle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="accident_type")
public class AccidentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accident_type_id	;
	
	@Column 
	private String accident_type_name;
	
	
	@Column
	private String status;
	
	@Column 
	private String note;
	
	@Column
	private int deleted_status;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;

	public int getAccident_type_id() {
		return accident_type_id;
	}

	public String getAccident_type_name() {
		return accident_type_name;
	}

	public String getStatus() {
		return status;
	}

	public String getNotes() {
		return note;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setAccident_type_id(int accident_type_id) {
		this.accident_type_id = accident_type_id;
	}

	public void setAccident_type_name(String accident_type_name) {
		this.accident_type_name = accident_type_name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNotes(String notes) {
		this.note = notes;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	
	

}
