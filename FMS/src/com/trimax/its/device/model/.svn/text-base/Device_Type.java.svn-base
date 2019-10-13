package com.trimax.its.device.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleDevice;

@Entity
@Table(name="device_type")
public class Device_Type {
	@Id
	@GeneratedValue
	
	@Column(name="device_type_id")
	private int device_type_id;
	
	@Column(name="device_type_name")
	private String device_type_name;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@OneToMany(mappedBy="device")
	private Set<Device> device;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="deviceTypeDetails")
    private Set<VehicleDevice> deviceTypeMappingVehicle;
	
	
	public int getDevice_type_id() {
		return device_type_id;
	}
	public String getDevice_type_name() {
		return device_type_name.trim();
	}
	public String getNotes() {
		return notes.trim();
	}
	public String getStatus() {
		return status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDevice_type_id(int device_type_id) {
		this.device_type_id = device_type_id;
	}
	public void setDevice_type_name(String device_type_name) {
		this.device_type_name = device_type_name.trim();
	}
	public void setNotes(String notes) {
		this.notes = notes.trim();
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	public Set<Device> getDevice() {
		return device;
	}
	public void setDevice(Set<Device> device) {
		this.device = device;
	}
	public Set<VehicleDevice> getDeviceTypeMappingVehicle() {
		return deviceTypeMappingVehicle;
	}
	public void setDeviceTypeMappingVehicle(
			Set<VehicleDevice> deviceTypeMappingVehicle) {
		this.deviceTypeMappingVehicle = deviceTypeMappingVehicle;
	}
	
	
	
}
