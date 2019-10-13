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

import com.trimax.its.device.model.Device;

@Entity
@Table(name="model_type")
public class ModelType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int model_type_id;
	
	@Column
	private String model_type_name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="modelType")
	private Set<Vehicle> vehicles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="model")
	private Set<Device> device;
	

	@Column(nullable=true)
	private String notes;

	@Column
	private String status;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	public int getModel_type_id() {
		return model_type_id;
	}

	public void setModel_type_id(int model_type_id) {
		this.model_type_id = model_type_id;
	}

	public String getModel_type_name() {
		return model_type_name;
	}

	public void setModel_type_name(String model_type_name) {
		this.model_type_name = model_type_name;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
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

	public Set<Device> getDevice() {
		return device;
	}

	public void setDevice(Set<Device> device) {
		this.device = device;
	} 
	
}
