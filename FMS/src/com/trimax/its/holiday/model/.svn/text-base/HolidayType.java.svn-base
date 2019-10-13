package com.trimax.its.holiday.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;




@Entity
@Table(name="holiday_type")
public class HolidayType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int holiday_id	;
	
	@Column 
	private String holiday_type;

	
	@Column
	private String notes;
	@Column
	private int created_by;
	
	@Column
	private int updated_by;
	
	@Column 
	private  Date updated_date;
	
	@Column
	private int deleted_status;

	public int getHoliday_id() {
		return holiday_id;
	}

	public void setHoliday_id(int holiday_id) {
		this.holiday_id = holiday_id;
	}

	public String getHoliday_type() {
		return holiday_type.trim();
	}

	public void setHoliday_type(String holiday_type) {
		this.holiday_type = holiday_type.trim();
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
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
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="holydaytype")
	Set<HolidayMasterModel> holydaytypeforholidaymaster;

	public Set<HolidayMasterModel> getHolydaytypeforholidaymaster() {
		return holydaytypeforholidaymaster;
	}

	public void setHolydaytypeforholidaymaster(
			Set<HolidayMasterModel> holydaytypeforholidaymaster) {
		this.holydaytypeforholidaymaster = holydaytypeforholidaymaster;
	}	
	
}
