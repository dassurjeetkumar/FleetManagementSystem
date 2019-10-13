package com.trimax.its.vehicle.model;

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
@Table(name = " breakdown_type_details")
public class BreakDownTypeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "breakdown_type_Id")
	private int breakdown_type_Id;
	
	public int getBreakdown_type_Id() {
		return breakdown_type_Id;
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	 private boolean isEmpty(String str) {
		 
	        return str == null ? true:(str.equals("") ? true:false);
	    }
	public void setBreakdown_type_Id(String breakdown_type_Id) {
		if(isEmpty(breakdown_type_Id))
		{
			this.breakdown_type_Id=0;
		}
		else if(isInteger(breakdown_type_Id))
		{
		this.breakdown_type_Id = Integer.parseInt(breakdown_type_Id);
	    }
		else
		{
			this.breakdown_type_Id=-1;
		}
	}
	public String getBreakdown_category() {
		return breakdown_category;
	}

	public void setBreakdown_category(String breakdown_category) {
		this.breakdown_category = breakdown_category.trim();
	}
	
	
	

	public String getBreakdown_system_type() {
		return breakdown_system_type;
	}

	public void setBreakdown_system_type(String breakdown_system_type) {
		this.breakdown_system_type = breakdown_system_type.trim();
	}

	public String getBreakdown_system_sub_type() {
		return breakdown_system_sub_type;
	}

	public void setBreakdown_system_sub_type(String breakdown_system_sub_type) {
		this.breakdown_system_sub_type = breakdown_system_sub_type.trim();
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes.trim();
	}

	@Column(name = "breakdown_category")
	private String breakdown_category;
	
	@Column(name = "breakdown_system_type")
	private String breakdown_system_type;
	
	@Column(name = "breakdown_system_sub_type")
	private String breakdown_system_sub_type;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "created_by")
	private int created_by;
	
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "breakDownTypeDetails")
	private Set<BreakDownHistory> breakDownHistory;
	public Set<BreakDownHistory> getBreakDownHistory() {
		return breakDownHistory;
	}

	public void setBreakDownHistory(Set<BreakDownHistory> breakDownHistory) {
		this.breakDownHistory = breakDownHistory;
	}

	@Column(name = "created_date")
	private Date created_date;
	
	@Column(name = "updated_by")
	private int updated_by;
	
	
	@Column(name = "updated_date")
	private Date updated_date;
	
	@Column(name = "deleted_status")
	private int deleted_status;
	
	@Column(name ="breakdown_name")
	private String breakdown_name;

	public String getBreakdown_name() {
		return breakdown_name;
	}

	public void setBreakdown_name(String breakdown_name) {
		this.breakdown_name = breakdown_name.trim();
	}
}
