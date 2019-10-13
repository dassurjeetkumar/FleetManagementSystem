package com.trimax.its.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bus_stop_group_stop")
public class BusStopGroupStop {
	
	@Id
	@GeneratedValue
	@Column(name="group_stop_id")
	private int group_stop_id;
	

	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="group_id")
	private BusStopGroup group_id;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="bus_stop_id")
	private BusStops bus_stop_group_stop;
	
	@Column(name="status")
	private String status;
	
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
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

	public int getGroup_stop_id() {
		return group_stop_id;
	}

	public void setGroup_stop_id(int group_stop_id) {
		this.group_stop_id = group_stop_id;
	}

	public BusStopGroup getGroup_id() {
		return group_id;
	}

	public void setGroup_id(BusStopGroup group_id) {
		this.group_id = group_id;
	}

	public BusStops getBus_stop_group_stop() {
		return bus_stop_group_stop;
	}

	public void setBus_stop_group_stop(BusStops bus_stop_group_stop) {
		this.bus_stop_group_stop = bus_stop_group_stop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
