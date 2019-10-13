package com.trimax.its.etm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="docket_info")
public class docketinfo {
	
	@Id
	@GeneratedValue
	@Column(name="docket_no")
	private int docket_no;
	
	@Column(name="issuer_name")
	private String issuer_name;
	
	@Column(name="receiver_name")
	private String receiver_name;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="depot_id")
	private int depot_id;
	
	public int getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(int depot_id) {
		this.depot_id = depot_id;
	}

	public int getDocket_no() {
		return docket_no;
	}

	public void setDocket_no(int docket_no) {
		this.docket_no = docket_no;
	}

	public String getIssuer_name() {
		return issuer_name;
	}

	public void setIssuer_name(String issuer_name) {
		this.issuer_name = issuer_name;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
