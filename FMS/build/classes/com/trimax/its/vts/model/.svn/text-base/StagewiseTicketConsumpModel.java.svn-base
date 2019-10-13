package com.trimax.its.vts.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="route")
public class StagewiseTicketConsumpModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="route_id")
	private String 	route_id;
	
	@Column(name="route_number")
	private String route_number;
	
	@Column(name="status")
	private String status;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="route_direction")
	private String route_direction;
	
	@Column(name="effective_till")
	private String effective_till;
	
	@Column(name="effective_till")
	private String route_detail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getRoute_number() {
		return route_number;
	}

	public void setRoute_number(String route_number) {
		this.route_number = route_number;
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

	public String getRoute_direction() {
		return route_direction;
	}

	public void setRoute_direction(String route_direction) {
		this.route_direction = route_direction;
	}

	public String getEffective_till() {
		return effective_till;
	}

	public void setEffective_till(String effective_till) {
		this.effective_till = effective_till;
	}
	

	
}
