package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.ETMAvailabilityDao;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;

public class ETMAvailabilityAction extends ActionSupport {

	ScheduleMapDAO daoObject = new ScheduleMapDAO();

	public int updatedScheduleMappingId;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String execute()
	{
//		this.setModelTypeList(daoObject.getModelTypeList());
		return "success";
	}

   private String orgdepotname;
	public String getOrgdepotname() {
	return orgdepotname;
}

public void setOrgdepotname(String orgdepotname) {
	this.orgdepotname = orgdepotname;
}
Session session = null;
	@SkipValidation
	public String getETMList() throws IOException{
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();	
	String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

	System.out.println("hii i m in getETMList()");
	
	ETMAvailabilityDao dao=new ETMAvailabilityDao(); 
	
	try{
		System.out.println("test1.........");
		session = HibernateUtil.getSession("hibernate.cfg.xml");	
		
		String[] dbcol={"org_name", "depot_name","Etm_Serial_number","sim_number","sim_serial_number"};
		String[] cols={"org_name", "depot_name","Etm_Serial_number","sim_number","sim_serial_number"};
		System.out.println("test2");
		JSONObject result = new JSONObject();
		
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		System.out.println("sStart is"+sStart);
		System.out.println("sAmount --"+sAmount);
		System.out.println("scol"+sCol);
		System.out.println("sdir"+sdir);
		
				
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
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
		System.out.println("orgtypeid..........." + orgtypeid
				+ "orgchartid................." + orgchartid);
		String id = "!=0";
		if (orgtypeid.equals("1")) {
			id = "depot_id!=0";

		}
		if (orgtypeid.equals("3")) {

			id = "depot_id in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
					+ orgchartid + "')";
		}
		System.out.println("id...." + id);
		String colName = cols[col];
		int total = -1;
		total=dao.getTotalETMList(request, dbcol[Integer.parseInt(sCol)],sdir,id);
		System.out.println("total----"+total);
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
	   System.out.println("serch term is---------"+SEARCH_TERM);
		//COL_NAME = colName;
		DIR = dir;
		START = start;
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		
		
		result=dao.getAvailableEtmList(total,request,dbcol[Integer.parseInt(sCol)],sdir,id);
		System.out.println("result---------"+result);
		out.print(result);
			
		}catch(Exception e){
//			e.printStackTrace();
		}
		return null;

}
		
	

	
	}





	

