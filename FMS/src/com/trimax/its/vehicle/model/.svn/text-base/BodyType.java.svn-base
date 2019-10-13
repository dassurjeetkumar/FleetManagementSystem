package com.trimax.its.vehicle.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="body_type")
public class BodyType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int body_type_id;
	
	@Column 
	private String body_type_name;
	
	@Column(name = "status")
	private String status;
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="bodyType")
	private Set<Vehicle> vehicles;


	public int getBody_type_id() {
		return body_type_id;
	}


	public void setBody_type_id(int body_type_id) {
		this.body_type_id = body_type_id;
	}


	public String getBody_type_name() {
		return body_type_name;
	}


	public void setBody_type_name(String body_type_name) {
		this.body_type_name = body_type_name;
	}
	


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	public Set<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	

}
