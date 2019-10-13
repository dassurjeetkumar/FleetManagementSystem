package com.trimax.its.route.model;

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

import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.pis.model.DisplayMappingModel;




@Entity
@Table(name="bay")
public class Bay {
	//bay master
	@Id
	@GeneratedValue
	@Column(name="bay_id")
	private int bay_id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="bay")
	private Set<DisplayMappingModel> displayMappingModel;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="display_bay")
	private Set<DisplayUnitModel> displayUnitModel;
	public Set<DisplayUnitModel> getDisplayUnitModel() {
		return displayUnitModel;
	}

	public void setDisplayUnitModel(Set<DisplayUnitModel> displayUnitModel) {
		this.displayUnitModel = displayUnitModel;
	}

	public Set<DisplayMappingModel> getDisplayMappingModel() {
		return displayMappingModel;
	}

	public void setDisplayMappingModel(Set<DisplayMappingModel> displayMappingModel) {
		this.displayMappingModel = displayMappingModel;
	}





	@Column(name="bay_name")
	private String bay_name;
	
//	@Column(name="floor_id")
//	private int floor_id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="parent_id")
	private int parent_id;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="bus_station_id")
	private Integer bus_station_id;

	public int getBay_id() {
		return bay_id;
	}

	public void setBay_id(int bay_id) {
		this.bay_id = bay_id;
	}

	public String getBay_name() {
		return bay_name;
	}

	public void setBay_name(String bay_name) {
		this.bay_name = bay_name;
	}

//	public int getFloor_id() {
//		return floor_id;
//	}
//
//	public void setFloor_id(int floor_id) {
//		this.floor_id = floor_id;
//	}

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

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Integer getBus_station_id() {
		return bus_station_id;
	}

	public void setBus_station_id(Integer bus_station_id) {
		this.bus_station_id = bus_station_id;
	}

	
	


	@ManyToOne
	@JoinColumn(name="floor_id")
	private Floor floor;
	
	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}
}
