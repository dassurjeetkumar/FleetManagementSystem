package com.trimax.its.transport.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="form_four")
public class FormFour {

	@Id
	@GeneratedValue
	@Column(name="form_four_id")
	private int id;
	
		
	@Column(name="schedule_number_name")
	private String scheduleNumberName;
	
	@Column(name="no_of_trips")
	private int NoofTrips;
	
	
	@Column(name="start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	
	@Column(name="toll_zone")
	private int tollZone;
	
	@Column(name="area_limit")
	private int areaLimit;
	
	@Column(name="total_km")
	private Double totalKm =0.0;
	
	@Column(name="total_dead_km")
	private Double totalDeadKm = 0.0;
	
	@Column(name="actual_km")
	private Double actualKm =0.0;
	
	@Column(name="total_running_time")
	private String totalRunTime;
	
	@Column(name="total_break_time")
	@Temporal(TemporalType.TIME)
	private Date totalBreakTime;
	
	@Column(name="total_steering_time")
	private String totalSteeringTime;
	
	@Column(name="spread_over_hours")
	private String spreadOverHours;
	
	@Column(name="ot_hours")
	private String otHours;
	
	@Column(name="sign_on")
	@Temporal(TemporalType.TIME)
	private Date signOn;
	
	@Column(name="sign_off")
	@Temporal(TemporalType.TIME)
	private Date signOff;
	
	@Column(name="effective_start_date")
	private Date effectiveStartDate;
	
	@Column(name="effective_end_date")
	private Date effectiveEndDate;
	
	@Column(name="created_date",nullable=true)
	private Date createdDate;
	
	@Column(name="updated_date",nullable=true)
	private Date updatedDate;
	
	@Column(name="stotal_dead_km")
	private Double stotalDeadKm = 0.0;
	
	@Column(name="sactual_km")
	private Double sactualKm = 0.0;
	
	@Column(name="stotal_steering_time")
	@Temporal(TemporalType.TIME)
	private Date stotalSteeringTime;
	
	@Column(name="sspread_over_hours")
	@Temporal(TemporalType.TIME)
	private Date sspreadOverHours;
	
	@Column(name="sot_hours")
	@Temporal(TemporalType.TIME)
	private Date sotHours;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	@Column(name="current_status")
	private String currentStatus;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="created_by",nullable=true)
	private Integer createdBy;
	
	@Column(name="updated_by",nullable=true)
	private Integer updatedBy;
	
	@Transient
	private Double schkms1=0.0;
	
	@Transient
	private Double schkms2=0.0;
	
	@Transient
	private Double dead1=0.0;
	
	@Transient
	private Double dead2=0.0;
	
	@Column(name="spread1")
	private String spread1 = "0:00";
	
	@Column(name="spread2")
	private String spread2="0:00";
	
	@Column(name="steering1")
	private String steering1="0:00";
	
	@Column(name="steering2")
	private String steering2="0:00";
	
	@Column(name="ot1")
	private String ot1 = "0:00";
	
	
	private String ot2 = "0:00";
	
	@Column(name="rest1")
	private String restForCrew1;
	
