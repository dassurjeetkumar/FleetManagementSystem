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

import com.trimax.its.model.Vendor;

@Entity
@Table(name="complaint")
public class Complaint {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)	
private	int complaint_id;

@Column
private String compliant_type;

@Column
private	String incident_date;

@Column
private String complaint_date;

public String getIncident_date() {
	return incident_date;
}

public void setIncident_date(String incident_date) {
	this.incident_date = incident_date;
}

public String getComplaint_date() {
	return complaint_date;
}

public void setComplaint_date(String complaint_date) {
	this.complaint_date = complaint_date;
}

@Column
private String identification_data;

@Column
private String complaint_description;

@Column
private String status;

@Column
private String complaint_media;

@Column
private int taken_by;

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

public int getComplaint_id() {
	return complaint_id;
}

public void setComplaint_id(int complaint_id) {
	this.complaint_id = complaint_id;
}

public String getCompliant_type() {
	return compliant_type;
}

public void setCompliant_type(String compliant_type) {
	this.compliant_type = compliant_type;
}


//@ManyToOne
//@JoinColumn(name="user_id")
//private User user;
//
//public User getUser() {
//	return user;
//	}
//
//public void setUser(User user) {
//	this.user = user;
//}




//
//
////@ManyToOne
////@JoinColumn(name="vehicle_id")
////private User user;
////  	
////public User getUser() {
////	return user;
////}
////
////public void setUser(User user) {
////	this.user = user;
////}
//
//public User getUser() {
//	return user;
//}
//
//public void setUser(User user) {
//	this.user = user;
//}

public String getComplaint_description() {
	return complaint_description;
}



public String getIdentification_data() {
	return identification_data;
}

public void setIdentification_data(String identification_data) {
	this.identification_data = identification_data;
}

public void setComplaint_description(String complaint_description) {
	this.complaint_description = complaint_description;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getComplaint_media() {
	return complaint_media;
}

public void setComplaint_media(String complaint_media) {
	this.complaint_media = complaint_media;
}

public int getTaken_by() {
	return taken_by;
}

public void setTaken_by(int taken_by) {
	this.taken_by = taken_by;
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


	
	

}
