package com.trimax.its.revenuesector.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="revenue_sector")
public class RevenueSector {
	
	@Id
	@GeneratedValue
	
	@Column(name="sector_id")
	private int sector_id;
	
	@Column
	private String sector_name;
	
	@Column(name="geo_fence")
	private String geo_fence;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private  String notes;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;

	public int getSector_id() {
		return sector_id;
	}

	public void setSector_id(int sector_id) {
		this.sector_id = sector_id;
	}

	public String getSector_name() {
		return sector_name.trim();
	}

	public void setSector_name(String sector_name) {
		
		
		
		
//		
		this.sector_name = sector_name.trim();
	}

	public String getGeo_fence() {
		return geo_fence;
	}

	public void setGeo_fence(String geo_fence) {
		this.geo_fence = geo_fence;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
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
