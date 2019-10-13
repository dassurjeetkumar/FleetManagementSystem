package com.trimax.its.toll.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.BusStops;
import com.trimax.its.vehicle.model.ServiceType;

/**
 * @author Deepak
 *
 */
@Entity
@Table(name="toll_fee_master")
public class Tollfee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="toll_fee_master_id")
	private int id;
	
	 @ManyToOne
	 @JoinColumn(name="service_type_id")
	 private ServiceType servicetype;	 
	 	
	@Column(name="amount")
	private String amount;
	@Column(name="effective_start_date")
	private String effect_start_date;
	@Column(name="effective_end_date")
	private String effect_end_date;
	@Column(name="version_number")
	private String versionNo;
	
	@ManyToOne
	@JoinColumn(name="bus_stop_id")
	private BusStops busstop;
	
	@Column(name="status")
	private String status;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	@Column (name="created_by")
	private int createdby;
	@Column (name="created_date")
	private Date createddate;
	@Column (name="updated_by")
	private int updatedby;
	@Column (name="updated_date")
	private Date updateddate;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	//	public int getAmount() {
//		return amount;
//	}
//	public void setAmount(int amount) {
//		this.amount = amount;
//	}
	public String getEffect_start_date() {
		return effect_start_date;
	}
	public void setEffect_start_date(String effect_start_date) {
		this.effect_start_date = effect_start_date;
	}
	public String getEffect_end_date() {
		return effect_end_date;
	}
	public void setEffect_end_date(String effect_end_date) {
		this.effect_end_date = effect_end_date;
	}
	/*public int getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}*/
	
	public ServiceType getServicetype() {
		return servicetype;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public void setServicetype(ServiceType servicetype) {
		this.servicetype = servicetype;
	}
	public BusStops getBusstop() {
		return busstop;
	}
	public void setBusstop(BusStops busstop) {
		this.busstop = busstop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	
	
	

}
