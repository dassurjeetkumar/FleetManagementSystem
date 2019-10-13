package com.trimax.its.reportmaster.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;

@Entity
@Table(name="report_master")
public class ReportMaster {
	@Id
	@GeneratedValue
	@Column(name="report_master_id")
	private int report_master_id;
	
	@Column(name="report_master_name")
	private String report_master_name;
	
	@Column(name="status")
	private String status;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;

	@OneToMany(mappedBy="reportMaster")
	private Set<EmailMaster> emailConfig;
	
	public int getReport_master_id() {
		return report_master_id;
	}

	public void setReport_master_id(int report_master_id) {
		this.report_master_id = report_master_id;
	}

	public String getReport_master_name() {
		return report_master_name.trim();
	}

	public void setReport_master_name(String report_master_name) {
		this.report_master_name = report_master_name.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
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
	}

	public Set<EmailMaster> getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(Set<EmailMaster> emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	

}
