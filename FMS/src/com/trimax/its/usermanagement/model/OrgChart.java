package com.trimax.its.usermanagement.model;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.trimax.its.vehicle.model.Vehicle;

@Entity
@Table(name="org_chart")
public class OrgChart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int org_chart_id;
	
	
	@Column(name="parent_id")
	private int parent_id;
	
	@Column(name="org_name")
	private String org_name;
	
	@Column(name="org_name_kannada")
	private String org_name_kannada;
	
	@Column(name="org_type_id")
	private int org_type_id;
	
	@Column(name="org_code")
	private String org_code;
	
	@Column(name="org_code_kannada")
	private String org_code_kannada;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="website")
	private String website;
	
	@Column(name="email")
	private String email;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="latitude")
	private int latitude;
	
	@Column(name="logitude")
	private int logitude;
	
	@Column(name="type_of_division")
	private String type_of_division;
	
	@Column(name="contact_person")
	private String contact_person;
	
	@Column(name="geo_fencedata")
	private String geo_fencedata;
	
	@Column(name="geo_area")
	private String geo_area;
	
	@Column(name="busstation_depot")
	private String busstation_depot;
	
	@Column(name="allocate_disp_units")
	private String allocate_disp_units;
	
	@Column(name="busstation_type_ttmc")
	private String busstation_type_ttmc;
	
	@Column(name="created_by")
	private int created_by;
	

	@Column(name="created_date")
	private String created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	
	@Column(name="updated_date")
	private String updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="type_of_depot")
	private String type_of_depot;
	
	@Column(name="point_id")
	private String point_id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgchart")
	private Set<Employee> employee;

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	public int getOrg_chart_id() {
		return org_chart_id;
	}

	public void setOrg_chart_id(int org_chart_id) {
		this.org_chart_id = org_chart_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_name_kannada() {
		return org_name_kannada;
	}

	public void setOrg_name_kannada(String org_name_kannada) {
		this.org_name_kannada = org_name_kannada;
	}

	public int getOrg_type_id() {
		return org_type_id;
	}

	public void setOrg_type_id(int org_type_id) {
		this.org_type_id = org_type_id;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getOrg_code_kannada() {
		return org_code_kannada;
	}

	public void setOrg_code_kannada(String org_code_kannada) {
		this.org_code_kannada = org_code_kannada;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLogitude() {
		return logitude;
	}

	public void setLogitude(int logitude) {
		this.logitude = logitude;
	}

	public String getType_of_division() {
		return type_of_division;
	}

	public void setType_of_division(String type_of_division) {
		this.type_of_division = type_of_division;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getGeo_fencedata() {
		return geo_fencedata;
	}

	public void setGeo_fencedata(String geo_fencedata) {
		this.geo_fencedata = geo_fencedata;
	}

	public String getGeo_area() {
		return geo_area;
	}

	public void setGeo_area(String geo_area) {
		this.geo_area = geo_area;
	}

	public String getBusstation_depot() {
		return busstation_depot;
	}

	public void setBusstation_depot(String busstation_depot) {
		this.busstation_depot = busstation_depot;
	}

	public String getAllocate_disp_units() {
		return allocate_disp_units;
	}

	public void setAllocate_disp_units(String allocate_disp_units) {
		this.allocate_disp_units = allocate_disp_units;
	}

	public String getBusstation_type_ttmc() {
		return busstation_type_ttmc;
	}

	public void setBusstation_type_ttmc(String busstation_type_ttmc) {
		this.busstation_type_ttmc = busstation_type_ttmc;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public String getType_of_depot() {
		return type_of_depot;
	}

	public void setType_of_depot(String type_of_depot) {
		this.type_of_depot = type_of_depot;
	}

	public String getPoint_id() {
		return point_id;
	}

	public void setPoint_id(String point_id) {
		this.point_id = point_id;
	}
	
	
}
