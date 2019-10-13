package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.common.Common;
import com.trimax.its.report.dao.EPKMReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.json.simple.JSONObject;

	public class EPKMReportSchduleWise {
		
	private Map<Integer, String> divisionlist;
	public String startdate;
	private String depotlist1;
	public String divisionlist1;
	private int scheduleNo;
    private String endDate;
    
	public String getEndDate() {
	return endDate;
    }
     public void setEndDate(String endDate) {
	this.endDate = endDate;
    }
	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

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

//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString = new StringBuffer();
	
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
		
		 public static String rbKey = String.valueOf(getSysParameterForVts());
		 
		public String getData()
 {
			  HttpServletResponse response = ServletActionContext.getResponse();
		try {
			JSONObject result = new JSONObject();
			VtsDataDAO dao= VtsDataDAO.getInstance();
			EPKMReportDao edao= new EPKMReportDao();
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			System.out.println("depotlist1   "+depotlist1+"===="+divisionlist1);
			int depotId=Integer.parseInt(depotlist1);
			int divId=Integer.parseInt(divisionlist1);
//			scheduleId;
			String qryy="";
			String qry2="in(";
			String qry="";
			String schId="";
//			if(divId == 0 && depotId == 0){      // if div and depot selected 'All'
//				qryy=" !=0";
//				
//			}else
				
				if(divId !=0 && depotId ==0){         // only depot="All" selected 
					List<String> l1 = dao.getDepotId(divId);
					for (int i = 0; i < l1.size(); i++) {
						 qry +=l1.get(i).toString()+",";
					}
					qry =qry2+qry+")";                   
					System.out.println("qry is====="+qry);
					String depotIn=remove(qry);
					qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
					schId = "!=''";
					
				}else if(divId !=0 && depotId !=0 && scheduleNo==0){
					qryy= "in('"+depotId+"')";
					schId = "!=''";
				}
			else {
					qryy = "in('"+depotId+"')";
					schId = "="+String.valueOf(scheduleNo);
					 // if division and depot selected
					  // qryy="in(10)"
				}
			System.out.println("added--"+qryy+scheduleNo);

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			Date endate = format.parse(endDate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
			String startDate1 = fomat2.format(startDate).toString();
			String enDate1 = fomat2.format(endate).toString();
			

			System.out.println("FROM " + startDate1 + " TO " + enDate1+"===="+schId);
//			model.jaxb.xml.trimax.com.VtsResponse6 activeresult = null;
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//			activeresult = port.webGetEPKMData(startDate1, todate,
//					qryy, rbKey); // calling Web service
//			
//
//			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Active Vehicle Report</br>From Date:- "
//					+ fromdate + " To Date:-" + todate + "</h4></br></div>");
//
//			regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
//					+ runDateTime + "</div></div>");
//
//		
//			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
//			regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot No</th><th>Schedule No</th><th>Shift Type</th><th>Waybill No</th><th>Vehicle No</th><th>Sch KM</th><th>Log KM</th><th>GPS KM</th><th>Revenue</th><th>Log EPKM</th><th>GPS EPKM</th>"
//					+ "</tr></thead><tbody>");
//
//
//			HttpServletResponse response = ServletActionContext.getResponse();
//			int i = 1;
//			int j=1;
//			System.out.println("size-"+ activeresult.getWaybillDetails().size());
//			for (i = 0; i < activeresult.getWaybillDetails().size(); i++) {
//				
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString.append("<td>" + j + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getDepotName()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getSCHEDULENO()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getShiftTypeName()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getWAYBILLNO()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getVEHICLENO() + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getSchDistance()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getRunningKM()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getGpsDistance()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getETIMCollAmt()+ "</td>");
//				
//				if(activeresult.getWaybillDetails().get(i).getForm4EPKM()== null){
//					regionTypeAjaxString.append("<td>"+0.00+"</td>");
//				}else{
//					regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getForm4EPKM()+ "</td>");
//				}
//				if(activeresult.getWaybillDetails().get(i).getVtuEPKM()== null){
//					regionTypeAjaxString.append("<td>"+0.00+"</td>");
//				}else{
//				regionTypeAjaxString.append("<td>"+ activeresult.getWaybillDetails().get(i).getVtuEPKM()+ "</td>");
//				}
//
//				regionTypeAjaxString.append("</tr>");
//
//				j++;
//			}
//
//			if (activeresult.getWaybillDetails().size() == 0) { // if no records
//				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
//				regionTypeAjaxString.append("</tr>");
//			}
			
			  response.setContentType("application/json");
  			response.setHeader("Cache-Control", "no-store");
  			PrintWriter out = response.getWriter();
  			result = edao.getDataForEPKMScheduleWise(enDate1,startDate1,qryy,schId);
  			out.print(result);       // to print
      		} catch (Exception e) {
      			e.printStackTrace();
      		}
		return null;
	}
		
		
		public String remove(String str) {
		    if (str != null && str.length() > 0 && str.charAt(str.length()-2)==',') {
		      str = str.substring(0, str.length()-2);
		    }
		    return str;
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
			session.close();
		}
		return param;
	}
			
	}
