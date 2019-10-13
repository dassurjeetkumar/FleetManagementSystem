package com.trimax.its.route.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="route_point")
public class RoutePoint {

	public Route getRoute() {
		return routepoint;
	}

	public void setRoute(Route routepoint) {
		this.routepoint = routepoint;
	}

	@Id
	@GeneratedValue
	@Column(name="route_points_id")
	private int route_points_id;
	
	/*private int route_id;
	
	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}*/
	//@Column(name="route_id")
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="route_id")
	private Route routepoint;
	
	@Column(name="bus_stop_id")
	private int bus_stop_id;
	
	@Column(name="route_order")
	private int route_order;
	
	@Column(name="point_status")
	private String point_status;
	
	@Column(name="fare_stage")
	private String fare_stage;
	
	@Column(name="sub_stage")
	private String sub_stage;
	
	public String getSub_stage() {
		return sub_stage;
	}

	public void setSub_stage(String sub_stage) {
		this.sub_stage = sub_stage;
	}

	@Column(name="created_date")
	private Date created_date;

	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	@Column(name="deleted_status")
	private int deleted_status;
	

	/*public Route getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Route route_id) {
		this.route_id = route_id;
	}*/

	public int getBus_stop_id() {
		return bus_stop_id;
	}

	public void setBus_stop_id(int bus_stop_id) {
		this.bus_stop_id = bus_stop_id;
	}

	public int getRoute_order() {
		return route_order;
	}

	public void setRoute_order(int route_order) {
		this.route_order = route_order;
	}

	public String getPoint_status() {
		return point_status;
	}

	public void setPoint_status(String point_status) {
		this.point_status = point_status;
	}

	public String getFare_stage() {
		return fare_stage;
	}

	public void setFare_stage(String fare_stage) {
		this.fare_stage = fare_stage;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	

	public int getRoute_points_id() {
		return route_points_id;
	}

	public void setRoute_points_id(int route_points_id) {
		this.route_points_id = route_points_id;
	}
	

	
	
}
