package com.trimax.its.device.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="devicehistory")
public class DeviceHistory {
	
	@Id
	@GeneratedValue
	@Column(name="device_history_id")
	private int device_history_id;
	

	@Column(name="date_of_activity")
	private String date_of_activity;
	
	
	@Column(name="activity_done_by")
	private int activity_done_by;
	
	@ManyToOne
	@JoinColumn(name="device_id")
	private Device deviceHis;
	public DeviceHistory()
	{
		
	}
	public String getDate_of_activity() {
		return date_of_activity;
	}
	public void setDate_of_activity(String date_of_activity) {
		this.date_of_activity = date_of_activity;
	}
	@Column(name="status")
	private String status;
	
	
	@Column(name="org_chart_id")
	private int org_chart_id;
	@Column(name="sim_id")
	private int sim_id;
	@Column(name="activity")
	private String activity;
	@Column(name="battery_id")
	private int battery_id;
	@Column(name="onboarding_date")
	private Date onboarding_date;
	@Column(name="malfunctioning_date")
	private Date malfunctioning_date;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	
	
	
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public Date getOnboarding_date() {
		return onboarding_date;
	}
	public void setOnboarding_date(Date onboarding_date) {
		this.onboarding_date = onboarding_date;
	}
	public Date getMalfunctioning_date() {
		return malfunctioning_date;
	}
	public void setMalfunctioning_date(Date malfunctioning_date) {
		this.malfunctioning_date = malfunctioning_date;
	}
	public int getDevice_history_id() {
		return device_history_id;
	}
	
	public int getActivity_done_by() {
		return activity_done_by;
	}
	public String getStatus() {
		return status;
	}
	public int getOrg_chart_id() {
		return org_chart_id;
	}
	public int getSim_id() {
		return sim_id;
	}
	public String getActivity() {
		return activity;
	}
	public int getBattery_id() {
		return battery_id;
	}
	public void setDevice_history_id(int device_history_id) {
		this.device_history_id = device_history_id;
	}
	
	public void setActivity_done_by(int activity_done_by) {
		this.activity_done_by = activity_done_by;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setOrg_chart_id(int org_chart_id) {
		this.org_chart_id = org_chart_id;
	}
	public void setSim_id(int sim_id) {
		this.sim_id = sim_id;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public void setBattery_id(int battery_id) {
		this.battery_id = battery_id;
	}
	public Device getDeviceHis() {
		return deviceHis;
	}
	public void setDeviceHis(Device deviceHis) {
		this.deviceHis = deviceHis;
	}
	
	

}
