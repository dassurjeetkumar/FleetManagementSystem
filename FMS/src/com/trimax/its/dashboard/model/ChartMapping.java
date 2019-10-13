package com.trimax.its.dashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vts.model.SarthiSectorGeofence;
@Entity
@Table(name="chart_mapping")
public class ChartMapping {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private int user_id;



	@Column(name="chart_id")
	private int chart_id;

	
	
	@Column(name="status")
	private String status;
	
	
	
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

	/*@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;*/

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
	
	

	public int getChart_id() {
		return chart_id;
	}

	public void setChart_id(int chart_id) {
		this.chart_id = chart_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	
}
