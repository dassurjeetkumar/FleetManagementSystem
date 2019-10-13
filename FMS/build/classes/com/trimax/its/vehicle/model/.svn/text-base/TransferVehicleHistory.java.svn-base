package com.trimax.its.vehicle.model;

import java.util.Date;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.common.Common;

@Entity
@Table(name="transfer_vehicle")
public class TransferVehicleHistory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transfer_vehicle_id;
	
	@Column(name="transfer_vehicle_type")
	private String transferVehicleType;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="from_unit_id", referencedColumnName="org_chart_id")
	private OrganisationChart fromOrganisationId;
	
	@ManyToOne
	@JoinColumn(name="to_unit_id", referencedColumnName="org_chart_id")
	private OrganisationChart toOrganisationId;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="to_date")
	private Date toDate;
	
	@Transient
	private String fromDateString;
	
	@Transient
	private String toDateString;
	
	
	@Column
	private String description;
	
	@Column 
	private String status;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Transient
	Common common = new Common();
	
	
	public int getTransfer_vehicle_id() {
		return transfer_vehicle_id;
	}

	public void setTransfer_vehicle_id(int transfer_vehicle_id) {
		this.transfer_vehicle_id = transfer_vehicle_id;
	}



	public String getTransferVehicleType() {
		return transferVehicleType;
	}

	public void setTransferVehicleType(String transferVehicleType) {
		this.transferVehicleType = transferVehicleType;
	}

	public OrganisationChart getFromOrganisationId() {
		return fromOrganisationId;
	}

	public void setFromOrganisationId(OrganisationChart fromOrganisationId) {
		this.fromOrganisationId = fromOrganisationId;
	}

	public OrganisationChart getToOrganisationId() {
		return toOrganisationId;
	}

	public void setToOrganisationId(OrganisationChart toOrganisationId) {
		this.toOrganisationId = toOrganisationId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getFromDateString() {
		return fromDateString;
	}

	public void setFromDateString(String fromDateString) {
		this.fromDateString = fromDateString;
	}

	public String getToDateString() {
		return toDateString;
	}

	public void setToDateString(String toDateString) {
		this.toDateString = toDateString;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	
}
