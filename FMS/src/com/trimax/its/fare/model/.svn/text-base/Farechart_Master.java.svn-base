package com.trimax.its.fare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.Route_Trans;

@Entity
@Table(name="farechart_master")
public class Farechart_Master {
	@Id
	@GeneratedValue
	@Column(name="farechart_master_id")
	
	private int farechart_master_id;
//	@Column(name="route_id")
//	private int route_id;
	@Column(name="route_name")
	private String route_name;
	@Column(name="service_type_id")
	private int service_type_id;
	@Column(name="passenger_type_id")
	private int passenger_type_id;
	@Column(name="rate_master_id")
	private int rate_master_id;
	@Column(name="route_fare_map_id")
	private int route_fare_map_id;
	@Column(name="farechart_name")
	private String farechart_name;
	@Column(name="effect_start_date")
	private String effect_start_date;
	@Column(name="effect_end_date")
	private String effect_end_date;
	@Column(name="opfrom_time")
	private String opfrom_time;
	@Column(name="opto_time")
	private String opto_time;
	@Column(name="peak_time")
	private String peak_time;
	@Column(name="schedule_service")
	private int schedule_service;
	@Column(name="percentage")
	private int percentage;
	@Column(name="deleted_status")
	private int deleted_status;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="created_by")
	private String created_by;
	@Column(name="updated_by")
	private String updated_by;
	
	@Column(name="ceiling_fare")
	private int ceiling_fare;
	@Column(name="nignt_service")
	private String nignt_service;
	
	@Column(name="flexi_fare")
	private String flexi_fare;
	
	@ManyToOne
	@JoinColumn(name="route_id")
	private Route route;
	
	
	public int getCeiling_fare() {
		return ceiling_fare;
	}

	public void setCeiling_fare(String ceiling_fare) {
		
		if(ceiling_fare!=null && ceiling_fare.trim().length()>0){
		if(isInteger(ceiling_fare)){
		
			this.ceiling_fare = Integer.parseInt(ceiling_fare);
		}else{
			this.ceiling_fare =-1;
		}
		}else{
			this.ceiling_fare=0;
		}
	}

	public String getNignt_service() {
		return nignt_service;
	}

	public void setNignt_service(String nignt_service) {
		this.nignt_service = nignt_service;
	}

	public String getEffect_start_date() {
		return effect_start_date;
	}

	public String getEffect_end_date() {
		return effect_end_date;
	}

	public void setEffect_start_date(String effect_start_date) {
		this.effect_start_date = effect_start_date;
	}

	public void setEffect_end_date(String effect_end_date) {
		this.effect_end_date = effect_end_date;
	}

	public int getFarechart_master_id() {
		return farechart_master_id;
	}
	
	public String getRoute_name() {
		return route_name;
	}
	public int getService_type_id() {
		return service_type_id;
	}
	public int getPassenger_type_id() {
		return passenger_type_id;
	}
	public int getRate_master_id() {
		return rate_master_id;
	}
	public int getRoute_fare_map_id() {
		return route_fare_map_id;
	}
//	public int getRoute_id() {
//		return route_id;
//	}
//
//	public void setRoute_id(int route_id) {
//		this.route_id = route_id;
//	}

	public String getFarechart_name() {
		return farechart_name;
	}
	
	public String getOpfrom_time() {
		return opfrom_time;
	}
	public String getOpto_time() {
		return opto_time;
	}
	public String getPeak_time() {
		return peak_time;
	}
	public int getSchedule_service() {
		return schedule_service;
	}
	public int getPercentage() {
		return percentage;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setFarechart_master_id(int farechart_master_id) {
		this.farechart_master_id = farechart_master_id;
	}
	
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public void setService_type_id(int service_type_id) {
		this.service_type_id = service_type_id;
	}
	public void setPassenger_type_id(int passenger_type_id) {
		this.passenger_type_id = passenger_type_id;
	}
	public void setRate_master_id(int rate_master_id) {
		this.rate_master_id = rate_master_id;
	}
	public void setRoute_fare_map_id(int route_fare_map_id) {
		this.route_fare_map_id = route_fare_map_id;
	}
	public void setFarechart_name(String farechart_name) {
		this.farechart_name = farechart_name;
	}
	
	public void setOpfrom_time(String opfrom_time) {
		this.opfrom_time = opfrom_time;
	}
	public void setOpto_time(String opto_time) {
		this.opto_time = opto_time;
	}
	public void setPeak_time(String peak_time) {
		this.peak_time = peak_time;
	}
	public void setSchedule_service(int schedule_service) {
		this.schedule_service = schedule_service;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}

	public String getFlexi_fare() {
		return flexi_fare;
	}

	public void setFlexi_fare(String flexi_fare) {
		this.flexi_fare = flexi_fare;
	}

}
