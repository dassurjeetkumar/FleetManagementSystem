package com.trimax.its.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="report_relation")
public class ReportRelation {
	@Id
	@GeneratedValue
	@Column(name="report_relation_id")
	private int reportRelationId;
	
	@Column(name="report_header_id")
	private int reportHeaderId;
	
	@Column(name="join_type")
	private String joinType;
	
	@Column(name="join_condition")
	private String joinCondition;
	
	@Column(name="sequence")
	private int sequence;
	
	@Column(name="status")
	private String status;

	public int getReportRelationId() {
		return reportRelationId;
	}

	public void setReportRelationId(int reportRelationId) {
		this.reportRelationId = reportRelationId;
	}

	public int getReportHeaderId() {
		return reportHeaderId;
	}

	public void setReportHeaderId(int reportHeaderId) {
		this.reportHeaderId = reportHeaderId;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getJoinCondition() {
		return joinCondition;
	}

	public void setJoinCondition(String joinCondition) {
		this.joinCondition = joinCondition;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	

}
