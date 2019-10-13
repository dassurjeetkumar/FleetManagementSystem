package com.trimax.its.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.transport.model.ShiftType;

@Entity
@Table(name="bata_config_details")
public class BataConfigDetails {
	@Id
	@GeneratedValue
	@Column(name="bata_config_id")
	private int bata_config_id;
	
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private DesignationType designation_type_id;
	
//	@ManyToOne
//	@JoinColumn(name="role_id")
//	private ShiftType shiftType;
	
	
	@ManyToOne
	@JoinColumn(name="shift_type_id")
	private ScheduleType schedule_type_id;
	
	@ManyToOne
	@JoinColumn(name="schedule_service_type")
	private ScheduleService servicetype;
	
	@Column(name="min_km")
	private String min_km;
	
	@Column(name="max_km")
	private String max_km;
	
	@Column(name="day_bata")
	private String day_bata;
	
	@Column(name="day_allowance")
	private String day_allowance;
	
	@Column(name="spcl_allowance")
	private String spcl_allowance;
	
	@Column(name="nighthalt_allowance")
	private String nighthalt_allowance;
	
	@Column(name="nightsevice_allowance")
	private String nightsevice_allowance;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="updated_date")
	private Date updated_date;
	
	
	@Column(name="deleted_status")
	private int deleted_status;

	@Column(name="effect_start_date")
	private String effect_start_date;
	
	@Column(name="effect_end_date")
	private String effect_end_date;
	
	@Column(name="incetive_percentage")
	private double incetive_percentage;
	
	public String getNighthalt_allowance() {
		return nighthalt_allowance;
	}


	public void setNighthalt_allowance(String nighthalt_allowance) {
		this.nighthalt_allowance = nighthalt_allowance;
	}


	public String getNightsevice_allowance() {
		return nightsevice_allowance;
	}


	public void setNightsevice_allowance(String nightsevice_allowance) {
		this.nightsevice_allowance = nightsevice_allowance;
	}


	public double getIncetive_percentage() {
		return incetive_percentage;
	}


	public void setIncetive_percentage(double incetive_percentage) {
		this.incetive_percentage = incetive_percentage;
	}


	public String getEffect_start_date() {
		return effect_start_date;
	}


	public void setEffect_start_date(String effect_start_date) {
		this.effect_start_date = effect_start_date;
	}


	public String getEffect_end_date() {
		return effect_end_date;
	}


	public void setEffect_end_date(String effect_end_date) {
		this.effect_end_date = effect_end_date;
	}


	public int getBata_config_id() {
		return bata_config_id;
	}


	public void setBata_config_id(int bata_config_id) {
		this.bata_config_id = bata_config_id;
	}


	public DesignationType getDesignation_type_id() {
		return designation_type_id;
	}


	public void setDesignation_type_id(DesignationType designation_type_id) {
		this.designation_type_id = designation_type_id;
	}


//	public ShiftType getShiftType() {
//		return shiftType;
//	}
//
//
//	public void setShiftType(ShiftType shiftType) {
//		this.shiftType = shiftType;
//	}


	public ScheduleType getSchedule_type_id() {
		return schedule_type_id;
	}


	public void setSchedule_type_id(ScheduleType schedule_type_id) {
		this.schedule_type_id = schedule_type_id;
	}


	public ScheduleService getServicetype() {
		return servicetype;
	}


	public void setServicetype(ScheduleService servicetype) {
		this.servicetype = servicetype;
	}


	public String getMin_km() {
		return min_km;
	}


	public void setMin_km(String min_km) {
	
		this.min_km = min_km.trim();
		
	}


	public String getMax_km() {
		return max_km;
	}


	public void setMax_km(String max_km) {
		
			
		this.max_km = max_km.trim();
		
	}


	public String getDay_bata() {
		return day_bata;
	}


	public void setDay_bata(String day_bata) {
		this.day_bata = day_bata.trim();
	}


	public String getDay_allowance() {
		return day_allowance;
	}


	public void setDay_allowance(String day_allowance) {
		this.day_allowance = day_allowance.trim();
	}


	public String getSpcl_allowance() {
		return spcl_allowance;
	}


	public void setSpcl_allowance(String spcl_allowance) {
		this.spcl_allowance = spcl_allowance.trim();
	}


	public int getCreated_by() {
		return created_by;
	}


	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}


	public Date getCreated_date() {
		return created_date;
	}


	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
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
	
	
	
}
