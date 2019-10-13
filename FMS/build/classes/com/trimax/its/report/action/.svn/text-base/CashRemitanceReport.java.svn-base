package com.trimax.its.report.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CashRemittanceDao;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CashRemitanceReport {
	
	public String execute() {
		
		return "success";
	}
	
	public String getcashRemitancePageAjax(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		try{
			String SelectedDate1 = req.getParameter("selectedDate1").toString();
			String SelectedDate2 = req.getParameter("selectedDate2").toString();
			//Calling Query For Cash Remittance ....
			JSONObject result = new JSONObject();
			Common cm = new Common();
			String formattedgivendate1 = cm.getDateFromPicker(SelectedDate1);
			String formattedgivendate2 = cm.getDateFromPicker(SelectedDate2);
			out = resp.getWriter();
			CashRemittanceDao doa=new CashRemittanceDao();
			result = doa.getDataForCashRemittanceReport(1, req, "", formattedgivendate1,
					formattedgivendate2);
			out.print(result);
			//
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
		}
		return null;
	}

}
