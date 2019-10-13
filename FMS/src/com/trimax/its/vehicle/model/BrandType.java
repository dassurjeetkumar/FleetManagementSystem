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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.transport.model.Schedule;

@Entity
@Table(name="brand_type")
public class BrandType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int brand_type_id;
	
	@Column
	private String brand_type_name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="brandType")
	private Set<Vehicle> vehicles;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="brandtypeId")
	private Set<ServiceLimit> ServiceLimit;

	public Set<ServiceLimit> getServiceLimit() {
		return ServiceLimit;
	}

	public void setServiceLimit(Set<ServiceLimit> serviceLimit) {
		ServiceLimit = serviceLimit;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="brand")
	private Set<Schedule> schDetails;
	
	@ManyToOne
	@JoinColumn(name="service_type_id")
	private  ServiceType serviceType;
	
	@Column
	private String status;
	
	
	@Column
	private String note;
	

	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="effective_start_date")
	private String effective_start_date	;
	
	@Column(name="effective_end_date")
	private String effective_end_date;
	
	
	public String getEffective_start_date() {
		return effective_start_date;
	}

	public String getEffective_end_date() {
		return effective_end_date;
	}

	public void setEffective_start_date(String effective_start_date) {
		this.effective_start_date = effective_start_date;
	}

	public void setEffective_end_date(String effective_end_date) {
		this.effective_end_date = effective_end_date;
	}

	@Column(name="deleted_status")
	private int deletedStatus;
	
	public int getBrand_type_id() {
		return brand_type_id;
	}

	public void setBrand_type_id(int brand_type_id) {
		this.brand_type_id = brand_type_id;
	}

	public String getBrand_type_name() {
		return brand_type_name;
	}

	public void setBrand_type_name(String brand_type_name) {
		this.brand_type_name = brand_type_name;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Set<Schedule> getSchDetails() {
		return schDetails;
	}

	public void setSchDetails(Set<Schedule> schDetails) {
		this.schDetails = schDetails;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note.trim();
	}

	public void setNote(String note) {
		this.note = note.trim();
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
