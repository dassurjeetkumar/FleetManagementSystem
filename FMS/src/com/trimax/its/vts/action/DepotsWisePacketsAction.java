package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;

import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

public class DepotsWisePacketsAction {
	private Map<Integer,String> divisionlist ;
	public String startdate;
	public String enddate;
	public String pckt_limit;
	public String type;
	private String depotlist1;
	private String divisionlist1;
	
	public Map<Integer,String> getDivisionlist(){
		return  divisionlist;
	}
	
	public void setgetDivisionlist(Map<Integer,String> divisionlist) {
		this.divisionlist=divisionlist;
	}
	
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndDate() {
		return enddate;
	}

	public void setEndDate(String enddate) {
		this.enddate = enddate;
	}

	public String getPckt_limit() {
		return pckt_limit;
	}
	
	public void setPckt_limit(String pckt_limit) {
		this.pckt_limit = pckt_limit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String execute() {
		VtsDataDAO vvt=VtsDataDAO.getInstance();
		divisionlist=vvt.getDivisionName();
		return "success";
	}
	StringBuilder regionTypeAjaxString =new StringBuilder();
	
	
	public String getDepotsWisePackets(){
		VtsDataDAO dao = VtsDataDAO.getInstance();
		
		    Date ss1=new Date();
		    SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime=sdf.format(ss1);
		    int depotId=Integer.parseInt(depotlist1);
		    int divId=Integer.parseInt(divisionlist1);
		    System.out.println("Ashu depotlist1 "+depotlist1);
		    System.out.println("Ashu divlist1 "+divisionlist1);
		  //date format 
		    try {
		    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		    Date startDate = format.parse(startdate);
		    Date endDate=format.parse(enddate);
		    SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
		    
			String startdate1 = fomat2.format(startDate).toString();
			String ssdd1=startdate1;
			startdate1=startdate1+" 00:00:01";
			String enddate1 = fomat2.format(endDate).toString();
			String ssdd2=enddate1;
			enddate1=enddate1+" 23:59:59";
		    System.out.println("Ashu Start Date " +startdate1);
		    System.out.println("Ashu end Date " +enddate1);
		    System.out.println("Ashu packet limit" +pckt_limit);
		    type=type+" "+pckt_limit;
		    System.out.println("Ashu type" +type);
		   
		    try {
		    	String rbKey = String.valueOf(getSysParameterForVts());
		    NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
		    System.out.println("1");
			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
			System.out.println("2");
			VtsResponseNew result=null;;
			result=port.webGetDepotWisePackets(startdate1,enddate1,depotlist1,type,rbKey);
			
			
			System.out.println("after result");
			System.out.println(result);
			
			//printing coding for html by ashu
			
//			resultString+="<div id='header' style='display: none;'><div align='center'> </br>Depot Wise Packets </br> Date:- "+startdate1+"</br></h4></div>";
//
//			resultString+="<div align='right'><b>Printed Date: </b>"+runDateTime+"</div></div>";
//		        
//
//			resultString+="<div class='component'><table class='overflow-y' border='1'>";
//			//deviceId, d.licence_number, lat, longitude,No_Packets"
//			resultString+="<thead><tr><th>Device Id</th><th>Licence Number</th><th>Lat</th><th>Long</th><th>No Of Packets</th>" +
//				"</tr></thead><tbody>";
			System.out.println("Ashu Size is "+result.getVtsDatamodelnew().size());
//			for(int i=0;i<res.getVtsDatamodelnew().size();i++) {
//				VtsDataModelNew list=res.getVtsDatamodelnew().get(i);
//			    	
//				resultString+="<tr>";
//				//resultString+="<td align='right'>"+list.getDeviceId().toString()+"</td>";
//				resultString+="<td align='right'>1</td>";
//				resultString+="<td align='right'>1</td>";
//			resultString+="<td align='right'>1</td>";
//				resultString+="<td align='right'>1</td>";
//				resultString+="<td align='right'>1</td>";
//				
////				resultString+="<td align='right'>"+list.getLicenceId().toString()+"</td>";
////				resultString+="<td align='right'>"+list.getLat().toString()+"</td>";
////				resultString+="<td align='right'>"+list.getLongitude().toString()+"</td>";
////				resultString+="<td align='right'>"+list.getNoPackets().toString()+"</td>";
//				resultString+="</tr>";
//				resultString+="</tbody></table></div>";
//				
//			}
//			if (res.getVtsDatamodelnew().size() == 0) { // if no records
//				resultString+="<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
//				resultString+="<tr>";
//				resultString+="<td colspan='3' align='center'><b>No Result Found</b></td>";
//				resultString+="</tr>";
//				resultString+="</tbody></table></div>";
//			}
//
//			HttpServletResponse response = ServletActionContext.getResponse();
//			PrintWriter out;
//			out=response.getWriter();
//			out.print(resultString);
			
		
			regionTypeAjaxString
			.append(" <div id='header' style='display: none;'><div align='center'><h4>Depot Wise No of Packets REPORT</br>From Date:- "
					+ ssdd1
					+ " To Date:-"
					+ ssdd2
					+ "</h4></br></div>");
	regionTypeAjaxString
			.append("<div align='right'><b>Current Date:-</b>"
					+ runDateTime + "</div></div>");

	regionTypeAjaxString
			.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString
			.append("<thead><tr><th>Sr.No.</th><th>Device Id</th><th>Licence Number</th><th>No of Packets</th>"
					+ "</tr></thead><tbody>");

	HttpServletResponse response = ServletActionContext.getResponse();
	
	for (int i = 0; i < result.getVtsDatamodelnew().size() ;i++) {

		regionTypeAjaxString.append("<tr>");
		regionTypeAjaxString.append("<td>" + (i+1) + "</td>");
		regionTypeAjaxString.append("<td>"
				+ result.getVtsDatamodelnew().get(i).getDeviceId()
		        + "</td>");
		regionTypeAjaxString.append("<td>"
				+ result.getVtsDatamodelnew().get(i).getLicenceId()
				+ "</td>");
		regionTypeAjaxString.append("<td>"
				+ result.getVtsDatamodelnew().get(i).getNoPackets()
				+ "</td>");


		regionTypeAjaxString.append("</tr>");

	
	}

	if (result.getVtsDatamodelnew().size() == 0) { // if no records
		regionTypeAjaxString
				.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
		regionTypeAjaxString.append("<tr>");
		regionTypeAjaxString
				.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
		regionTypeAjaxString.append("</tr>");
	}

	PrintWriter out;
	out = response.getWriter();
	out.print(regionTypeAjaxString); // to print

			
			
		    }catch(Exception ex){
		    	System.out.println("Problem in Web service ");
		    	ex.printStackTrace();
		    }
		    }catch(Exception ex){
		    	System.out.println("problem in date format");
		    	ex.printStackTrace();
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
