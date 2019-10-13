package com.trimax.its.transport.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="schedule_details")
public class ScheduleDetails {

	@Id
	@GeneratedValue
	@Column(name="schedule_details_id")
	private int id;
	
	@Column(name="number_of_trips")
	private int noofTrips;
	
	@Column(name="list_item_number")
	private int listItemNumber;
	
	
	@Column(name="trip_number")
	private int tripNumber;
	
	@Column(name="start_point")
	private int startPoint;
	
	@Column(name="end_point")
	private int endPoint;
	
	@Column(name="distance")
	private double distance = 0.0;
	
	@Column(name="start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	
	@Column(name="end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;
	
	@Column(name="running_time")
	@Temporal(TemporalType.TIME)
	private Date runningTime;
	
	@Column(name="break_time")
	@Temporal(TemporalType.TIME)
	private Date breakTime;
	
	@Column(name="crew_change")
	private int crewChange;
	
	@Column(name="night_halt")
	private int nightHalt;
	
	@Column(name="is_dread_trip")
	private int isDreadTrip;
	
	@Column(name="crew_change_location")
	private String crewChangeLocation;
	
	@Column(name="night_halt_location")
	private String nightHaltLocation;
	
	@Column(name="break_location")
	private String breakLocation;
	
	@Column(name="operation_type")
	private String operationType;
	
	
	
	@Column(name="remarks")
	private String remarks;
	
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
	
	@Transient
	private String origin;
	
	@Transient
	private String groupStartPoint;
	
	/**
	 * @return the groupStartPoint
	 */
	public String getGroupStartPoint() {
		return groupStartPoint;
	}

	/**
	 * @param groupStartPoint the groupStartPoint to set
	 */
	public void setGroupStartPoint(String groupStartPoint) {
		this.groupStartPoint = groupStartPoint;
	}

	@Transient
	private String destination;
	
	@Transient
	private String routeNo;
	
	@Transient
	private String starttimeString;
	
	@Transient
	private String endtimeString;
	
	@Transient
	private String breaktimeString;
	
	@Transient
	private String journeyTime;
	
	@Transient
	private String breakTypeString;
	
	@Transient
	private String selectedRoute;
	
	@Transient
	private String selectedTrip;
	
	@Transient
public boolean crewChangeStatus;
	
	@Transient
	public boolean deadTrip;
	
	@Transient
	public boolean nightTrip;
	
	@Transient
	private String routeDetails;
	
	@Transient
	private String groupStops;
	
		
	public boolean isNightTrip() {
		return nightTrip;
	}

	public void setNightTrip(boolean nightTrip) {
		this.nightTrip = nightTrip;
	}

	@ManyToOne
	@JoinColumn(name="form_four_id")
	private FormFour formFour;
	
	
	@ManyToOne
	@JoinColumn(name="schedule_number")
	private Schedule scheduleNumber;
	
	@ManyToOne
	@JoinColumn(name="trip_type", referencedColumnName="trip_type_id")
	private TripType tripType;
	
	@ManyToOne
	@JoinColumn(name="break_type_id", referencedColumnName="break_type_id")
	private BreakType breakType;
	
	@ManyToOne
	@JoinColumn(name="shift_type_id")
	private ShiftType shiftType;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="route_number_id",referencedColumnName="route_id")
	private Route_Trans routeNumber;

	@ManyToOne
	@JoinColumn(name="customer_id",referencedColumnName="c_cid")
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoofTrips() {
		return noofTrips;
	}

	public void setNoofTrips(int noofTrips) {
		this.noofTrips = noofTrips;
	}

	public int getListItemNumber() {
		return listItemNumber;
	}

	public void setListItemNumber(int listItemNumber) {
		this.listItemNumber = listItemNumber;
	}

	public int getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(int tripNumber) {
		this.tripNumber = tripNumber;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

	

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(Date runningTime) {
		this.runningTime = runningTime;
	}

	public Date getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(Date breakTime) {
		this.breakTime = breakTime;
	}

	public int getCrewChange() {
		return crewChange;
	}

	public void setCrewChange(int crewChange) {
		this.crewChange = crewChange;
	}

	public int getNightHalt() {
		return nightHalt;
	}

	public void setNightHalt(int nightHalt) {
		this.nightHalt = nightHalt;
	}

	public int getIsDreadTrip() {
		return isDreadTrip;
	}

	public void setIsDreadTrip(int isDreadTrip) {
		this.isDreadTrip = isDreadTrip;
	}

	public String getCrewChangeLocation() {
		return crewChangeLocation;
	}

	public void setCrewChangeLocation(String crewChangeLocation) {
		this.crewChangeLocation = crewChangeLocation;
	}

	public String getNightHaltLocation() {
		return nightHaltLocation;
	}

	public void setNightHaltLocation(String nightHaltLocation) {
		this.nightHaltLocation = nightHaltLocation;
	}

	public String getBreakLocation() {
		return breakLocation;
	}

	public void setBreakLocation(String breakLocation) {
		this.breakLocation = breakLocation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public FormFour getFormFour() {
		return formFour;
	}

	public void setFormFour(FormFour formFour) {
		this.formFour = formFour;
	}

	public Schedule getScheduleNumber() {
		return scheduleNumber;
	}

	public void setScheduleNumber(Schedule scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public TripType getTripType() {
		return tripType;
	}

	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}

	public BreakType getBreakType() {
		return breakType;
	}

	public void setBreakType(BreakType breakType) {
		this.breakType = breakType;
	}

	public ShiftType getShiftType() {
		return shiftType;
	}

	public void setShiftType(ShiftType shiftType) {
		this.shiftType = shiftType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public String getStarttimeString() {
		return starttimeString;
	}

	public void setStarttimeString(String starttimeString) {
		this.starttimeString = starttimeString;
	}

	public String getEndtimeString() {
		return endtimeString;
	}

	public void setEndtimeString(String endtimeString) {
		this.endtimeString = endtimeString;
	}

	public String getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}

	public String getBreakTypeString() {
		return breakTypeString;
	}

	public void setBreakTypeString(String breakTypeString) {
		this.breakTypeString = breakTypeString;
	}

	public Route_Trans getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(Route_Trans routeNumber) {
		this.routeNumber = routeNumber;
	}

	

	public boolean isCrewChangeStatus() {
		return crewChangeStatus;
	}

	public void setCrewChangeStatus(boolean crewChangeStatus) {
		this.crewChangeStatus = crewChangeStatus;
	}

	public boolean isDeadTrip() {
		return deadTrip;
	}

	public void setDeadTrip(boolean deadTrip) {
		this.deadTrip = deadTrip;
	}

	public String getSelectedRoute() {
		return selectedRoute;
	}

	public void setSelectedRoute(String selectedRoute) {
		this.selectedRoute = selectedRoute;
	}

	public String getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(String selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

	public String getBreaktimeString() {
		return breaktimeString;
	}

	public void setBreaktimeString(String breaktimeString) {
		this.breaktimeString = breaktimeString;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return the routeDetails
	 */
	public String getRouteDetails() {
		return routeDetails;
	}

	/**
	 * @param routeDetails the routeDetails to set
	 */
	public void setRouteDetails(String routeDetails) {
		this.routeDetails = routeDetails;
	}

	/**
	 * @return the groupStops
	 */
	public String getGroupStops() {
		return groupStops;
	}

	/**
	 * @param groupStops the groupStops to set
	 */
	public void setGroupStops(String groupStops) {
		this.groupStops = groupStops;
	}

	
	
	
}
