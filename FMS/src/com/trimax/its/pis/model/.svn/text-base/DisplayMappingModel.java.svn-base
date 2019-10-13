package com.trimax.its.pis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="pis_display_platfrom")
public class DisplayMappingModel {
	@Id
	@GeneratedValue
	@Column(name="pis_display_platform_id")
	private int pis_display_platform_id;
	public Bay getBay() {
		return bay;
	}
	public void setBay(Bay bay) {
		this.bay = bay;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	@ManyToOne
	@JoinColumn(name="display_unit_id")
	private DisplayUnitModel display_unit;
	public DisplayUnitModel getDisplay_unit() {
		return display_unit;
	}
	public void setDisplay_unit(DisplayUnitModel display_unit) {
		this.display_unit = display_unit;
	}
	@ManyToOne
	@JoinColumn(name="platform_id")
	private Platform platform;
	@ManyToOne
	@JoinColumn(name="bay_id")
	private Bay bay;

	@ManyToOne
	@JoinColumn(name="floor_id")
	private Floor floor;

	public Floor getFloor() {
		return floor;
	}
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	@ManyToOne
	@JoinColumn(name="bus_station_id")
	private OrganisationChart bus_station_id;
	public OrganisationChart getBus_station_id() {
		return bus_station_id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setBus_station_id(OrganisationChart bus_station_id) {
		this.bus_station_id = bus_station_id;
	}
	
	@Column(name="created_by")
    private int created_by;
	
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
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="created_date")
    private Date created_date;
	@Column(name="updated_date")
    private Date updated_date;
	@Column(name="deleted_status")
	private int deleted_status;
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getPis_display_platform_id() {
		return pis_display_platform_id;
	}
	public void setPis_display_platform_id(int pis_display_platform_id) {
		this.pis_display_platform_id = pis_display_platform_id;
	}
	
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
}
