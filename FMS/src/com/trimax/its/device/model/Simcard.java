package com.trimax.its.device.model;

import java.math.BigInteger;
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

@Entity
@Table(name="simcard")
public class Simcard {
	@Id
	@GeneratedValue
	@Column(name="simcard_id")
	private int simcard_id;
	
	
	@Column(name="serial_number")
	private String serial_number;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="procured_date")
	private Date procured_date;
	
	public Date getProcured_date() {
		return procured_date;
	}



	public void setProcured_date(Date procured_date) {
		this.procured_date = procured_date;
	}

	@Column(name="notes")
	private String notes;

	@Column(name="created_by")
	private String created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="service_plan")
	private String service_plan;
	
	@Column(name="plan_type")
	private String plan_type;
	
	@Column(name="org_name")
	private int org_name;
	
	@Column(name="status")
	private String status;
	
	@Column(name="deleted_status")
	private int deleted_status;

	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendorDetailsSim;
	
	@Transient
	private String procuredDateString;
	
	
	
	public String getProcuredDateString() {
		return procuredDateString;
	}



	public void setProcuredDateString(String procuredDateString) {
		this.procuredDateString = procuredDateString;
	}



	public Vendor getVendorDetailsSim() {
		return vendorDetailsSim;
	}



	public void setVendorDetailsSim(Vendor vendorDetailsSim) {
		this.vendorDetailsSim = vendorDetailsSim;
	}



	public int getSimcard_id() {
		return simcard_id;
	}

	

	public String getNotes() {
		return notes;
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

	public String getService_plan() {
		return service_plan;
	}

	public String getPlan_type() {
		return plan_type;
	}

	public int getOrg_name() {
		return org_name;
	}

	public String getStatus() {
		return status;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setSimcard_id(int simcard_id) {
		this.simcard_id = simcard_id;
	}

	


	public String getSerial_number() {
		return serial_number.trim();
	}



	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number.trim();
	}




	public String getPhone_number() {
		return phone_number;
	}



	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}



	public void setNotes(String notes) {
		this.notes = notes.trim();
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

	public void setService_plan(String service_plan) {
		this.service_plan = service_plan;
	}

	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}

	public void setOrg_name(int org_name) {
		this.org_name = org_name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	
	
	
	

}
