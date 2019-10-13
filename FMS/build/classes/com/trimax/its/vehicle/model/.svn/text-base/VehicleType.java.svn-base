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
@Table(name="vehicle_type")
public class VehicleType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int vehicle_type_id	;
	
	@Column
	private String vehicle_type_name;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="vehicleType")
	private Set<Vehicle> vehicles;

	@Column
	private String status;
	
	@Column(nullable=true)
	private String notes;
	
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

	
	
	
	public int getVehicle_type_id() {
		return vehicle_type_id;
	}

	public void setVehicle_type_id(int vehicle_type_id) {
		this.vehicle_type_id = vehicle_type_id;
	}

	public String getVehicle_type_name() {
		return vehicle_type_name;
	} 

	public void setVehicle_type_name(String vehicle_type_name) {
		this.vehicle_type_name = vehicle_type_name;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
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

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	
	
	
}
