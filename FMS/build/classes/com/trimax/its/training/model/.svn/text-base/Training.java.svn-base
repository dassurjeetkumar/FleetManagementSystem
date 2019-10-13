package com.trimax.its.training.model;

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
import com.trimax.its.usermanagement.model.Employee;



@Entity
@Table(name="training")
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int training_id	;

	@Column
	private Date training_date;
	
	@Column
	private String status;
	
	@Column
	private String duration	;
	
	@Column
	private String remarks	;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;
	
	@Transient
	private String stringtrainingdate;

	public int getTraining_id() {
		return training_id;
	}

	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}

	public Date getTraining_date() {
		return training_date;
	}

	public void setTraining_date(Date training_date) {
		this.training_date = training_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getStringtrainingdate() {
		return stringtrainingdate;
	}

	public void setStringtrainingdate(String stringtrainingdate) {
		this.stringtrainingdate = stringtrainingdate;
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private  Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne
	@JoinColumn(name="training_type_id")
	private  TrainingType trainingtype;

	public TrainingType getTrainingtype() {
		return trainingtype;
	}

	public void setTrainingtype(TrainingType trainingtype) {
		this.trainingtype = trainingtype;
	}

	

	
	
}
