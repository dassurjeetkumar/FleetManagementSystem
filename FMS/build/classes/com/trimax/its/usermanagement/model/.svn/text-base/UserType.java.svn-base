package com.trimax.its.usermanagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.vehicle.model.OrganisationChart;


@Entity
@Table(name="menu_user_type")
public class UserType {
	
	@Id
	@GeneratedValue
	@Column(name="user_type_id")
	private int user_type_id;
	
	@Column(name="user_type_name")
	private String user_type_name;
	
	@Column(name="user_org")
	private String user_org;

	public int getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}

	public String getUser_type_name() {
		return user_type_name;
	}

	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}

	public String getUser_org() {
		return user_org;
	}

	public void setUser_org(String user_org) {
		this.user_org = user_org;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="usertypedetails")
	private Set<UserDetails> userDetails;

	public Set<UserDetails> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	
}
