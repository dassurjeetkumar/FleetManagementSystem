package com.trimax.its.fare.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "rate_master_details")
public class RateMasterDetail {
	@Id
	@GeneratedValue
	@Column(name = "rate_master_details_id")
	private int rateMasterDetailId;
	
	@Column(name = "rate_master_id")
	private int rateMasterId;
	
	@Column(name = "service_type_id")
	private int serviceTypeId;
	
	@Column(name = "stage_no")
	private int stageNo;
	
	public int getRateMasterDetailId() {
		return rateMasterDetailId;
	}

	public void setRateMasterDetailId(int rateMasterDetailId) {
		this.rateMasterDetailId = rateMasterDetailId;
	}

	public int getRateMasterId() {
		return rateMasterId;
	}

	public void setRateMasterId(int rateMasterId) {
		this.rateMasterId = rateMasterId;
	}

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public int getStageNo() {
		return stageNo;
	}

	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getSeniorCitizen() {
		return seniorCitizen;
	}

	public void setSeniorCitizen(int seniorCitizen) {
		this.seniorCitizen = seniorCitizen;
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

	/*public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}*/

	public String getUpdatedDate() {
		
		return updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public double getLuggage() {
		return luggage;
	}

	public void setLuggage(double luggage) {
		this.luggage = luggage;
	}

	public int getHappyhour1() {
		return happyhour1;
	}

	public void setHappyhour1(int happyhour1) {
		this.happyhour1 = happyhour1;
	}

	public int getHappyhour2() {
		return happyhour2;
	}

	public void setHappyhour2(int happyhour2) {
		this.happyhour2 = happyhour2;
	}

	@Column(name = "adult")
	private int adult;
	
	@Column(name = "children")
	private int children;
	
	@Column(name = "senior_citizen")
	private int seniorCitizen;
	
	@Column(name = "luggage")
	private double luggage;
	
	@Column(name = "happy_hour1")
	private int happyhour1;
	
	@Column(name = "happy_hour2")
	private int happyhour2;
	
	@Column(name = "deleted_status")
	private int deletedStatus;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date",nullable=true)
	private String updatedDate;
	
}
