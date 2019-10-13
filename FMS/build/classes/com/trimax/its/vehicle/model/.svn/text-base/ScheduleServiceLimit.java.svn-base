package com.trimax.its.vehicle.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.transport.model.Schedule;

@Entity
@Table(name="schedule_service_limit")
public class ScheduleServiceLimit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int service_limit_id ;


	public int getService_limit_id() {
		return service_limit_id;
	}

	public void setService_limit_id(int service_limit_id) {
		this.service_limit_id = service_limit_id;
	}

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedulServiceId;
	

	public Schedule getSchedulServiceId() {
		return schedulServiceId;
	}

	public void setSchedulServiceId(Schedule schedulServiceId) {
		this.schedulServiceId = schedulServiceId;
	}

	@Column(name="brand_service_id")
	private String brand_service_id ;
	
	public String getBrand_service_id() {
		return brand_service_id;
	}

	public void setBrand_service_id(String brand_service_id) {
		this.brand_service_id = brand_service_id;
	}

	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="created_date")
	private String created_date;

	

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	
	
}
