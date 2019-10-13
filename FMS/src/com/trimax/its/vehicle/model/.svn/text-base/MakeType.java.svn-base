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
@Table(name="make_type")
public class MakeType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int make_type_id;
	
	@Column
	private String make_type_name;
	
	@Column
	private String status;
	
	@Column
	private String  note;
	

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

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="makeType")
	private Set<Vehicle> vehicles;


	public int getMake_type_id() {
		return make_type_id;
	}

	public void setMake_type_id(int make_type_id) {
		this.make_type_id = make_type_id;
	}

	public String getMake_type_name() {
		return make_type_name;
	}

	public void setMake_type_name(String make_type_name) {
		this.make_type_name = make_type_name;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
