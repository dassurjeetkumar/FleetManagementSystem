package com.trimax.its.transport.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bus_stop_group")
public class BusStopGroup {
	
	@Id
	@GeneratedValue
	@Column(name="group_id")
	private int group_id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="group_id")
	private Set<BusStopGroupStop> bus_stop_group_group_id;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="bus_stop_id")
	//private Integer end_point_id;
	private BusStops group_stop;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="group_type_id")
	private GroupType group_type_id;

	@Column(name="group_name")
	private String group_name;
	
	@Column(name="bus_stop_count")
	private int bus_stop_count;
	
	@Column(name="status")
	private String status;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="inserted_date")
	private Date inserted_date;
	
	@Column(name="inserted_by")
	private int inserted_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="updated_by")
	private int updated_by;

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public Set<BusStopGroupStop> getBus_stop_group_group_id() {
		return bus_stop_group_group_id;
	}

	public void setBus_stop_group_group_id(
			Set<BusStopGroupStop> bus_stop_group_group_id) {
		this.bus_stop_group_group_id = bus_stop_group_group_id;
	}

	public BusStops getGroup_stop() {
		return group_stop;
	}

	public void setGroup_stop(BusStops group_stop) {
		this.group_stop = group_stop;
	}

	public GroupType getGroup_type_id() {
		return group_type_id;
	}

	public void setGroup_type_id(GroupType group_type_id) {
		this.group_type_id = group_type_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public int getBus_stop_count() {
		return bus_stop_count;
	}

	public void setBus_stop_count(int bus_stop_count) {
		this.bus_stop_count = bus_stop_count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public Date getInserted_date() {
		return inserted_date;
	}

	public void setInserted_date(Date inserted_date) {
		this.inserted_date = inserted_date;
	}

	public int getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(int inserted_by) {
		this.inserted_by = inserted_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}



}
