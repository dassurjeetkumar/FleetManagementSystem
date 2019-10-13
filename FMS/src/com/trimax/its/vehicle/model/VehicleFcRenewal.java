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
@Table(name="vehicle_fc_renewal")
public class VehicleFcRenewal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int vehicle_fc_renewal_id;
	
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name="fc_expiry_date")
	private Date fcExpiryDate;
	
	
	@Column(name="fc_renewal_date")
	private Date fcRenewalDate;
	
	@Transient
	Common common = new Common();
	
	@Transient
	private String fcRenewDateString;
	
	@Transient
	private String fcExpiryDateString;
	
	public int getVehicle_fc_renewal_id() {
		return vehicle_fc_renewal_id;
	}

	public void setVehicle_fc_renewal_id(int vehicle_fc_renewal_id) {
		this.vehicle_fc_renewal_id = vehicle_fc_renewal_id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}


	public Date getFcExpiryDate() {
		return fcExpiryDate;
	}

	public void setFcExpiryDate(Date fcExpiryDate) {
		this.fcExpiryDate = fcExpiryDate;
	}

	public Date getFcRenewalDate() {
		return fcRenewalDate;
	}

	public void setFcRenewalDate(Date fcRenewalDate) {
		this.fcRenewalDate = fcRenewalDate;
	}

	public String getFcRenewDateString() {
		return fcRenewDateString;
	}

	public void setFcRenewDateString(String fcRenewDateString) {
		this.fcRenewDateString = fcRenewDateString;
	}

	public String getFcExpiryDateString() {
		return fcExpiryDateString;
	}

	public void setFcExpiryDateString(String fcExpiryDateString) {
		this.fcExpiryDateString = fcExpiryDateString;
	}
	
		

}
