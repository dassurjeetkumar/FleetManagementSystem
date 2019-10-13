package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.route.model.Floor;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class FloorAjaxAction extends ActionSupport{
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	private Floor floor;
	
	
	@SkipValidation
	public String getFloorData()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			FloorDao dao = new FloorDao();
			int busstationId=Integer.parseInt(request.getParameter("busid"));
			//System.out.println("busstationId======>"+busstationId);
			//int cnt = dao.getTotalRecords();

			String[] cols = { "","floor_id","floor_name","orgChart.org_name","status"};
			String[] dbcol={"","floor_id","floor_name","orgChart.org_name","status"};
						
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

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
				if (sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			total = dao.getTotalRecordsForBusstation(request,dbcol[col],dir,busstationId);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getDataForBusstation(total, request,dbcol[col],dir,busstationId);
			//JSONObject result = new JSONObject();
			//result.put("aaData", "Floor1");
			out.print(result);
			//System.out.println("AjAx End.....");
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	public String updaterows() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String floor_name=request.getParameter("floorname").trim();
		String status=request.getParameter("floorstatus").trim();
		int floorid=Integer.parseInt(request.getParameter("id"));
		int parentId=Integer.parseInt(request.getParameter("parentID"));
		CommonValidation common=new CommonValidation();
		boolean flag=true;
		PrintWriter out = null;
		if(edit.equalsIgnoreCase("Y")){
		FloorDao dao = new FloorDao();
		HttpServletResponse response = ServletActionContext.getResponse();
		out = response.getWriter();
		String result = "";
		
		DependencyChecker dc=new DependencyChecker();
		String statuss=dc.getStatus(floorid,"floor");
		
		if(statuss.equals("")||(!statuss.equals("") &&  status.equals("ACTIVE"))){
			
			
		
		
		if(floor_name.equals(" "))
		{
			result="Floor Name is Required";
			out.print(result);
			flag=false;
		}
		
		if(flag==true){
		if(!dao.checkDuplcateFloorForUpdate(floor_name, parentId,floorid)){
		dao.updateFloorDetails(floor_name, status, floorid,request);
		result="Floor Id "+ floorid +" Updated Successfully";
		
		out.print(result);
		}else if(dao.checkDuplcateFloor(floor_name, parentId,floorid))
		{
			dao.updateFloorDetails(floor_name, status, floorid,request);
			result="Floor Id "+ floorid +" Updated Successfully";
			addActionMessage("Floor Id "+ floorid +" Updated Successfully");
			//System.out.println("Updated...");
			out.print(result);
		}
		else{
			result="Floor Name Already Exist!!";
			addActionMessage("Floor Name Already Exist!!");
			//System.out.println("Updated...");
			out.print(result);
		}
		}
		}else
		{
			
			if(status.equals("INACTIVE"))
			{
				//setUpdatestaus("success");
				out.print(statuss);
			addActionError(statuss);
			//return "input";
			}	
			
		}}else{
			out.print("Access Denied - User Does Not Have Access Privileges");
		}
		return null;
	}
	
	public String insertrows() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		PrintWriter out = null;
		if(create.equalsIgnoreCase("Y")){
		String floor_name=request.getParameter("floorname");
		String status=request.getParameter("floorstatus");
		int floorid=Integer.parseInt(request.getParameter("id"));
		int parentId=Integer.parseInt(request.getParameter("parentID"));
		boolean flag=true;
		FloorDao dao = new FloorDao();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		out = response.getWriter();
		String result = "";
		CommonValidation common=new CommonValidation();
		if(floor_name.equals(" ")){
			result="Floor Name is Required";
			out.print(result);
			flag=false;
		}
		if(flag==true){
			if(!dao.checkDuplcateFloor(floor_name.trim(), parentId, floorid)){
				dao.insertFloorDetails(floor_name.trim(), status, floorid,request);
				int i=dao.getFloorId();
				result="Floor Id "+i+" Inserted SuccessFully";
				//addActionMessage("Floor Name "+i+" Created SuccessFully");
				out.print(result);
			}else{
				result="Floor Name already Exists!!";
				addActionMessage("Floor Name already Exists!!");
				out.print(result);
				flag=false;
			}
		}
		}else{
			out.print("Access Denied - User Does Not Have Access Privileges");
		}
		return null;
		
	}
	
	public String deleterows() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String floor_name=request.getParameter("floorname");
		String status=request.getParameter("floorstatus");
		int floorid=Integer.parseInt(request.getParameter("id"));
		DependencyChecker dc=new DependencyChecker();
		FloorDao dao = new FloorDao();
		PrintWriter out = null;
		if(delete.equalsIgnoreCase("Y")){
		HttpServletResponse response = ServletActionContext.getResponse();
		out = response.getWriter();
		String result = "";
		try{
			String statusdetails=dc.getStatus(floorid, "floor");
			if(statusdetails.equals(""))
			{
		dao.deleteFloor(floorid,request);
		result="Floor Id "+floorid+" Deleted SuccessFully";
		addActionMessage("Floor Id "+floorid+" Deleted SuccessFully");
			}else{
				result=statusdetails;
			}
		out.print(result);
		
		}catch(Exception ex)
		{
			
		}finally{
			
		}
		}else{
			out.print("Access Denied - User Does Not Have Access Privileges");
		}
		return null;
	}

}
