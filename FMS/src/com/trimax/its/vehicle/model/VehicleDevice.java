package com.trimax.its.vehicle.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.Device_Type;

@Table
@Entity(name="vehicle_device")
public class VehicleDevice {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicleDetails;
	
	@ManyToOne
	@JoinColumn(name="device_id")
	private Device deviceDetails;
	
	@ManyToOne
	@JoinColumn(name="device_type_id")
	private Device_Type deviceTypeDetails;
	
	@Column(nullable = true,name="updated_date")
	private String updatedDate;

	@Column(nullable = true,name="updated_by")
	private Integer updatedBy;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="created_by")
	private Integer createdBy;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(Vehicle vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	public Device getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(Device deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}


	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Device_Type getDeviceTypeDetails() {
		return deviceTypeDetails;
	}

	public void setDeviceTypeDetails(Device_Type deviceTypeDetails) {
		this.deviceTypeDetails = deviceTypeDetails;
	}

	
}
