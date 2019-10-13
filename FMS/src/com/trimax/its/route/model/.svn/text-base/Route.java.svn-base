package com.trimax.its.route.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.fare.model.TollPassTicketModel;


import com.trimax.its.transport.model.BusStops;

//import com.vividsolutions.jts.geom.Geometry;
///import com.vividsolutions.jts.geom.LineString; 


@Entity
@Table(name="route")
public class Route {

	@Id
	@GeneratedValue
	@Column(name="route_id")
	private int route_id;
	
	@Column(name="route_number")
	private String route_number;

	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="start_point_id")
	private BusStops start_stop;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="end_point_id")
	//private Integer end_point_id;
	private BusStops end_stop;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="route_type_id")
	private RouteType route_type;
	
	@Column(name="route_name")
	private String route_name;
	
	@Column(name="status")
	private String status;
	
	@Column(name="via")
	private String via;
	
	@Column(name="description")
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	public RouteType getRoute_type() {
		return route_type;
	}

	public void setRoute_type(RouteType route_type) {
		this.route_type = route_type;
	}

	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="effective_from")
	private String effective_from;
	
	@Column(name="effective_till")
	private String effective_till;
	
	@Transient
	private String Value;
	
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getEffective_from() {
		return effective_from;
	}

	public void setEffective_from(String effective_from) {
		this.effective_from = effective_from;
	}

	public String getEffective_till() {
		return effective_till;
	}

	public void setEffective_till(String effective_till) {
		this.effective_till = effective_till;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public String getRoute_number() {
		return route_number;
	}

	public void setRoute_number(String route_number) {
		this.route_number = route_number;
	}


	/*public Integer getStart_point_id() {
		return start_point_id;
	}

	public void setStart_point_id(Integer start_point_id) {
		this.start_point_id = start_point_id;
	}

	public Integer getEnd_point_id() {
		return end_point_id;
	}

	public void setEnd_point_id(Integer end_point_id) {
		this.end_point_id = end_point_id;
	}*/

	public BusStops getStart_stop() {
		return start_stop;
	}

	public void setStart_stop(BusStops start_stop) {
		this.start_stop = start_stop;
	}

	public BusStops getEnd_stop() {
		return end_stop;
	}

	public void setEnd_stop(BusStops end_stop) {
		this.end_stop = end_stop;
	}

	/*public int getRoute_type_id() {
		return route_type_id;
	}

	public void setRoute_type_id(int route_type_id) {
		this.route_type_id = route_type_id;
	}*/

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
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

	public String getRoute_direction() {
		return route_direction;
	}

	public void setRoute_direction(String route_direction) {
		this.route_direction = route_direction;
	}

	/*public int getAqualogic_distance() {
		return aqualogic_distance;
	}

	public void setAqualogic_distance(int aqualogic_distance) {
		this.aqualogic_distance = aqualogic_distance;
	}

	public int getAqualogic_time() {
		return aqualogic_time;
	}

	public void setAqualogic_time(int aqualogic_time) {
		this.aqualogic_time = aqualogic_time;
	}*/

	public int getGoogle_distance() {
		return google_distance;
	}

	public void setGoogle_distance(int google_distance) {
		this.google_distance = google_distance;
	}

	

	public Integer getTrimax_distance() {
		return trimax_distance;
	}

	public void setTrimax_distance(Integer trimax_distance) {
		this.trimax_distance = trimax_distance;
	}

	public Integer getTrimax_time() {
		return trimax_time;
	}

	public void setTrimax_time(Integer trimax_time) {
		this.trimax_time = trimax_time;
	}

	public int getGoogle_time() {
		return google_time;
	}

	public void setGoogle_time(int google_time) {
		this.google_time = google_time;
	}

	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="route_direction")
	private String route_direction;
	
	/*@Column(name="aqualogic_distance")
	private int aqualogic_distance;*/
	
	@Column(name="trimax_distance")
	private Integer trimax_distance;
	
	/*@Column(name="aqualogic_time")
	private int aqualogic_time;*/
	@Column(name="trimax_time")
	private Integer trimax_time;	
	
	@Column(name="google_distance")
	private int google_distance;
	
	@Column(name="google_time")
	private int google_time;
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="route_id")
	Set<RouteMap> routeMapDetails;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="route_id")
	Set<RoutePoint> routePointDetails;

	public Set<RouteMap> getRouteMapDetails() {
		return routeMapDetails;
	}

	public void setRouteMapDetails(Set<RouteMap> routeMapDetails) {
		this.routeMapDetails = routeMapDetails;
	}

	public Set<RoutePoint> getRoutePointDetails() {
		return routePointDetails;
	}

	public void setRoutePointDetails(Set<RoutePoint> routePointDetails) {
		this.routePointDetails = routePointDetails;
	}*/
	@OneToMany( fetch = FetchType.LAZY,mappedBy="routemap")
	private Collection<RouteMap> routeMap = new ArrayList<RouteMap>();
	
	@OneToMany( fetch = FetchType.LAZY,mappedBy="routepoint")
	private Collection<RoutePoint> routePoint = new ArrayList<RoutePoint>();

	public Collection<RouteMap> getRouteMap() {
		return routeMap;
	}

	public void setRouteMap(Collection<RouteMap> routeMap) {
		this.routeMap = routeMap;
	}

	public Collection<RoutePoint> getRoutePoint() {
		return routePoint;
	}

	public void setRoutePoint(Collection<RoutePoint> routePoint) {
		this.routePoint = routePoint;
	}
	

	@OneToMany(cascade = CascadeType.ALL,mappedBy="route")// fetch = FetchType.LAZY,mappedBy="route")
	Set<Farechart_Master> fareChartMaster;

	public Set<Farechart_Master> getFareChartMaster() {
		return fareChartMaster;
	}

	public void setFareChartMaster(Set<Farechart_Master> fareChartMaster) {
		this.fareChartMaster = fareChartMaster;
	}
	

	/*@Type(type = "org.hibernate.spatial.GeometryType")
	@Column(name = "route_string",columnDefinition="Geometry", nullable = true)
	
	private Geometry route_string;

	public Geometry getRoute_string() {
		return route_string;
	}

	public void setRoute_string(Geometry route_string) {
		this.route_string = route_string;
	}*/
	
	@Column(name="route_geofence")
	private String route_geofence;

	public String getRoute_geofence() {
		return route_geofence;
	}

	public void setRoute_geofence(String route_geofence) {
		this.route_geofence = route_geofence;
	}
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="routeno")
	Set<TollPassTicketModel> tollpass;

	public Set<TollPassTicketModel> getTollpass() {
		return tollpass;
	}

	public void setTollpass(Set<TollPassTicketModel> tollpass) {
		this.tollpass = tollpass;
	}
	
}
