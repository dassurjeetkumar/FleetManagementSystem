package com.trimax.its.reportmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.reportmaster.dao.EmailMasterDao;
import com.trimax.its.reportmaster.dao.ReportMasterDao;
import com.trimax.its.reportmaster.model.EmailMaster;
import com.trimax.its.reportmaster.model.ReportMaster;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class EmailMasterAction extends ActionSupport{
	EmailMaster emailmaster=new EmailMaster();
	ReportMaster reportMaster=new ReportMaster();
	
	String insertstaus;
	String updatestaus;
	String deletestaus;
	String reportid;
	
	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	public EmailMaster getEmailmaster() {
		return emailmaster;
	}

	public void setEmailmaster(EmailMaster emailmaster) {
		this.emailmaster = emailmaster;
	}

	public String getInsertstaus() {
		return insertstaus;
	}

	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}

	public String getUpdatestaus() {
		return updatestaus;
	}

	public void setUpdatestaus(String updatestaus) {
		this.updatestaus = updatestaus;
	}

	public String getDeletestaus() {
		return deletestaus;
	}

	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}

	@SkipValidation
	public String emailConfigurationView()
	{
		HttpServletRequest request = ServletActionContext.getRequest();

		String report_id=request.getParameter("deratemasterid");
		setReportid(report_id);
		
		return "success";
		
	}
	
	public String createEmailConfiguration(){
		
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewReportMaster.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
	//	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		EmailMasterDao dao=new EmailMasterDao();
		
		//if(!dao.checkReportMaster(emailmaster.getReport_master_name())){
		int reid=Integer.parseInt(request.getParameter("reportid"));
        ReportMaster rm=new ReportMaster();
        rm.setReport_master_id(reid);
		String res="";
		int id=0;
		try{
		 emailmaster.setReportMaster(rm);
		 id= dao.createEmailConfiguration(emailmaster);
		}catch(Exception ex){
			setInsertstaus("fail");
			return "input";
		}finally{
			setInsertstaus("success");
			addActionMessage("Report Master "+id+" Created Successfully");
		}
		/*}else{
			setInsertstaus("duplicate");
			return "input";
		}*/
		return "success";
	   }else{
			return "input";
		}
		
	}

}
