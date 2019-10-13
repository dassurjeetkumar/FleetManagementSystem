package com.trimax.its.vehicle.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="breakdown_category")
public class BreakDownCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int breakdown_category_id;
	
	@Column
	private String breakdown_category_name;
	
	@Column
	private String status;
	
	@Column
	private String notes;
	
	@Column
	private int deleted_status;
	
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;

	/*@OneToMany(mappedBy="breakdown_category")
	private Set<BreakDownType> breakdown_category;*/
	
	
	
/*
	public Set<BreakDownType> getBreakdown_category() {
		return breakdown_category;
	}

	public void setBreakdown_category(Set<BreakDownType> breakdown_category) {
		this.breakdown_category = breakdown_category;
	}
*/
	public int getBreakdown_category_id() {
		return breakdown_category_id;
	}

	public String getBreakdown_category_name() {
		return breakdown_category_name;
	}

	public String getStatus() {
		return status;
	}

	public String getNotes() {
		return notes;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setBreakdown_category_id(int breakdown_category_id) {
		this.breakdown_category_id = breakdown_category_id;
	}

	public void setBreakdown_category_name(String breakdown_category_name) {
		this.breakdown_category_name = breakdown_category_name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	
	

}
