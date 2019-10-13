
package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.trimax.com.VtsResponse4;
import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class DashBoardSummaryReportAction {

	
	private static final long serialVersionUID = 1L;
	
	public String startdate;
	public String enddate;
	private String depotlist1;
	public String divisionlist1;
	private Map<Integer, String> divisionlist;
		
	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

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

	
	String regionTypeAjaxString = "";

	public static String rbKey = String.valueOf(getSysParameterForVts());

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
			session.close();
		}
		return param;
	}
	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			try {
				HttpServletRequest req = ServletActionContext.getRequest();
				 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
//				VtsDataDAO vvt = VtsDataDAO.getInstance();
				if(orgtypeid.equals("2")){
		        	//Our Logic
				int parentId=0;
				try {
				} catch (Exception ex) {

				}
				try {
					parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
				//Ends..
		        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
				divisionlist = vvt.getDivisionName();
				//schedulelist = vvt.getScheduleName();
		        }else{
		        	int parentId=0;
					try {
					} catch (Exception ex) {

					}
					try {
						parentId = vvt.getDepot1(orgtypeid,orgchartid);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
//			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		@SuppressWarnings({ "finally", "unchecked" })
		public String getDasboardsummary()
		{
					
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			
		VtsDataDAO dao= VtsDataDAO.getInstance();
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			JSONObject result = new JSONObject();
			String SelectedDate = req.getParameter("selectedenddate").toString();
			String endDate = req.getParameter("endDate").toString();
			
			String type = req.getParameter("type");
			
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(SelectedDate);
			String formattedEndDate = cm.getDateFromPicker(endDate);
//			System.out.println("formattedgivendate=="+formattedgivendate+"---"+formattedEndDate);
			String fromDate = formattedgivendate + " 00:00:00";
			String toDate = formattedgivendate + " 23:59:59";
//			String endDate1= formattedEndDate+ " 00:00:00";
			String endDate1 = formattedEndDate+ " 23:59:59";
			int divId=Integer.parseInt(req.getParameter("divId"));
			int depotId=Integer.parseInt(req.getParameter("depotId"));
			
			String qryy="";
			String qry2="in(";
			String qry="";
			
			if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
				qryy=" !=0";
				
			}else
				
				if(divId !=0 && depotId ==0){         // only depot="All" selected 
					List<String> l1 = dao.getDepotId(divId);
					for (int i = 0; i < l1.size(); i++) {
						 qry +=l1.get(i).toString()+",";
					}
					qry =qry2+qry+")";                   
					String depotIn=remove(qry);
					qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
					
				}else {
					
					 // if division and depot selected
					qryy= "in('"+depotId+"')";  // qryy="in(10)"
				}
//			System.out.println("added--"+qryy);
			try {

				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				resp.setContentType("application/json");
				out = resp.getWriter();
				if(type.equalsIgnoreCase("1")){
				if(modifiedDate.equalsIgnoreCase(formattedgivendate)){
					result =getDataForDashBoardSummaryLateDepartureWithoutDate(1, req, "", "",
							fromDate,endDate1, type,qryy);
				}else{
				
					result =getDataForDashBoardSummaryLateDepartureWithDate(1, req, "", "",
							fromDate,endDate1, type,qryy);
				}
				}
				if(type.equalsIgnoreCase("2")){
					
					if(modifiedDate.equalsIgnoreCase(formattedgivendate)){
						result =getDataForDashBoardSummaryEarlyArrivalWithoutDate(1, req, "", "",
								fromDate,endDate1, type,qryy);
					}else{
						
						result =getDataForDashBoardSummaryEarlyArrivalWithDate(1, req, "", "",
								fromDate,endDate1, type,qryy);
					}
					
				}
              if(type.equalsIgnoreCase("3")){
					
					if(modifiedDate.equalsIgnoreCase(formattedgivendate)){
						result =getDataForDashBoardSummaryScheduleNotDepartedWithoutDate(1, req, "", "",
								fromDate,endDate1, type,qryy);
					}else{
						
						result =getDataForDashBoardSummaryScheduleNotDepartedWithDate(1, req, "", "",
								fromDate,endDate1, type,qryy);
					}
					
				}
         /*     if(type.equalsIgnoreCase("4")){
            	  result = getDataForDashboardSummaryPartialCancellation(1,req,"", "", fromDate,toDate, type);
              }
              if(type.equalsIgnoreCase("5")){
            	  result = getDataForDashboardSummaryActualRunningTimeOfSchedule(1, req, "", "", fromDate, toDate, type);
              }*/
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
			}
		
		public String remove(String str) {
		    if (str != null && str.length() > 0 && str.charAt(str.length()-2)==',') {
		      str = str.substring(0, str.length()-2);
		    }
		    return str;
		}
		
		
		@SuppressWarnings("finally")
		public String getDasboardsummaryRunningData()
		{
			
			VtsDataDAO dao= VtsDataDAO.getInstance();
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		
			HttpServletRequest req = ServletActionContext.getRequest();
			int divId=Integer.parseInt(req.getParameter("divId"));
			int depotId=Integer.parseInt(req.getParameter("depotId"));
			
			String qryy="";
			String qry2="in(";
			String qry="";
			
			if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
				qryy=" !=0";
				
			}else
				
				if(divId !=0 && depotId ==0){         // only depot="All" selected 
					List<String> l1 = dao.getDepotId(divId);
					for (int i = 0; i < l1.size(); i++) {
						 qry +=l1.get(i).toString()+",";
					}
					qry =qry2+qry+")";                   
					System.out.println("qry is====="+qry);
					String depotIn=remove(qry);
					qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
					
				}else {
					
					 // if division and depot selected
					qryy= "in('"+depotId+"')";  // qryy="in(10)"
				}
//			System.out.println("added--"+qryy);
			
			
			HttpServletResponse resp = ServletActionContext.getResponse();
			PrintWriter out = null;
			//ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
			JSONObject result = new JSONObject();
			String SelectedDate = req.getParameter("selectedenddate").toString();
			String type = req.getParameter("type");
			
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(SelectedDate);
//			System.out.println("formattedgivendate=="+formattedgivendate);
			String fromDate = formattedgivendate + " 00:00:00";
			String toDate = formattedgivendate + " 23:59:59";
			
			
			try {

				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				resp.setContentType("application/json");
				out = resp.getWriter();
		

            	  result = getDataForDashboardSummaryActualRunningTimeOfSchedule(1,req,"", "", fromDate,toDate, type,qryy);

           
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
			}
		
		
		@SuppressWarnings("finally")
		public String getDasboardsummaryPartialData()
		{
	
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			VtsDataDAO dao= VtsDataDAO.getInstance();
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			//ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
			JSONObject result = new JSONObject();
			String SelectedDate = req.getParameter("selectedenddate").toString();
			String type = req.getParameter("type");
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(SelectedDate);
			String fromDate = formattedgivendate + " 00:00:00";
			String toDate = formattedgivendate + " 23:59:59";
			int divId=Integer.parseInt(req.getParameter("divId"));
			int depotId=Integer.parseInt(req.getParameter("depotId"));
			String qryy="";
			String qry2="in(";
			String qry="";
			
			if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
				qryy=" !=0";
				
			}else
				
				if(divId !=0 && depotId ==0){         // only depot="All" selected 
					List<String> l1 = dao.getDepotId(divId);
					for (int i = 0; i < l1.size(); i++) {
						 qry +=l1.get(i).toString()+",";
					}
					qry =qry2+qry+")";                   
					String depotIn=remove(qry);
					qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;

				}else {
					
					 // if division and depot selected
					qryy= "in('"+depotId+"')";  // qryy="in(10)"
				}
//			System.out.println("added--"+qryy);
			
			
			try {

				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				resp.setContentType("application/json");
				out = resp.getWriter();
		
            	  result = getDataForDashboardSummaryPartialCancellation(1,req,"", "", fromDate,toDate, type,qryy);
           
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
			}
		

		@SuppressWarnings("finally")
		public String getDasboardsummaryNRDTotalData()
		{
			Date date = new Date();
			PrintWriter out = null;
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			JSONObject result = new JSONObject();
			try{
			VtsDataDAO dao= VtsDataDAO.getInstance();
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			
			//ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
			String SelectedDate = req.getParameter("selectedenddate").toString();
			String endDate=req.getParameter("endDate").toString();
			String type = req.getParameter("type");
			Common cm = new Common();
			String endate=cm.getDateFromPicker(endDate);
			endate =endate+" 23:59:59";
			String formattedgivendate = cm.getDateFromPicker(SelectedDate);
			String fromDate = formattedgivendate + " 00:00:00";
			String toDate = formattedgivendate + " 23:59:59";
			int divId=Integer.parseInt(req.getParameter("divId"));
			int depotId=Integer.parseInt(req.getParameter("depotId"));
			String qryy="";
			String qry2="in(";
			String qry="";
			
			if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
				qryy=" !=0";
				
			}else
				
				if(divId !=0 && depotId ==0){         // only depot="All" selected 
					List<String> l1 = dao.getDepotId(divId);
					for (int i = 0; i < l1.size(); i++) {
						 qry +=l1.get(i).toString()+",";
					}
					qry =qry2+qry+")";                   
					String depotIn=remove(qry);
					qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
					
				}else {
					
					 // if division and depot selected
					qryy= "in('"+depotId+"')";  // qryy="in(10)"
				}
			
				resp.setContentType("application/json");
				out = resp.getWriter();
		
            	  result = getDashboardSummaryNrd(1,req,"", "", fromDate,endate, type,qryy);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
			}
		
			
			@SuppressWarnings("finally")
			public String getDashboardSummaryRouteDeviateData()
			{
				
				Date date = new Date();
				PrintWriter out = null;
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
				JSONObject result = new JSONObject();
				try{
				VtsDataDAO dao= VtsDataDAO.getInstance();
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				
				//ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
				String endDate=req.getParameter("endDate").toString();
				String SelectedDate = req.getParameter("selectedenddate").toString();
				String type = req.getParameter("type");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(SelectedDate);
				String fromDate = formattedgivendate + " 00:00:00";
				String toDate = formattedgivendate + " 23:59:59";
				int divId=Integer.parseInt(req.getParameter("divId"));
				int depotId=Integer.parseInt(req.getParameter("depotId"));
				String endate=cm.getDateFromPicker(endDate);
				endate =endate+" 23:59:59";
				
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 && depotId ==0){         // only depot="All" selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}else {
						
						qryy= "in('"+depotId+"')";  // qryy="in(10)"
					}
//				System.out.println("added--"+qryy);
				
					resp.setContentType("application/json");
					out = resp.getWriter();
	            	  result = getDashboardSummaryrouteDeviate(1,req,"", "", fromDate,endate, type,qryy);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
				}
			
			
			@SuppressWarnings("finally")
			public String getDashboardSummaryLatedataScheWise()
			{
				Date date = new Date();
				PrintWriter out = null;
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
				JSONObject result = new JSONObject();
				try{
				VtsDataDAO dao= VtsDataDAO.getInstance();
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				String endDate=req.getParameter("endDate").toString();
				String SelectedDate = req.getParameter("selectedenddate").toString();
				String type = req.getParameter("type");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(SelectedDate);
				int divId=Integer.parseInt(req.getParameter("divId"));
				int depotId=Integer.parseInt(req.getParameter("depotId"));
				String endate=cm.getDateFromPicker(endDate);
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 && depotId ==0){         // only depot="All" selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}else {
						qryy= "in('"+depotId+"')";  // qryy="in(10)"
					}
//				System.out.println("added--"+qryy);

					resp.setContentType("application/json");
					out = resp.getWriter();
	            	  result = getDashboardSummarySchwiseLateDept(1,req,"", "", formattedgivendate,endate, type,qryy);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
				}
		
			

			@SuppressWarnings("finally")
			public String getDashboardSummaryLatedataDrivereWise()
			{
				Date date = new Date();
				PrintWriter out = null;
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
				JSONObject result = new JSONObject();
				try{
				VtsDataDAO dao= VtsDataDAO.getInstance();
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				
				String endDate=req.getParameter("endDate").toString();
				String SelectedDate = req.getParameter("selectedenddate").toString();
				String type = req.getParameter("type");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(SelectedDate);
				int divId=Integer.parseInt(req.getParameter("divId"));
				int depotId=Integer.parseInt(req.getParameter("depotId"));
				String endate=cm.getDateFromPicker(endDate);
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 && depotId ==0){         // only depot="All" selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}else {
						
						 // if division and depot selected
						qryy= "in('"+depotId+"')";  // qryy="in(10)"
					}
				
				
					resp.setContentType("application/json");
					out = resp.getWriter();
	            	  result = getDashboardSummaryDriverwiseLateDept(1,req,"", "", formattedgivendate,endate, type,qryy);
	           
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
				}		
			
		

			@SuppressWarnings("finally")
			public String getDashboardSummaryEarlydataScheWise()
			{
				Date date = new Date();
				PrintWriter out = null;
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
				JSONObject result = new JSONObject();
				try{
				VtsDataDAO dao= VtsDataDAO.getInstance();
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				
				String endDate=req.getParameter("endDate").toString();
				String SelectedDate = req.getParameter("selectedenddate").toString();
				String type = req.getParameter("type");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(SelectedDate);
				int divId=Integer.parseInt(req.getParameter("divId"));
				int depotId=Integer.parseInt(req.getParameter("depotId"));
				String endate=cm.getDateFromPicker(endDate);
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 && depotId ==0){         // only depot="All" selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}else {
						 // if division and depot selected
						qryy= "in('"+depotId+"')";  // qryy="in(10)"
					}
				
					resp.setContentType("application/json");
					out = resp.getWriter();
			
	            	  result = getDashboardSummarySchwiseEarly(1,req,"", "", formattedgivendate,endate, type,qryy);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
				}
			
			@SuppressWarnings("finally")
			public String getDashboardSummaryEarlydataDriverWise()
			{
				Date date = new Date();
				PrintWriter out = null;
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
				JSONObject result = new JSONObject();
				try{
				VtsDataDAO dao= VtsDataDAO.getInstance();
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				String endDate=req.getParameter("endDate").toString();
				String SelectedDate = req.getParameter("selectedenddate").toString();
				String type = req.getParameter("type");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(SelectedDate);
				int divId=Integer.parseInt(req.getParameter("divId"));
				int depotId=Integer.parseInt(req.getParameter("depotId"));
				String endate=cm.getDateFromPicker(endDate);
				
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 && depotId ==0){         // only depot="All" selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}else {
						
						 // if division and depot selected
						qryy= "in('"+depotId+"')";  // qryy="in(10)"
					}
					resp.setContentType("application/json");
					out = resp.getWriter();
	      
	            	  result = getDashboardSummaryDriverwiseEarly(1,req,"", "", formattedgivendate,endate, type,qryy);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
				}
			
			@SuppressWarnings("finally")
			public String getDashboardSummaryPartialDataScheWise()
			{
				Date date = new Date();
				PrintWriter out = null;
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
				JSONObject result = new JSONObject();
				try{
				VtsDataDAO dao= VtsDataDAO.getInstance();
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				
				String endDate=req.getParameter("endDate").toString();
				String SelectedDate = req.getParameter("selectedenddate").toString();
				String type = req.getParameter("type");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(SelectedDate);
				int divId=Integer.parseInt(req.getParameter("divId"));
				int depotId=Integer.parseInt(req.getParameter("depotId"));
				String endate=cm.getDateFromPicker(endDate);
				
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 && depotId ==0){         // only depot="All" selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}else {
						
						 // if division and depot selected
						qryy= "in('"+depotId+"')";  // qryy="in(10)"
					}
				
					resp.setContentType("application/json");
					out = resp.getWriter();
	            	  result = getDashboardSummarySchwisePartialOp(1,req,"", "", formattedgivendate,endate, type,qryy);
	           
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
				}
			
		@SuppressWarnings("finally")
		public String getdashboardSkipStopDetails()
		{
			
			Date date = new Date();
			PrintWriter out = null;
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			JSONObject result = new JSONObject();
			try{
			VtsDataDAO dao= VtsDataDAO.getInstance();
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			
			String SelectedDate = req.getParameter("selectedenddate").toString();
			String type = req.getParameter("type");
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(SelectedDate);
			String fromDate = formattedgivendate + " 00:00:00";
			String toDate = formattedgivendate + " 23:59:59";
			String endDate=req.getParameter("endDate").toString();
	
			String endate=cm.getDateFromPicker(endDate);
			endate =endate+" 23:59:59";
			
			
			int divId=Integer.parseInt(req.getParameter("divId"));
			int depotId=Integer.parseInt(req.getParameter("depotId"));
			String qryy="";
			String qry2="in(";
			String qry="";
			
			if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
				qryy=" !=0";
				
			}else
				
				if(divId !=0 && depotId ==0){         // only depot="All" selected 
					List<String> l1 = dao.getDepotId(divId);
					for (int i = 0; i < l1.size(); i++) {
						 qry +=l1.get(i).toString()+",";
					}
					qry =qry2+qry+")";                   
					String depotIn=remove(qry);
					qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
					
				}else {
					
					 // if division and depot selected
					qryy= "in('"+depotId+"')";  // qryy="in(10)"
				}
//			System.out.println("added--"+qryy);
			
				resp.setContentType("application/json");
				out = resp.getWriter();
		
            	  result = getDashboardSummarySkipstop(1,req,"", "", fromDate,endate, type,qryy);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
			}
		

		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashBoardSummaryLateDepartureWithoutDate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type, String queryy) {
			JSONObject result = new JSONObject();
			Common common = new Common();
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				VtsResponse6 alertresult = port.webGetDashBoardSummaryLateDepartureWithoutDate(
						selectedstartDate,selectedendDate, type, rbKey, queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 int depotid= alertresult.getWaybillDetails().get(i).getDepotId();
					 int totalSchedule = geTotalSchedule(depotid);
					 ja.add(totalSchedule);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
//					 ja.add(alertresult.getWaybillDetails().get(i).getPer());
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
	
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashBoardSummaryLateDepartureWithDate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String depotIn) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				VtsResponse6 alertresult = port.webGetDashBoardSummaryLateDepartureWithDate(
						selectedstartDate,selectedendDate, type, rbKey,depotIn);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 int depot= alertresult.getWaybillDetails().get(i).getDepotId();
					 int totalSchedule= geTotalSchedule(depot);
					 ja.add(totalSchedule);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashBoardSummaryEarlyArrivalWithoutDate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String depotIn) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				VtsResponse6 alertresult = port.webGetDashBoardSummaryEarlyArrivalWithoutDate(
						selectedstartDate,selectedendDate, type, rbKey,depotIn);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 int depot = alertresult.getWaybillDetails().get(i).getDepotId();
					 int totalSch= geTotalSchedule(depot);
					 ja.add(totalSch);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 
					/* DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);*/
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashBoardSummaryEarlyArrivalWithDate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String depotIn) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
			
				VtsResponse6 alertresult = port.webGetDashBoardSummaryEarlyArrivalWithDate(
						selectedstartDate,selectedendDate, type, rbKey,depotIn);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 int depotNo = alertresult.getWaybillDetails().get(i).getDepotId();
					 int totl = geTotalSchedule(depotNo);
					 ja.add(totl);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 
					/* DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);*/
					
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashBoardSummaryScheduleNotDepartedWithoutDate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String depotIn) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
			
				VtsResponse6 alertresult = port.webGetDashBoardSummaryScheduleNotDepartedWithoutDate(
						selectedstartDate,selectedendDate, type, rbKey,depotIn);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					int depot=alertresult.getWaybillDetails().get(i).getDepotId();
					 int total=geTotalSchedule(depot);
					 ja.add(total);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 
					 /*DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);*/
					
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashBoardSummaryScheduleNotDepartedWithDate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String depotIn) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				VtsResponse6 alertresult = port.webGetDashBoardSummaryScheduleNotDepartedWithDate(
						selectedstartDate,selectedendDate, type, rbKey,depotIn);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 int depotId = alertresult.getWaybillDetails().get(i).getDepotId();
					 int ttl=geTotalSchedule(depotId);
					 ja.add(ttl);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 
					/* DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);
*/										 
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashboardSummaryPartialCancellation(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String queryy) {
			JSONObject result = new JSONObject();
			Common common = new Common();
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				
				VtsResponse6 alertresult = port.webGetPartialCancellation(selectedstartDate,selectedendDate, type, rbKey,queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
					 ja.add(alertresult.getWaybillDetails().get(i).getShiftTypeName());
					 ja.add(alertresult.getWaybillDetails().get(i).getSchDistance());
					 ja.add(alertresult.getWaybillDetails().get(i).getGpsDistance());
					 
					 // round decimal value upto 2 place
					 DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);
//					 ja.add(alertresult.getWaybillDetails().get(i).getPer());
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getDashboardSummaryNrd(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String queryy) {
			JSONObject result = new JSONObject();
			Common common = new Common();
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				double percval=0.0;
				int total=0;
				double ttt=0.0;
				
				 int totalCount=0;
				VtsResponse6 alertresult = port.webGetDashboardSummaryNRD(selectedstartDate,selectedendDate, type, rbKey,queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 
					 int depotId = alertresult.getWaybillDetails().get(i).getDepotId();
					  total = geTotalVehicles(depotId,selectedstartDate,selectedendDate);
					 ja.add(total);
					 ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());
					 
					 totalCount=alertresult.getWaybillDetails().get(i).getTotalcount();
			
					  ttt=(double) totalCount;
					  double tol=(double) total;
					 if(total==0){
						
					      percval=0.0;
					 }else{
						 percval = (ttt/tol)*100.0;
						 
					 }
					 // round decimal value upto 2 place
					 DecimalFormat df = new DecimalFormat("#.##");      
						 percval = Double.valueOf(df.format(percval));
					 ja.add(percval);
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getDashboardSummarySchwiseLateDept(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String queryy) {
			JSONObject result = new JSONObject();
			Common common = new Common();
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				
				VtsResponse6 alertresult = port.webGetDashboardSummaryScheduleWiseLateDept(selectedstartDate,selectedendDate, type, rbKey,queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
		             ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());
					 
					 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertSchWiseLateDept('"
								+ alertresult.getWaybillDetails().get(i).getDepotId()
                          + "','"
		                        + alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
		                        +"','"
		                        +selectedstartDate
		                        +"','"
		                        +selectedendDate
								+ "') title='Alert Details' id='alert_details"
								+ alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
								+ "' value='"
								+ alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
								+ "'>"
								+ alertresult.getWaybillDetails().get(i).getTotalcount()
								+ "</a>");
					 // round decimal value upto 2 place
					/* DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);*/
				
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }	   
		
					 
						@SuppressWarnings("unchecked")
						public JSONObject getDashboardSummarySchwisePartialOp(int j,
								HttpServletRequest req, String string, String string2,
								String selectedstartDate,String selectedendDate, String type,String queryy) {
							JSONObject result = new JSONObject();
							Common common = new Common();
							String shift="";
							try {
								WsUtil_Service service = new WsUtil_Service();
								WsUtil port = service.getWsUtilPort();
								
								
								VtsResponse6 alertresult = port.webGetDashboardSummarySchWisePartialOperated(selectedstartDate,selectedendDate, type, rbKey,queryy);
								JSONArray array = new JSONArray();
								for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
									 JSONArray ja = new JSONArray();
									 ja.add(i + 1);
									
									 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
									 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());

//									 ja.add(alertresult.getWaybillDetails().get(i).getShiftTypeName());
									 

									 int dutytypeid=alertresult.getWaybillDetails().get(i).getDutyTypeId();
									 
									 switch (alertresult.getWaybillDetails().get(i)
												.getDutyTypeId()) {
										case 1:
											shift = "General Shift";
											break;
										case 2:
											shift = "Day 1";
											break;
										case 3:
											shift = "Day 2";
											break;
										case 4:
											shift = "Shift 1";
											break;
										case 5:
											shift = "Shift 2";
											break;
										case 6:
											shift = "Day 1 old";
											break;
										case 7:
											shift = "Day 2 old";
											break;
										case 78:
											shift = "Night Service";
											break;
										case 79:
											shift = "Shift 1";
											break;
										case 80:
											shift = "Shift 2";
											break;
										case 82:
											shift = "Split Service";
											break;
										}
										ja.add(shift);

									 // round decimal value upto 2 place
										 DecimalFormat df = new DecimalFormat("#.##");      
											double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getSchDistance()));
										 ja.add(percVal);
										 
										/*ja.add("<a data-toggle='modal'  role='button' href='#mymodal4'  onclick=javascript:viewPartialCountDetails('"
													+ alertresult.getWaybillDetails().get(i).getDepotName()
													+ "','"
													+ alertresult.getWaybillDetails().get(i).getSCHEDULENO()+"','"+selectedstartDate+"','"+selectedendDate+"') >"
													+ alertresult.getWaybillDetails().get(i).getTotalcount()
													+ "</a>");	 
									 */
//									 ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());
//									 ja.add(alertresult.getWaybillDetails().get(i).getSchDistance());
										 
										 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertScheduledist80percent('"
													+ alertresult.getWaybillDetails().get(i).getDepotId()
	                                             + "','"
							                        + alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
							                        +"','"
							                        +selectedstartDate
							                        +"','"
							                        +selectedendDate
							                        +"','"
							                        +dutytypeid
													+ "') title='Alert Details' id='alert_details"
													+ alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
													+ "' value='"
													+ alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
													+ "'>"
													+ alertresult.getWaybillDetails().get(i).getTotalcount()
													+ "</a>"); 
//									 ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());

								
									 array.add(ja);
									
									 }
									 result.put("aaData", array);
									 } catch (Exception ex) {
									 ex.printStackTrace();
									 }
									 return result;
									 }
						@SuppressWarnings("unchecked")
						public JSONObject getDashboardSummaryDriverwiseEarly(int j,
								HttpServletRequest req, String string, String string2,
								String selectedstartDate,String selectedendDate, String type,String queryy) {
							JSONObject result = new JSONObject();
							Common common = new Common();
							try {
								WsUtil_Service service = new WsUtil_Service();
								WsUtil port = service.getWsUtilPort();
								
								VtsResponse6 alertresult = port.webGetDashboardSummaryDriverWiseEarlyArrival(selectedstartDate,selectedendDate, type, rbKey,queryy);
								JSONArray array = new JSONArray();
								for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
									 JSONArray ja = new JSONArray();
									 ja.add(i + 1);
								
									 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
									 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
									 ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
									 ja.add(alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO());
									// ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());
									 System.out.println("alertresult.getWaybillDetails().get(i).getDepotId()==="+alertresult.getWaybillDetails().get(i).getDepotId());
									 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertDriverWiseEarlyArrival('"
												+ alertresult.getWaybillDetails().get(i).getDepotId()
                                             + "','"
						                        + alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO()
						                        +"','"
						                        +selectedstartDate
						                        +"','"
						                        +selectedendDate
												+ "') title='Alert Details' id='alert_details"
												+ alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO()
												+ "' value='"
												+ alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO()
												+ "'>"
												+ alertresult.getWaybillDetails().get(i).getTotalcount()
												+ "</a>");
									 
									 // round decimal value upto 2 place
									/* DecimalFormat df = new DecimalFormat("#.##");      
										double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
									 ja.add(percVal);*/
								
									 array.add(ja);
									
									 }
									 result.put("aaData", array);
									 } catch (Exception ex) {
									 ex.printStackTrace();
									 }
									 return result;
									 }
						@SuppressWarnings("unchecked")
						public JSONObject getDashboardSummarySchwiseEarly(int j,
								HttpServletRequest req, String string, String string2,
								String selectedstartDate,String selectedendDate, String type,String queryy) {
							JSONObject result = new JSONObject();
							Common common = new Common();
							try {
								WsUtil_Service service = new WsUtil_Service();
								WsUtil port = service.getWsUtilPort();
								
								VtsResponse6 alertresult = port.webGetDashboardSummarySchWiseEarlyArrival(selectedstartDate,selectedendDate, type, rbKey,queryy);
								JSONArray array = new JSONArray();
								for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
									 JSONArray ja = new JSONArray();
									 ja.add(i + 1);
									 
									 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
									 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//									 String sch[]=alertresult.getWaybillDetails().get(i).getSCHEDULENAME().split(" ");
//									 String schName=sch[0];
//									 System.out.println("sch name after split  "+schName);
									 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertScheduleWiseEarlyArrival('"
												+ alertresult.getWaybillDetails().get(i).getDepotId()
                                                + "','"
						                        + alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "@")
						                        +"','"
						                        +selectedstartDate
						                        +"','"
						                        +selectedendDate
												+ "') title='Alert Details' id='alert_details"
												+ alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "")
												+ "' value='"
												+ alertresult.getWaybillDetails().get(i).getSCHEDULENAME().replace(" ", "")
												+ "'>"
												+ alertresult.getWaybillDetails().get(i).getTotalcount()
												+ "</a>");
									 
									 //ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());
									 
									 // round decimal value upto 2 place
									/* DecimalFormat df = new DecimalFormat("#.##");      
										double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
									 ja.add(percVal);*/
								
									 array.add(ja);
									
									 }
									 result.put("aaData", array);
									 } catch (Exception ex) {
									 ex.printStackTrace();
									 }
									 return result;
									 }
						@SuppressWarnings("unchecked")
						public JSONObject getDashboardSummaryDriverwiseLateDept(int j,
								HttpServletRequest req, String string, String string2,
								String selectedstartDate,String selectedendDate, String type,String queryy) {
							JSONObject result = new JSONObject();
							Common common = new Common();
							try {
								WsUtil_Service service = new WsUtil_Service();
								WsUtil port = service.getWsUtilPort();
								
								VtsResponse6 alertresult = port.webGetDashboardSummaryDriverWiseLateDept(selectedstartDate,selectedendDate, type, rbKey,queryy);
								JSONArray array = new JSONArray();
								for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
									 JSONArray ja = new JSONArray();

									 ja.add(i + 1);
									 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
//									 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
									 ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
									 ja.add(alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO());
//									 ja.add(alertresult.getWaybillDetails().get(i).getTotalcount());
									// System.out.println("alertresult.getWaybillDetails().get(i).getDepotId()=="+alertresult.getWaybillDetails().get(i).getDepotId());
									 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertDriverWiseLateDept('"
												+ alertresult.getWaybillDetails().get(i).getDepotId()
                                                + "','"
						                        + alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO()
						                        +"','"
						                        +selectedstartDate
						                        +"','"
						                        +selectedendDate
												+ "') title='Alert Details' id='alert_details"
												+ alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO()
												+ "' value='"
												+ alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO()
												+ "'>"
												+ alertresult.getWaybillDetails().get(i).getTotalcount()
												+ "</a>");
								
									 array.add(ja);
									 }
									 result.put("aaData", array);
									 } catch (Exception ex) {
									 ex.printStackTrace();
									 }
									 return result;
									 }
					 
		
		@SuppressWarnings("unchecked")
		public JSONObject getDashboardSummaryrouteDeviate(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String queryy) {
			JSONObject result = new JSONObject();
			Common common = new Common();
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				
				VtsResponse6 alertresult = port.webGetDashboardSummaryRouteDeviateDetail(selectedstartDate,selectedendDate, type, rbKey,queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 
					 int depotId =  alertresult.getWaybillDetails().get(i).getDepotId();
					 int totalSch = geTotalSchedule(depotId);
					 ja.add(totalSch);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getCompletedTrip());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 // round decimal value upto 2 place
					/* DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);*/
				
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getDashboardSummarySkipstop(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String queryy) {
			JSONObject result = new JSONObject();
			Common common = new Common();
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				
				VtsResponse6 alertresult = port.webGetDashboardSummarySkipStop(selectedstartDate,selectedendDate, type, rbKey,queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					 
					 int depot_id=alertresult.getWaybillDetails().get(i).getDepotId();
					 
					 int totalSch = geTotalSchedule(depot_id);
					
					 ja.add(totalSch);
					 ja.add(alertresult.getWaybillDetails().get(i).getGeneralShift());
					 ja.add(alertresult.getWaybillDetails().get(i).getCompletedTrip());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift1());
					 ja.add(alertresult.getWaybillDetails().get(i).getSplitservice());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightOut());
					 ja.add(alertresult.getWaybillDetails().get(i).getShift2());
					 ja.add(alertresult.getWaybillDetails().get(i).getNightshift());
					 ja.add(alertresult.getWaybillDetails().get(i).getTotal());
					 // round decimal value upto 2 place
					/* DecimalFormat df = new DecimalFormat("#.##");      
						double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
					 ja.add(percVal);*/
					
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForDashboardSummaryActualRunningTimeOfSchedule(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String type,String queryy) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				
				VtsResponse6 alertresult = port.webGetActualRunningTime(selectedstartDate,selectedendDate, type, rbKey,queryy);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
					 ja.add(alertresult.getWaybillDetails().get(i).getShiftTypeName());
					 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
					 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
					 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		
		
		
		public int geTotalSchedule(int depotId){
			int count=0;
			Session session =null;
			try{
			String query="select count(*) as count from schedule where (status = 'ACTIVE' or status = 'NEW' or status='Rationalised') " +
					" AND (current_status = 'APPROVED' or current_status = 'CASE WORKER')  AND deleted_status=0 and depot_id='"+depotId+"' " ;
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query qry = session.createSQLQuery(query).addScalar("count",Hibernate.INTEGER);
			count = (Integer) qry.uniqueResult();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				
			}
			return count;
		}
		
		
		public int geTotalVehicles(int depotId,String date1,String date2){
			int count=0;
			Session session =null;
			try{
			String query="SELECT count(*)*(DATEDIFF ('"+date2+"', '"+date1+"')+1) count FROM `vehicle` " +
					" WHERE `org_chart_id` = '"+depotId+"' AND `status` = 'active' AND `deleted_status` = '0' and cause_status !='s' " ;
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query qry = session.createSQLQuery(query).addScalar("count",Hibernate.INTEGER);
			count = (Integer) qry.uniqueResult();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				
			}
			return count;
		}
	
		public String getAlertScheduleWiseEarlyArrival() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			JSONObject result = new JSONObject();
			Session session = null;
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				Date FromDate = new Date();
				String depot = req.getParameter("depot");
				String schedulename = req.getParameter("schedulename");
				String schName=req.getParameter("schedulename").replace("@"," ");
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(startdate);
				String enddate1 = cm.getDateFromPicker(enddate);
				
				out = resp.getWriter();
				
				result = getAlertScheduleWiseEarlyArrivalDetails(1,req,"", "", startdate,enddate, depot,schName);
				out.print(result);
			} catch (Exception ex) {
             ex.printStackTrace();
			} finally {
//				session.close();
			}
			return null;
		}
	
		
		@SuppressWarnings({ "unchecked", "unused" })
		public JSONObject getAlertScheduleWiseEarlyArrivalDetails(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String depot,String schedulename) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
		
				Common common = new Common();
				try {
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					
					VtsResponse6 alertresult = port.webGetDashboardSummarySchWiseEarlyArrivalDrill(selectedstartDate,selectedendDate, depot, rbKey,"",schedulename);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
						 JSONArray ja = new JSONArray();
						 ja.add(i + 1);
					
						 
						 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						 ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
						 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getENTRYTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getDifference());
						 ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
