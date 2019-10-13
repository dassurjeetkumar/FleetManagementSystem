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

@Entity
@Table(name="vehicle_scrap")
public class VehicleScrap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scrap_id")
	private int scrapId;
	
	@Column(name="scrap_date")
	private Date scrapDate;
	
	@Column(name="created_date")
	private String created_date;  
	
	@Column(name="updated_date")
	private String updated_date;  
	
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	
	@Column(name="reason")
	private String reason;
	
	@Column
	private String status;

	
	@Transient
	private String scrapDateString;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle scrapVehicle;
	
	
	public int getScrapId() {
		return scrapId;
	}

	public void setScrapId(int scrapId) {
		this.scrapId = scrapId;
	}

	public Date getScrapDate() {
		return scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getScrapDateString() {
		return scrapDateString;
	}

	public Vehicle getScrapVehicle() {
		return scrapVehicle;
	}

	public void setScrapVehicle(Vehicle scrapVehicle) {
		this.scrapVehicle = scrapVehicle;
	}

	public void setScrapDateString(String scrapDateString) {
		this.scrapDateString = scrapDateString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	

}	
