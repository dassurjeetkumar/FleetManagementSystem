package com.trimax.its.fare.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;

import com.trimax.its.pass.model.PassRate;

@Entity
@Table(name="passenger")
public class PassengerType {
	@Id
	@GeneratedValue
	@Column(name="passenger_type_id")
	private int id;
	
	@Column(name="passenger_type")
	private String passengerTypeName;
	
	@Column(name="concession")
	private int concessions;
	
	@Column(name="start_age")
	private int startAge;
	
	@Column(name="end_age")
	private int endAge;
	
	@Column(name="passenger_start_date")
	private String effectiveStartDate;
	
	@Column(name="passenger_end_date")
	private String effectiveEndDate;
	
	@Column(name="pass_type")
	private String passType;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="deleted_status")
	private int deletedStatus;

	@Column(name="status")
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="passengerType")
	Set<Concession> concession;

	public Set<Concession> getConcession() {
		return concession;
	}

	public void setConcession(Set<Concession> concession) {
		this.concession = concession;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassengerTypeName() {
		return passengerTypeName;
	}

	public void setPassengerTypeName(String passengerTypeName) {
		this.passengerTypeName = passengerTypeName;
	}

	public int getConcessions() {
		return concessions;
	}

	public void setConcessions(int concessions) {
		this.concessions = concessions;
	}

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
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

	public String getPassType() {
		return passType;
	}

	public void setPassType(String passType) {
		this.passType = passType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
