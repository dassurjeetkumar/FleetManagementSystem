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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.vehicle.model.ServiceType;

@Entity
@Table(name="happy_hour")
public class HappyHour {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)		
@Column(name="id")
private int id;	

@Column(name="name")
private String name;

@Column(name="start_time")
private String start_time;
	
@Column(name="end_time")
private String end_time;	



@Column(name="percentage")
private double percentage;


@Column(name="lumpsum_amount")
private int lumpsum_amount;

@Column(name="Effective_start_date")
private String Effective_start_date;

@Column(name="Effective_end_date" ,nullable=true)
private String Effective_end_date;

@Column(name="created_by")
private int created_by;

@Column(name="created_date")
private Date created_date;

@Column(name="updated_by")
private int updated_by;

@Column(name="updated_date")
private Date updated_date;

@Column(name="deleted_status")
private int deleted_status;

@Column(name="increase_decrease")
private int increase_decrease;
@Column(name="service_type_id")
private int service_type_id;
@Column(name="schedule_type_id")
private int schedule_type_id;

public int getService_type_id() {
	return service_type_id;
}

public void setService_type_id(int service_type_id) {
	this.service_type_id = service_type_id;
}

public int getSchedule_type_id() {
	return schedule_type_id;
}

public void setSchedule_type_id(int schedule_type_id) {
	this.schedule_type_id = schedule_type_id;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public double getPercentage() {
	return percentage;
}

public void setPercentage(double percentage) {
	this.percentage = percentage;
}

public int getLumpsum_amount() {
	return lumpsum_amount;
}

public void setLumpsum_amount(int lumpsum_amount) {
	this.lumpsum_amount = lumpsum_amount;
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



	

}
