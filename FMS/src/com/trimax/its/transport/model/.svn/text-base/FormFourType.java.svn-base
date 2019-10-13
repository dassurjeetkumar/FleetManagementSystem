package com.trimax.its.transport.model;

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

@Entity
@Table(name="form_four_type")
public class FormFourType {

	@Id
	@GeneratedValue
	@Column(name="form_four_type_id")
	private int id;
	
	@Column(name="form_four_type_name")
	private String formFourTypeName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date",nullable=true)
	private Date updatedDate;
	
	@Column(name="created_by",nullable=true)
	private Integer createdBy;
	
	@Column(name="updated_by",nullable=true)
	private Integer updatedBy;
	
	@Column(name="deleted_status",nullable=true)
	private int deletedStatus;
	
	@Column(name="notes")
	private String notes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="formFourType")
	private Set<FormFour> formFour;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormFourTypeName() {
		return formFourTypeName;
	}

	public void setFormFourTypeName(String formFourTypeName) {
		this.formFourTypeName = formFourTypeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<FormFour> getFormFour() {
		return formFour;
	}

	public void setFormFour(Set<FormFour> formFour) {
		this.formFour = formFour;
	}
	
	
	
}
