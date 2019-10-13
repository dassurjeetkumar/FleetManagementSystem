package com.trimax.doa.ticketing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="ticket_inventory_central_receipt")
	public class TicketInventoryIssue {
		
		@Id
		@GeneratedValue
		@Column(name="id")
		private int id;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		@Column(name="ticket_inventory_det_id")
		private int ticket_inventory_id;
		@Column(name="unit_type")
		private int unittype;
		@Column(name="ticket_inventory_mst_id")
		private int ticketinventorymasterid;
		
		@Column(name="priority")
		private int priority;
		@Column(name="opening_number")
		private int startno;
		@Column(name="closing_number")
		private int endno;
		@Column(name="number_of_passes")
		private int noofpasses;
		@Column(name="pass_day")
		private String pass_day;
		@Column(name="stock_entry")
		private String stock_entry;
		
		public String getStock_entry() {
			return stock_entry;
		}
		public void setStock_entry(String stock_entry) {
			this.stock_entry = stock_entry;
		}
		public String getPass_day() {
			return pass_day;
		}
		public void setPass_day(String pass_day) {
			this.pass_day = pass_day;
		}
		public int getNoofpasses() {
			return noofpasses;
		}
		public void setNoofpasses(int noofpasses) {
			this.noofpasses = noofpasses;
		}
		public int getStartno() {
			return startno;
		}
		public void setStartno(int startno) {
			System.out.println("inside setter");
			this.startno = startno;
		}
		public int getEndno() {
			return endno;
		}
		public void setEndno(int endno) {
			this.endno = endno;
		}
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		
		public int getTicketinventorymasterid() {
			return ticketinventorymasterid;
		}
		public void setTicketinventorymasterid(int ticketinventorymasterid) {
			this.ticketinventorymasterid = ticketinventorymasterid;
		}

		@Column(name="unit_name")
		private int unitname;
		@Column(name="denomination_type_manual_id")
		private int denoimination_type;
		@Column(name="ticket_type_manual_id")
		private int ticket_type_manual_id;
		public int getTicket_type_manual_id() {
			return ticket_type_manual_id;
		}
		public void setTicket_type_manual_id(int ticket_type_manual_id) {
			this.ticket_type_manual_id = ticket_type_manual_id;
		}

		@Column(name="number_of_books")
		private int noofbooks;
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
		
		@Column(name="current_status")
		private String currentstatus;
		public String getCurrentstatus() {
			return currentstatus;
		}
		public void setCurrentstatus(String currentstatus) {
			this.currentstatus = currentstatus;
		}
		@Column(name="Denomination_key")
		private String key_number;
		
		/*@Column(name="closing_number")
		private Integer closing_number;*/
		@Column(name="number_of_tickets")
		private int number_of_tickets;
		@Column(name="value")
		private int value;
		@Column(name="partial_book")
	    private String partialbook;
	    
		public String getPartialbook() {
			return partialbook;
		}
		public void setPartialbook(String partialbook) {
			this.partialbook = partialbook;
		}

		@Column(name="status")
		private String status;
		@Column(name="created_by")
		private int created_by;
		@Column(name="created_date")
		private Date created_date;
		private boolean isEmpty(String str) {
			 
	        return str == null ? true:(str.equals("") ? true:false);
	    }
		public int getTicket_inventory_id() {
			return ticket_inventory_id;
		}
		public void setTicket_inventory_id(int ticket_inventory_id) {
			this.ticket_inventory_id = ticket_inventory_id;
		}
		public int getDenoimination_type() {
			
			return denoimination_type;
		}
		public void setDenoimination_type(String denoimination_type) {
			if(isEmpty(denoimination_type))
			{
				this.denoimination_type=0;
			}
			else
			{
				if(isInteger(denoimination_type))
				{
					this.denoimination_type =Integer.parseInt(denoimination_type);
				}
				else	
				{
					this.denoimination_type=-1;
				}
		}
			
			
		}
		public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
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
		/*public Integer getOpening_number() {
			return opening_number;
		}
		public void setOpening_number(String opening_number) {
			System.out.println("inside setter after converted to integer"+opening_number);
			if(isEmpty(opening_number))
			{
				this.opening_number=0;
			}
			else
			{
				if(isInteger(opening_number))
				{
					System.out.println("after converted to integer");
					this.opening_number =Integer.parseInt(opening_number);
				}
			    else	
				{
					//this.opening_number=-1;
				}
		}
			
		}*/
		/*public Integer getClosing_number() {
			return closing_number;
		}
		public void setClosing_number(String closing_number) {
			if(isEmpty(closing_number))
			{
				this.closing_number=0;
			}
			else
			{
				if(isInteger(closing_number))
				{
					this.closing_number =Integer.parseInt(closing_number);
				}
				else	
				{
					//this.closing_number=-1;
				}
		}
			
		}*/
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
