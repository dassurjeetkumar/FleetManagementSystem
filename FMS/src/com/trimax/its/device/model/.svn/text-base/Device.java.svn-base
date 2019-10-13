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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.model.Vendor;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleDevice;

@Entity
@Table(name = "device")
public class Device {
	@Id
	@GeneratedValue
	@Column(name = "device_id")
	private int device_id;

	@Column(name = "device_serial_number")
	private String device_serial_number;
	
	@ManyToOne
	@JoinColumn(name = "model")
	private ModelType model;

	@Transient
	private String purchasedateString;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "deviceHis")
	private Set<DeviceHistory> deviceHis;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "deviceDetails")
	private Set<VehicleDevice> deviceMappingVehicle;

	@Column(name = "created_by")
	private String created_by;

	@Column(name = "created_date")
	private Date created_date;

	@Column(name = "purchase_date")
	private Date purchase_date;

	@Column(name = "notes")
	private String notes;

	@Column(name = "updated_by")
	private int updated_by;

	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "deleted_status")
	private int deleted_status;
	
	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private Device_Type device;

	@ManyToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vtuDevice")
	private Set<Vehicle> vtuVehicle;

	public Device() {

	}

	public int getDevice_id() {
		return device_id;
	}

	public String getDevice_serial_number() {
		return device_serial_number.trim();
	}

	public ModelType getModel() {
		return model;
	}

	public void setModel(ModelType model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public String getNotes() {
		return notes;
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

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public void setDevice_serial_number(String device_serial_number) {
		this.device_serial_number = device_serial_number.trim();
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

	public void setNotes(String notes) {
		this.notes = notes.trim();
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

	public Device_Type getDevice() {
		return device;
	}

	public void setDevice(Device_Type device) {
		this.device = device;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Set<DeviceHistory> getDeviceHis() {
		return deviceHis;
	}

	public void setDeviceHis(Set<DeviceHistory> deviceHis) {
		this.deviceHis = deviceHis;
	}

	public Set<Vehicle> getVtuVehicle() {
		return vtuVehicle;
	}

	public void setVtuVehicle(Set<Vehicle> vtuVehicle) {
		this.vtuVehicle = vtuVehicle;
	}

	public Set<VehicleDevice> getDeviceMappingVehicle() {
		return deviceMappingVehicle;
	}

	public void setDeviceMappingVehicle(Set<VehicleDevice> deviceMappingVehicle) {
		this.deviceMappingVehicle = deviceMappingVehicle;
	}

	public String getPurchasedateString() {
		return purchasedateString;
	}

	public void setPurchasedateString(String purchasedateString) {
		this.purchasedateString = purchasedateString;
	}
	
}
