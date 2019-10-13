package com.trimax.its.device.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="battery_device")
public class BatteryDevice {
	
	@Column(name="battery_id")
	private int battery_id;
	
	@Column(name="device_id")
	private int device_id;
	@Id
	@GeneratedValue
	@Column(name="device_battery_id	")
	private int device_battery_id;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="created_by")
	private String created_by;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	@Column(name="status")
	private String status;
	
	
	
	public int getBattery_id() {
		return battery_id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public int getDevice_battery_id() {
		return device_battery_id;
	}

	public void setBattery_id(int battery_id) {
		this.battery_id = battery_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public void setDevice_battery_id(int device_battery_id) {
		this.device_battery_id = device_battery_id;
	}
	
	
}
