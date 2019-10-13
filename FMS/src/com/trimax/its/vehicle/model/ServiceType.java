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

import com.trimax.its.fare.model.SpecialPassTicketModel;

import com.trimax.its.transport.model.PeakHour;

@Entity
@Table(name="service_type")
public class ServiceType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int service_type_id;
	
	@Column
	private String service_type_name;
	
	@Column
	private String abbreviation;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="serviceType")
	private Set<Vehicle> vehicles;
	
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
	
	@Column(name="deleted_status")
	private int deletedStatus;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="serviceType")
	private Set<BrandType> brandTypes;

	
	public int getService_type_id() {
		return service_type_id;
	}

	public void setService_type_id(int service_type_id) {
		this.service_type_id = service_type_id;
	}

	public String getService_type_name() {
		return service_type_name;
	}

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}

	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<BrandType> getBrandTypes() {
		return brandTypes;
	}

	public void setBrandTypes(Set<BrandType> brandTypes) {
		this.brandTypes = brandTypes;
	}
	
	// paek hour mapping start
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servicetype")
	private Set<PeakHour> peakhour;


	public Set<PeakHour> getPeakhour() {
		return peakhour;
	}

	public void setPeakhour(Set<PeakHour> peakhour) {
		this.peakhour = peakhour;
	}

	// paek hour mapping end
	@OneToMany(fetch = FetchType.LAZY,mappedBy="servicetype")
	Set<SpecialPassTicketModel> specialpass;
	
	

}
