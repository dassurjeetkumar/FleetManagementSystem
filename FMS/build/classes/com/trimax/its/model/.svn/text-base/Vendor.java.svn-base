package com.trimax.its.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.sun.istack.internal.Nullable;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.DataCard;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.RFIDCard;
import com.trimax.its.device.model.Simcard;
import com.trimax.its.vehicle.model.VehicleMaintenance;



@Entity
@Table(name="vendor", catalog = "its")
public class Vendor implements java.io.Serializable {
	/**
	 * @param args
	 */
	@Id
	@GeneratedValue
	@Column(name="vendor_id") 
	private int id;
	
	@Column(name="vendor_name")
	private String vendor_name;
	@Column(name="device_type_id")
	private String device_type_id;
	public String getDevice_type_id() {
		return device_type_id;
	}

	public void setDevice_type_id(String device_type_id) {
		this.device_type_id = device_type_id;
	}

	@Column(name="vendor_contact_person")
	private String vendor_contact_person;
	
	@Column(name="note")
	private String note;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="updated_by",nullable=true)
	private int updated_by	;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;

	@OneToMany(mappedBy="vendor")
	private Set<Device> vendor;
	
	@OneToMany(mappedBy="vendorDetails")
	private Set<Battery> batteryDetails;
	
	@OneToMany(mappedBy="vendorDetailsSim")
	private Set<Simcard> simDetails;
	
	

	public Set<Simcard> getSimDetails() {
		return simDetails;
	}

	public void setSimDetails(Set<Simcard> simDetails) {
		this.simDetails = simDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the vendor_name
	 */
	public String getVendor_name() {
		return vendor_name;
	}

	/**
	 * @param vendor_name the vendor_name to set
	 */
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name.trim();
	}

	/**
	 * @return the vendor_contact_person
	 */
	public String getVendor_contact_person() {
		return vendor_contact_person;
	}

	/**
	 * @param vendor_contact_person the vendor_contact_person to set
	 */
	public void setVendor_contact_person(String vendor_contact_person) {
		this.vendor_contact_person = vendor_contact_person.trim();
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note.trim();
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	

	
	

	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	/**
	 * @return the updated_date
	 */
	public Date getUpdated_date() {
		return updated_date;
	}

	/**
	 * @param updated_date the updated_date to set
	 */
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	/**
	 * @return the deleted_status
	 */
	public int getDeleted_status() {
		return deleted_status;
	}

	/**
	 * @param deleted_status the deleted_status to set
	 */
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	/**
	 * @return the created_by
	 */
	public int getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	/**
	 * @return the updated_by
	 */
	public int getUpdated_by() {
		return updated_by;
	}

	/**
	 * @param updated_by the updated_by to set
	 */
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	
	@Transient
	private boolean modifyrecord;

	/**
	 * @return the modifyrecord
	 */
	public boolean isModifyrecord() {
		return modifyrecord;
	}

	/**
	 * @param modifyrecord the modifyrecord to set
	 */
	public void setModifyrecord(boolean modifyrecord) {
		this.modifyrecord = modifyrecord;
	}

	
	public Set<Device> getVendor() {
		return vendor;
	}

	public void setVendor(Set<Device> vendor) {
		this.vendor = vendor;
	}

	public Set<Battery> getBatteryDetails() {
		return batteryDetails;
	}

	public void setBatteryDetails(Set<Battery> batteryDetails) {
		this.batteryDetails = batteryDetails;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vendor")
	private Set<DataCard> datacard;



	public Set<DataCard> getDatacard() {
		return datacard;
	}

	public void setDatacard(Set<DataCard> datacard) {
		this.datacard = datacard;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vendor")
	private Set<RFIDCard> rfidcard;



	public Set<RFIDCard> getRfidcard() {
		return rfidcard;
	}

	public void setRfidcard(Set<RFIDCard> rfidcard) {
		this.rfidcard = rfidcard;
	}


	
	
}