package com.trimax.its.vehicle.model;

import java.util.Date;
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
import javax.persistence.Transient;

@Entity
@Table(name="docking_type")
public class DockingType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docking_type_id	;
	
	@Column 
	private String docking_type;
	
	@Column(name="docking_kms")
	private String dockingTypeKms;
	
	@Column(name="kms_limit_for_alert")
	private String dockingKmAlert;
	
	@Column
	private String status;
	
	@Column 
	private String notes;
	
	@Column
	private int deleted_status;
	
	@Column(name="created_by")
	private int CreatedBy ;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date") 
	private  String updatedDate;
	
	@Column(name="created_date") 
	private  String createdDate;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="dockingType")
	private Set<DockingHistory> dockingHistory;
	
	
	
	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	


	public String getDockingTypeKms() {
		return dockingTypeKms;
	}

	public void setDockingTypeKms(String dockingTypeKms) {
		this.dockingTypeKms = dockingTypeKms;
	}

	public int getDocking_type_id() {
		return docking_type_id;
	}

	public void setDocking_type_id(int docking_type_id) {
		this.docking_type_id = docking_type_id;
	}

	public String getDocking_type() {
		return docking_type;
	}

	public void setDocking_type(String docking_type) {
		this.docking_type = docking_type;
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

	public Set<DockingHistory> getDockingHistory() {
		return dockingHistory;
	}

	public void setDockingHistory(Set<DockingHistory> dockingHistory) {
		this.dockingHistory = dockingHistory;
	}


	public String getDockingKmAlert() {
		return dockingKmAlert;
	}

	public void setDockingKmAlert(String dockingKmAlert) {
		this.dockingKmAlert = dockingKmAlert;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
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
