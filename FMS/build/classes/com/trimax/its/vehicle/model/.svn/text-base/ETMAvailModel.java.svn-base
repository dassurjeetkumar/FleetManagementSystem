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

import com.trimax.its.device.model.Device;
import com.trimax.its.transport.model.Schedule;

@Entity
@Table(name="device_org")
public class ETMAvailModel {

	
	@Column(name="device_serial_number")
	private String Etm_Serial_number ;
	

	@Column(name="serial_number")
	private String sim_serial_number;

	@Column(name="org_name")
	private String org_name;
	
	public String getEtm_Serial_number() {
		return Etm_Serial_number;
	}

	public void setEtm_Serial_number(String etm_Serial_number) {
		Etm_Serial_number = etm_Serial_number;
	}

	public String getSim_serial_number() {
		return sim_serial_number;
	}

	public void setSim_serial_number(String sim_serial_number) {
		this.sim_serial_number = sim_serial_number;
	}

	

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getSim_number() {
		return sim_number;
	}

	public void setSim_number(String sim_number) {
		this.sim_number = sim_number;
	}

	@Column(name="depot_name")
	private String depot_name;
	
	@Column(name="phone_number")
	private String sim_number;
	

	public String getDepot_name() {
		return depot_name;
	}

	public void setDepot_name(String depot_name) {
		this.depot_name = depot_name;
	}
	

	
}
