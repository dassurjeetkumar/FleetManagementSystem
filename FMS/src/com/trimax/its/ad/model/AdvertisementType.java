package com.trimax.its.ad.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.pis.model.DisplayUnitModel;

@Entity
@Table(name="advertisement_type")
public class AdvertisementType {
	@Id
	@GeneratedValue 
	@Column(name="advertisement_type_id")
	private int advertisement_type_id;
	@Column(name="advertisement_type_name")
	private String advertisement_type_name;
	@Column(name="status")
	private String status;
	@Column(name="notes")
	private String notes;
	@Column(name="created_date")
    private Date created_date;
	@Column(name="created_by")
    private int created_by;
	@Column(name="updated_date")
    private Date updated_date;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="deleted_status")
	private int deleted_status;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="advetisetype")
	private Set<Advertisement> advertisement;
	public Set<Advertisement> getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Set<Advertisement> advertisement) {
		this.advertisement = advertisement;
	}
	public int getAdvertisement_type_id() {
		return advertisement_type_id;
	}
	public void setAdvertisement_type_id(int advertisement_type_id) {
		this.advertisement_type_id = advertisement_type_id;
	}
	public String getAdvertisement_type_name() {
		return advertisement_type_name;
	}
	public void setAdvertisement_type_name(String advertisement_type_name) {
		this.advertisement_type_name = advertisement_type_name;
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
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
}
