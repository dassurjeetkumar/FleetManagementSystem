package com.trimax.its.vehicle.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name="extrakm_cause")
public class ExtraKmCause {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int extrakm_id	;
	
	@Column
	private String extrakm_name;

	
	

	@Column
	private String status;
	
	@Column(nullable=true)
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

	public int getExtrakm_id() {
		return extrakm_id;
	}

	public void setExtrakm_id(int extrakm_id) {
		this.extrakm_id = extrakm_id;
	}

	public String getExtrakm_name() {
		return extrakm_name;
	}

	public void setExtrakm_name(String extrakm_name) {
		this.extrakm_name = extrakm_name;
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
