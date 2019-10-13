package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.trimax.its.common.DependencyChecker;
import com.trimax.its.route.dao.BayDao;
import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class BayAjaxAction {
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;

	@SkipValidation
	public String execute()
	{
		return null;
	}
	@SkipValidation
	public String getBayData() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			BayDao  dao = new BayDao();
			int busstationId = Integer.parseInt(request.getParameter("busid"));
			// int cnt = dao.getTotalRecords();

			String[] cols = { "", "floor_id", "floor_name",
					"orgChart.org_name", "status" };
			String[] dbcol = { "", "floor_id", "floor_name",
					"orgChart.org_name", "status" };

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
			total = dao.getTotalRecordsForBusstationFloor(request, dbcol[col], dir,
					busstationId);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getDataForBusstationFloor(total, request, dbcol[col], dir,
					busstationId);
			// JSONObject result = new JSONObject();
			// result.put("aaData", "Floor1");
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String updaterows() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BayAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		
		String floor_name = request.getParameter("floorname");
		String status = request.getParameter("floorstatus");
		int floorid = Integer.parseInt(request.getParameter("id"));
		int parentId=Integer.parseInt(request.getParameter("parentID"));
		boolean flag=true;
		PrintWriter out = null;
		if(edit.equalsIgnoreCase("Y")){
		HttpServletResponse response = ServletActionContext.getResponse();
		out = response.getWriter();
		String result = "";
		
		DependencyChecker dc=new DependencyChecker();
		String statuss=dc.getStatus(floorid,"bay");
		if(statuss.equals("")||(!statuss.equals("") &&  status.equals("ACTIVE"))){
		
		if(floor_name.equals(""))
		{
			result="Bay Name is Required";
			out.print(result);
			flag=false;
		}
		BayDao dao = new BayDao();
		if(flag==true){
			if(dao.checkDuplcateBayForUpdate(floor_name, parentId,floorid)){
		dao.updateFloorDetails(floor_name, status, floorid, request);
		result="Bay Id "+ floorid +" Updated SuccessFully";
		out.print(result);
			}else if(!dao.checkDuplcateBay(floor_name, parentId,parentId))
			{
				dao.updateFloorDetails(floor_name, status, floorid, request);
				result="Bay Id "+ floorid +" Updated SuccessFully";
				out.print(result);
			}else
			{
				result="Bay Name already Exists!!";
				out.print(result);
				flag=false;
			}
		}
		}else
		{
			
			if(status.equals("INACTIVE"))
			{
				//setUpdatestaus("success");
				out.print(statuss);
			
			//return "input";
			}	
			
		}
		}else{
			out.print("Access Denied - User Does Not Have Access Privileges");
		}
		return null;
	}

	public String insertrows() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String floor_name = request.getParameter("floorname");
		String status = request.getParameter("floorstatus");
		int floorid = Integer.parseInt(request.getParameter("id"));
		int parentid = Integer.parseInt(request.getParameter("parent_id"));
		int parentId=Integer.parseInt(request.getParameter("parentID"));
		boolean flag=true;
		PrintWriter out = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BayAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		out = response.getWriter();
		String result = "";
		BayDao dao = new BayDao();
		
		
		if(create.equalsIgnoreCase("Y")){
		
		if(floor_name.equals(""))
		{
			result="Bay Name is Required";
			out.print(result);
			flag=false;
		}
		if(flag==true){
			if(!dao.checkDuplcateBay(floor_name, parentId, parentid)){
		dao.insertFloorDetails(floor_name, status, parentid,floorid, request);
		result="Bay Name "+ floor_name +" Inserted SuccessFully";
		out.print(result);
			}else{
				result="Bay Name already Exists!!";
				out.print(result);
				flag=false;
			}
		}
		
		}else{
			out.print("Access Denied - User Does Not Have Access Privileges");
		}
		return null;
		
	}

	public String deleterows() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String floor_name = request.getParameter("floorname");
		String status = request.getParameter("floorstatus");
		int floorid = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BayAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		DependencyChecker dc=new DependencyChecker();
		out = response.getWriter();
		BayDao dao = new BayDao();
		String result="";
		try{
			String statusdetails=dc.getStatus(floorid, "bay");
			if(statusdetails.equals(""))
			{
		dao.deleteFloor(floorid, request);
		result="Bay Id "+floorid+" Deleted SuccessFully";
			}else{
				result=statusdetails;
			}
		out.print(result);
		}catch(Exception ex)
		{
			
		}finally{
			
		}}else{
			out.print("Access Denied - User Does Not Have Access Privileges");
		}
		return null;
	}

}
