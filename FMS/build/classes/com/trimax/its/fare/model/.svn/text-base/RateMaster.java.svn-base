package com.trimax.its.fare.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

import com.trimax.its.transport.model.PeakHour;

@Entity
@Table(name = "rate_master")
public class RateMaster {
	@Id
	@GeneratedValue
	@Column(name = "rate_master_id")
	private int rateMasterId;
	
	@Column(name = "parent_rate_master")
	private int parent_rate_master;
	
	@Column(name = "version_number")
	private String versionNumber;
	
	@Column(name = "version_number_service_stype")
	private String versionNoServiceType;
	
	@Column(name = "service_type_id")
	private int serviceTypeId;
	
	@Column(name = "effective_start_date")
	private String effectiveStartDate;
	
	@Column(name = "effective_end_date",nullable=true)
	private String effectiveEndDate;
	
	@Column(name = "status")
	private String status;
	
	public int getRateMasterId() {
		return rateMasterId;
	}

	public void setRateMasterId(int rateMasterId) {
		this.rateMasterId = rateMasterId;
	}
		
	

	public int getParent_rate_master() {
		return parent_rate_master;
	}

	public void setParent_rate_master(int parent_rate_master) {
		this.parent_rate_master = parent_rate_master;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		if(versionNumber.indexOf('.')>0){
			versionNumber=versionNumber.replace('.',' ').trim();
			versionNumber=versionNumber.replace('0',' ').trim();
		}
		this.versionNumber = versionNumber;
	}

	public String getVersionNoServiceType() {
		return versionNoServiceType;
	}

	public void setVersionNoServiceType(String versionNoServiceType) {
		this.versionNoServiceType = versionNoServiceType;
	}

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "deleted_status")
	private int deletedStatus;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "updated_by")
	private int updatedBy;
	
	@Column(name = "updated_date",nullable=true)
	private String updatedDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rateMaster")
	private Set<PeakHour> peakhours;

	public Set<PeakHour> getPeakhours() {
		return peakhours;
	}

	public void setPeakhours(Set<PeakHour> peakhours) {
		this.peakhours = peakhours;
	}
	
	

}
