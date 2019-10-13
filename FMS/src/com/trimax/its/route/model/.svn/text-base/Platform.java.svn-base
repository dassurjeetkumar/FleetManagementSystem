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
import com.trimax.its.pis.model.DisplayUnitModel;

@Entity
@Table(name="platform")
public class Platform {
	//floor
	@Id
	@GeneratedValue
	@Column(name="platform_id")
	private int platform_id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="bay")
	private Set<DisplayMappingModel> displayMappingModel;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="display_platform")
	private Set<DisplayUnitModel> displayUnitModel;

	public Set<DisplayUnitModel> getDisplayUnitModel() {
		return displayUnitModel;
	}

	public void setDisplayUnitModel(Set<DisplayUnitModel> displayUnitModel) {
		this.displayUnitModel = displayUnitModel;
	}



	@Column(name="platform_name")
	private String platform_name;
	
//	@Column(name="bay_id")
//	private int bay_id;
	
	public Set<DisplayMappingModel> getDisplayMappingModel() {
		return displayMappingModel;
	}

	public void setDisplayMappingModel(Set<DisplayMappingModel> displayMappingModel) {
		this.displayMappingModel = displayMappingModel;
	}



	@Column(name="latitude")
	private Double latitude;	
	
	@Column(name="longitude")
	private Double longitude;	
	
	@Column(name="geo_fence_data")
	private String geo_fence_data;	
	
	@Column(name="status")
	private String status;	
	
	@Column(name="landmark")
	private String landmark;	
	
	@Column(name="platform_description")
	private String platform_description;	
	
	@Column(name="deleted_status")
	private Integer deleted_status;	
	
	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="created_date")
	private Date created_date;	
	
	@Column(name="updated_by")
	private Integer updated_by;	
	
	@Column(name="updated_date")
	private Date updated_date;	
	
	@Column(name="floor_id")
	private Integer floor_id;
	
	@Column(name="parent_id")
	private Integer parent_id;
	
	@Column(name="bus_station_id")
	private Integer bus_station_id;

	public int getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(int platform_id) {
		this.platform_id = platform_id;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

//	public int getBay_id() {
//		return bay_id;
//	}
//
//	public void setBay_id(int bay_id) {
//		this.bay_id = bay_id;
//	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getGeo_fence_data() {
		return geo_fence_data;
	}

	public void setGeo_fence_data(String geo_fence_data) {
		this.geo_fence_data = geo_fence_data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPlatform_description() {
		return platform_description;
	}

	public void setPlatform_description(String platform_description) {
		this.platform_description = platform_description;
	}

	public Integer getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(Integer deleted_status) {
		this.deleted_status = deleted_status;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
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

	public Integer getFloor_id() {
		return floor_id;
	}

	public void setFloor_id(Integer floor_id) {
		this.floor_id = floor_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getBus_station_id() {
		return bus_station_id;
	}

	public void setBus_station_id(Integer bus_station_id) {
		this.bus_station_id = bus_station_id;
	}
	

	
	@ManyToOne
	@JoinColumn(name="bay_id")
	private Bay bay;
	
	public Bay getBay() {
		return bay;
	}

	public void setBay(Bay bay) {
		this.bay = bay;
	}
}
