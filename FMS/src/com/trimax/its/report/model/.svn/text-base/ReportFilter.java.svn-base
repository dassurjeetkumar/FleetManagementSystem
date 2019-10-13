package com.trimax.its.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="report_filter")
public class ReportFilter {
	@Id
	@GeneratedValue
	@Column(name="report_filter_id")
	private int reportFilterId;
	
	@Column(name="column_name")
	private String columnName;
	
	@Column(name="operator")
	private String operator;
	
	@Column(name="value")
	private String value;
	
	@Column(name="default_column")
	private String defaultColumn;
	
	@Column(name="status")
	private String status;
	
	@Column(name="report_header_id")
	private int reportHeaderId;

	public int getReportFilterId() {
		return reportFilterId;
	}

	public void setReportFilterId(int reportFilterId) {
		this.reportFilterId = reportFilterId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultColumn() {
		return defaultColumn;
	}

	public void setDefaultColumn(String defaultColumn) {
		this.defaultColumn = defaultColumn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getReportHeaderId() {
		return reportHeaderId;
	}

	public void setReportHeaderId(int reportHeaderId) {
		this.reportHeaderId = reportHeaderId;
	}
}
