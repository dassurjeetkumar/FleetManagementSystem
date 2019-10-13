package com.trimax.its.usermanagement.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.OrgType;
import com.trimax.its.utility.model.Group_Master;
import com.trimax.its.utility.model.Role;
import com.trimax.its.vehicle.model.OrganisationChart;

import javax.persistence.Transient;

@Entity
@Table(name="menu_user_master")
public class UserDetails {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int user_id;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/*public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type.trim();
	}*/

	public String getUserloginname() {
		return userloginname;
	}

	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname.trim();
	}

	/*public DesignationType getDesignationtypeid() {
		return designationtypeid;
	}

	public void setDesignationtypeid(DesignationType designationtypeid) {
		this.designationtypeid = designationtypeid;
	}*/

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid.trim();
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.trim();
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

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}



	

	/*@Column(name="user_type")
	private String user_type;*/
	
	
	
	
	@ManyToOne
	@JoinColumn(name="user_type_id")
	private UserType usertypedetails;
	
	
	/*@ManyToOne
	@JoinColumn(name="emp_id")
	private Employee empid;*/
	
	




	public UserType getUsertypedetails() {
		return usertypedetails;
	}

	public void setUsertypedetails(UserType usertypedetails) {
		this.usertypedetails = usertypedetails;
	}





	@Column(name="emp_id")
	private int empid;
	
	
	@Column(name="agent_id")
	private String agent_id;
	
	
	
	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}





	@Column(name="userloginname")
	private String userloginname;
	
	/*@Column(name="role_id")
	private int role_id;*/
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private  Role role;
	
	/*@ManyToOne
	@JoinColumn(name="usergroup_id")
	private Group_Master usergroupid;
*/
	@Column(name="usergroup_id")
	private int usergroupid;

	/*public Employee getEmpid() {
		return empid;
	}

	public void setEmpid(Employee empid) {
		this.empid = empid;
	}*/

	public int getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(int usergroupid) {
		this.usergroupid = usergroupid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*public Group_Master getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(Group_Master usergroupid) {
		this.usergroupid = usergroupid;
	}*/

	/*@ManyToOne
	@JoinColumn(name="designation_type_id")
	private DesignationType designationtypeid;*/
	
	@Column(name="designation_type_id")
	private int designation_type_id;
	
	public int getDesignation_type_id() {
		return designation_type_id;
	}

	public void setDesignation_type_id(int designation_type_id) {
		this.designation_type_id = designation_type_id;
	}


	@ManyToOne
	@JoinColumn(name="org_type_id")
	private OrgType orgnizationType;

	
	public OrgType getOrgnizationType() {
		return orgnizationType;
	}

	public void setOrgnizationType(OrgType orgnizationType) {
		this.orgnizationType = orgnizationType;
	}





	@Column(name="emailid")
	private String emailid;
	
	@Column(name="contactno")
	private String contactno;
	
	@Column(name="note")
	private String note;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="password")
	private String password;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@Column(name="access_status")
	private String access_status;
	
	/*@Column(name="org_chart_id")
	private int org_chart_id;
	

	public int getOrg_chart_id() {
		return org_chart_id;
	}

	public void setOrg_chart_id(int org_chart_id) {
		this.org_chart_id = org_chart_id;
	}*/

	
	public String getAccess_status() {
		return access_status;
	}

	public void setAccess_status(String access_status) {
		this.access_status = access_status;
	}





	@ManyToOne
	@JoinColumn(name="org_chart_id")
	private OrganisationChart orgchartdetails;
	




	



	public OrganisationChart getOrgchartdetails() {
		return orgchartdetails;
	}

	public void setOrgchartdetails(OrganisationChart orgchartdetails) {
		this.orgchartdetails = orgchartdetails;
	}





	
	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public String getPassword() {
		return password;
	}

	

	public void setPassword(String password) {
		this.password = password.trim();
	}

	@Transient
	private int oldGroupID;

	public int getOldGroupID() {
		return oldGroupID;
	}

	public void setOldGroupID(int oldGroupID) {
		this.oldGroupID = oldGroupID;
	}

	

	@Transient
	private String designationtype;
	public String getDesignationtype() {
	return designationtype;
}

public void setDesignationtype(String designationtype) {
	this.designationtype = designationtype;
}
	
}
