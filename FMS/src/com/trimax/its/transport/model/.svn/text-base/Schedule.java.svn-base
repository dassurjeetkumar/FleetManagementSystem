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
import javax.persistence.Transient;

import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ScheduleServiceLimit;

@Entity
@Table(name="schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int schedule_id;
	
	@Column(name="schedule_number")
	private String scheduleNumber;
	
	@Column(name="status")
	private String status;
	
	@Column(name="effective_start_date")
	private Date effectiveStartDate;
	
	@Column(name="effective_end_date")
	private Date effectiveEndDate;
	
	@Transient
	private String startdate;
	
	@Transient
	private String endDate;
	
	@Column(name="is_trunk_schedule")
	private String isTrunkSchedule;
	
	@Column(name="route_id")
	private int routeId;
	
	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	@Column(name="current_status")
	private String currentStatus;
	
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
	
	@Column(name="target_revenue_status")
	private String targetRevenueStatus;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="form_four_weekly_status")
	private String formFourStatus;
	
	@Column(name="conductor")
	private int conductor;
	@Column(name="driver")
	private int driver;
	
	/*@ManyToOne
	@JoinColumn(name="route_id")
	private Route_Trans routeno;*/
	
	

	@ManyToOne
	@JoinColumn(name="brand_type_id")
	private BrandType brand;
	
	public int getConductor() {
		return conductor;
	}

	public void setConductor(int conductor) {
		this.conductor = conductor;
	}

	public int getDriver() {
		return driver;
	}

	public void setDriver(int driver) {
		this.driver = driver;
	}

	@ManyToOne
	@JoinColumn(name="depot_id")
	private OrganisationChart depot;
	
	@ManyToOne
	@JoinColumn(name="schedule_type" ,referencedColumnName="schedule_type_id")
	private ScheduleType scheduletype;
	
	@ManyToOne
	@JoinColumn(name="schedule_service_type" ,referencedColumnName="service_type_id")
	private ScheduleService servicetype;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="scheduleNumber")
	private Set<FormFour> formFour;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="scheduleNumber")
	private Set<ScheduleDetails> tripDetails;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="schedulServiceId")
	private Set<ScheduleServiceLimit> ScheduleServiceLimit;
	
	public Set<ScheduleServiceLimit> getScheduleServiceLimit() {
		return ScheduleServiceLimit;
	}

	public void setScheduleServiceLimit(
			Set<ScheduleServiceLimit> scheduleServiceLimit) {
		ScheduleServiceLimit = scheduleServiceLimit;
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getScheduleNumber() {
		return scheduleNumber;
	}

	public void setScheduleNumber(String scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	
	public String getIsTrunkSchedule() {
		return isTrunkSchedule;
	}

	public void setIsTrunkSchedule(String isTrunkSchedule) {
		this.isTrunkSchedule = isTrunkSchedule;
	}

	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
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

	public String getTargetRevenueStatus() {
		return targetRevenueStatus;
	}

	public void setTargetRevenueStatus(String targetRevenueStatus) {
		this.targetRevenueStatus = targetRevenueStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFormFourStatus() {
		return formFourStatus;
	}

	public void setFormFourStatus(String formFourStatus) {
		this.formFourStatus = formFourStatus;
	}

	/*public Route_Trans getRouteno() {
		return routeno;
	}

	public void setRouteno(Route_Trans routeno) {
		this.routeno = routeno;
	}*/

	

	public BrandType getBrand() {
		return brand;
	}

	public void setBrand(BrandType brand) {
		this.brand = brand;
	}

	public OrganisationChart getDepot() {
		return depot;
	}

	public void setDepot(OrganisationChart depot) {
		this.depot = depot;
	}

	public ScheduleType getScheduletype() {
		return scheduletype;
	}

	public void setScheduletype(ScheduleType scheduletype) {
		this.scheduletype = scheduletype;
	}

	public ScheduleService getServicetype() {
		return servicetype;
	}

	public void setServicetype(ScheduleService servicetype) {
		this.servicetype = servicetype;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Set<FormFour> getFormFour() {
		return formFour;
	}

	public void setFormFour(Set<FormFour> formFour) {
		this.formFour = formFour;
	}

	public Set<ScheduleDetails> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(Set<ScheduleDetails> tripDetails) {
		this.tripDetails = tripDetails;
	}

	
}
