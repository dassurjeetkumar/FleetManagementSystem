package com.trimax.its.device.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.model.Vendor;

/**
 * @author root
 *
 */
@Entity
@Table(name="battery")
public class Battery {

	@Id
	@GeneratedValue
	@Column(name="battery_id")
	private int battery_id;
	
	@Column(name="serial_number")
	private String serial_number;

	
	@Column(name="procured_date")
	private Date procured_date;
	
	@Column(name="capacity")
	private String capacity;
	
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

	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendorDetails;
	
	
	@Transient
	private String procuredDateString;
	
	public int getBattery_id() {
		return battery_id;
	}

	public String getSerial_number() {
		return serial_number;
	}

	
	public Date getProcured_date() {
		return procured_date;
	}

	public void setProcured_date(Date procured_date) {
		this.procured_date = procured_date;
	}

	public String getCapacity() {
		return capacity;
	}

	public String getNotes() {
		return notes;
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

	public void setBattery_id(int battery_id) {
		this.battery_id = battery_id;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number.trim();
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity.trim();
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public Vendor getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(Vendor vendorDetails) {
		this.vendorDetails = vendorDetails;
	}

	public String getProcuredDateString() {
		return procuredDateString;
	}

	public void setProcuredDateString(String procuredDateString) {
		this.procuredDateString = procuredDateString;
	}
	
	
}
