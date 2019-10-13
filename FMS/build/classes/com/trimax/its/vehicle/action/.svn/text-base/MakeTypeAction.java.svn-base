package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.MakeTypeDAO;
import com.trimax.its.vehicle.model.MakeType;

/**
 * @author root
 *
 */
public class MakeTypeAction extends ActionSupport {

	String returnResult = "";
	static MakeTypeDAO daoObject = new MakeTypeDAO();
	
	private List<MakeType> makeTypeList;
	private MakeType makeTypeDetails;
	private int isUpdateMakeType;
	private int isNewMakeType;
	static Number cnt = daoObject.getTotalMakeTypeRecords();

	
	public List<MakeType> getMakeTypeList() {
		return makeTypeList;
	}


	public void setMakeTypeList(List<MakeType> MakeTypeList) {
		this.makeTypeList = MakeTypeList;
	}

	
	public MakeType getMakeTypeDetails() {
		return makeTypeDetails;
	}


	public void setMakeTypeDetails(MakeType MakeTypeDetails) {
		this.makeTypeDetails = MakeTypeDetails;
	}


	public MakeTypeDAO getDaoObject() {
		return daoObject;
	}


	public void setDaoObject(MakeTypeDAO daoObject) {
		this.daoObject = daoObject;
	}


	public int getIsNewMakeType() {
		return isNewMakeType;
	}


	public void setIsNewMakeType(int isNewMakeType) {
		this.isNewMakeType = isNewMakeType;
	}
	
	

	public int getIsUpdateMakeType() {
		return isUpdateMakeType;
	}


	public void setIsUpdateMakeType(int isUpdateMakeType) {
		this.isUpdateMakeType = isUpdateMakeType;
	}


	public String execute()
	{
		
//		this.setMakeTypeList(daoObject.getMakeTypeList());
		return "success";
	}
	
	
	
	public String editMakeType()
	{
		
		String makeTypeId = (ServletActionContext.getRequest().getParameter("editMakeTypeId"));
		Object makeTypeID = ServletActionContext.getRequest().getSession().getAttribute("makeTypeId");
		String makeTypeid = (makeTypeId != null) ? makeTypeId : makeTypeID.toString();
		this.setMakeTypeDetails(daoObject.getMakeTypeDetails(Integer.parseInt(makeTypeid)));
		return "success";
	}
	
	public String saveEditedMakeTypeDetails(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "MakeTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(makeTypeDetails.getMake_type_id(),"make_type");
			System.out.println("status---"+status);
			if(status.equals("")||(!status.equals("") &&  makeTypeDetails.getStatus().equals("ACTIVE"))){	
		if(daoObject.isMakeTypeNew(makeTypeDetails,"update")){
			if(daoObject.saveEditedDetails(makeTypeDetails)){
				addActionMessage("Make Type Id "+makeTypeDetails.getMake_type_id()+" updated successfully");
				returnResult = "success";
			}
			else{
				addActionError("Error in make type updation");
				returnResult = "fail";
			}
		}
		else{
			addFieldError("updatedMakeTypeName","Duplicate  make type ");
			returnResult = "fail";
			
			//ServletActionContext.getRequest().getSession().setAttribute("makeTypeId",updatedMakeTypeId);
			//editMakeType();
		}
			}else
			{
				
				if(makeTypeDetails.getStatus().equals("INACTIVE"))
				{
					//setUpdatestaus("success");
				addActionError(status);
				return "input";
				}	
				
			}
		return returnResult;
		}else{
			return "input";
		}
	}
	
	public String createMakeType()
	{
		return "success";
	}
	
	public String saveNewMakeType()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "MakeTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		if(daoObject.isMakeTypeNew(makeTypeDetails,"update")){
			if(daoObject.saveMakeType(makeTypeDetails)){
				addActionMessage("Make Type Id "+ServletActionContext.getRequest().getAttribute("createdMakeTypeId")+" Created successfully");
				returnResult = "success";
			}
			else{
				addActionError("Error in make type creation");
				returnResult = "fail";
			}
		}
		else{
			addFieldError("newMakeType","Duplicate make type ");
			returnResult = "fail";
		}
		return returnResult;
		}else{
			return "fail";
		}
	}
	
	public String deleteMakeType()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "MakeTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String status="";
		int MakeTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("makeTypeId"));
		if(delete.equalsIgnoreCase("Y")){
		status=daoObject.deleteMakeType(MakeTypeId);
		
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Make Type Id "+MakeTypeId+" deleted successfully");			
			returnResult = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Make Type Id "+MakeTypeId+" deleted successfully");			
			returnResult = "success";
		} else {
			addActionError(status);
			returnResult = "success";
		}
			
			
		
//		else{
//			addActionError("Error in make type deletion");
//			returnResult = "fail";
//		}
		return returnResult;
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "success";
		}
	}
	public void validate()
	{
		if(isNewMakeType!=0)
		{
			if(makeTypeDetails.getMake_type_name()!=null&&!makeTypeDetails.getMake_type_name().trim().equals(""))
			{
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(makeTypeDetails.getMake_type_name()).matches())){
					addFieldError("newMakeType", "Invalid make type name");
				}
			}
			else {
				addFieldError("newMakeType", "Please enter make type name");
			}
			
		}
		if(isUpdateMakeType!=0)
		{
			if(makeTypeDetails.getMake_type_name()!=null&&!makeTypeDetails.getMake_type_name().trim().equals(""))
			{
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(makeTypeDetails.getMake_type_name()).matches())){
					addFieldError("updatedMakeTypeName", "Invalid make type name");
				}
			}
			else {
				addFieldError("updatedMakeTypeName", "Please enter make type name");
			}
			/*makeTypeDetails.setMake_type_name(this.makeTypeDetails.getMake_type_name());
			makeTypeDetails.setNote(this.makeTypeDetails.getNote());
			makeTypeDetails.setStatus(this.makeTypeDetails.getStatus());*/
			//ServletActionContext.getRequest().getSession().setAttribute("makeTypeId",updatedMakeTypeId);
			//editMakeType();
		}
	}
	

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String getMakeTypeRecordsList() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		
//		System.out.println("Count------>"+cnt);
		
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
		result = daoObject.getMakeTypeData(amount, request,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

}
