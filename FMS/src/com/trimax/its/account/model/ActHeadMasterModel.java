package com.trimax.its.account.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.trimax.its.fare.model.RevenueType;

@Entity
@Table(name="account_head")
public class ActHeadMasterModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column (name="account_head_id")
	private int acthead_id;	
	@Column (name="account_head_number")
	private String acthead_name;
	
	@ManyToOne
	@JoinColumn(name="revenue_type_id")
	private RevenueType revenuetype;
	
	@Column (name="status")
	private String status;	
	@Column (name="notes")
	private String notes;
	@Column (name="remarks")
	private String remarks;
	@Column (name="CR_DB")
	private String CR_DB;
	@Column (name="deleted_status")
	private int deletedstatus;
	@Column (name="created_by")
	private int createdby;
	@Column (name="created_date")
	private Date createddate;
	@Column (name="updated_by")
	private int updatedby;
	@Column (name="updated_date")
	private Date updateddate;
	
	
	
	
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCR_DB() {
		return CR_DB;
	}
	public void setCR_DB(String cR_DB) {
		CR_DB = cR_DB;
	}
	public int getActhead_id() {
		return acthead_id;
	}
	public void setActhead_id(int acthead_id) {
		this.acthead_id = acthead_id;
	}
	public String getActhead_name() {
		return acthead_name;
	}
	public void setActhead_name(String acthead_name) {
		this.acthead_name = acthead_name;
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
	public int getDeletedstatus() {
		return deletedstatus;
	}
	public void setDeletedstatus(int deletedstatus) {
		this.deletedstatus = deletedstatus;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	
	
	public RevenueType getRevenuetype() {
		return revenuetype;
	}
	public void setRevenuetype(RevenueType revenuetype) {
		this.revenuetype = revenuetype;
	}
	
	
	
	
	

}
