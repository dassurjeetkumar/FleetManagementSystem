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

import com.trimax.its.pis.model.DisplayMappingModel;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.pis.model.DisplayUnitModel;
@Entity
@Table(name="floor")
public class Floor {
	//floor
	@Id
	@GeneratedValue
	@Column(name="floor_id")
	private int floor_id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="floor")
	private Set<DisplayMappingModel> displayMappingModel;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="display_floor")
	private Set<DisplayUnitModel> DisplayUnitModel;
	public Set<DisplayUnitModel> getDisplayUnitModel() {
		return DisplayUnitModel;
	}

	public void setDisplayUnitModel(Set<DisplayUnitModel> displayUnitModel) {
		DisplayUnitModel = displayUnitModel;
	}

	public Set<DisplayMappingModel> getDisplayMappingModel() {
		return displayMappingModel;
	}

	public void setDisplayMappingModel(Set<DisplayMappingModel> displayMappingModel) {
		this.displayMappingModel = displayMappingModel;
	}

	@Column(name="floor_name")
	private String floor_name;
	
//	@Column(name="parent_id")
//	private int parent_id;
//	
//	@Column(name="bus_station_id")
//	private Integer bus_station_id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="created_date")
	private Date created_date;	
	
	@Column(name="updated_by")
	private int updated_by;	
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@ManyToOne
	@JoinColumn(name="parent_id",referencedColumnName="org_chart_id")
	private OrganisationChart orgChart;

	public int getFloor_id() {
		return floor_id;
	}

	public void setFloor_id(int floor_id) {
		this.floor_id = floor_id;
	}

	public String getFloor_name() {
		return floor_name;
	}

	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
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

	public OrganisationChart getOrgChart() {
		return orgChart;
	}

	public void setOrgChart(OrganisationChart orgChart) {
		this.orgChart = orgChart;
	}
	
	
}
