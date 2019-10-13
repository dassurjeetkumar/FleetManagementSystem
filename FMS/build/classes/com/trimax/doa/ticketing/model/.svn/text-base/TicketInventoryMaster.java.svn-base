package com.trimax.doa.ticketing.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_inventory_master")
public class TicketInventoryMaster {
	
	@Id
	@GeneratedValue
	@Column(name="ticket_inventory_mst_id")
	private int ticketinventorymasterid;
	
	@Column(name="unit_type")
	private int unittype;
	
	@Column(name="updated_by")
	private int updatedby;
	
	@Column(name="updated_date")
	private int updateddate;
	
	@Column(name="ticket_type_manual_id")
	private int ticket_type_manual_id;
	
	@Column(name="number_of_passes")
	private int noofpasses;
	
	@Column(name="pass_day")
	private String pass_day;
	
	@Column(name="unit_name")
	private int unitname;
	
	@Column(name="denoimination_type_manual_id")
	private int denoimination_type;
	
	@Column(name="number_of_books")
	private int noofbooks;
	
	@Column(name="current_status")
	private String currentstatus;
	
	@Column(name="Denomination_key")
	private String key_number;
	
	@Column(name="opening_number")
	private Integer opening_number;
	
	@Column(name="closing_number")
	private Integer closing_number;
	
	@Column(name="number_of_tickets")
	private int number_of_tickets;
	
	@Column(name="value")
	private int value;
	
	@Column(name="status")
	private String status;
	
	@Column(name="inventory_temp_ref_id")
	private int tempRefId;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="priority")
	private int priority;
	
	public int getNoofpasses() {
		return noofpasses;
	}
	public void setNoofpasses(int noofpasses) {
		this.noofpasses = noofpasses;
	}
	public String getPass_day() {
		return pass_day;
	}
	public void setPass_day(String pass_day) {
		this.pass_day = pass_day;
	}

	public int getTicket_type_manual_id() {
		return ticket_type_manual_id;
	}
	public void setTicket_type_manual_id(int ticket_type_manual_id) {
		this.ticket_type_manual_id = ticket_type_manual_id;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public int getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(int updateddate) {
		this.updateddate = updateddate;
	}
	public int getTicketinventorymasterid() {
		return ticketinventorymasterid;
	}
	public void setTicketinventorymasterid(int ticketinventorymasterid) {
		this.ticketinventorymasterid = ticketinventorymasterid;
	}
	public int getNoofbooks() {
		return noofbooks;
	}
	public void setNoofbooks(int noofbooks) {
		this.noofbooks = noofbooks;
	}
	public int getUnittype() {
		return unittype;
	}
	public void setUnittype(int unittype) {
		this.unittype = unittype;
	}
	public int getUnitname() {
		return unitname;
	}
	public void setUnitname(int unitname) {
		this.unitname = unitname;
	}
	public String getCurrentstatus() {
		return currentstatus;
	}
	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	private boolean isEmpty(String str) {
		 
        return str == null ? true:(str.equals("") ? true:false);
    }
	
	public int getDenoimination_type() {
		
		return denoimination_type;
	}
	public void setDenoimination_type(String denoimination_type) {
		if(isEmpty(denoimination_type)){
			this.denoimination_type=0;
		}
		else{
			if(isInteger(denoimination_type)){
				this.denoimination_type =Integer.parseInt(denoimination_type);
			}else{
				this.denoimination_type=-1;
			}
		}
		
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    }catch(NumberFormatException e){ 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	
	public String getKey_number() {
		return key_number;
	}
	public void setKey_number(String key_number) {
		this.key_number = key_number;
	}
	public Integer getOpening_number() {
		return opening_number;
	}
	public void setOpening_number(String opening_number) {
		/*if(isEmpty(opening_number))
		{
			this.opening_number=0;
		}
		else
		{*/
			if(isInteger(opening_number)){
				this.opening_number =Integer.parseInt(opening_number);
			}else{				
				this.opening_number=-1;
			}
	/*}
		*/
	}
	public Integer getClosing_number() {
		return closing_number;
	}
	public void setClosing_number(String closing_number) {
		/*if(isEmpty(closing_number))
		{
			this.closing_number=0;
		}
		else
		{*/
			if(isInteger(closing_number)){
				this.closing_number =Integer.parseInt(closing_number);
			}else{
				this.closing_number=-1;
			}
	/*}*/
		
	}
	public int getNumber_of_tickets() {
		return number_of_tickets;
	}
	public void setNumber_of_tickets(int number_of_tickets) {
		this.number_of_tickets = number_of_tickets;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getTempRefId() {
		return tempRefId;
	}
	public void setTempRefId(int tempRefId) {
		this.tempRefId = tempRefId;
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
	
}