//						 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
						 
						 // round decimal value upto 2 place
						/* DecimalFormat df = new DecimalFormat("#.##");      
							double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
						 ja.add(percVal);*/
					
						 array.add(ja);
						
						 }
						 result.put("aaData", array);
						 } catch (Exception ex) {
						 ex.printStackTrace();
						 }
						 return result;
			}
		
		
		public String getAlertDriverWiseEarlyArrival() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			JSONObject result = new JSONObject();
			Session session = null;
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				Date FromDate = new Date();
				String depot = req.getParameter("depot");
				int drivertokenno = Integer.parseInt(req.getParameter("drivertokenno"));
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(startdate);
				String enddate1 = cm.getDateFromPicker(enddate);
				
				out = resp.getWriter();
				
				result = getAlertDriverWiseEarlyArrivalDetails(1,req,"", "", startdate,enddate, depot,drivertokenno);
				out.print(result);
			} catch (Exception ex) {
             ex.printStackTrace();
			} finally {
//				session.close();
			}
			return null;
		}
		
		@SuppressWarnings({ "unchecked", "unused" })
		public JSONObject getAlertDriverWiseEarlyArrivalDetails(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String depot,int drivertokenno) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
			//System.out.println("hello");
				Common common = new Common();
				try {
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					VtsResponse6 alertresult = port.webGetDashboardSummaryDriverWiseEarlyArrivalDrill(selectedstartDate,selectedendDate, depot, rbKey,"",drivertokenno);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
						 JSONArray ja = new JSONArray();
						 ja.add(i + 1);
						 
						 ja.add(alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO());
						 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getENTRYTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getDifference());
						 ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
						 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
						 
						 // round decimal value upto 2 place
						/* DecimalFormat df = new DecimalFormat("#.##");      
							double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
						 ja.add(percVal);*/
					
						 array.add(ja);
						
						 }
						 result.put("aaData", array);
						 } catch (Exception ex) {
						 ex.printStackTrace();
						 }
						 return result;
			}
	
		
		public String getAlertSchWiseLateDept() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			JSONObject result = new JSONObject();
			Session session = null;
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				Date FromDate = new Date();
				String depot = req.getParameter("depot");
				String schedulename = req.getParameter("schedulename").replace("@", " ");
				
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(startdate);
				String enddate1 = cm.getDateFromPicker(enddate);
			
				out = resp.getWriter();
				
				result = getAlertSchWiseLateDeptDetails(1,req,"", "", startdate,enddate, depot,schedulename);
				out.print(result);
			} catch (Exception ex) {
             ex.printStackTrace();
			} finally {
//				session.close();
			}
			return null;
		}
		
		@SuppressWarnings({ "unchecked", "unused" })
		public JSONObject getAlertSchWiseLateDeptDetails(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String depot,String schedulename) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
		
				Common common = new Common();
				try {
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					
					VtsResponse6 alertresult = port.webGetDashboardSummaryScheduleWiseLateDeptDrill(selectedstartDate,selectedendDate, depot, rbKey,"",schedulename);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
						 JSONArray ja = new JSONArray();
						 ja.add(i + 1);
					
						 
						 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						 ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
						 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getACTTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getDEPDIFF());
						 ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
