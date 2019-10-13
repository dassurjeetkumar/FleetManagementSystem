package com.trimax.its.vehicle.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.common.Common;

@Entity
@Table(name="fuel")
public class Fuel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fuel_id;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@Column
	private String kms;
	
	@Column
	private String litres;
	
	@Column(nullable=true)
	private String date;
	
	@Column
	private String fuel_type;

	@javax.persistence.Transient
	Common common = new Common();
	public int getFuel_id() {
		return fuel_id;
	}

	public void setFuel_id(int fuel_id) {
		this.fuel_id = fuel_id;
	}

	

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getKms() {
		return kms;
	}

	public void setKms(String kms) {
		this.kms = kms;
	}

	public String getLitres() {
		return litres;
	}

	public void setLitres(String litres) {
		this.litres = litres;
	}


	public String getDate() {
		
		return date;
	}

	public void setDate(String date) {
		if(date.trim().length() <= 0 ){
			date = "0000-00-00";
		}
		this.date = date;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	
	
	
	

}
