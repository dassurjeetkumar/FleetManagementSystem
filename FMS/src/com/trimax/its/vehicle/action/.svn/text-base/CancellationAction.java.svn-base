package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.CancellationDAO;
import com.trimax.its.vehicle.dao.VehicleTypeDAO;
import com.trimax.its.vehicle.model.CancellationCause;
import com.trimax.its.vehicle.model.VehicleType;

public class CancellationAction extends ActionSupport{
	CancellationDAO canldao=new CancellationDAO();
	String returnResult="";
	public CancellationCause cancellationDetails;
	
	public CancellationCause getCancellationDetails() {
		return cancellationDetails;
	}
	public void setCancellationDetails(CancellationCause cancellationDetails) {
		this.cancellationDetails = cancellationDetails;
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
	
public String getCancellationRecordsList() throws IOException{
		

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
		result = canldao.getCancelltnCauseData(amount, request,Integer.parseInt(sCol),sdir);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
public String createCauseCancel(){
	return "success";
}

public String SaveCancellCause()
{
	 
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		
	System.out.println("CancellationDetails................"+cancellationDetails);
	if((canldao.isCancleCauseTypeNew(cancellationDetails, "create"))==true){
		
	
		if(canldao.saveNewCanceltionType(cancellationDetails))
		{
			addActionMessage("Cancellation Cause Id "+ServletActionContext.getRequest().getAttribute("createdCancellationCauseId")+" created successfully");
			returnResult="success";
		}else{
			addActionError("Error in Cancellation Cause creation ");
			returnResult = "fail";
		}
	
	
	}	else{
		addActionError("Already Exist this Cancellation Cause ");
		returnResult = "fail";
	}
	
	return returnResult;
}

public String editCanclCauseType() {
System.out.println("editCanclCauseType....................");
	String canclTypeId = ServletActionContext.getRequest().getParameter("editCancelTypeId");
	System.out.println("editCanclCauseType...................."+canclTypeId);
	this.setCancellationDetails(canldao.getEditCanclDetails(canclTypeId));
	return "success";
}
public String saveEditedCauseCanclTypeDetails()
{     AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
HttpServletRequest request = ServletActionContext.getRequest();

int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "causeCancellation.action");

	
	
		
	
	if(canldao.isCancleCauseTypeNew(cancellationDetails,"update")==true){
		if(canldao.saveEditCanlCauseDetails(cancellationDetails))
		{
			addActionMessage("Cancellation Type Id "+cancellationDetails.getCancellation_id()+" updated successfully");
			returnResult="success";
		}else{
			addActionError("Error in Cancellation type updation ");
			returnResult = "fail";
		}
	}
	else{
		addFieldError("updatedCancellationTypeName", "Duplicate Cancellation Type");
		returnResult = "fail";
		
	}
		
	return returnResult;
	
}

public String deletecanclcauseType()
{
	String status=""; 
	int canclcauseTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("CancCauseTypeId"));
	 AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		
	status=canldao.deleteCauseCanclType(canclcauseTypeId);

	
	if(status.equals("")){
		addActionMessage("Cancellation Type Id "+canclcauseTypeId+" deleted successfully");
		returnResult = "success";
	}else{
		addActionError(status);
		returnResult = "success";
	}
		
return returnResult;
}		

}
