package com.trimax.its.transport.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="group_type")
public class GroupType {
	
	@Id
	@GeneratedValue
	@Column(name="group_type_id")
	private int group_type_id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="group_type_id")
	private Set<BusStopGroup> bus_group_type_id;
	
	@Column(name="group_type_name")
	private String group_type_name;

	
	
	public String getGroup_type_name() {
		return group_type_name;
	}

	public void setGroup_type_name(String group_type_name) {
		this.group_type_name = group_type_name;
	}

	public int getGroup_type_id() {
		return group_type_id;
	}

	public void setGroup_type_id(int group_type_id) {
		this.group_type_id = group_type_id;
	}

	public Set<BusStopGroup> getBus_group_type_id() {
		return bus_group_type_id;
	}

	public void setBus_group_type_id(Set<BusStopGroup> bus_group_type_id) {
		this.bus_group_type_id = bus_group_type_id;
	}
	
	

}
