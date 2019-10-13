package com.trimax.its.usermanagement.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.memo.model.Memo;
import com.trimax.its.training.model.Training;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.vehicle.model.OrganisationChart;



@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue
	@Column(name="EMPLOYEE_ID")
	private int employee_id;
	
	@Column(name="EMPLOYEE_NAME")
	private String employee_name;
	
	@Column(name="EMPLOYEE_DESIGNATION")
	private String EMPLOYEE_DESIGNATION;
	
	@Column(name="TOKEN")
	private String TOKEN;
	
	@Column(name="PF")
	private String PF;
	
	@Column(name="GENDER")
	private String GENDER;
	
	@Column(name="WORKING_DEPOT")
	private String WORKING_DEPOT;
	
	@Column(name="WORKING_DEPT")
	private String WORKING_DEPT;
	
	@Column(name="DL_EXPIRY_DT")
	private Date DL_EXPIRY_DT;

	@Column(name="DRIVING_LC_NO")
	private String DRIVING_LC_NO;
	
	@Column(name="DOB")
	private Date DOB;
	
	@Column(name="CELL_PHONE")
	private String CELL_PHONE;
	
	
	
	@Column(name="EMAIL")
	private String EMAIL;
	
	@Column(name="RETIREMENT_DT")
	private Date RETIREMENT_DT;
	
	@Column(name="FATHER_NAME")
	private String FATHER_NAME;
	
	@Column(name="ADDRESS")
	private String ADDRESS;
	
	@Column(name="EMPLOYEE_TYPE_NAME")
	private String EMPLOYEE_TYPE_NAME;
	
	@Column(name="DIVISION_ID")
	private Integer DIVISION_ID;
	
	@Column(name="Driver_RENEWAL_DT", nullable = true)
	private Date Driver_RENEWAL_DT;
	@Column(name="Conductor_RENEWAL_DT", nullable = true)
	private Date Conductor_RENEWAL_DT;

	@Column(name="ADHAR_CARD_NO")
	private String ADHAR_CARD_NO;
	
