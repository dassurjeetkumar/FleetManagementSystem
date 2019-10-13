package com.trimax.doa.ticketing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_inventory_logs")
public class TicketInventoryLog {
	@Id
	@GeneratedValue
	@Column(name = "ticket_inventory_logs_id")
	private int ticket_inventory_logs_id;
	@Column(name = "ticket_invoice_mast_id")
	private int ticket_invoice_mast_id;
	@Column(name = "invoice_status")
	private String invoice_status;
	@Column(name = "transaction_on_type")
	private String transaction_on_type;
	@Column(name = "action_unit")
	private String action_unit;
	@Column(name = "action_by")
	private String action_by;
	@Column(name = "created_date")
	private Date created_date;
	@Column(name = "created_by")
	private String created_by;

	public int getTicket_inventory_logs_id() {
		return ticket_inventory_logs_id;
	}

	public void setTicket_inventory_logs_id(int ticket_inventory_logs_id) {
		this.ticket_inventory_logs_id = ticket_inventory_logs_id;
	}

	public int getTicket_invoice_mast_id() {
		return ticket_invoice_mast_id;
	}

	public void setTicket_invoice_mast_id(int ticket_invoice_mast_id) {
		this.ticket_invoice_mast_id = ticket_invoice_mast_id;
	}

	public String getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(String invoice_status) {
		this.invoice_status = invoice_status;
	}

	public String getTransaction_on_type() {
		return transaction_on_type;
	}

	public void setTransaction_on_type(String transaction_on_type) {
		this.transaction_on_type = transaction_on_type;
	}

	public String getAction_unit() {
		return action_unit;
	}

	public void setAction_unit(String action_unit) {
		this.action_unit = action_unit;
	}

	public String getAction_by() {
		return action_by;
	}

	public void setAction_by(String action_by) {
		this.action_by = action_by;
	}

	

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
}
