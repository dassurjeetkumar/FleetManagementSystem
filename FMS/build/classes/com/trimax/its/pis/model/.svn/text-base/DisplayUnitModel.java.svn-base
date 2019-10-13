package com.trimax.its.pis.model;

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
import javax.persistence.Transient;

import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="pis_display_unit")
public class DisplayUnitModel {
	@Id
	@GeneratedValue 
	@Column(name="pis_display_unit_id")
	private int pis_display_unit_id;
	@Column(name="display_unit_name ")
	private String pis_display_unit_name;
	public String getPis_display_unit_name() {
		return pis_display_unit_name;
	}
	public void setPis_display_unit_name(String pis_display_unit_name) {
		this.pis_display_unit_name = pis_display_unit_name;
	}
	@Transient
	private String[] platform;
	public String[] getPlatform() {
		return platform;
	}

	public void setPlatform(String[] platform) {
		this.platform = platform;
	}
	@ManyToOne
	@JoinColumn(name="bus_station_id")
	private OrganisationChart display_bus_station_id;
	@ManyToOne
	@JoinColumn(name="floor_id")
	private Floor display_floor;
	@ManyToOne
	@JoinColumn(name="bay_id")
	private Bay display_bay;
	
	@ManyToOne
	@JoinColumn(name="platform_id")
	private Platform display_platform;
	
	public Platform getDisplay_platform() {
		return display_platform;
	}
	public void setDisplay_platform(Platform display_platform) {
		this.display_platform = display_platform;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="display_unit")
	private Set<DisplayMappingModel> displayMappingModel;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="scroll_display_unit")
	private Set<PisScrollMessageModel> pisScrollMessageModel;
	
	public Set<PisScrollMessageModel> getPisScrollMessageModel() {
		return pisScrollMessageModel;
	}
	public void setPisScrollMessageModel(
			Set<PisScrollMessageModel> pisScrollMessageModel) {
		this.pisScrollMessageModel = pisScrollMessageModel;
	}
	public Set<DisplayMappingModel> getDisplayMappingModel() {
		return displayMappingModel;
	}
	public void setDisplayMappingModel(Set<DisplayMappingModel> displayMappingModel) {
		this.displayMappingModel = displayMappingModel;
	}
	@Column(name="status")
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getPis_display_unit_id() {
		return pis_display_unit_id;
	}
	public void setPis_display_unit_id(int pis_display_unit_id) {
		this.pis_display_unit_id = pis_display_unit_id;
	}
	public OrganisationChart getDisplay_bus_station_id() {
		return display_bus_station_id;
	}
	public void setDisplay_bus_station_id(OrganisationChart display_bus_station_id) {
		this.display_bus_station_id = display_bus_station_id;
	}
	public Floor getDisplay_floor() {
		return display_floor;
	}
	public void setDisplay_floor(Floor display_floor) {
		this.display_floor = display_floor;
	}
	public Bay getDisplay_bay() {
		return display_bay;
	}
	public void setDisplay_bay(Bay display_bay) {
		this.display_bay = display_bay;
	}
	/* */
	public Date getCreated_date() {
		return created_date;
	}
	
	
	@Column(name="refresh_rate")
	private int refresh_rate;

	public int getRefresh_rate() {
		return refresh_rate;
	}
	public void setRefresh_rate(int refresh_rate) {
		this.refresh_rate = refresh_rate;
	}
	@Column(name="deleted_status")
	private int deleted_status;
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	@Column(name="template_name")
	private String template_name;
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	
}
