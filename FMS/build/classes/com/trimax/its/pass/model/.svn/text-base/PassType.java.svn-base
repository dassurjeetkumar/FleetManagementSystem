package com.trimax.its.pass.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.fare.model.Farechart_Master;

@Entity
@Table(name="passtype")
public class PassType {
@Id
@GeneratedValue
@Column(name="pass_type_id")
private int pass_type_id;

@Column(name="pass_type_name")
private String pass_type_name;

//@Column(name="valid_days")
//private int valid_days;

@Column(name="status")
private String status;

@Column(name="note")
private String note;

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

//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="passType")
//Set<PassRate> passRate;

//public Set<PassRate> getPassRate() {
//	return passRate;
//}
//
//public void setPassRate(Set<PassRate> passRate) {
//	this.passRate = passRate;
//}

public String getPass_type_name() {
	return pass_type_name;
}

public void setPass_type_name(String pass_type_name) {
	this.pass_type_name = pass_type_name;
}

//public int getValid_days() {
//	return valid_days;
//}
//
//public void setValid_days(int valid_days) {
//	this.valid_days = valid_days;
//}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getNote() {
	return note;
}

public void setNote(String note) {
	this.note = note;
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

public int getPass_type_id() {
	return pass_type_id;
}

public void setPass_type_id(int pass_type_id) {
	this.pass_type_id = pass_type_id;
}
}
