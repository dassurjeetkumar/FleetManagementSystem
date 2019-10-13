package com.trimax.its.etm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="etm_device_issue")
public class EtmDeviceIssue {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmtDeviceHistoryId() {
		return emtDeviceHistoryId;
	}

	public void setEmtDeviceHistoryId(int emtDeviceHistoryId) {
		this.emtDeviceHistoryId = emtDeviceHistoryId;
	}

	public int getEtmIssueId() {
		return etmIssueId;
	}

	public void setEtmIssueId(int etmIssueId) {
		this.etmIssueId = etmIssueId;
	}

	public String getIssueCreatedDate() {
		return issueCreatedDate;
	}

	public void setIssueCreatedDate(String pickupdate) {
		this.issueCreatedDate = pickupdate;
	}

	@Column(name="etm_device_history_id")
	private int emtDeviceHistoryId;
	@Column(name="etm_issue_id")
	private int etmIssueId;
	
	@Column(name="issue_created_date")
	private String issueCreatedDate;
}
