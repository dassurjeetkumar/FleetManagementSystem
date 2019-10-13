package com.trimax.its.etm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="etm_device_history")
public class EtmDeviceHistory {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="etm_number")
	private int etm_number;
	
	@Column(name="division_id")
	private int division_id;
	
	@Column(name="depot_id")
	private int depot_id;
	
	@Column(name="etm_issue")
	private int etm_issue;
	@Column(name="docket_no")
	private int docket_no;
	
	@Column(name="etm_created_date")
	private String etm_created_date;
	
	@Column(name="etm_pickup_by_fme")
	private Date etm_pickup_by_fme;
	
	@Column(name="etm_resolved_by_fme	")
	private Date etm_resolved_by_fme	;
	
	@Column(name="etm_pickup_by_verifone")
	private Date etm_pickup_by_verifone;
	
	@Column(name="etm_resolved_by_verifone")
	private Date etm_resolved_by_verifone;
	
	@Column(name="etm_received_by_fme")
	private Date etm_received_by_fme;
	
	@Column(name="etm_received_date")
	private Date etm_received_date;
	
	@Column(name="resolved_reason")
	private String resolved_reason;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="fme_id")
	private int fme_id;
	
	@Column(name="verifone_id")
	private int verifone_id;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	
	@Column(name="updated_date")
	private Date updated_date;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getEtm_number() {
		return etm_number;
	}


	public void setEtm_number(int etm_number) {
		this.etm_number = etm_number;
	}


	public int getDocket_no() {
		return docket_no;
	}


	public void setDocket_no(int docket_no) {
		this.docket_no = docket_no;
	}


	public int getDivision_id() {
		return division_id;
	}


	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}


	public int getDepot_id() {
		return depot_id;
	}


	public void setDepot_id(int depot_id) {
		this.depot_id = depot_id;
	}


	public int getEtm_issue() {
		return etm_issue;
	}


	public void setEtm_issue(int etm_issue) {
		this.etm_issue = etm_issue;
	}


	public String getEtm_created_date() {
		return etm_created_date;
	}


	public void setEtm_created_date(String etm_created_date) {
		this.etm_created_date = etm_created_date;
	}

	
	public Date getEtm_pickup_by_fme() {
		return etm_pickup_by_fme;
	}


	public void setEtm_pickup_by_fme(Date etm_pickup_by_fme) {
		this.etm_pickup_by_fme = etm_pickup_by_fme;
	}


	public Date getEtm_resolved_by_fme() {
		return etm_resolved_by_fme;
	}


	public void setEtm_resolved_by_fme(Date etm_resolved_by_fme) {
		this.etm_resolved_by_fme = etm_resolved_by_fme;
	}


	public Date getEtm_pickup_by_verifone() {
		return etm_pickup_by_verifone;
	}


	public void setEtm_pickup_by_verifone(Date etm_pickup_by_verifone) {
		this.etm_pickup_by_verifone = etm_pickup_by_verifone;
	}


	public Date getEtm_resolved_by_verifone() {
		return etm_resolved_by_verifone;
	}


	public void setEtm_resolved_by_verifone(Date etm_resolved_by_verifone) {
		this.etm_resolved_by_verifone = etm_resolved_by_verifone;
	}


	public Date getEtm_received_by_fme() {
		return etm_received_by_fme;
	}


	public void setEtm_received_by_fme(Date etm_received_by_fme) {
		this.etm_received_by_fme = etm_received_by_fme;
	}


	public Date getEtm_received_date() {
		return etm_received_date;
	}


	public void setEtm_received_date(Date etm_received_date) {
		this.etm_received_date = etm_received_date;
	}


	public String getResolved_reason() {
		return resolved_reason;
	}


	public void setResolved_reason(String resolved_reason) {
		this.resolved_reason = resolved_reason;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public int getCreated_by() {
		return created_by;
	}


	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}


	public Date getCreated_date() {
		return created_date;
	}


	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}


	public int getUpdated_by() {
		return updated_by;
	}


	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}


	public Date getUpdated_date() {
		return updated_date;
	}


	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	
}
