package com.trimax.its.vehicle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.common.Common;

@Entity
@Table(name="docking_history")
public class DockingHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docking_id;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	
	@ManyToOne
	@JoinColumn(name="docking_type")
	private DockingType dockingType;

	@Column
	private String mechanic_user_id;
	
	@Column (name="start_time")
	private Date startTime;
	
	@Column (name="end_time")
	private Date endTime;
	
	@Column 
	private String docking_batch_name;
	
	@Column
	private String status;
	
	@Column
	private String notes;
	
	@Column 
	private String fip_change;
	
	@Column 
	private String eoc_change;
	
	@Column
	private String daily_kms;
	
	@Column 
	private String eoc_kms;
	
	@Column 
	private String docking_kms;
	
	@ManyToOne
	@JoinColumn(name="component_types")
	private ComponentsTypes componenetType;
	
	
	@ManyToOne
	@JoinColumn(name="sub_components")
	private SubComponents subComponenetType;
	
	
	@Column(name="created_by")
	private Integer cretaedBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String createdDate;
	
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	@Transient
	Common common = new Common();
	
	@Transient
	private String startDateString;
	
	@Transient
	private String endDateString;
	
	
	public int getDocking_id() {
		return docking_id;
	}

	public void setDocking_id(int docking_id) {
		this.docking_id = docking_id;
	}

	

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	
	public String getDocking_batch_name() {
		return docking_batch_name;
	}

	public void setDocking_batch_name(String docking_batch_name) {
		this.docking_batch_name = docking_batch_name;
	}

	public DockingType getDockingType() {
		return dockingType;
	}

	public void setDockingType(DockingType dockingType) {
		this.dockingType = dockingType;
	}

	public String getMechanic_user_id() {
		return mechanic_user_id;
	}

	public void setMechanic_user_id(String mechanic_user_id) {
		this.mechanic_user_id = mechanic_user_id;
	}


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFip_change() {
		return fip_change;
	}

	public void setFip_change(String fip_change) {
		this.fip_change = fip_change;
	}

	public String getEoc_change() {
		return eoc_change;
	}

	public void setEoc_change(String eoc_change) {
		this.eoc_change = eoc_change;
	}

	public String getDaily_kms() {
		return daily_kms;
	}

	public void setDaily_kms(String daily_kms) {
		this.daily_kms = daily_kms;
	}

	public String getEoc_kms() {
		return eoc_kms;
	}

	public void setEoc_kms(String eoc_kms) {
		this.eoc_kms = eoc_kms;
	}

	public String getDocking_kms() {
		return docking_kms;
	}

	public void setDocking_kms(String docking_kms) {
		this.docking_kms = docking_kms;
	}

	public Integer getCretaedBy() {
		return cretaedBy;
	}

	public void setCretaedBy(Integer cretaedBy) {
		this.cretaedBy = cretaedBy;
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

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ComponentsTypes getComponenetType() {
		return componenetType;
	}

	public void setComponenetType(ComponentsTypes componenetType) {
		this.componenetType = componenetType;
	}

	public SubComponents getSubComponenetType() {
		return subComponenetType;
	}

	public void setSubComponenetType(SubComponents subComponenetType) {
		this.subComponenetType = subComponenetType;
	}


	
	
}
