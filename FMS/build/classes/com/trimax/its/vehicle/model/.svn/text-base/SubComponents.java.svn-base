package com.trimax.its.vehicle.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sub_components")
public class SubComponents {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sub_components_id")
	private int subComponentId;
	
	@Column(name="sub_component_name")
	private String subComponentType;
	
	
	@ManyToOne
	@JoinColumn(name="component_type_id")
	private ServiceType componentType;
	
	
	@Column
	private String status;
	
	@Column
	private String notes;
	
	@Column(name="created_by")
	private int cretaedBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String cretaedDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="subComponenetType")
	private Set<DockingHistory> dockingHistory;


	public int getSubComponentId() {
		return subComponentId;
	}


	public void setSubComponentId(int subComponentId) {
		this.subComponentId = subComponentId;
	}


	public String getSubComponentType() {
		return subComponentType;
	}


	public void setSubComponentType(String subComponentType) {
		this.subComponentType = subComponentType;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ServiceType getComponentType() {
		return componentType;
	}


	public void setComponentType(ServiceType componentType) {
		this.componentType = componentType;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public int getCretaedBy() {
		return cretaedBy;
	}


	public void setCretaedBy(int cretaedBy) {
		this.cretaedBy = cretaedBy;
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


	public String getCretaedDate() {
		return cretaedDate;
	}


	public void setCretaedDate(String cretaedDate) {
		this.cretaedDate = cretaedDate;
	}


	public int getDeletedStatus() {
		return deletedStatus;
	}


	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}


	public Set<DockingHistory> getDockingHistory() {
		return dockingHistory;
	}


	public void setDockingHistory(Set<DockingHistory> dockingHistory) {
		this.dockingHistory = dockingHistory;
	}
	
	
	

}
