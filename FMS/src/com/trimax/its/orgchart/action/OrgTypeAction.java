package com.trimax.its.orgchart.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.orgchart.dao.OrgChartDao;
import com.trimax.its.orgchart.dao.OrgTypeDao;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
public class OrgTypeAction extends ActionSupport implements Preparable {
	private OrgType organizationType;
	String insertmsg;
	String updatemsg;
	String deletestatus;
	
	public String getInsertmsg() {
		return insertmsg;
	}

	public void setInsertmsg(String insertmsg) {
		this.insertmsg = insertmsg;
	}

	public String getUpdatemsg() {
		return updatemsg;
	}

	public void setUpdatemsg(String updatemsg) {
		this.updatemsg = updatemsg;
	}

	public String getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}

	public OrgType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrgType organizationType) {
		this.organizationType = organizationType;
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	
	@SkipValidation
	public String execute() throws IOException {
		return null;
	}

	public void prepare() throws Exception {
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			OrgTypeDao orgdao = new OrgTypeDao();
			// orglist = orgdao.getorganization(1);
			/*
			 * int cnt = orgdao.getTotalRecords();
			 * System.out.println("Count------>" + cnt);
			 */
			String[] cols = { "","org_type_id", "orgType", "notes", "status" };
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
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			total = orgdao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = orgdao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			System.out.println("Result of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}
	@SkipValidation
	public String view() {
		System.out.println("Inside View...");
		return "success";
	}
	@SkipValidation
	public String createorgType()
	{
		return "success";
	}
	@SkipValidation
	public String editorgType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		OrgTypeDao orgdao = new OrgTypeDao();
		organizationType=orgdao.getEditedOrg(Integer.parseInt(request
				.getParameter("orgid")));
		return "success";
	}
	
	public String saveCreatedorgType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "orgtypeview!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		OrgTypeDao orgdao = new OrgTypeDao();
		try {
			if(!orgdao.checkOrgtype(organizationType.getOrgType())){
				//organizationType.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try{
			int result=orgdao.saveOrgChart(organizationType);
			
			//orgdao.upDateLineString(orgchart.getLocation_string(), orgchart.getOrg_chart_id());
			if(result!=0){
			setInsertmsg("success");
			addActionMessage("Organization Type Id "+ result +" Inserted Successfully");
			}else{
				setInsertmsg("database");
				addActionMessage("Organization Type Could Not Created Please Try After SomeTime");
				return "input";
			}
			}catch(Exception ex)
			{
				setInsertmsg("database");
				addActionMessage("Organization Type Could Not Created Please Try After SomeTime");
				return "input";
			}finally{
				
			}
			}else{
				setInsertmsg("duplicate");
				addActionMessage("Organization Type Name already Exists!!");
				return "input";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
		}else{
			return "input";
		}
		
	}
	public String saveEditedorgType()
	{
		
		OrgTypeDao orgdao = new OrgTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiii");
		if(orgdao.checkOrgtypeForUpdate(organizationType.getOrgType(),organizationType.getOrg_type_id())){
			organizationType.setUpdatedBy(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			System.out.println("hiii");
			organizationType.setUpdatedDate(new java.util.Date());
		try{
			orgdao.updateOrgType(organizationType, organizationType.getOrg_type_id());
		setUpdatemsg("success");
		addActionMessage("Organization Type  Id "+organizationType.getOrg_type_id() +" Updated SuccessFully");
		}catch(Exception ex)
		{
			setUpdatemsg("database");
		}finally{
			
		}
		} else{
			try {
				if(!orgdao.checkOrgtype(organizationType.getOrgType())){
					organizationType.setUpdatedBy(Integer.parseInt(request.getSession()
							.getAttribute("userid").toString()));
					organizationType.setUpdatedDate(new java.util.Date());
					try{
						orgdao.updateOrgType(organizationType, organizationType.getOrg_type_id());
					setUpdatemsg("success");
					addActionMessage("Organization Type Id "+ organizationType.getOrg_type_id()+" Updated SuccessFully");
					}catch(Exception ex)
					{
						setUpdatemsg("database");
						addActionMessage("Could Not Update Organization Type ");
					}finally{
						
					}
				}else{
					setUpdatemsg("duplicate");
					addActionMessage("Could Not Update Duplicate Organization Type  ");
				}
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return "success";
		
	}
	
	@SkipValidation
	public String deleteorgType() {
		//System.out.println("delete()");
		String returnResult = "success";
		int orgid = Integer.parseInt(ServletActionContext.getRequest().getParameter("orgid"));
		System.out.println("orgid......"+orgid);
		OrgTypeDao orgdao = new OrgTypeDao();
			if(orgdao.deleteOrg(orgid))
			{
				addActionMessage("OrgType id "+orgid+" deleted successfully");
				
			}else{
				addActionError("Problem in deleting OrgType");
				
			}
		
		return returnResult;
	}
	
	public void validate() {
		CommonValidation commvalidate = new CommonValidation();
		if (organizationType.getOrgType().trim().equals("")) {
			
		
			addFieldError("orgtypename", "Organization Type Name is Required");
			//addActionError("Device Serial No. is Required");
		}
		if (!commvalidate.isSpecialChar(organizationType.getOrgType().trim())) {
			
			addFieldError("orgtypename", "Special Character For Organization Type Name is Not Allowed");
			//addActionError("Special Character For Device Serial Number is Not Allowed");
		}
	}
}