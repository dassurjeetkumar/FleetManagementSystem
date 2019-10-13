package com.trimax.its.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.Vehicle;

@Entity
@Table(name="PeakHour")
public class PeakHour {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)		
private int Peak_Slack_hour_Id;	

@Column
private String peak_Name;

@Column
private String start_time;
	
@Column
private String end_time;	



@Column
private float percentage;


@Column
private int lumpsum_amount;

@Column
private String note;

@Column
private String Effective_start_date;

@Column
private String Effective_end_date;

@Column
private int created_by;

@Column
private Date created_date;

@Column
private int updated_by;

@Column
private Date updated_date;

@Column
private int deleted_status;

@Column
private int increase_decrease;

public int getPeak_Slack_hour_Id() {
	return Peak_Slack_hour_Id;
}

public void setPeak_Slack_hour_Id(int peak_Slack_hour_Id) {
	Peak_Slack_hour_Id = peak_Slack_hour_Id;
}






public String getPeak_Name() {
	return peak_Name.trim();
}

public void setPeak_Name(String peak_Name) {
	this.peak_Name = peak_Name.trim();
}

public float getPercentage() {
	return percentage;
}

public String getStart_time() {
	return start_time;
}

public void setStart_time(String start_time) {
	this.start_time = start_time;
}

public String getEnd_time() {
	return end_time;
}

public void setEnd_time(String end_time) {
	this.end_time = end_time;
}

public void setPercentage(float percentage) {
	this.percentage = percentage;
}
public int getLumpsum_amount() {
	return lumpsum_amount;
}

public void setLumpsum_amount(int lumpsum_amount) {
	this.lumpsum_amount = lumpsum_amount;
}

public String getNote() {
	return note.trim();
}

public void setNote(String note) {
	this.note = note.trim();
}





public String getEffective_start_date() {
	return Effective_start_date;
}

public void setEffective_start_date(String effective_start_date) {
	Effective_start_date = effective_start_date;
}

public String getEffective_end_date() {
	return Effective_end_date;
}

public void setEffective_end_date(String effective_end_date) {
	Effective_end_date = effective_end_date;
}

public int getCreated_by() {
	return created_by;
}

public void setCreated_by(int created_by) {
	this.created_by = created_by;
}

public Date getCreated_date() {
	return created_date;
}

public void setCreated_date(Date created_date) {
	this.created_date = created_date;
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



public int getIncrease_decrease() {
	return increase_decrease;
}

public void setIncrease_decrease(int increase_decrease) {
	this.increase_decrease = increase_decrease;
}



@ManyToOne
@JoinColumn(name="service_type_id")
private  ServiceType servicetype;

public ServiceType getServicetype() {
	return servicetype;
}

public void setServicetype(ServiceType servicetype) {
	this.servicetype = servicetype;
}

@ManyToOne
@JoinColumn(name="rate_master_id")
private  RateMaster rateMaster;

public RateMaster getRateMaster() {
	return rateMaster;
}

public void setRateMaster(RateMaster rateMaster) {
	this.rateMaster = rateMaster;
}

@NotFound(action=NotFoundAction.IGNORE)
@ManyToOne
@JoinColumn(name="schedule_type_id")
private  ScheduleType scheduletype;

public ScheduleType getScheduletype() {
	return scheduletype;
}

public void setScheduletype(ScheduleType scheduletype) {
	this.scheduletype = scheduletype;
}
}
