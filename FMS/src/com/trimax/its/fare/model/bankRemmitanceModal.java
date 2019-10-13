package com.trimax.its.fare.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="Bank_Remmitance_Details")
public class bankRemmitanceModal {
		
		@Id
		@GeneratedValue
		@Column(name="id")
		private int id;
		
		@Column(name="Depot")
		private Integer depotno;
		
		@Column(name="Bank_name")
		private String bankname;
		
		@Column(name="Account_number")
		private String accnumber;
		
		@Column(name="Division")
		private String division;
		
		@Column(name="Address")
		private String addr;
		
		@Column(name="inserted_by")
		private String insertedby;
		
		@Column(name="inserted_date")
		private Date inserteddate;
		
		@Column(name="updated_by")
		private String updatedby;
		
		@Column(name="updated_date")
		private Date updateddate;
		
		@Column(name="deleted_status")
		private Integer deletedstatus;
		
		public Integer getDeletedstatus() {
			return deletedstatus;
		}

		public void setDeletedstatus(Integer deletedstatus) {
			this.deletedstatus = deletedstatus;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		

		public Integer getDepotno() {
			return depotno;
		}

		public void setDepotno(Integer depotno) {
			this.depotno = depotno;
		}

		public String getBankname() {
			return bankname;
		}

		public void setBankname(String bankname) {
			this.bankname = bankname;
		}

		public String getAccnumber() {
			return accnumber;
		}

		public void setAccnumber(String accnumber) {
			this.accnumber = accnumber;
		}

		public String getDivision() {
			return division;
		}

		public void setDivision(String division) {
			this.division = division;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

		public String getInsertedby() {
			return insertedby;
		}

		public void setInsertedby(String insertedby) {
			this.insertedby = insertedby;
		}

		public Date getInserteddate() {
			return inserteddate;
		}

		public void setInserteddate(Date inserteddate) {
			this.inserteddate = inserteddate;
		}

		public String getUpdatedby() {
			return updatedby;
		}

		public void setUpdatedby(String updatedby) {
			this.updatedby = updatedby;
		}

		public Date getUpdateddate() {
			return updateddate;
		}

		public void setUpdateddate(Date updateddate) {
			this.updateddate = updateddate;
		}
		
		

}
