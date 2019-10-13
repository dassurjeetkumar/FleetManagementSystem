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

import org.hibernate.type.ComponentType;
@Entity
@Table(name="breakdown_type")
public class BreakDownType {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int breakdown_type_id;
	
	/*@ManyToOne
	@JoinColumn(name="breakdown_category")
	private BreakDownCategory breakdown_category;*/
	
	@ManyToOne
	@JoinColumn(name="system_type")
	private ComponentsTypes system_type;
	
	@ManyToOne
	@JoinColumn(name="system_sub_type")
	private SubComponents system_sub_type;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehiclelist;
	
	public int getBreakdown_type_id() {
		return breakdown_type_id;
	}

	/*public BreakDownCategory getBreakdown_category() {
		return breakdown_category;
	}*/

	public ComponentsTypes getSystem_type() {
		return system_type;
	}

	public SubComponents getSystem_sub_type() {
		return system_sub_type;
	}

	

	public int getUser_id() {
		return user_id;
	}

	public String getStatus() {
		return status;
	}

	public String getNotes() {
		return notes;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public Date getOperation_date() {
		return operation_date;
	}

	public void setBreakdown_type_id(int breakdown_type_id) {
		this.breakdown_type_id = breakdown_type_id;
	}

	/*public void setBreakdown_category(BreakDownCategory breakdown_category) {
		this.breakdown_category = breakdown_category;
	}*/

	public void setSystem_type(ComponentsTypes system_type) {
		this.system_type = system_type;
	}

	public void setSystem_sub_type(SubComponents system_sub_type) {
		this.system_sub_type = system_sub_type;
	}

	

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public void setOperation_date(Date operation_date) {
		this.operation_date = operation_date;
	}
	public Vehicle getVehiclelist() {
		return vehiclelist;
	}

	public void setVehiclelist(Vehicle vehiclelist) {
		this.vehiclelist = vehiclelist;
	}

	@Column
	private int user_id;
	
	@Column
	private String status;
	
	@Column
	private String notes;
	
	
	
	
	
	
	@Column
	private int deleted_status;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column 
	private  Date operation_date;
	
	
	

}