	@Column(name="rest2")
	private String restForCrew2;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="formFour")
	private Set<ScheduleDetails> schDetails;
	
	
	@ManyToOne
	@JoinColumn(name="form_four_name", referencedColumnName="form_four_type_id")
	FormFourType formFourType;
	
	@ManyToOne
	@JoinColumn(name="schedule_number_id", referencedColumnName="schedule_id")
	Schedule scheduleNumber;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="route_id", referencedColumnName="route_id")
	Route_Trans formFourRoute;
	
	@Transient
	private String starttimeString;
	
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	
	@Transient
	private String scheduleString;
	
	@Transient
	private String formFourString;
	
	@Transient
	private String recordDate;
	
	@Transient
	private String traffic_order_no;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getScheduleNumberName() {
		return scheduleNumberName;
	}

	public void setScheduleNumberName(String scheduleNumberName) {
		this.scheduleNumberName = scheduleNumberName;
	}

	

	public int getNoofTrips() {
		return NoofTrips;
	}

	public void setNoofTrips(int noofTrips) {
		NoofTrips = noofTrips;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getTollZone() {
		return tollZone;
	}

	public void setTollZone(int tollZone) {
		this.tollZone = tollZone;
	}

	public int getAreaLimit() {
		return areaLimit;
	}

	public void setAreaLimit(int areaLimit) {
		this.areaLimit = areaLimit;
	}

	public Double getTotalKm() {
		return totalKm;
	}

	public void setTotalKm(Double totalKm) {
		this.totalKm = totalKm;
	}

	public Double getTotalDeadKm() {
		return totalDeadKm;
	}

	public void setTotalDeadKm(Double totalDeadKm) {
		this.totalDeadKm = totalDeadKm;
	}

	public Double getActualKm() {
		return actualKm;
	}

	public void setActualKm(Double actualKm) {
		this.actualKm = actualKm;
	}

	

	public String getTotalRunTime() {
		return totalRunTime;
	}

	public void setTotalRunTime(String totalRunTime) {
		this.totalRunTime = totalRunTime;
	}

	public Date getTotalBreakTime() {
		return totalBreakTime;
	}

	public void setTotalBreakTime(Date totalBreakTime) {
		this.totalBreakTime = totalBreakTime;
	}
	
	public String getTotalSteeringTime() {
		return totalSteeringTime;
	}

	public void setTotalSteeringTime(String totalSteeringTime) {
		this.totalSteeringTime = totalSteeringTime;
	}

	public String getSpreadOverHours() {
		return spreadOverHours;
	}

	public void setSpreadOverHours(String spreadOverHours) {
		this.spreadOverHours = spreadOverHours;
	}

	public String getOtHours() {
		return otHours;
	}

	public void setOtHours(String otHours) {
		this.otHours = otHours;
	}

	public Date getSignOn() {
		return signOn;
	}

	public void setSignOn(Date signOn) {
		this.signOn = signOn;
	}

	public Date getSignOff() {
		return signOff;
	}

	public void setSignOff(Date signOff) {
		this.signOff = signOff;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Double getStotalDeadKm() {
		return stotalDeadKm;
	}

	public void setStotalDeadKm(Double stotalDeadKm) {
		this.stotalDeadKm = stotalDeadKm;
	}

	public Double getSactualKm() {
		return sactualKm;
	}

	public void setSactualKm(Double sactualKm) {
		this.sactualKm = sactualKm;
	}

	public Date getStotalSteeringTime() {
		return stotalSteeringTime;
	}

	public void setStotalSteeringTime(Date stotalSteeringTime) {
		this.stotalSteeringTime = stotalSteeringTime;
	}

	public Date getSspreadOverHours() {
		return sspreadOverHours;
	}

	public void setSspreadOverHours(Date sspreadOverHours) {
		this.sspreadOverHours = sspreadOverHours;
	}

	public Date getSotHours() {
		return sotHours;
	}

	public void setSotHours(Date sotHours) {
		this.sotHours = sotHours;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public FormFourType getFormFourType() {
		return formFourType;
	}

	public void setFormFourType(FormFourType formFourType) {
		this.formFourType = formFourType;
	}

	public Schedule getScheduleNumber() {
		return scheduleNumber;
	}

	public void setScheduleNumber(Schedule scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public Route_Trans getFormFourRoute() {
		return formFourRoute;
	}

	public void setFormFourRoute(Route_Trans formFourRoute) {
		this.formFourRoute = formFourRoute;
	}

	public String getStarttimeString() {
		return starttimeString;
	}

	public void setStarttimeString(String starttimeString) {
		this.starttimeString = starttimeString;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getScheduleString() {
		return scheduleString;
	}

	public void setScheduleString(String scheduleString) {
		this.scheduleString = scheduleString;
	}

	public String getFormFourString() {
		return formFourString;
	}

	public void setFormFourString(String formFourString) {
		this.formFourString = formFourString;
	}

	public Double getSchkms1() {
		return schkms1;
	}

	public void setSchkms1(Double schkms1) {
		this.schkms1 = schkms1;
	}

	public Double getSchkms2() {
		return schkms2;
	}

	public void setSchkms2(Double schkms2) {
		this.schkms2 = schkms2;
	}

	public Double getDead1() {
		return dead1;
	}

	public void setDead1(Double dead1) {
		this.dead1 = dead1;
	}

	public Double getDead2() {
		return dead2;
	}

	public void setDead2(Double dead2) {
		this.dead2 = dead2;
	}

	public String getSpread1() {
		return spread1;
	}

	public void setSpread1(String spread1) {
		this.spread1 = spread1;
	}

	public String getSpread2() {
		return spread2;
	}

	public void setSpread2(String spread2) {
		this.spread2 = spread2;
	}

	public String getSteering1() {
		return steering1;
	}

	public void setSteering1(String steering1) {
		this.steering1 = steering1;
	}

	public String getSteering2() {
		return steering2;
	}

	public void setSteering2(String steering2) {
		this.steering2 = steering2;
	}

	public String getOt1() {
		return ot1;
	}

	public void setOt1(String ot1) {
		this.ot1 = ot1;
	}

	public String getOt2() {
		return ot2;
	}

	public void setOt2(String ot2) {
		this.ot2 = ot2;
	}

	public String getRestForCrew1() {
		return restForCrew1;
	}

	public void setRestForCrew1(String restForCrew1) {
		this.restForCrew1 = restForCrew1;
	}

	public String getRestForCrew2() {
		return restForCrew2;
	}

	public void setRestForCrew2(String restForCrew2) {
		this.restForCrew2 = restForCrew2;
	}

	public Set<ScheduleDetails> getSchDetails() {
		return schDetails;
	}

	public void setSchDetails(Set<ScheduleDetails> schDetails) {
		this.schDetails = schDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="formFour")
	private Set<WeeklyChart> weeklychart;



	public Set<WeeklyChart> getWeeklychart() {
		return weeklychart;
	}

	public void setWeeklychart(Set<WeeklyChart> weeklychart) {
		this.weeklychart = weeklychart;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getTraffic_order_no() {
		return traffic_order_no;
	}

	public void setTraffic_order_no(String traffic_order_no) {
		this.traffic_order_no = traffic_order_no;
	}
	
	
	
}
