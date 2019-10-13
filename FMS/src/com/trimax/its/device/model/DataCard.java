package com.trimax.its.device.model;

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

import com.trimax.its.model.Vendor;
import com.trimax.its.vehicle.model.Vehicle;

@Entity
@Table(name="data_card")
public class DataCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int data_card_id	;
	
	@Column
	private Date procured_date;
	
	@Column
	private String status;
	
	@Column
	private String serial_number	;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;
	
	@Transient
	private String stringprocureddate;

	public int getData_card_id() {
		return data_card_id;
	}

	public void setData_card_id(int data_card_id) {
		this.data_card_id = data_card_id;
	}

	public Date getProcured_date() {
		return procured_date;
	}

	public void setProcured_date(Date procured_date) {
		this.procured_date = procured_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerial_number() {
		return serial_number.trim();
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number.trim();
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public String getStringprocureddate() {
		return stringprocureddate;
	}

	public void setStringprocureddate(String stringprocureddate) {
		this.stringprocureddate = stringprocureddate;
	}
	
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private  Vendor vendor;

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
}
