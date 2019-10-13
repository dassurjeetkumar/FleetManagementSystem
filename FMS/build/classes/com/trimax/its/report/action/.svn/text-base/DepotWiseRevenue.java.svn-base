package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;

public class DepotWiseRevenue extends ActionSupport{

	 public String startdate;
	    public String enddate;
	    String regionTypeAjaxString = "";
	
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
	@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	return "success";
}
 public String depotDataRevenueReport(){
	 HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Depot Wise Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
		     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
		     regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
					regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Depot</th><th>Total Schedules Audited</th><th> Passenger Fare (ETM)</th><th>Passenger Fare (Manual)</th><th>Passenger Fare (Card)</th>" +
							"<th>Daily Pass Revenue (ETM)</th><th>Daily Pass Revenue (Manual)</th><th>Daily Pass Revenue (Card)</th><th>Luggage Revenue (ETM)</th>" +
							"<th>Luggage Revenue (Manual)</th><th>Luggage Revenue (Card)</th><th>Monthly Pass (ETM)</th><th>Monthly Pass (Manual)</th><th>Monthly Pass (Card)</th>" +
							"<th>Total Revenue (ETM)</th><th>Total Revenue (Manual)</th><th>Total Revenue (Card)</th>" +
							"<th>Total (Gross)</th><th>Batta</th><th>Incentive</th><th>Daily Pass Inc.</th>" +
							"<th>All Trip Inc.</th><th>Monthly Pass Inc.</th><th>Misc. Exp</th><th>Deduction</th>" +
							"<th>Service Tax</th><th>Amt to be Remitted</th><th>POS</th><th>Amt Rmitted</th><th>Shortage/Excess</th><th>Total Revenue</th></tr></thead><tbody>";
				
				regionTypeAjaxString +="<tr><td colspan='22'>No Records Found</td></tr>";
				 regionTypeAjaxString += "</tbody></table></div>        </div>"; 
				 HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
						out = response.getWriter();
						out.print(regionTypeAjaxString);
		}catch (Exception e) {
			e.printStackTrace();
		}
	 return null;
 }
}
