package com.trimax.its.usermanagement.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="designation_type")
public class DesignationType {
	
	@Id
	@GeneratedValue
	@Column(name="designation_type_id")
	private int designation_type_id;
	
	
	public int getDesignation_type_id() {
		return designation_type_id;
	}


	public void setDesignation_type_id(int designation_type_id) {
		this.designation_type_id = designation_type_id;
	}


	public String getDesignation_type_name() {
		return designation_type_name;
	}


	public void setDesignation_type_name(String designation_type_name) {
		this.designation_type_name = designation_type_name.trim();
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status.trim();
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note.trim();
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="WORKING_DESIGNATION")
	private Set<Employee> employee;





	public int getUpdated_by() {
		return updated_by;
	}


	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}


	


	public int getDeleted_status() {
		return deleted_status;
	}


	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
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


	public Date getUpdated_date() {
		return updated_date;
	}


	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}


	@Column(name="updated_by")
	private int updated_by;
	
	
	@Column(name="updated_date")
	private Date updated_date;
	
	
	@Column(name="deleted_status")
	private int deleted_status;
	

	@Column(name="created_by")
	private int created_by;
	
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="designation_type_name")
	private String designation_type_name;
	
	
	@Column(name="status")
	private String status;
	
	@Column(name="note")
	private String note;
	
	@Column(name="designation_type_code")
	private String designation_type_code;


	public String getDesignation_type_code() {
		return designation_type_code;
	}


	public void setDesignation_type_code(String designation_type_code) {
		this.designation_type_code = designation_type_code.trim();
	}

	
	
	public Set<BataConfigDetails> getBataconfig() {
		return bataconfig;
	}


	public void setBataconfig(Set<BataConfigDetails> bataconfig) {
		this.bataconfig = bataconfig;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="designation_type_id")
	Set<BataConfigDetails> bataconfig;
}
