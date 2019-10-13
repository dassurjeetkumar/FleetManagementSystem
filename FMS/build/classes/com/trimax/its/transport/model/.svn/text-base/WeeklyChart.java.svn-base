package com.trimax.its.transport.model;

import java.util.Date;

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
import javax.persistence.Transient;



@Entity
@Table(name="weeklyChart")
public class WeeklyChart {
	@Id
	@GeneratedValue
	@Column(name="weekly_chart_id")
	private int weekly_chart_id;
	
	@Column(name="monday")
	private int monday;
	
	@Column(name="tuesday")
	private int tuesday;
	
	@Column(name="wednesday")
	private int wednesday;
	
	@Column(name="thursday")
	private int thursday;
	
	@Column(name="friday")
	private int friday;
	
	@Column(name="saturday")
	private int saturday;
	
	@Column(name="sunday")
	private int sunday;
	
	@Column(name="holiday")
	private int holiday;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="status")
	private String status;
	
	@Transient
	private boolean mondaystatus;
	
	@Transient
	private boolean tuesdaystatus;
	
	@Transient
	private boolean wednesdaystatus;
	
	@Transient
	private boolean thursdaystatus;
	
	@Transient
	private boolean fridaystatus;
	
	@Transient
	private boolean saturdaystatus;
	
	@Transient
	private boolean sundaystatus;
	
	@Transient
	private boolean holidaystatus;
	
	public int getHoliday() {
		return holiday;
	}

	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}

	public boolean isHolidaystatus() {
		return holidaystatus;
	}

	public void setHolidaystatus(boolean holidaystatus) {
		this.holidaystatus = holidaystatus;
	}

	@ManyToOne
	@JoinColumn(name="form_four_id")
	private FormFour formFour;

	public int getWeekly_chart_id() {
		return weekly_chart_id;
	}

	public void setWeekly_chart_id(int weekly_chart_id) {
		this.weekly_chart_id = weekly_chart_id;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}



	public int getMonday() {
		return monday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public int getThursday() {
		return thursday;
	}

	public int getFriday() {
		return friday;
	}

	public int getSaturday() {
		return saturday;
	}

	public int getSunday() {
		return sunday;
	}

	

	public FormFour getFormFour() {
		return formFour;
	}

	public void setFormFour(FormFour formFour) {
		this.formFour = formFour;
	}

	public boolean isMondaystatus() {
		return mondaystatus;
	}

	public void setMondaystatus(boolean mondaystatus) {
		this.mondaystatus = mondaystatus;
	}

	public boolean isTuesdaystatus() {
		return tuesdaystatus;
	}

	public void setTuesdaystatus(boolean tuesdaystatus) {
		this.tuesdaystatus = tuesdaystatus;
	}

	public boolean isWednesdaystatus() {
		return wednesdaystatus;
	}

	public void setWednesdaystatus(boolean wednesdaystatus) {
		this.wednesdaystatus = wednesdaystatus;
	}

	public boolean isThursdaystatus() {
		return thursdaystatus;
	}

	public void setThursdaystatus(boolean thursdaystatus) {
		this.thursdaystatus = thursdaystatus;
	}

	public boolean isFridaystatus() {
		return fridaystatus;
	}

	public void setFridaystatus(boolean fridaystatus) {
		this.fridaystatus = fridaystatus;
	}

	public boolean isSaturdaystatus() {
		return saturdaystatus;
	}

	public void setSaturdaystatus(boolean saturdaystatus) {
		this.saturdaystatus = saturdaystatus;
	}

	public boolean isSundaystatus() {
		return sundaystatus;
	}

	public void setSundaystatus(boolean sundaystatus) {
		this.sundaystatus = sundaystatus;
	}

	

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	@Transient
	public String startdate;
	
	@Transient
	public String enddate;
	
	@Transient
	public String formfourname;
	
	@Transient
	public int formfourid;

	public int getFormfourid() {
		return formfourid;
	}

	public void setFormfourid(int formfourid) {
		this.formfourid = formfourid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getFormfourname() {
		return formfourname;
	}

	public void setFormfourname(String formfourname) {
		this.formfourname = formfourname;
	}
	

}
