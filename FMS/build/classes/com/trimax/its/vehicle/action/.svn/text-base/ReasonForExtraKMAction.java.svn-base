package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.CancellationDAO;
import com.trimax.its.vehicle.dao.ExtraKmDAO;
import com.trimax.its.vehicle.model.ExtraKmCause;

public class ReasonForExtraKMAction extends ActionSupport{
	String returnResult="";
	ExtraKmDAO canldao=new ExtraKmDAO();
	ExtraKmCause extrkm;
	public ExtraKmCause getExtrkm() {
		return extrkm;
	}
	public void setExtrkm(ExtraKmCause extrkm) {
		this.extrkm = extrkm;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
public String getextraKmRecordsList() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		String search_term = request.getParameter("sSearch");
		
		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
			start = 0;
			}
		}
		if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
				amount = 10;
			}
		}
		if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
				col = 0;
			}
		if (sdir != null) {
			if (!sdir.equals("asc"))
				dir = "desc";
		}
		int total = -1;
//		total = (Integer) cnt;
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = canldao.getExtrKMData(amount, request,Integer.parseInt(sCol),sdir);
		System.out.println("result..........."+result);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
public String createReasonExtraKm(){
	return "success";
}
public String SaveExtraKMlCause()
{
	 
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		
	System.out.println("extrkm................"+extrkm);
	if((canldao.isCancleCauseTypeNew(extrkm, "create"))==true){
		
	
		if(canldao.saveNewCanceltionType(extrkm))
		{
			addActionMessage("Extra KM Cause Id "+ServletActionContext.getRequest().getAttribute("createdCancellationCauseId")+" created successfully");
			returnResult="success";
		}else{
			addActionError("Error in Extra KM Cause creation ");
			returnResult = "fail";
		}
	
	
	}	else{
		addActionError("Already Exist this Extra KM Cause ");
		returnResult = "fail";
	}
	
	return returnResult;
}

public String editExtraKmType() {
System.out.println("editCanclCauseType....................");
	String canclTypeId = ServletActionContext.getRequest().getParameter("editExtraTypeId");
	System.out.println("editCanclCauseType...................."+canclTypeId);
	this.setExtrkm(canldao.getEditExtrakmDetails(canclTypeId));
	return "success";
}
public String saveEditedExtraKmTypeDetails()
{     AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
HttpServletRequest request = ServletActionContext.getRequest();

int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "reasonExtraKM.action");

	
	
		
	
	if(canldao.isCancleCauseTypeNew(extrkm,"update")==true){
		if(canldao.saveEditCanlCauseDetails(extrkm))
		{
			addActionMessage("Extra KM Type Id "+extrkm.getExtrakm_id()+" updated successfully");
			returnResult="success";
		}else{
			addActionError("Error in Extra KM type updation ");
			returnResult = "fail";
		}
	}
	else{
		addFieldError("updatedExtraKMTypeName", "Duplicate Cancellation Type");
		returnResult = "fail";
		
	}
		
	return returnResult;
	
}

public String deleteExtraKmType()
{
	String status=""; 
	int canclcauseTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("extrakTypeId"));
	 AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		
	status=canldao.deleteCauseCanclType(canclcauseTypeId);

	
	if(status.equals("")){
		addActionMessage("Extra KM Type Id "+canclcauseTypeId+" deleted successfully");
		returnResult = "success";
	}else{
		addActionError(status);
		returnResult = "success";
	}
		
return returnResult;
}		


}
