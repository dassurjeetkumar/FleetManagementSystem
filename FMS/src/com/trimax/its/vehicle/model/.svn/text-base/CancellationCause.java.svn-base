package com.trimax.its.vehicle.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;




@Entity
@Table(name="cancellation_cause")
public class CancellationCause {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cancellation_id	;
	
	@Column(name="cancellation_name")
	private String cancellation_name;

	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="modified_by")
	private Integer updatedBy;
	
	@Column(name="modified_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="deletedstatus")
	private int deletedstatus;
	
	public int getDeletedstatus() {
		return deletedstatus;
	}

	public void setDeletedstatus(int deletedstatus) {
		this.deletedstatus = deletedstatus;
	}

	public int getCancellation_id() {
		return cancellation_id;
	}

	public void setCancellation_id(int cancellation_id) {
		this.cancellation_id = cancellation_id;
	}

	public String getCancellation_name() {
		return cancellation_name;
	}

	public void setCancellation_name(String cancellation_name) {
		this.cancellation_name = cancellation_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}



	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	

	
	
	
}
