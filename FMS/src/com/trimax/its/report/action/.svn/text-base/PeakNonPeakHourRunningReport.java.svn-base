package com.trimax.its.report.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class PeakNonPeakHourRunningReport {
	
	private Map<Integer, String> divisionlist;
	private String division1;
	private String depot1;
	public String startdate;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDivision1() {
		return division1;
	}

	public void setDivision1(String division1) {
		this.division1 = division1;
	}

	public String getDepot1() {
		return depot1;
	}

	public void setDepot1(String depot1) {
		this.depot1 = depot1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {
		System.out.println("in execute");

		// try {
		// if(dateStr!=null){
		// Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		// request.getSession().setAttribute("cDate", date);
		// }else{
		// request.getSession().setAttribute("cDate", new Date());
		// }
		// } catch (Exception e) {
		// request.getSession().setAttribute("cDate", new Date());
		// e.printStackTrace();
		// }

		//this.setDivisionlist(getDivisionName());
		return "success";
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String getPeakNonPeakHourRunningReport() {

		try {

			
			
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();


          String sourceid=request.getParameter("source");
          String destid=request.getParameter("dest");
          String starttime=request.getParameter("startdate");
          String endtime=request.getParameter("enddate");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
