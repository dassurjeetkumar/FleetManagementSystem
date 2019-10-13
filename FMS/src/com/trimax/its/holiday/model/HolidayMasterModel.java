package com.trimax.its.holiday.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.ScheduleType;

@Entity
@Table(name="holiday_master")
public class HolidayMasterModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int holiday_master_id	;
	
	@Column 
	private String holiday_date;
	@Column 
	private String holiday_day;
	@Column 
	private String holiday_name;
	
	@ManyToOne
	@JoinColumn(name = "holiday_type",nullable=true)
	private HolidayType holydaytype;
	@Column 
	private String status;
	@Column 
	private int deleted_status;
	@Column 
	private Date create_date;
	
	@Column 
	private int created_by;
	
	
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public int getHoliday_master_id() {
		return holiday_master_id;
	}
	public void setHoliday_master_id(int holiday_master_id) {
		this.holiday_master_id = holiday_master_id;
	}
	public String getHoliday_date() {
		return holiday_date;
	}
	public void setHoliday_date(String holiday_date) {
		this.holiday_date = holiday_date;
	}
	public String getHoliday_day() {
		return holiday_day;
	}
	public void setHoliday_day(String holiday_day) {
		this.holiday_day = holiday_day;
	}
	public String getHoliday_name() {
		return holiday_name;
	}
	public void setHoliday_name(String holiday_name) {
		this.holiday_name = holiday_name;
	}
	
	public HolidayType getHolydaytype() {
		return holydaytype;
	}
	public void setHolydaytype(HolidayType holydaytype) {
		this.holydaytype = holydaytype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
	

}
