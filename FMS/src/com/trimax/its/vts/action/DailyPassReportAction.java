

package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

	public class DailyPassReportAction {
		
	public String startdate;
	public String startdate11;
	public String Mobile_number;
	
	public String getMobile_number() {
		return Mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		Mobile_number = mobile_number;
	}

	public String getStartdate() {
		return startdate;
	}

	public String getStartdate11() {
		return startdate11;
	}

	public void setStartdate11(String startdate11) {
		this.startdate11 = startdate11;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	
//	String regionTypeAjaxString = "";
	StringBuilder regionTypeAjaxString=new StringBuilder();

	
	public String execute() {
		return "success";
	}
		
		
	//public static String rbKey = String.valueOf(getSysParameterForVts());

	public String getDailyPassReport() {
//		VtsDataDAO dao = VtsDataDAO.getInstance();
		//HttpServletRequest request=ServletActionContext.getRequest();
		try {
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = fomat2.format(startDate).toString();
			
			
			System.out.println("Ashu start Date"+startDate1);
			String regionTypeAjaxString="";
			
			try {
		    Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		       System.out.println("ashu before query ");
		    String q="select date(booking_time) travel_date,passenger_name,gender,mobile_number,ticket_type,nt.ticket_number ticket_no,count(nt.ticket_number) trip from nammapass_Details nd"+ 
		    		" inner join nammapass_transaction nt on  nd.ticket_number=nt.ticket_number where date(booking_time)='"+startDate1+"' group by ticket_no";
		    Query query = session.createSQLQuery(q).addScalar("travel_date").addScalar("passenger_name").addScalar("gender").addScalar("mobile_number").addScalar("ticket_type").addScalar("ticket_no").addScalar("trip");
		      System.out.println("After Query ");       
		    	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		    	System.out.println("after mapping ");
				List<Map<String,Object>> mapList = query.list();
				System.out.println("ashu query size is ..."+query.list().size());
				System.out.println("ashu =======>"+mapList.size());
				
				
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><td colspan='7' align='left'><h4>DAily Pass Reports </br>Date:- "+startDate1+" </h4></br></div>";

		        regionTypeAjaxString +="<div align='right'><td colspan='7' align='left'><b>Run Date:-</b>"+runDateTime+"</div></div>";
		       
		        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
                regionTypeAjaxString +="<td align='center' width='10%'><b>travel_date</b></td><td align='center' width='20%'><b>passenger_name</b></td>";
                regionTypeAjaxString +="<td align='center' width='20%'><b>gender</b></td><td align='center' width='20%'><b>mobile_number</b></td>";
                regionTypeAjaxString +="<td align='center' width='10%'><b>ticket_type</b></td><td align='center' width='20%'><b>ticket_number</b></td><td align='center' width='20%'><b>trip</b></td></tr>";
                for (int i = 0; i < mapList.size(); i++) {
                	System.out.println("ashu now in list"+mapList);
                    regionTypeAjaxString +="<tr>";
                    Map<String, Object> list = mapList.get(i);
                    System.out.println("ashu now in map "+list);
                    
                    regionTypeAjaxString +="<td>"+list.get("travel_date").toString()+"</td>";
                    
                    regionTypeAjaxString +="<td>"+list.get("passenger_name").toString()+"</td>";
                    System.out.println(list.get("passenger_name").toString());
                    regionTypeAjaxString +="<td>"+list.get("gender").toString()+"</td>";
                    System.out.println(list.get("gender").toString());
                    regionTypeAjaxString +="<td>"+list.get("mobile_number").toString()+"</td>";
                    System.out.println(list.get("passenger_name").toString());
                    regionTypeAjaxString +="<td>"+list.get("ticket_type")+"</td>";
                    regionTypeAjaxString +="<td>"+list.get("ticket_no").toString()+"</td>";
                    regionTypeAjaxString +="<td> <a data-toggle='modal'  role='button' href='#mymodal313'  onclick=javascript:viewPassDetails('"
                    		+list.get("travel_date").toString()
                    		+"','"
                    		+list.get("mobile_number").toString()
                    		+"') >"
                    		+list.get("trip").toString()
                    		+"</a>"
                    		+"</td>";
                   
                }
                HttpServletResponse response = ServletActionContext.getResponse();
                PrintWriter out;
               
   
                out = response.getWriter();
                    out.print(regionTypeAjaxString);
				
		       
		    } catch (Exception ex) {
		    	System.out.println("Ashu problem in hibernate ");
		    	ex.printStackTrace();
		    } finally {
		        HibernateUtil.closeSession();
		    }
			
			
		} catch (Exception e) {

			e.printStackTrace();
	}

		return null;
	}
	
	
	//method for getting details 
	public String getDailyPassDetails(){
		System.out.println("ashu called getDailyPassDetails");
		Date ss12 = new Date();
		SimpleDateFormat sdfo = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdfo.format(ss12);
try {
	System.out.println("inside try method 2");
		SimpleDateFormat format3 = new SimpleDateFormat("dd-MM-yyyy");
//		Date startDate3 = format3.parse(startdate11);
		System.out.println("here one more step ");
//		SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
//		String startDate12 = fomat2.format(startDate3).toString();
		
		
//		System.out.println("Ashu method 2 start Date"+startDate12);
		String regionTypeAjaxString="";
		
		try {

			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	       System.out.println("ashu2 before query ");
	       String q="select date(booking_time) as travel_date,passenger_name,gender,mobile_number,ticket_type,nt.ticket_number as ticket_no,nt.conductor_id as conductr_id,nt.route_number as route_no from nammapass_Details " 
	       + "nd inner join nammapass_transaction nt on  nd.ticket_number=nt.ticket_number where date(booking_time)='"+startdate11+"'and nd.mobile_number='"+Mobile_number+"'";
	       
	     Query query = session.createSQLQuery(q).addScalar("travel_date").addScalar("passenger_name").addScalar("gender").addScalar("mobile_number").addScalar("ticket_type").addScalar("ticket_no").addScalar("Conductr_id").addScalar("Route_no");
	      System.out.println("After Query 2");       
	    	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    	System.out.println("after mapping ");
			List<Map<String,Object>> mapList = query.list();
			System.out.println("ashu query2 size is ..."+query.list().size());
			System.out.println("ashu2 =======>"+mapList.size());
	
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><td colspan='7' align='left'><h4>Daily Namma Pass Reports </br> Dated: "+startdate11+" </h4></br></div>";

	        regionTypeAjaxString +="<div align='right'><td colspan='7' align='left'><b>Run Date:-</b>"+runDateTime+"</div>";
	       //verflow-y:scroll
	        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
            regionTypeAjaxString +="<td align='center' width='10%'><b>Travel_date</b></td><td align='center' width='20%'><b>Passenger Name</b></td>";
            regionTypeAjaxString +="<td align='center' width='20%'><b>Gender</b></td><td align='center' width='20%'><b>Mobile Number</b></td>";
            regionTypeAjaxString +="<td align='center' width='10%'><b>Ticket Type</b></td><td align='center' width='20%'><b>Ticket Number</b></td><td align='center' width='20%'><b>Conductor ID</b></td><td align='center' width='20%'><b>Route No </b></td></tr>";
            for (int i = 0; i < mapList.size(); i++) {
            	System.out.println("ashu 2 now in list"+mapList);
                regionTypeAjaxString +="<tr>";
                Map<String, Object> list = mapList.get(i);
                System.out.println("ashu 2 now in map "+list);
                
                regionTypeAjaxString +="<td>"+list.get("travel_date").toString()+"</td>";
                System.out.println("1");
                regionTypeAjaxString +="<td>"+list.get("passenger_name").toString()+"</td>";
                System.out.println("2");
                //System.out.println(list.get("passenger_name").toString());
                regionTypeAjaxString +="<td>"+list.get("gender").toString()+"</td>";
                System.out.println("3");
                //System.out.println(list.get("gender").toString());
                regionTypeAjaxString +="<td>"+list.get("mobile_number").toString()+"</td>";
                System.out.println("4");
                //System.out.println(list.get("passenger_name").toString());
                regionTypeAjaxString +="<td>"+list.get("ticket_type")+"</td>";
                System.out.println("5");
                regionTypeAjaxString +="<td>"+list.get("ticket_no").toString()+"</td>";
                System.out.println("6");
                regionTypeAjaxString +="<td>"+list.get("Conductr_id").toString()+"</td>";
                System.out.println("7");
                regionTypeAjaxString +="<td>"+list.get("Route_no").toString()+"</td>";
                System.out.println("8");
            }
            
            //travel_date,passenger_name,gender,mobile_number,ticket_type,ticket_no,nt.conductor_id as conductr_id,route_no
            
            
            HttpServletResponse response = ServletActionContext.getResponse();
            PrintWriter out;
           
            out = response.getWriter();
                out.print(regionTypeAjaxString);

	    } catch (Exception ex) {
	    	System.out.println("Ashu problem in hibernate  2");
	    	ex.printStackTrace();
	    } finally {
	        HibernateUtil.closeSession();
	    }
		
		
	} catch (Exception e) {
		System.out.println("Ashu outside catch ");
		e.printStackTrace();
}
		
		return null;
	}
	
	}
		
		



	
	
	

