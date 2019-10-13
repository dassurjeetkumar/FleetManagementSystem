package com.trimax.its.orgchart.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.model.Device;
import com.trimax.its.orgchart.dao.OrgChartDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.OrganisationChart;

public class DepotAction extends ActionSupport implements Preparable {

	List<OrganisationChart> orglist;
	private OrganisationChart orgchart;
	String insertmsg;
	String updatemsg;
	String deletestatus;
	String newlineString;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public List<OrganisationChart> getOrglist() {
		return orglist;
	}

	public void setOrglist(List<OrganisationChart> orglist) {
		this.orglist = orglist;
	}

	public OrganisationChart getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}

	@SkipValidation
	public String execute() throws IOException {
		return null;
	}

	public String getInsertmsg() {
		return insertmsg;
	}

	public String getUpdatemsg() {
		return updatemsg;
	}

	public String getDeletestatus() {
		return deletestatus;
	}

	public void setInsertmsg(String insertmsg) {
		this.insertmsg = insertmsg;
	}

	public void setUpdatemsg(String updatemsg) {
		this.updatemsg = updatemsg;
	}

	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}

	
	public String getNewlineString() {
		return newlineString;
	}

	public void setNewlineString(String newlineString) {
		this.newlineString = newlineString;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			OrgChartDao orgdao = new OrgChartDao();
			orglist = orgdao.getorganization(2);
			/*int cnt = orgdao.getTotalRecords();
			System.out.println("Count------>" + cnt);*/
			String[] cols = { "", "org_chart_id", "org_name", "","", "", "","", "", "", "", "","", "","","","" };
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
			total = orgdao.getTotalRecordsForDepots(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = orgdao.getDataForDepots(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}
	@SkipValidation
	public String showOrg() {
		return "success";
	}
	@SkipValidation
	public String createOrg() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		OrgChartDao orgdao = new OrgChartDao();
		

		return "success";
	}
	@SkipValidation
	public String editorgchart() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		OrgChartDao orgdao = new OrgChartDao();
		orgchart = orgdao.getEditedDevice(Integer.parseInt(request
				.getParameter("orgid")));
		orgchart.setLocation_string(orgdao.getText(Integer.parseInt(request
				.getParameter("orgid"))));
		orgchart.setOrgcodekannada(orgchart.getOrg_code_kannada());
		orgchart.setOrgnamekannada(orgchart.getOrg_name_kannada());
		if(orgchart.getParent_id()==0)
		{
//			System.out.println("orgchart.getOrg_name()"+orgchart.getOrg_name());
			orgchart.setParent_org_name(orgchart.getOrg_name());
		}else{
		orgchart.setParent_org_name(orgdao.getParentName(orgchart.getParent_id()));
		}
		return "success";
	}

	public String createDepot() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "AllDepots.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		OrgChartDao orgdao = new OrgChartDao();
		try {
			if(!orgdao.checkOrgtype(orgchart.getOrg_name())){
				if(!orgdao.checkOrgCode(orgchart.getOrg_code())){
			orgchart.setCreated_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			try{
			int result=orgdao.saveOrgChart(orgchart);
			if(result!=0){
			orgdao.upDateLineString(newlineString, result);
			setInsertmsg("success");
			addActionMessage("Depot Id  "+ result +" Inserted Successfully");
			}else{
				setInsertmsg("database");
				addActionMessage("Depot Could Not Created Please Try After SomeTime");
				return "input";
			}
			}catch(Exception ex)
			{
				setInsertmsg("database");
				addActionMessage("Depot Could Not Created Please Try After SomeTime");
				return "input";
			}finally{
				
			}}else{
				setInsertmsg("duplicate");
				addActionMessage("Depot Code already Exists!!");
				return "input";
			}
			}else{
				setInsertmsg("duplicate");
				addActionMessage("Depot Name already Exists!!");
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
	
	public String saveeditedDepot() {
		OrgChartDao orgdao = new OrgChartDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "AllDepots.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		if(orgdao.checkOrgtypeForUpdate(orgchart.getOrg_name(),orgchart.getOrg_chart_id())){
			if(orgdao.checkOrgCodeForUpdate(orgchart.getOrg_code(),orgchart.getOrg_chart_id())){
		orgchart.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		orgchart.setUpdated_date(new java.util.Date());
		try{
		orgdao.updateDevice(orgchart, orgchart.getOrg_chart_id());
		orgdao.upDateLineString(orgchart.getLocation_string(), orgchart.getOrg_chart_id());
		setUpdatemsg("success");
		addActionMessage("Depot Id "+ orgchart.getOrg_chart_id()+" Updated SuccessFully");
		}catch(Exception ex)
		{
			setUpdatemsg("database");
		}finally{
			
		}}else{
			setUpdatemsg("duplicate");
			addActionMessage("Depot Code already Exists!!");
			return "input";
		}
		} else
			try {
				if(!orgdao.checkOrgtype(orgchart.getOrg_name())){
					if(orgdao.checkOrgCodeForUpdate(orgchart.getOrg_code(),orgchart.getOrg_chart_id())){
					orgchart.setUpdated_by(Integer.parseInt(request.getSession()
							.getAttribute("userid").toString()));
					orgchart.setUpdated_date(new java.util.Date());
					try{
					orgdao.updateDevice(orgchart, orgchart.getOrg_chart_id());
					orgdao.upDateLineString(orgchart.getLocation_string(), orgchart.getOrg_chart_id());
					setUpdatemsg("success");
					addActionMessage("Depot Id "+ orgchart.getOrg_chart_id()+" Updated SuccessFully");
					}catch(Exception ex)
					{
						setUpdatemsg("database");
						addActionMessage("Could Not Update Depot Record");
						return "input";
					}finally{
						
					}}else{
						setUpdatemsg("duplicate");
						addActionMessage("Depot Code already Exists!!");
						orgchart.setOrgcodekannada(orgchart.getOrg_code_kannada());
						orgchart.setOrgnamekannada(orgchart.getOrg_name_kannada());
						return "input";
					}
				}else{
					setUpdatemsg("duplicate");
					addActionMessage("Depot Name already Exists!!");
					orgchart.setOrgcodekannada(orgchart.getOrg_code_kannada());
					orgchart.setOrgnamekannada(orgchart.getOrg_name_kannada());
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
	
	@SkipValidation
	public String deleteselected() {
		OrgChartDao orgdao = new OrgChartDao();
		//OrganisationChart orgchart = new OrganisationChart();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "AllDepots.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		try{
		
			DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatusForOrgChart(Integer.parseInt(request.getParameter("delorgid")), "org_chart","Depots");
			System.out.println("status---"+status);
			if(status.equals("")){
					String res = orgdao.deleteDevice(orgchart,Integer.parseInt(request.getParameter("delorgid")));
					setDeletestatus("success");
					addActionMessage("Depot Id "+ Integer.parseInt(request.getParameter("delorgid"))+" Deleted SuccessFully" );
			}else{
				setDeletestatus("fail");
				addActionMessage(status);
			}
		
		}catch(Exception ex)
		{
			setDeletestatus("database");
			addActionMessage("Could Not Delete Depot");
		}finally{
			
		}
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	@SkipValidation
	public String showDepots(){
		return "success";
	}
	public void validate() {
		// TODO Auto-generated method stub
		boolean validation=false;
		CommonValidation common=new CommonValidation();
		
		
		if(orgchart.getOrg_code().equals(""))
		{
			addFieldError("org_code", "Depot Code is Required");
			validation=true;
			//addActionError("Organization Code is Required");
		}
		
		if(orgchart.getOrg_code_kannada().equals(""))
		{
			addFieldError("org_code_kannada", "Depot Code Kannada is Required");
			validation=true;
			//addActionError("Organization Code Kannada is Required");
		}
		if(orgchart.getOrg_name().equals(""))
		{
			addFieldError("org_name", "Depot Name is Required");
			validation=true;
			//addActionError("Organization Name is Required");
		}
		if(!common.isSpecialChar(orgchart.getOrg_name()))
		{
			addFieldError("org_name", "Special Characters are not allowed on Depot Name");
			validation=true;
			//addActionError("Organization Name is Required");
		}
		if(orgchart.getOrg_name_kannada().equals(""))
		{
			addFieldError("org_name_kannada", "Depot Name Kannada is Required");
			validation=true;
			//addActionError("Organization Name Kannada is Required");
		}
	
	}
	

}
