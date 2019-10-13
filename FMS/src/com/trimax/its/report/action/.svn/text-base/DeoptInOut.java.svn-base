package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;

public class DeoptInOut extends ActionSupport {
	private Map<Integer, String> divisionlist;
	public String startdate;
    public String enddate;
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
	@Override
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
//		System.out.println("division........"+divisionlist);	
		return "success";
	}
	public String getdepotinout(){
		try {
			String regionTypeAjaxString = "";
			StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			//String date2=common.getDateFromPicker(enddate);
		   
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String depot=req.getParameter("depot");
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		//session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 //transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");

		/* String sql="select ea.DEPOT_NAME,ld.DEVICE_ID,ld.vehicle_no,ea.DEPOT_ENTRY_TM as early_entry_time,ld.DEPOT_EXIT_TM as late_exit_time from  dash_board_early_Arival_2 ea "+
           "left OUTER JOIN dash_board_late_Departure_6 ld on ea.DEVICE_ID=ld.DEVICE_ID "+
           "where ea.DEPOT_ID=10 and DEPOT_ENTRY_TM like '%2017-11-15%' and ld.DEPOT_ID=10 and DEPOT_EXIT_TM like '%2017-11-15%' "+ 
            "union "+
           "select ea.DEPOT_NAME,ld.DEVICE_ID,ld.vehicle_no,ea.DEPOT_ENTRY_TM as early_entry_time,ld.DEPOT_EXIT_TM as late_exit_time from  dash_board_early_Arival_2 ea "+
          "right OUTER JOIN dash_board_late_Departure_6 ld on ea.DEVICE_ID=ld.DEVICE_ID "+ 
          "where ea.DEPOT_ID=10 and DEPOT_ENTRY_TM like '%2017-11-15%' and ld.DEPOT_ID=10 and DEPOT_EXIT_TM like '%2017-11-15%'";
		Query query = session1.createSQLQuery(sql);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();*/
		 String rbKey = String.valueOf(getSysParameterForVts());
			NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
			VtsResponseNew alertresult = port.webGetdepotinout(depot, date1, rbKey);
	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Depot IN Out Report </br> Date:- "+startdate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>Device Id</th><th>VehNo</th><th>Sch No</th>" +
				"<th>Entry Time</th><th>Exit Time</th>" +
					""+"</tr></thead><tbody>");
		

		        
		        for (int i = 0; i < alertresult.getVtsDatamodelnew().size(); i++) {
		        	int j=i+1;
		        	VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(i);
		        	
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.getOrgName().toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.getDEVICEID().toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.getLICENCENUMBER().toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.getScheduleno().toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.getReasonDate().toString()  +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.getSolvedDate().toString() +"</td>");
		 regionTypeAjaxString1.append("</tr>");
					
					 }
		        regionTypeAjaxString1.append("</tbody></table></div>");
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;

	}

	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			session.close();
		}
		return param;
	}
	
}