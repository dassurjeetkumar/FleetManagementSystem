package com.trimax.its.vehicle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehicle_transfer")
public class VehicleTrasnsfer {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="vehicle_id")
	private int vehicleId;
	@Column(name="from_depot_id")
	private int fromDepotId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getFromDepotId() {
		return fromDepotId;
	}
	public void setFromDepotId(int fromDepotId) {
		this.fromDepotId = fromDepotId;
	}
	public int getToDepotId() {
		return toDepotId;
	}
	public void setToDepotId(int toDepotId) {
		this.toDepotId = toDepotId;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	
	@Column(name="to_depot_id")
	private int toDepotId;
	@Column(name="updated_by")
	private int updateBy;
	@Column(name="updated_date")
	private String updatedDate;
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
}
