package com.trimax.its.pis.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

import com.trimax.its.ad.model.Advertisement;

@Entity
@Table(name="pis_play_list")
public class PlayList {
	
	@Id
	@GeneratedValue
	@Column(name="play_list_id")
	private int id;
	
	@Column(name="play_list_name")
	private String playListName;
	
	@Transient
	private String advertisementList;
	
	@OneToOne
	@JoinColumn(name="advertisement_id")
	private Advertisement advertisement;
	
	@Column(name="play_start_datetime")
	private String playStartTime;
	
	@Column(name="play_end_datetime")
	private String playEndTime;

	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	
	@Column(name="status")
	private String status;
	
	public String getPlayListName() {
		return playListName;
	}

	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public String getPlayStartTime() {
		return playStartTime;
	}

	public void setPlayStartTime(String playStartTime) {
		this.playStartTime = playStartTime;
	}

	public String getPlayEndTime() {
		return playEndTime;
	}

	public void setPlayEndTime(String playEndTime) {
		this.playEndTime = playEndTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public String getAdvertisementList() {
		return advertisementList;
	}

	public void setAdvertisementList(String advertisementList) {
		this.advertisementList = advertisementList;
	}

}
