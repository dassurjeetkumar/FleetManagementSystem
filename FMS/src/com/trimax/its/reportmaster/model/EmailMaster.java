package com.trimax.its.reportmaster.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="email_master")
public class EmailMaster {
	@Id
	@GeneratedValue
	@Column(name="em_id")
	private int email_master_id;
	
	@Column(name="email_id")
	private String email_id;
	
	@Column(name="email_type")
	private String email_type;
	
	@Column(name="email_sync")
	private String email_sync;
	
	/*@Column(name="report_master_id")
	private int report_master_id;
	*/
	/*@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;*/
	
	@ManyToOne
	@JoinColumn(name="report_master_id")
	private ReportMaster reportMaster;

	public int getEmail_master_id() {
		return email_master_id;
	}

	public void setEmail_master_id(int email_master_id) {
		this.email_master_id = email_master_id;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getEmail_type() {
		return email_type;
	}

	public void setEmail_type(String email_type) {
		this.email_type = email_type;
	}

	public String getEmail_sync() {
		return email_sync;
	}

	public void setEmail_sync(String email_sync) {
		this.email_sync = email_sync;
	}

	/*public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}*/

	public ReportMaster getReportMaster() {
		return reportMaster;
	}

	public void setReportMaster(ReportMaster reportMaster) {
		this.reportMaster = reportMaster;
	}

	/*public int getReport_master_id() {
		return report_master_id;
	}

	public void setReport_master_id(int report_master_id) {
		this.report_master_id = report_master_id;
	}
	
	*/
	
}
