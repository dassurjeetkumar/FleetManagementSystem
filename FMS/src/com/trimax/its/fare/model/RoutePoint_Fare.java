package com.trimax.its.fare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;
@Entity
@Table(name="route_point")
public class RoutePoint_Fare {
@Id
@GeneratedValue
@Column(name="route_points_id")
private int id;

@Column(name="route_id")
private int routeId;

@Column(name="bus_stop_id")
private int busStopId;

@Column(name="route_order")
private int routeOrder;

@Column(name="point_status")
private String pointStatus;

@Column(name="fare_stage")
private String fareStage;

@Column(name="deleted_status")
private int deletedStatus;

@Transient
private String tollZone;

@Transient
private int tollFee;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getRouteId() {
	return routeId;
}

public void setRouteId(int routeId) {
	this.routeId = routeId;
}

public int getBusStopId() {
	return busStopId;
}

public void setBusStopId(int busStopId) {
	this.busStopId = busStopId;
}

public int getRouteOrder() {
	return routeOrder;
}

public void setRouteOrder(int routeOrder) {
	this.routeOrder = routeOrder;
}

public String getPointStatus() {
	return pointStatus;
}

public void setPointStatus(String pointStatus) {
	this.pointStatus = pointStatus;
}

public String getFareStage() {
	return fareStage;
}

public void setFareStage(String fareStage) {
	this.fareStage = fareStage;
}

public int getDeletedStatus() {
	return deletedStatus;
}

public void setDeletedStatus(int deletedStatus) {
	this.deletedStatus = deletedStatus;
}

public String getTollZone() {
	return tollZone;
}

public void setTollZone(String tollZone) {
	this.tollZone = tollZone;
}

public int getTollFee() {
	return tollFee;
}

public void setTollFee(int tollFee) {
	this.tollFee = tollFee;
}


}
