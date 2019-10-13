package com.trimax.its.vehicle.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.device.model.Device;
import com.trimax.its.transport.model.Schedule;

@Entity
@Table(name="schedule_mapping_vehicle")
public class ScheduleMap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int schedule_type_id ;
	
	@Column
	private int shift_type_id ;
	
	public int getForm_four_id() {
		return form_four_id;
	}

	public void setForm_four_id(int form_four_id) {
		this.form_four_id = form_four_id;
	}

	@Column
	private int form_four_id ;
	
	public int getShift_type_id() {
		return shift_type_id;
	}

	public void setShift_type_id(int shift_type_id) {
		this.shift_type_id = shift_type_id;
	}

	@Column
	private String schedule_name ;
	
	@Column
	private String vehicle_no ;

	@Column(name="schedule_no")
	private String schedule_no;
	
//	@Column(name="created_by")
//	private Integer createdBy;

	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
//	@Column(name="created_date")
//	private String createdDate;
	
//	@Column(name="deleted_status")
//	private int deletedStatus;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="scheduleMap")
//	private Set<Schedule> schedule;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="model")
//	private Set<Device> device;
	
	@Column(name="depot_name")
	private String depot_name;
	
	@Column
	private int depot_id;
	
	

	public int getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(int depot_id) {
		this.depot_id = depot_id;
	}

	@Column
	private String shiftName ;

	

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getDepot_name() {
		return depot_name;
	}

	public void setDepot_name(String depot_name) {
		this.depot_name = depot_name;
	}

	public int getSchedule_type_id() {
		return schedule_type_id;
	}

	public void setSchedule_type_id(int schedule_type_id) {
		this.schedule_type_id = schedule_type_id;
	}

	public String getSchedule_name() {
		return schedule_name;
	}

	public void setSchedule_name(String schedule_name) {
		this.schedule_name = schedule_name;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(String schedule_no) {
		this.schedule_no = schedule_no;
	}

	
	
//
//	public Set<Schedule> getSchedule() {
//		return schedule;
//	}
//
//	public void setSchedule(Set<Schedule> schedule) {
//		this.schedule = schedule;
//	}

	
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
@Transient
	private String shift_type_name ;
@Transient
	private String schedule_type_name;

public String getShift_type_name() {
	return shift_type_name;
}

public void setShift_type_name(String shift_type_name) {
	this.shift_type_name = shift_type_name;
}

public String getSchedule_type_name() {
	return schedule_type_name;
}

public void setSchedule_type_name(String schedule_type_name) {
	this.schedule_type_name = schedule_type_name;
}
	

	/*
	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
*/
	

	
}
