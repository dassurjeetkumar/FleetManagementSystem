package com.trimax.its.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

	@Entity
	@Table(name="ticket_sub_type")
	public class TicketSubType {

		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="ticket_sub_type_id")
		private int id;
		
		@Column(name="ticket_sub_type_code")
		private String ticket_sub_type_code;
		@Column(name="ticket_sub_type_name")
		private String ticket_sub_type_name;
		@Column(name="ticket_sub_type_name_kannada")
		private String ticket_sub_type_name_kannada;
		@Column(name="ticket_type_id")
		private String ticket_type_id;
		
		@Column(name="status")
		private String status;
		
		@Column(name="notes")
		private String notes;
		
		@Column(name="created_date")
		private Date createdDate;
		
		@Column(name="created_by")
		Integer createdBy;
		
		@Column(name="updated_by")
		Integer updatedBy;
		
		@Column(name="updated_date")
		private Date updatedDate;
		
		@Column(name="deleted_status")
		private int deletedStatus;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTicket_sub_type_code() {
			return ticket_sub_type_code;
		}

		public void setTicket_sub_type_code(String ticket_sub_type_code) {
			this.ticket_sub_type_code = ticket_sub_type_code;
		}

		public String getTicket_sub_type_name() {
			return ticket_sub_type_name;
		}

		public void setTicket_sub_type_name(String ticket_sub_type_name) {
			this.ticket_sub_type_name = ticket_sub_type_name;
		}

		public String getTicket_sub_type_name_kannada() {
			return ticket_sub_type_name_kannada.trim();
		}

		public void setTicket_sub_type_name_kannada(String ticket_sub_type_name_kannada) {
			
			StringBuffer out = new StringBuffer();
			System.out.println("org_name_kannada===>"+ticket_sub_type_name_kannada);
		    for(int i=0; i<ticket_sub_type_name_kannada.length(); i++)
		    {
		        char c = ticket_sub_type_name_kannada.charAt(i);
		        if(c > 127 || c=='"' || c=='<' || c=='>')
		        {
		           out.append("&#"+(int)c+";");
		           System.out.println("hiiii&#"+(int)c+";");
		        }
		        else
		        {
		        	System.out.println("c===="+c);
		            out.append(c);
		        }
		    }
		    System.out.println("outout===>"+out);
			this.ticket_sub_type_name_kannada = out.toString();
			//this.ticket_sub_type_name_kannada = ticket_sub_type_name_kannada.trim();
		}

		public String getTicket_type_id() {
			return ticket_type_id;
		}

		public void setTicket_type_id(String ticket_type_id) {
			this.ticket_type_id = ticket_type_id;
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

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Integer getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}

		public Integer getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(Integer updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public int getDeletedStatus() {
			return deletedStatus;
		}

		public void setDeletedStatus(int deletedStatus) {
			this.deletedStatus = deletedStatus;
		}

		@Transient
		private String subtypekannada;
		
		
		public String getSubTypekannada() {
			String out = "";
			subtypekannada = subtypekannada.replace(" ", "");
			String[] s = subtypekannada.split(";");
			//String[] s = a.split(";");
			try {
			for(int i=0; i<s.length; i++)
		    {
				
				String s1= s[i].replace("#", "");
				s1= s1.replace("&", "");
				s1= s1.trim();
		        char c = (char)Integer.parseInt(s1);
		      System.out.println(s.length+""+c+"----"+s1);
		           out+=""+c;
		        
		    }
			System.out.println("Out-------->"+out);
			}
			catch(Exception e){
				System.out.println("Exception -----> "+e);
			}
			return out;
		}

		public void setSubtypekannada(String subtypekannada) {
			this.subtypekannada = subtypekannada;
		}


}
