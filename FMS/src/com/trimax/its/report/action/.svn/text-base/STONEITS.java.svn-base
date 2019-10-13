package com.trimax.its.report.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.common.Common;
import com.trimax.its.vts.dao.VtsDataDAO;

public class STONEITS {
	StOneItsDao doa=new StOneItsDao();
	 public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String startdate;
	    public String enddate;
	    private Map<Integer, String> divisionlist;
	    
		public String execute() throws Exception {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
	divisionlist = vvt.getDivisionName();

	return "success";
	}
		public void getdepot() {
			doa.getdepot();
		}
		public void getdata() {
			Common common = new Common();
			HttpServletRequest req=ServletActionContext.getRequest();
			
			String date=common.getDateFromPicker(startdate);
			
			String div=req.getParameter("division");
			String depot=req.getParameter("depot");
			StOneItsDao dao=new StOneItsDao();
			dao.getdatadao(date,div,depot,startdate);
		
			
		}
}
