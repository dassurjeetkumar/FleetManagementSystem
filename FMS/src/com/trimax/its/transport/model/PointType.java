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
@Table(name="point_type")
public class PointType {
	
	@Id
	@GeneratedValue
	@Column(name="point_type_id")
	private int point_type_id;
	
	@Column(name="point_type_name")
	private String point_type_name;
	
	@Column(name="status")
	private String status;
	
	@Column(name="note")
	private String note;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="org_type_id")
	private Integer org_type_id;

	public Integer getOrg_type_id() {
		return org_type_id;
	}

	public void setOrg_type_id(Integer org_type_id) {
		this.org_type_id = org_type_id;
	}

	public int getPoint_type_id() {
		return point_type_id;
	}

	public void setPoint_type_id(int point_type_id) {
		this.point_type_id = point_type_id;
	}

	public String getPoint_type_name() {
		return point_type_name;
	}

	public void setPoint_type_name(String point_type_name) {
		this.point_type_name = point_type_name;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="pointtype")
	private Set<BusStops> pointtype;

	public Set<BusStops> getPointtype() {
		return pointtype;
	}

	public void setPointtype(Set<BusStops> pointtype) {
		this.pointtype = pointtype;
	}

}
