package com.trimax.doa.ticketing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.OrgType;

@Entity
@Table(name="denomination_type_manual")
public class DenominationType {
	@Id
	@GeneratedValue
	@Column(name="denomination_type_manual_id")
	private int denomination_type_id;
	@Column(name="denomination_type_manual")
	private String denomtype;
	@Column(name="service_tax")
	private String servicetax;
	
	/*@Column(name="denomination_series")
	private String denomination_series;*/
	/*public String getDenomination_series() {
		return denomination_series;
	}
	public void setDenomination_series(String denomination_series) {
		this.denomination_series = denomination_series;
	}*/


	

	@ManyToOne
	@JoinColumn(name="ticket_type_manual_id")
	private TicketTypeManual ticketTypeManual;

	public String getServicetax() {
		return servicetax;
	}
	public void setServicetax(String servicetax) {
		this.servicetax = servicetax;
	}
	public TicketTypeManual getTicketTypeManual() {
		return ticketTypeManual;
	}
	public void setTicketTypeManual(TicketTypeManual ticketTypeManual) {
		this.ticketTypeManual = ticketTypeManual;
	}

	
	public String getDenomtype() {
		return denomtype;
	}
	public void setDenomtype(String denomtype) {
		this.denomtype = denomtype;
	}

	@Column
	private float percentage;

	@Column(name="lumpsum_amount")
	private int amount;
	
	@Column(name="status")
	private String status;
	@Column(name="notes")
	private String notes;
	@Column(name="created_by")
	private int createdby;
	@Column(name="created_date")
	private Date createddate;
	@Column(name="updated_by")
	private int updatedby;
	@Column(name="updated_date")
	private Date updateddate;
	@Column(name="deleted_status")
	private int deletedstatus;
	public int getDenomination_type_id() {
		return denomination_type_id;
	}
	public void setDenomination_type_id(int denomination_type_id) {
		this.denomination_type_id = denomination_type_id;
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
	public int getDeletedstatus() {
		return deletedstatus;
	}
	public void setDeletedstatus(int deletedstatus) {
		this.deletedstatus = deletedstatus;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	
}