//	@Column(name="WORKING_DESIGNATION")
//	private String WORKING_DESIGNATION;
	
	public Date getDriver_RENEWAL_DT() {
		return Driver_RENEWAL_DT;
	}

	public void setDriver_RENEWAL_DT(Date driver_RENEWAL_DT) {
		Driver_RENEWAL_DT = driver_RENEWAL_DT;
	}

	public Date getConductor_RENEWAL_DT() {
		return Conductor_RENEWAL_DT;
	}

	public void setConductor_RENEWAL_DT(Date conductor_RENEWAL_DT) {
		Conductor_RENEWAL_DT = conductor_RENEWAL_DT;
	}

	public String getADHAR_CARD_NO() {
		return ADHAR_CARD_NO;
	}

	public void setADHAR_CARD_NO(String aDHAR_CARD_NO) {
		ADHAR_CARD_NO = aDHAR_CARD_NO;
	}

	public Integer getDIVISION_ID() {
		return DIVISION_ID;
	}

	public void setDIVISION_ID(Integer dIVISION_ID) {
		DIVISION_ID = dIVISION_ID;
	}

	public String getEMPLOYEE_TYPE_NAME() {
		return EMPLOYEE_TYPE_NAME;
	}

	public void setEMPLOYEE_TYPE_NAME(String eMPLOYEE_TYPE_NAME) {
		EMPLOYEE_TYPE_NAME = eMPLOYEE_TYPE_NAME;
	}



	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "WORKING_DESIGNATION")
	private DesignationType  WORKING_DESIGNATION;
	
	



	public DesignationType getWORKING_DESIGNATION() {
		return WORKING_DESIGNATION;
	}

	public void setWORKING_DESIGNATION(DesignationType wORKING_DESIGNATION) {
		WORKING_DESIGNATION = wORKING_DESIGNATION;
	}



	@Column(name="STATUS")
	private String STATUS;
	
	@Column(name="DOA")
	private Date  DOA;
	
	@Transient
	private String DL_Exp_Date;
	@Transient
	private String Driver_Renewal_Date;
	@Transient
	private String Conductor_Renewal_Date;
	public String getDriver_Renewal_Date() {
		return Driver_Renewal_Date;
	}

	public void setDriver_Renewal_Date(String driver_Renewal_Date) {
		Driver_Renewal_Date = driver_Renewal_Date;
	}

	public String getConductor_Renewal_Date() {
		return Conductor_Renewal_Date;
	}

	public void setConductor_Renewal_Date(String conductor_Renewal_Date) {
		Conductor_Renewal_Date = conductor_Renewal_Date;
	}



	@Transient
	private String D_O_B;
	@Transient
	private String D_O_A;
	@Transient
	private String RetirementDate;
	@Transient
	private String Con_Lic_Exp_Date;
	
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "org_type_id")
	private OrgType  orgtype;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "org_chart_id")
	private OrganisationChart orgchart;
	
	@Column(name="created_by")
	private String created_by;
	


	@Column(name="created_date")
	private Date created_date;
	@Column(name="updated_by")
	private String updated_by;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="effective_end_date")
	private Date effective_end_date;
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empid")
	private Set<UserDetails> userDetails;*/
	
	

	


	

	public int getEmployee_id() {
		return employee_id;
	}

	public OrgType getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(OrgType orgtype) {
		this.orgtype = orgtype;
	}

	public OrganisationChart getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEMPLOYEE_DESIGNATION() {
		return EMPLOYEE_DESIGNATION;
	}

	public void setEMPLOYEE_DESIGNATION(String eMPLOYEE_DESIGNATION) {
		EMPLOYEE_DESIGNATION = eMPLOYEE_DESIGNATION;
	}

	public String getTOKEN() {
		return TOKEN.trim();
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN.trim();
	}

	public String getPF() {
		return PF;
	}

	public void setPF(String pF) {
		PF = pF;
	}

	public String getGENDER() {
		return GENDER;
	}

	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}

	public String getWORKING_DEPOT() {
		return WORKING_DEPOT;
	}

	public void setWORKING_DEPOT(String wORKING_DEPOT) {
		WORKING_DEPOT = wORKING_DEPOT;
	}

	public String getWORKING_DEPT() {
		return WORKING_DEPT;
	}

	public void setWORKING_DEPT(String wORKING_DEPT) {
		WORKING_DEPT = wORKING_DEPT;
	}

	public Date getDL_EXPIRY_DT() {
		return DL_EXPIRY_DT;
	}

	public void setDL_EXPIRY_DT(Date dL_EXPIRY_DT) {
		DL_EXPIRY_DT = dL_EXPIRY_DT;
	}

	public String getDRIVING_LC_NO() {
		return DRIVING_LC_NO;
	}

	public void setDRIVING_LC_NO(String dRIVING_LC_NO) {
		DRIVING_LC_NO = dRIVING_LC_NO;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getCELL_PHONE() {
		return CELL_PHONE;
	}

	public void setCELL_PHONE(String cELL_PHONE) {
		CELL_PHONE = cELL_PHONE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Date getRETIREMENT_DT() {
		return RETIREMENT_DT;
	}

	public void setRETIREMENT_DT(Date rETIREMENT_DT) {
		RETIREMENT_DT = rETIREMENT_DT;
	}

	public String getFATHER_NAME() {
		return FATHER_NAME;
	}

	public void setFATHER_NAME(String fATHER_NAME) {
		FATHER_NAME = fATHER_NAME;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

//	public String getWORKING_DESIGNATION() {
//		return WORKING_DESIGNATION;
//	}
//
//	public void setWORKING_DESIGNATION(String wORKING_DESIGNATION) {
//		WORKING_DESIGNATION = wORKING_DESIGNATION;
//	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	

	public Date getDOA() {
		return DOA;
	}

	public void setDOA(Date dOA) {
		DOA = dOA;
	}

	public Date getCONDUCTOR_LC_EXPDATE() {
		return CONDUCTOR_LC_EXPDATE;
	}

	public void setCONDUCTOR_LC_EXPDATE(Date cONDUCTOR_LC_EXPDATE) {
		CONDUCTOR_LC_EXPDATE = cONDUCTOR_LC_EXPDATE;
	}
	
	

/*	public Set<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}*/



	@Column(name="CONDUCTOR_LC_EXPDATE")
	private Date  CONDUCTOR_LC_EXPDATE;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<Training> training;

	public Set<Training> getTraining() {
		return training;
	}

	public void setTraining(Set<Training> training) {
		this.training = training;
	}


	
	@Column(name="EMPLOYEE_NAME_KANNADA")
	private String EMPLOYEE_NAME_KANNADA;
	

	
	

	public String getEMPLOYEE_NAME_KANNADA() {
		return EMPLOYEE_NAME_KANNADA;
	}

	public void setEMPLOYEE_NAME_KANNADA(String eMPLOYEE_NAME_KANNADA) {
		EMPLOYEE_NAME_KANNADA = eMPLOYEE_NAME_KANNADA;
	}
	
	public String getDL_Exp_Date() {
		return DL_Exp_Date;
	}

	public void setDL_Exp_Date(String dL_Exp_Date) {
		DL_Exp_Date = dL_Exp_Date;
	}

	public String getD_O_B() {
		return D_O_B;
	}

	public void setD_O_B(String d_O_B) {
		D_O_B = d_O_B;
	}

	public String getD_O_A() {
		return D_O_A;
	}

	public void setD_O_A(String d_O_A) {
		D_O_A = d_O_A;
	}

	public String getRetirementDate() {
		return RetirementDate;
	}

	public void setRetirementDate(String retirementDate) {
		RetirementDate = retirementDate;
	}

	public String getCon_Lic_Exp_Date() {
		return Con_Lic_Exp_Date;
	}

	public void setCon_Lic_Exp_Date(String con_Lic_Exp_Date) {
		Con_Lic_Exp_Date = con_Lic_Exp_Date;
	}


	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Date getEffective_end_date() {
		return effective_end_date;
	}

	public void setEffective_end_date(Date effective_end_date) {
		this.effective_end_date = effective_end_date;
	}

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Memo> memo;

	public Set<Memo> getMemo() {
		return memo;
	}

	public void setMemo(Set<Memo> memo) {
		this.memo = memo;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="employeeCash")
	private Set<CashRemittanceVoucher> empCashRemittanceVoucher;

	public Set<CashRemittanceVoucher> getEmpCashRemittanceVoucher() {
		return empCashRemittanceVoucher;
	}

	public void setEmpCashRemittanceVoucher(
			Set<CashRemittanceVoucher> empCashRemittanceVoucher) {
		this.empCashRemittanceVoucher = empCashRemittanceVoucher;
	}
	@Column(name="status_remark")
	private String status_remark;

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}
}
