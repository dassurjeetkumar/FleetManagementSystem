package com.trimax.its.vehicle.action;

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
import com.trimax.its.route.dao.BayDao;

import com.trimax.its.vehicle.dao.BreakdownTypeDao;

import com.trimax.its.vehicle.model.BreakDownType;
import com.trimax.its.vehicle.model.Vehicle;

public class BreakDownTypeAction extends ActionSupport implements Preparable {
	BreakDownType brekdowntype;
	String insertstatus;
	String deletestatus;
	List breakdowncategoryname;
	List comptype;
	

	public List getComptype() {
		return comptype;
	}

	public void setComptype(List comptype) {
		this.comptype = comptype;
	}

	public List getBreakdowncategoryname() {
		return breakdowncategoryname;
	}

	public void setBreakdowncategoryname(List breakdowncategoryname) {
		this.breakdowncategoryname = breakdowncategoryname;
	}

	public String getInsertstatus() {
		return insertstatus;
	}

	public String getDeletestatus() {
		return deletestatus;
	}

	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}

	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}

	

	public BreakDownType getBrekdowntype() {
		return brekdowntype;
	}

	public void setBrekdowntype(BreakDownType brekdowntype) {
		this.brekdowntype = brekdowntype;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello");

		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			BreakdownTypeDao breakdowndao = new BreakdownTypeDao();

			int cnt = breakdowndao.getTotalRecords();
			System.out.println("Count------>" + cnt);
			String[] cols = { "", "breakdown_type_id", "category.breakdown_category_name","systype.componentName","syssubtype.subComponentType","notes" };
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
			total = breakdowndao.getTotalRecords();
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = breakdowndao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}
@SkipValidation
	public String viewBreakdownType() {
		return "success";
	}
@SkipValidation
	public String createBreakdowntypeview() {
	BreakdownTypeDao breakdowndao = new BreakdownTypeDao();
	breakdowncategoryname=breakdowndao.getbreakdowncategory();
	comptype=breakdowndao.getComponentsType();
		return "success";
	}
	

@SkipValidation
public String getSubComponent() {
	HttpServletRequest request = ServletActionContext.getRequest();
	System.out.println("ORGID======="+request.getParameter("orgid"));
	
	BreakdownTypeDao breakdowndao = new BreakdownTypeDao();
	String subcomponenttype=breakdowndao.getSubComponentType(Integer.parseInt(request.getParameter("orgid")));
	
	System.out.println("subcomponenttype=" + subcomponenttype);
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	try {
		out = response.getWriter();
		out.print(subcomponenttype);
		//out.print("Hello");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}
public String getDetails(){
	try{
	PrintWriter out;
	HttpServletResponse response = ServletActionContext.getResponse();
	out = response.getWriter();
	out.print("Hello");
	}catch(Exception e){
		e.printStackTrace();
		
	}
	return null;
}

	public String addBreakDownType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//AccidentTypeDao accidentdao = new AccidentTypeDao();
		BreakdownTypeDao breakdowndao = new BreakdownTypeDao();
		
		{
		try{
			brekdowntype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			brekdowntype.setUpdated_by(0);
			brekdowntype.setUser_id(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//brekdowntype.setVehiclelist()
			//brekdowntype.setVehicle(Vehicle.class)
			//int vecid=brekdowntype.g'
			//brekdowntype.setVehiclelist();
			//int id=brekdowntype.getVehiclelist().getVehicleId();
			//brekdowntype.setVehiclelist(brekdowntype.getVehiclelist().getVehicleId());
			breakdowndao.saveBreakDownType(brekdowntype);
			
			//accidentdao.saveAccidentType(brekdowntype);
			/*int id=brekdowntype.getBreakdown_category().getBreakdown_category_id();
			System.out.println("subComponentId--------"+id);
			int systemid=brekdowntype.getSystem_type().getComponentTypeId();
			System.out.println("systemid--------"+systemid);
			int systemsubid=brekdowntype.getSystem_sub_type().getSubComponentId();
			System.out.println("systemsubid--------"+systemsubid);*/
			
		}catch(Exception ex)
		{
			setInsertstatus("fail");
			
		}
		finally{
			setInsertstatus("success");
		}
		}
		return "success";
	}
	
	public String deleteBreakdownType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//AccidentTypeDao accidentdao = new AccidentTypeDao();
//		accidenttype.setUpdated_by(Integer.parseInt(request.getSession()
//				.getAttribute("userid").toString()));
		try{
		//String res = accidentdao.deleteAccidentType(accidenttype,
			//	Integer.parseInt(request.getParameter("accid")),request);
		
		}catch(Exception ex){
			setDeletestatus("fail");
		}finally{
			setDeletestatus("success");
		}
		return "success";
	}

}
