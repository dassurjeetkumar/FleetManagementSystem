package com.trimax.its.route.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.transport.model.TripType;


@Entity
@Table(name="route_type")
public class RouteType {
	
	@Id
	@GeneratedValue
	@Column(name="route_type_id")
	private int route_type_id;
	
	@Column(name="route_type_name")
	private String route_type_name;
	
	public int getRoute_type_id() {
		return route_type_id;
	}

	public void setRoute_type_id(int route_type_id) {
		this.route_type_id = route_type_id;
	}

	public String getRoute_type_name() {
		return route_type_name;
	}

	public void setRoute_type_name(String route_type_name) {
		this.route_type_name = route_type_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="route_type")
	private Set<Route> route_type;

	public Set<Route> getRoute_type() {
		return route_type;
	}

	public void setRoute_type(Set<Route> route_type) {
		this.route_type = route_type;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="routetypedetails")
	private Set<TripType> routetypefortriptype;

	public Set<TripType> getRoutetypefortriptype() {
		return routetypefortriptype;
	}

	public void setRoutetypefortriptype(Set<TripType> routetypefortriptype) {
		this.routetypefortriptype = routetypefortriptype;
	}
}
