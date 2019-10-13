package com.trimax.its.vehicle.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="vehicle_maintence")
public class VehicleMaintenance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vehicle_maintaince_id	;
	
	
	@Column
	private Date maintainance_date;
	
	@Column
	private String description;
	
//	@Column
//	private String vehicle_id;
//	
//	public String getVehicle_id() {
//		return vehicle_id;
//	}
//
//	public void setVehicle_id(String vehicle_id) {
//		this.vehicle_id = vehicle_id;
//	}

	@Column
	private String maintenance_status;
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;

	public int getVehicle_maintaince_id() {
		return vehicle_maintaince_id;
	}

	public void setVehicle_maintaince_id(int vehicle_maintaince_id) {
		this.vehicle_maintaince_id = vehicle_maintaince_id;
	}


	public Date getMaintainance_date() {
		return maintainance_date;
	}

	public void setMaintainance_date(Date maintainance_date) {
		this.maintainance_date = maintainance_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaintenance_status() {
		return maintenance_status;
	}

	public void setMaintenance_status(String maintenance_status) {
		this.maintenance_status = maintenance_status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
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
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private  Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@Transient
	private String maintenancedate;

	public String getMaintenancedate() {
		return maintenancedate;
	}

	public void setMaintenancedate(String maintenancedate) {
		this.maintenancedate = maintenancedate;
	}
	

}
