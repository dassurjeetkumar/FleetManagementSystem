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

@Entity
@Table(name="break_type")
public class BreakType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="break_type_id")
	private int id;
	
	@Column(name="break_type_name")
	private String breakTypeName;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="spread_hours")
	private int spreadHours;
	
	@Column(name="steering_hours")
	private int steeringHours;
	
	@Column(name="ot_hours")
	private int ot_hours;
	
	@Column(name="rest")
	private int rest;
	
	@Column(name="status")
	private String status;
	
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	Integer createdBy;
	
	@Column(name="updated_by")
	Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	
	
	public int getOt_hours() {
		return ot_hours;
	}

	public void setOt_hours(int ot_hours) {
		this.ot_hours = ot_hours;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="breakType")
	private Set<ScheduleDetails> breakDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreakTypeName() {
		return breakTypeName.trim();
	}

	public void setBreakTypeName(String breakTypeName) {
		this.breakTypeName = breakTypeName.trim();
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getSpreadHours() {
		return spreadHours;
	}

	public void setSpreadHours(int spreadHours) {
		this.spreadHours = spreadHours;
	}

	public int getSteeringHours() {
		return steeringHours;
	}

	public void setSteeringHours(int steeringHours) {
		this.steeringHours = steeringHours;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes.trim();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
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

	public Set<ScheduleDetails> getBreakDetails() {
		return breakDetails;
	}

	public void setBreakDetails(Set<ScheduleDetails> breakDetails) {
		this.breakDetails = breakDetails;
	}
	
	
}
