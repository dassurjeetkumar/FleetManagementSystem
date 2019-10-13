package com.trimax.its.route.model;

import java.sql.Date;

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

@Entity
@Table(name="route_map")
public class RouteMap {
	
	public Route getRoute() {
		return routemap;
	}

	public void setRoute(Route routemap) {
		this.routemap = routemap;
	}

	@Id
	@GeneratedValue
	@Column(name="route_map_id")
	private int route_map_id;
	
	private int route_id;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY) 
	private Route routemap;
	
	@Column(name="start_bus_stop_id")
	private int start_bus_stop_id;
	
	@Column(name="end_bus_stop_id")
	private int end_bus_stop_id;
	
	@Column(name="fare_stage")
	private String fare_stage;
	
	@Column(name="road_type")
	private String road_type;
	
	@Column(name="google_distance")
	private int google_distance;
	
	@Column(name="vtu_distance")
	private int vtu_distance;
	
	@Column(name="distance")
	private Integer distance;
	
	@Column(name="google_time")
	private int google_time;
	
	@Column(name="vtu_time")
	private int vtu_time;
	
	@Column(name="time_to_travel")
	private Integer time_to_travel;
	
	@Column(name="status")
	private String status;
	
	@Column(name="bus_stop_order")
	private int bus_stop_order;
	
	@Column(name="path")
	private String path;
	
	@Column(name="route_type")
	private String route_type;

	public int getRoute_map_id() {
		return route_map_id;
	}

	public void setRoute_map_id(int route_map_id) {
		this.route_map_id = route_map_id;
	}


	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public int getStart_bus_stop_id() {
		return start_bus_stop_id;
	}

	public void setStart_bus_stop_id(int start_bus_stop_id) {
		this.start_bus_stop_id = start_bus_stop_id;
	}

	public int getEnd_bus_stop_id() {
		return end_bus_stop_id;
	}

	public void setEnd_bus_stop_id(int end_bus_stop_id) {
		this.end_bus_stop_id = end_bus_stop_id;
	}

	public String getFare_stage() {
		return fare_stage;
	}

	public void setFare_stage(String fare_stage) {
		this.fare_stage = fare_stage;
	}

	public String getRoad_type() {
		return road_type;
	}

	public void setRoad_type(String road_type) {
		this.road_type = road_type;
	}

	public int getGoogle_distance() {
		return google_distance;
	}

	public void setGoogle_distance(int google_distance) {
		this.google_distance = google_distance;
	}

	public int getVtu_distance() {
		return vtu_distance;
	}

	public void setVtu_distance(int vtu_distance) {
		this.vtu_distance = vtu_distance;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public int getGoogle_time() {
		return google_time;
	}

	public void setGoogle_time(int google_time) {
		this.google_time = google_time;
	}

	public int getVtu_time() {
		return vtu_time;
	}

	public void setVtu_time(int vtu_time) {
		this.vtu_time = vtu_time;
	}

	public Integer getTime_to_travel() {
		return time_to_travel;
	}

	public void setTime_to_travel(Integer time_to_travel) {
		this.time_to_travel = time_to_travel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBus_stop_order() {
		return bus_stop_order;
	}

	public void setBus_stop_order(int bus_stop_order) {
		this.bus_stop_order = bus_stop_order;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRoute_type() {
		return route_type;
	}

	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}
	
	private Integer schedule_time;
	private Double schedule_distance;
	private String edit_path;

	public Integer getSchedule_time() {
		return schedule_time;
	}

	public void setSchedule_time(Integer schedule_time) {
		this.schedule_time = schedule_time;
	}

	public Double getSchedule_distance() {
		return schedule_distance;
	}

	public void setSchedule_distance(Double schedule_distance) {
		this.schedule_distance = schedule_distance;
	}

	public String getEdit_path() {
		return edit_path;
	}

	public void setEdit_path(String edit_path) {
		this.edit_path = edit_path;
	}
	

}
