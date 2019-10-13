package com.trimax.its.report.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="report_header")
public class ReportHeader {
	
	@Id
	@GeneratedValue
	@Column(name="report_header_id")
	private int reportHeaderId;
	
	@Column(name="report_title")
	private String reportTitle;
	
	@Column(name="table_name")
	private String reportTableName;
	
	@Column(name="status")
	private String status;

	@Column(name="report_code")
	private String reportCode;
	
	public int getReportHeaderId() {
		return reportHeaderId;
	}

	public void setReportHeaderId(int reportHeaderId) {
		this.reportHeaderId = reportHeaderId;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportTableName() {
		return reportTableName;
	}

	public void setReportTableName(String reportTableName) {
		this.reportTableName = reportTableName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	
}
