package com.trimax.its.transport.model;

import java.util.Date;
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

import com.trimax.its.usermanagement.model.BataConfigDetails;

@Entity
@Table(name="schedule_type")
public class ScheduleType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int schedule_type_id;
	
	@Column(name="schedule_type_name")
	private String scheduleName;
	
	@Column(name="schedule_code")
	private String scheduleCode;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;

	public int getSchedule_type_id() {
		return schedule_type_id;
	}

	public void setSchedule_type_id(int schedule_type_id) {
		this.schedule_type_id = schedule_type_id;
	}

	public String getScheduleName() {
		return scheduleName.trim();
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes.trim();
	}

	public void setNotes(String notes) {
		this.notes = notes.trim();
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	/*public Set<ShiftType> getShifttypes() {
		return shifttypes;
	}

	public void setShifttypes(Set<ShiftType> shifttypes) {
		this.shifttypes = shifttypes;
	}*/
	/*@OneToMany(mappedBy="scheduletype")
    private Set<ShiftType> shifttypes;*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="scheduletype")
	Set<Schedule> scheduleTypeDetails;

	public Set<Schedule> getScheduleTypeDetails() {
		return scheduleTypeDetails;
	}

	public void setScheduleTypeDetails(Set<Schedule> scheduleTypeDetails) {
		this.scheduleTypeDetails = scheduleTypeDetails;
	}

	/**
	 * @return the scheduleCode
	 */
	public String getScheduleCode() {
		return scheduleCode;
	}

	/**
	 * @param scheduleCode the scheduleCode to set
	 */
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="schedule_type_id")
	Set<BataConfigDetails> bataconfig;

	public Set<BataConfigDetails> getBataconfig() {
		return bataconfig;
	}

	public void setBataconfig(Set<BataConfigDetails> bataconfig) {
		this.bataconfig = bataconfig;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scheduletype")
	private Set<PeakHour> peakhour;


	public Set<PeakHour> getPeakhour() {
		return peakhour;
	}

	public void setPeakhour(Set<PeakHour> peakhour) {
		this.peakhour = peakhour;
	}
}
