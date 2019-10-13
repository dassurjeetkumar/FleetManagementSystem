package com.trimax.its.training.model;

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
@Table(name="training_type")
public class TrainingType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int training_type_id	;
	
	@Column
	private String training_type_name;
	
	@Column
	private String status;
	
	@Column
	private String notes;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;

	public int getTraining_type_id() {
		return training_type_id;
	}

	public void setTraining_type_id(int training_type_id) {
		this.training_type_id = training_type_id;
	}

	public String getTraining_type_name() {
		return training_type_name.trim();
	}

	public void setTraining_type_name(String training_type_name) {
		this.training_type_name = training_type_name.trim();
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
		this.notes = notes;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trainingtype")
	private Set<Training> training;

	public Set<Training> getTraining() {
		return training;
	}

	public void setTraining(Set<Training> training) {
		this.training = training;
	}

}
