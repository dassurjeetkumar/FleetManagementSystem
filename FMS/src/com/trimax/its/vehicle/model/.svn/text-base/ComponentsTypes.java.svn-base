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
@Table(name="components_type")
public class ComponentsTypes {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="component_type_id")
	private int componentTypeId;
	
	
	@Column(name="component_name")
	private String componentName;
	

	@Column 
	private String status;
	
	@Column
	private String notes;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	@Column(name="created_by")
	private int cretaedBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String cretaedDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="componentType")
	private Set<SubComponents> subComponenets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="componenetType")
	private Set<DockingHistory> dockingHisiory;

	
	
	

	public int getComponentTypeId() {
		return componentTypeId;
	}

	public void setComponentTypeId(int componentTypeId) {
		this.componentTypeId = componentTypeId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
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

	public Set<SubComponents> getSubComponenets() {
		return subComponenets;
	}

	public void setSubComponenets(Set<SubComponents> subComponenets) {
		this.subComponenets = subComponenets;
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

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public Set<DockingHistory> getDockingHisiory() {
		return dockingHisiory;
	}

	public void setDockingHisiory(Set<DockingHistory> dockingHisiory) {
		this.dockingHisiory = dockingHisiory;
	}
	
	
	
	
	
}
