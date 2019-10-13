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

@Entity
@Table(name="route")
public class Route_Trans{


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Integer route_id;
	
	@Column(name="route_number")
	String routeNumber;
	
	
	@Column(name="route_type_id")
	Integer routeTypeId;
	
	@Column(name="route_name")
	String routeName;
	
	@Column(name="status")
	String status;
	
	@Column(name="via")
	String via;
	
	@Column(name="created_date")
	Date createdDate;
	
	@Column(name="created_by")
	Integer createdBy;
	
	@Column(name="updated_by")
	Integer updatedBy;
	
	@Column(name="updated_date")
	Date updatedDate;
	
	@Column(name="deleted_status")
	int deletedStatus;
	
	@Column(name="route_direction")
	String  routeDirection;
	
	/*@Column(name="aqualogic_distance")
	Integer aqualogicDistance;
	
	@Column(name="aqualogic_time")
	Integer aqualogicTime;*/
	
	@Column(name="google_distance")
	Integer googleDistance;
	
	@Column(name="google_time")
	Integer googleTime;
	
	@Column(name="route_string")
	String routeString;
	
	@Transient
	String busStopid;
	
	@Transient
	String busStopName;
	
	@Transient
	String routeOrder;
	
	@Transient
	String effectiveFrom;
	
	@Transient
	String effectiveTill;
	

	public String getBusStopid() {
		return busStopid;
	}

	public void setBusStopid(String busStopid) {
		this.busStopid = busStopid;
	}

	public String getBusStopName() {
		return busStopName;
	}

	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}

	

	public Integer getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}


	

	public Integer getRouteTypeId() {
		return routeTypeId;
	}

	public void setRouteTypeId(Integer routeTypeId) {
		this.routeTypeId = routeTypeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
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

	
	
	/*public Integer getAqualogicDistance() {
		return aqualogicDistance;
	}

	public void setAqualogicDistance(Integer aqualogicDistance) {
		this.aqualogicDistance = aqualogicDistance;
	}

	public Integer getAqualogicTime() {
		return aqualogicTime;
	}

	public void setAqualogicTime(Integer aqualogicTime) {
		this.aqualogicTime = aqualogicTime;
	}*/

	public Integer getGoogleDistance() {
		return googleDistance;
	}

	public void setGoogleDistance(Integer googleDistance) {
		this.googleDistance = googleDistance;
	}

	public Integer getGoogleTime() {
		return googleTime;
	}

	public void setGoogleTime(Integer googleTime) {
		this.googleTime = googleTime;
	}

	public String getRouteDirection() {
		return routeDirection;
	}

	public void setRouteDirection(String routeDirection) {
		this.routeDirection = routeDirection;
	}

	
	public String getRouteString() {
		return routeString;
	}

	public void setRouteString(String routeString) {
		this.routeString = routeString;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="routeno")
	private Set<Schedule> schDetails;

	public Set<Schedule> getSchDetails() {
		return schDetails;
	}

	public void setSchDetails(Set<Schedule> schDetails) {
		this.schDetails = schDetails;
	}*/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="start_point_id", referencedColumnName="bus_stop_id")
	private BusStops busstartpoint;

	public BusStops getBusstartpoint() {
		return busstartpoint;
	}

	public void setBusstartpoint(BusStops busstartpoint) {
		this.busstartpoint = busstartpoint;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="end_point_id", referencedColumnName="bus_stop_id")
	private BusStops busendpoint;

	public BusStops getBusendpoint() {
		return busendpoint;
	}

	public void setBusendpoint(BusStops busendpoint) {
		this.busendpoint = busendpoint;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="formFourRoute")
	private Set<FormFour> formFourDetails;

	public Set<FormFour> getFormFourDetails() {
		return formFourDetails;
	}

	public void setFormFourDetails(Set<FormFour> formFourDetails) {
		this.formFourDetails = formFourDetails;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="routeNumber")
	private Set<ScheduleDetails> tripDetails;



	public Set<ScheduleDetails> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(Set<ScheduleDetails> tripDetails) {
		this.tripDetails = tripDetails;
	}

	/**
	 * @return the routeOrder
	 */
	public String getRouteOrder() {
		return routeOrder;
	}

	/**
	 * @param routeOrder the routeOrder to set
	 */
	public void setRouteOrder(String routeOrder) {
		this.routeOrder = routeOrder;
	}

	/**
	 * @return the effectiveFrom
	 */
	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	/**
	 * @param effectiveFrom the effectiveFrom to set
	 */
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	/**
	 * @return the effectiveTill
	 */
	public String getEffectiveTill() {
		return effectiveTill;
	}

	/**
	 * @param effectiveTill the effectiveTill to set
	 */
	public void setEffectiveTill(String effectiveTill) {
		this.effectiveTill = effectiveTill;
	}
	
	
	
}
