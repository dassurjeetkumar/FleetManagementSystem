package com.trimax.its.usermanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_type")
public class EmployeeType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	private int emp_id;
	
	@Column(name="employee_type_name")
	private String employee_typename;
	
	@Column(name="status")
	private String employee_status;
	
	@Column(name="created_by")
    private int created_by;
	
	@Column(name="created_date")
    private Date created_date;
	
	@Column(name="updated_date")
    private Date updated_date;
	
	@Column(name="updated_by")
    private int updated_by;
	
	@Column(name="note")
    private String employee_note;
	
	@Column(name="deleted_status")
    private String deleted_status;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmployee_typename() {
		return employee_typename;
	}

	public void setEmployee_typename(String employee_typename) {
		this.employee_typename = employee_typename;
	}

	public String getEmployee_status() {
		return employee_status;
	}

	public void setEmployee_status(String employee_status) {
		this.employee_status = employee_status;
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

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public String getEmployee_note() {
		return employee_note;
	}

	public void setEmployee_note(String employee_note) {
		this.employee_note = employee_note;
	}

	public String getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(String deleted_status) {
		this.deleted_status = deleted_status;
	}
    
    
    
}