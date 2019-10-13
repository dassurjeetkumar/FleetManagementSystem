package com.trimax.its.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name=" road_type")
public class RoadType 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="road_type_id")
    private int road_type_id;
	@Column(name="road_type_name")
	private String road_type_name;
	@Column(name="status")
	private String status;
	@Column(name="note")
	private String note;
	@Column(name="deleted_status")
	private int deleted_status;
	@Column(name="created_by")
	private int created_by;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="updated_date")
	private Date updated_date;
	public int getRoad_type_id() {
		return road_type_id;
	}
	public void setRoad_type_id(int road_type_id) {
		this.road_type_id = road_type_id;
	}
	public String getRoad_type_name() {
		return road_type_name.trim();
	}
	public void setRoad_type_name(String road_type_name) {
		this.road_type_name = road_type_name.trim();
	}
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
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
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
	
  
}