//						 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
						 
						 // round decimal value upto 2 place
						/* DecimalFormat df = new DecimalFormat("#.##");      
							double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
						 ja.add(percVal);*/
					
						 array.add(ja);
						
						 }
						 result.put("aaData", array);
						 } catch (Exception ex) {
						 ex.printStackTrace();
						 }
						 return result;
			}
		
		
		public String getAlertDriverWiseLateDept() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			JSONObject result = new JSONObject();
			Session session = null;
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				Date FromDate = new Date();
				String depot = req.getParameter("depot");
				//System.out.println("depot=="+depot);
				int drivertokenno = Integer.parseInt(req.getParameter("drivertokenno"));
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(startdate);
				String enddate1 = cm.getDateFromPicker(enddate);
			
				out = resp.getWriter();
				
				result = getAlertDriverWiseLateDeptDetails(1,req,"", "", startdate,enddate, depot,drivertokenno);
				out.print(result);
			} catch (Exception ex) {
             ex.printStackTrace();
			} finally {
//				session.close();
			}
			return null;
		}
		
		@SuppressWarnings({ "unchecked", "unused" })
		public JSONObject getAlertDriverWiseLateDeptDetails(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String depot,int drivertokenno) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
		
			System.out.println("depot  "+depot);
				Common common = new Common();
				try {
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					
					VtsResponse6 alertresult = port.webGetDashboardSummaryDriverWiseLateDeptDrill(selectedstartDate,selectedendDate, depot, rbKey,"",drivertokenno);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
						 JSONArray ja = new JSONArray();
						 ja.add(i + 1);
					
						// System.out.println(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						 ja.add(alertresult.getWaybillDetails().get(i).getDRIVERTOKENNO());
						 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getACTTIME());
						 ja.add(alertresult.getWaybillDetails().get(i).getDEPDIFF());
						 ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
						 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
						 
						 // round decimal value upto 2 place
						/* DecimalFormat df = new DecimalFormat("#.##");      
							double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
						 ja.add(percVal);*/
					
						 array.add(ja);
						
						 }
						 result.put("aaData", array);
						 } catch (Exception ex) {
						 ex.printStackTrace();
						 }
						 return result;
			}
		
		
		public String getAlertScheduledist80percent() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			JSONObject result = new JSONObject();
			Session session = null;
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				Date FromDate = new Date();
				String depot = req.getParameter("depot");
				System.out.println("depot=="+depot);
				String schedulename = req.getParameter("schdulename").replace("@", " ");
				int dutytypeid = Integer.parseInt(req.getParameter("dutytypeid"));
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				Common cm = new Common();
				String formattedgivendate = cm.getDateFromPicker(startdate);
				String enddate1 = cm.getDateFromPicker(enddate);
			
				out = resp.getWriter();
				
				result = getgetAlertScheduledist80percentDetails(1,req,"", "", startdate,enddate, depot,schedulename,dutytypeid);
				out.print(result);
			} catch (Exception ex) {
             ex.printStackTrace();
			} finally {
//				session.close();
			}
			return null;
		}
		
		@SuppressWarnings({ "unchecked", "unused" })
		public JSONObject getgetAlertScheduledist80percentDetails(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate, String depot,String schedulename,int dutytypeid) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
		
			//System.out.println("hello");
				Common common = new Common();
				try {
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					
					VtsResponse6 alertresult = port.webGetDashboardSummarySchWisePartialOperatedDrill(selectedstartDate,selectedendDate, depot, rbKey,"",dutytypeid,schedulename);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
						 JSONArray ja = new JSONArray();
						 ja.add(i + 1);
					
						 
						 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						 ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
						 ja.add(alertresult.getWaybillDetails().get(i).getSchDistance());
						 ja.add(alertresult.getWaybillDetails().get(i).getDISTANCE());
						 ja.add(alertresult.getWaybillDetails().get(i).getPer());
						 ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
						 
						 // round decimal value upto 2 place
						/* DecimalFormat df = new DecimalFormat("#.##");      
							double percVal = Double.valueOf(df.format(alertresult.getWaybillDetails().get(i).getPer()));
						 ja.add(percVal);*/
					
						 array.add(ja);
						
						 }
						 result.put("aaData", array);
						 } catch (Exception ex) {
						 ex.printStackTrace();
						 }
						 return result;
			}
}
