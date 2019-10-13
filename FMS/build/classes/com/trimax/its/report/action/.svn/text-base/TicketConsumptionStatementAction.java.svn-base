package com.trimax.its.report.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.trimax.its.report.dao.TicketConsumptionStatementDAO;
import com.trimax.its.report.model.TicketConsumptionStatementModel;

public class TicketConsumptionStatementAction {

	TicketConsumptionStatementDAO daoObject = new TicketConsumptionStatementDAO();
	
	public String reportDate;
	public List<Map<String,String>> divisionList;
	
	
	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public List<Map<String, String>> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<Map<String, String>> divisionList) {
		this.divisionList = divisionList;
	}

	public String execute(){
		
		this.setDivisionList(daoObject.getDivisionList());
		this.setReportDate(new SimpleDateFormat("MM-YYYY").format(new Date()));
		return "success";
	}
	public TicketConsumptionStatementModel getTicketConsumptionStatement(){
		return daoObject.getTicketConsumptionStatement();
	}
}
