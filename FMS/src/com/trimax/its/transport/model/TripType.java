package com.trimax.its.transport.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.route.model.RouteType;

@Entity
@Table(name="trip_type")
public class TripType {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="trip_type_id")
	private int id;
	
	@Column(name="trip_type_name")
	private String tripTypeName;
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "route_type_id",nullable=true)
	private RouteType routetypedetails;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	Integer createdBy;
	
	@Column(name="updated_by")
	Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	@Column(name="trip_type_code")
	private String tripTypeCode;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="tripType")
	private Set<ScheduleDetails> tripDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTripTypeName() {
		return tripTypeName;
	}

	public void setTripTypeName(String tripTypeName) {
		this.tripTypeName = tripTypeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	
	public Set<ScheduleDetails> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(Set<ScheduleDetails> tripDetails) {
		this.tripDetails = tripDetails;
	}

	/**
	 * @return the tripTypeCode
	 */
	public String getTripTypeCode() {
		return tripTypeCode;
	}

	/**
	 * @param tripTypeCode the tripTypeCode to set
	 */
	public void setTripTypeCode(String tripTypeCode) {
		this.tripTypeCode = tripTypeCode;
	}

	public RouteType getRoutetypedetails() {
		return routetypedetails;
	}

	public void setRoutetypedetails(RouteType routetypedetails) {
		this.routetypedetails = routetypedetails;
	}

	
}
