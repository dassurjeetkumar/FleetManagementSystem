package com.trimax.its.vehicle.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Vehicle_Status")
public class VehicleStatusModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "status_id")
	private int statusid;

	@Column(name = "vehicle_no")
	private String vehicleno;

	@Column(name = "status_cause")
	private String statuscause;
	
	@Column(name = "remarks",nullable=true)
	private String remarksvehicle;

	public String getRemarksvehicle() {
		return remarksvehicle;
	}

	public void setRemarksvehicle(String remarksvehicle) {
		this.remarksvehicle = remarksvehicle;
	}

	@Column
	private String from_date;
	
	@Column
	private String reason;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(nullable =true)
	private String to_date;
	
	@Column(name = "place")
	private String place;

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getStatuscause() {
		return statuscause;
	}

	public void setStatuscause(String statuscause) {
		this.statuscause = statuscause;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	
}
