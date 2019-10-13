package com.trimax.its.fare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.route.model.Route;
@Entity
@Table(name="Toll_Pass_Ticket")
public class TollPassTicketModel {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		@ManyToOne
		@JoinColumn(name = "Route_no",nullable=true)
		private Route routeno;
		@Column 
		private String toll_name;
		
		@Column 
		private String Amount;
		@Column 
		private String Notes;
		@Column 
		private int deleted_status;
		@Column 
		private int inserted_by;
		@Column 
		private Date inserted_date;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public Route getRouteno() {
			return routeno;
		}
		public void setRouteno(Route routeno) {
			this.routeno = routeno;
		}
		public String getAmount() {
			return Amount;
		}
		public void setAmount(String amount) {
			Amount = amount;
		}
		public String getNotes() {
			return Notes;
		}
		public void setNotes(String notes) {
			Notes = notes;
		}
		public int getDeleted_status() {
			return deleted_status;
		}
		public void setDeleted_status(int deleted_status) {
			this.deleted_status = deleted_status;
		}
		public int getInserted_by() {
			return inserted_by;
		}
		public void setInserted_by(int inserted_by) {
			this.inserted_by = inserted_by;
		}
		public Date getInserted_date() {
			return inserted_date;
		}
		public void setInserted_date(Date inserted_date) {
			this.inserted_date = inserted_date;
		}
		public String getToll_name() {
			return toll_name;
		}
		public void setToll_name(String toll_name) {
			this.toll_name = toll_name.trim();
		}
		
		
}
