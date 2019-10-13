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

import com.trimax.its.ad.model.Advertisement;
import com.trimax.its.pis.model.DisplayUnitModel;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name="c_cid")
	private int id;
	

	@Column(name="c_cname")
	private String name;

	@Column(name="c_code")
	private String code;
	
	@Column(name="c_address")
	private String address;
	
	@Column(name="c_city")
	private String city;
	
	@Column(name="c_state")
	private String state;
	
	@Column(name="c_country")
	private String country;
	
	@Column(name="c_email")
	private String email;
	
	@Column(name="c_website")
	private String website;
	
	@Column(name="c_phone")
	private String phone;
	
	@Column(name="c_cell")
	private String cell; 
	
	@Column(name="c_contact_person_name")
	private String contactPPersonName;
	
	@Column(name="c_contact_person_email")
	private String contactPersonEmail;
	
	@Column(name="c_contact_person_phone")
	private String contactPersonPhone;
	
	@Column(name="c_created_date")
	private String createDate;
	
	@Column(name="updated_date")
	private String updateDate;
	@Column(name="deleted_status")
	private String deletedStatus;
	
	@Column(name="created_by")
	public String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getContactPPersonName() {
		return contactPPersonName;
	}

	public void setContactPPersonName(String contactPPersonName) {
		this.contactPPersonName = contactPPersonName;
	}



	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="c_status")
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="customer")
	private Set<ScheduleDetails> tripDetails;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="cust")
	private Set<Advertisement> advertisement;
	public Set<Advertisement> getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Set<Advertisement> advertisement) {
		this.advertisement = advertisement;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public Set<ScheduleDetails> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(Set<ScheduleDetails> tripDetails) {
		this.tripDetails = tripDetails;
	}
	
	
	
}
