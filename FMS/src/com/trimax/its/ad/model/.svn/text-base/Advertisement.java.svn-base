package com.trimax.its.ad.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.transport.model.Customer;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="advertisement")
public class Advertisement {
	@Id
	@GeneratedValue 
	@Column(name="advertisement_id")
	private int advertisement_id;
	@Column(name="advertisement_name")
	private String advertisement_name;
	@Column(name="file_path")
	private String file_path;
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	@ManyToOne 
	@JoinColumn(name="customer_id")
	private Customer cust;
	@Column(name="start_date")
    private String start_date;
	@Column(name="end_date")
    private String end_date;
	@ManyToOne
	@JoinColumn(name="advertisement_type")
	private AdvertisementType advetisetype;
	@Column(name="status")
	private String status;
	@Column(name="amount")
	private long amount;
	@Column(name="duration")
	private int duration;
	@Column(name="display_peak_hour")
	private String display_peak_hour;
	
	
	public String getDisplay_peak_hour() {
		return display_peak_hour;
	}
	public void setDisplay_peak_hour(String display_peak_hour) {
		this.display_peak_hour = display_peak_hour;
	}
	@Column(name="frequency")
	private int frequency;
	@Column(name="airtime")
    private String airtime;
	@Column(name="created_date")
    private Date created_date;
	@Column(name="created_by")
    private int created_by; 
	@Column(name="updated_date")
    private Date updated_date;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="deleted_status")
	private int deleted_status;
	@Column(name="notes")
	private String notes;
	
	public int getAdvertisement_id() {
		return advertisement_id;
	}
	public void setAdvertisement_id(int advertisement_id) {
		this.advertisement_id = advertisement_id;
	}
	public String getAdvertisement_name() {
		return advertisement_name;
	}
	public void setAdvertisement_name(String advertisement_name) {
		this.advertisement_name = advertisement_name;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public AdvertisementType getAdvetisetype() {
		return advetisetype;
	}
	public void setAdvetisetype(AdvertisementType advetisetype) {
		this.advetisetype = advetisetype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) { 
		this.amount = amount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		if(isInteger(duration.trim()))
		{
		
		this.duration = Integer.parseInt(duration);
		}
		else
		{
			this.duration=0;
		}
	}
	
	public int getFrequency() {
		return frequency;
	}
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	public void setFrequency(String frequency) {
		
		if(isInteger(frequency.trim()))
		{
		this.frequency = Integer.parseInt(frequency);
		}
		else
		{
			this.frequency=0;
		}
		}
	
	public String getAirtime() {
		return airtime;
	}
	public void setAirtime(String airtime) {
		this.airtime = airtime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	
	
	
	
	public Date getCreated_date() {
		return created_date;
	}
	
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

}
