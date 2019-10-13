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


@Entity
@Table(name="vehicle_defects")

public class VehicleDefects {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int defect_id	;
	
	@Column 
	private String defect_type;
	
//	@Column 
//	private int vehicle_id;
	
	@Column
	private String status;
	
	@Column 
	private  String date;
	
	@Column 
	private String notes;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;
	
	public int getDefect_id() {
		return defect_id;
	}

	public void setDefect_id(int defect_id) {
		this.defect_id = defect_id;
	}

	public String getDefect_type() {
		return defect_type.trim();
	}

	public void setDefect_type(String defect_type) {
		this.defect_type = defect_type.trim();
	}

	/*public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}*/

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
	
	@Transient
	private String defectdate;
	
	public String getDefectdate() {
		return defectdate;
	}

	public void setDefectdate(String defectdate) {
		this.defectdate = defectdate;
	}
	
//	@ManyToOne
//	@JoinColumn(name="vehicle_id")
//	private  Vehicle vehicle;
//
//	public Vehicle getVehicle() {
//		return vehicle;
//	}
//
//	public void setVehicle(Vehicle vehicle) {
//		this.vehicle = vehicle;
//	}

	
}
