package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class DivisionWiseWeeklyReport extends ActionSupport {
	

	String path="";
	char ft = 15;
	String str="";
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double earnAmmount=0.0;
	double dedtotal=0.0;
	int subtotalValues=0;
	double totalammount=0.0;
    public String orderno;
	String regionTypeAjaxString = "";
	
   
	
	
	private Map<Integer, String> divisionlist;
	
	private Map<Integer,String> orderlist1;
	private Map<String,String> orderlist;
	
	

	

	public Map<String, String> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Map<String, String> orderlist) {
		this.orderlist = orderlist;
	}

	public Map<Integer, String> getOrderlist1() {
		return orderlist1;
	}

	public void setOrderlist1(Map<Integer, String> orderlist1) {
		this.orderlist1 = orderlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {
		System.out.println("in execute");
		
		 HttpServletRequest req = ServletActionContext.getRequest();
//			VtsDataDAO dao = VtsDataDAO.getInstance();
			CollectionReportDAO dao=new CollectionReportDAO();
//			int parentId = Integer.parseInt(req.getParameter("val"));
//			String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
//	        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	      
//	      if(orgtypeid.equals("2")){
//	      	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
	      		List<String> l1 = dao.getFormfourid();
	      		List<String> l2 = dao.getTrafficOrder();
	      		String regionTypeAjaxString = "";
	      		//regionTypeAjaxString += "<option value='0'>------select------</option>";
	      		Map<String, String> resultMap = new LinkedHashMap<String, String>();
	      		for (int i = 0; i < l1.size(); i++) {
	      			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
	      					+ ">" + l2.get(i).toString() + "</option>";
	      			resultMap.put(l2.get(i).toString(), l2.get(i).toString());
	      		}
	      		HttpServletResponse response = ServletActionContext.getResponse();
	      		PrintWriter out;
	      		try {
	      			orderlist=resultMap;
//	      			out = response.getWriter();
//	      			out.print(regionTypeAjaxString);
	      		} catch (Exception e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		}
		
		
		return "success";
	}
	
	
	public String getNorthDivisionWiseWeeklyReport(){
		
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift),2) distadeadshift,(ot1+ot2) ot, "+
				  " (case when ot1+ot2!=0 THEN  count(schedule_number) ELSE 0 END) ot_sch,org_name from( "+
				  " select traffic_order_no,sd.schedule_number,ft.form_four_Id,tot_sch,dista,trip,distadeadshift,TIME_TO_SEC(ot1) ot1, "+
				  " TIME_TO_SEC(ot2) ot2,org_name from form_four_traffic_order ft "+
				  " inner join form_four f on f.form_four_Id=ft.form_four_Id "+
				  " inner join schedule s on s.schedule_id=f.schedule_number_id  "+
				  " inner join schedule_details sd on sd.form_four_id=f.form_four_id  "+
				  " inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='7' "+
				  " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
				  " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
				  " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
				  " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
				  " where traffic_order_no='"+orderno+"' and  f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' ) d group by d.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("dista").addScalar("trip").addScalar("distadeadshift").addScalar("ot").addScalar("ot_sch").addScalar("org_name");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
             double Total_sch=0.0;
             double Total_kms=0.0;
             double Total_trip=0.0;
             double Total_uti=0.0;
             double Total_dead=0.0;
             double Total_ot_sch=0.0;
             double Total_ot_hr=0.0;
             String Total_uti1="";
             String Total_dead1="";
             
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+"  </br>CENTRAL OFFICES, BANALORE-27  </br> Form-V  After Issue Of Traffic Order No "+orderno+" ";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        


	        regionTypeAjaxString += "<tr><th>Depot</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
					      "<tr><th>No</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
					"</tr>";

			
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
	        
	        
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("dista").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("trip").toString()+"</td>";
			double veh_uti=Double.parseDouble(list.get("dista").toString())/Double.parseDouble(list.get("tot_sch").toString());
			String veh_uti1=String.format("%.2f", veh_uti);
			regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot_sch").toString()+"</td>";
			int sec=Integer.parseInt(list.get("ot").toString());
			int hours = sec / 3600;
	        int minutes = (sec%3600)/60;
	        regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
			
	        Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
	        
	       
	    	
	        Total_kms+=Double.parseDouble(list.get("dista").toString());
	       
	        Total_trip+=Double.parseDouble(list.get("trip").toString());
	      
	        Total_uti+=veh_uti;
	       
	        Total_uti1=String.format("%.2f", Total_uti);
	        Total_dead+=Double.parseDouble(list.get("distadeadshift").toString());
	       
	        Total_dead1=String.format("%.2f", Total_dead);
	        Total_ot_sch+=Double.parseDouble(list.get("ot_sch").toString());
	        
	        Total_ot_hr+=hours;
	      
			   regionTypeAjaxString +="</tr>";
		}
		    
		     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_sch+"</td>"+"<td align='right'><b>"+Total_kms+"</td><td align='right'><b>"+ Total_trip+"</td>" +
						"<td align='right'><b>"+Total_uti1+"</td><td align='right'><b>"+Total_dead1+"</td><td align='right'><b>"+Total_ot_sch+"</td><td align='right'><b>"+Total_ot_hr+"</td></tr>" +"\n";  
				 regionTypeAjaxString += "</table></div> </div>  ";
		     
				 
	          }
				
		 
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
public String getSouthDivisionWiseWeeklyReport(){
		
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift),2) distadeadshift,(ot1+ot2) ot, "+
				   " (case when ot1+ot2!=0 THEN  count(schedule_number) ELSE 0 END) ot_sch,org_name from( "+
				   " select traffic_order_no,sd.schedule_number,ft.form_four_Id,tot_sch,dista,trip,distadeadshift,TIME_TO_SEC(ot1) ot1, "+
				   " TIME_TO_SEC(ot2) ot2,org_name from form_four_traffic_order ft "+
				   " inner join form_four f on f.form_four_Id=ft.form_four_Id "+
				   " inner join schedule s on s.schedule_id=f.schedule_number_id  "+
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				   " inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='6' "+
				   " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
				   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
				   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
				   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id  "+
				   " where traffic_order_no='"+orderno+"' and  f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' ) d group by d.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("dista").addScalar("trip").addScalar("distadeadshift").addScalar("ot").addScalar("ot_sch").addScalar("org_name");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
             double Total_sch=0.0;
             double Total_kms=0.0;
             double Total_trip=0.0;
             double Total_uti=0.0;
             double Total_dead=0.0;
             double Total_ot_sch=0.0;
             double Total_ot_hr=0.0;
             String Total_uti1="";
             String Total_dead1="";
             
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+"  </br>CENTRAL OFFICES, BANALORE-27";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        


	        regionTypeAjaxString += "<tr><th>Depot</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
					      "<tr><th>No</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
					"</tr>";

	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
	        
			                   
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("dista").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("trip").toString()+"</td>";
			double veh_uti=Double.parseDouble(list.get("dista").toString())/Double.parseDouble(list.get("tot_sch").toString());
			String veh_uti1=String.format("%.2f", veh_uti);
			regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot_sch").toString()+"</td>";
			int sec=Integer.parseInt(list.get("ot").toString());
			int hours = sec / 3600;
	        int minutes = (sec%3600)/60;
	        regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
			
	        Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
	        Total_kms+=Double.parseDouble(list.get("dista").toString());
	        Total_trip+=Double.parseDouble(list.get("trip").toString());
	        Total_uti+=veh_uti;
	        Total_uti1=String.format("%.2f", Total_uti);
	        Total_dead+=Double.parseDouble(list.get("distadeadshift").toString());
	        Total_dead1=String.format("%.2f", Total_dead);
	        Total_ot_sch+=Double.parseDouble(list.get("ot_sch").toString());
	        Total_ot_hr+=hours;
			   regionTypeAjaxString +="</tr>";
		}
		    
		     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_sch+"</td>"+"<td align='right'><b>"+Total_kms+"</td><td align='right'><b>"+ Total_trip+"</td>" +
						"<td align='right'><b>"+Total_uti1+"</td><td align='right'><b>"+Total_dead1+"</td><td align='right'><b>"+Total_ot_sch+"</td><td align='right'><b>"+Total_ot_hr+"</td></tr>" +"\n";  
				 regionTypeAjaxString += "</table></div> </div>  ";
		     
				 
	          }
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
public String getEastDivisionWiseWeeklyReport(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
			//dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift),2) distadeadshift,(ot1+ot2) ot, "+
			   " (case when ot1+ot2!=0 THEN  count(schedule_number) ELSE 0 END) ot_sch,org_name from( "+
			   " select traffic_order_no,sd.schedule_number,ft.form_four_Id,tot_sch,dista,trip,distadeadshift,TIME_TO_SEC(ot1) ot1, "+
			   " TIME_TO_SEC(ot2) ot2,org_name from form_four_traffic_order ft "+
			   " inner join form_four f on f.form_four_Id=ft.form_four_Id "+
			   " inner join schedule s on s.schedule_id=f.schedule_number_id  "+
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id  "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='2' "+
			   " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
			   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
			   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
			   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
			   " where traffic_order_no='"+orderno+"' and  f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' ) d group by d.org_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("dista").addScalar("trip").addScalar("distadeadshift").addScalar("ot").addScalar("ot_sch").addScalar("org_name");
  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
      double Total_sch=0.0;
      double Total_kms=0.0;
      double Total_trip=0.0;
      double Total_uti=0.0;
      double Total_dead=0.0;
      double Total_ot_sch=0.0;
      double Total_ot_hr=0.0;
      String Total_uti1="";
      String Total_dead1="";
		
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
       
       

        regionTypeAjaxString += "<tr><th>Depot</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
				      "<tr><th>No</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
				"</tr>";

	
        if(aliasToValueMapList.size()==0){
          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
               regionTypeAjaxString += "<tr>";
               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
              
               regionTypeAjaxString += "</tr>";
          	
          }else{
        
              
        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
		int j=i+1;
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("dista").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("trip").toString()+"</td>";
		double veh_uti=Double.parseDouble(list.get("dista").toString())/Double.parseDouble(list.get("tot_sch").toString());
		String veh_uti1=String.format("%.2f", veh_uti);
		regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot_sch").toString()+"</td>";
		int sec=Integer.parseInt(list.get("ot").toString());
		int hours = sec / 3600;
       int minutes = (sec%3600)/60;
       regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
		
       Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
       Total_kms+=Double.parseDouble(list.get("dista").toString());
       Total_trip+=Double.parseDouble(list.get("trip").toString());
       Total_uti+=veh_uti;
       Total_uti1=String.format("%.2f", Total_uti);
       Total_dead+=Double.parseDouble(list.get("distadeadshift").toString());
       Total_dead1=String.format("%.2f", Total_dead);
       Total_ot_sch+=Double.parseDouble(list.get("ot_sch").toString());
       Total_ot_hr+=hours;
		   regionTypeAjaxString +="</tr>";
	}
	    
	     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_sch+"</td>"+"<td align='right'><b>"+Total_kms+"</td><td align='right'><b>"+ Total_trip+"</td>" +
					"<td align='right'><b>"+Total_uti1+"</td><td align='right'><b>"+Total_dead1+"</td><td align='right'><b>"+Total_ot_sch+"</td><td align='right'><b>"+Total_ot_hr+"</td></tr>" +"\n";  
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
          }
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	
	return null;
}	

public String getWestDivisionWiseWeeklyReport(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
			//dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift),2) distadeadshift,(ot1+ot2) ot, "+
			   " (case when ot1+ot2!=0 THEN  count(schedule_number) ELSE 0 END) ot_sch,org_name from( "+
			   " select traffic_order_no,sd.schedule_number,ft.form_four_Id,tot_sch,dista,trip,distadeadshift,TIME_TO_SEC(ot1) ot1, "+
			   " TIME_TO_SEC(ot2) ot2,org_name from form_four_traffic_order ft "+
			   " inner join form_four f on f.form_four_Id=ft.form_four_Id "+
			   " inner join schedule s on s.schedule_id=f.schedule_number_id  "+
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id  "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='4' "+
			   " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
			   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
			   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
			   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
			   " where traffic_order_no='"+orderno+"' and  f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' ) d group by d.org_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("dista").addScalar("trip").addScalar("distadeadshift").addScalar("ot").addScalar("ot_sch").addScalar("org_name");
	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
      double Total_sch=0.0;
      double Total_kms=0.0;
      double Total_trip=0.0;
      double Total_uti=0.0;
      double Total_dead=0.0;
      double Total_ot_sch=0.0;
      double Total_ot_hr=0.0;
      String Total_uti1="";
      String Total_dead1="";
		
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
       

        regionTypeAjaxString += "<tr><th>Depot</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
				      "<tr><th>No</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
				"</tr>";

        if(aliasToValueMapList.size()==0){
          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
               regionTypeAjaxString += "<tr>";
               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
              
               regionTypeAjaxString += "</tr>";
          	
          }else{
        
        
        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
		int j=i+1;
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("dista").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("trip").toString()+"</td>";
		double veh_uti=Double.parseDouble(list.get("dista").toString())/Double.parseDouble(list.get("tot_sch").toString());
		String veh_uti1=String.format("%.2f", veh_uti);
		regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot_sch").toString()+"</td>";
		int sec=Integer.parseInt(list.get("ot").toString());
		int hours = sec / 3600;
       int minutes = (sec%3600)/60;
       regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
		
       Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
       Total_kms+=Double.parseDouble(list.get("dista").toString());
       Total_trip+=Double.parseDouble(list.get("trip").toString());
       Total_uti+=veh_uti;
       Total_uti1=String.format("%.2f", Total_uti);
       Total_dead+=Double.parseDouble(list.get("distadeadshift").toString());
       Total_dead1=String.format("%.2f", Total_dead);
       Total_ot_sch+=Double.parseDouble(list.get("ot_sch").toString());
       Total_ot_hr+=hours;
		   regionTypeAjaxString +="</tr>";
	}
	    
	     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_sch+"</td>"+"<td align='right'><b>"+Total_kms+"</td><td align='right'><b>"+ Total_trip+"</td>" +
					"<td align='right'><b>"+Total_uti1+"</td><td align='right'><b>"+Total_dead1+"</td><td align='right'><b>"+Total_ot_sch+"</td><td align='right'><b>"+Total_ot_hr+"</td></tr>" +"\n";  
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
          }
			 
			 
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return null;
}

public String getCentralDivisionWiseWeeklyReport(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
			//dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift),2) distadeadshift,(ot1+ot2) ot, "+
			   " (case when ot1+ot2!=0 THEN  count(schedule_number) ELSE 0 END) ot_sch,org_name from( "+
			   " select traffic_order_no,sd.schedule_number,ft.form_four_Id,tot_sch,dista,trip,distadeadshift,TIME_TO_SEC(ot1) ot1, "+
			   " TIME_TO_SEC(ot2) ot2,org_name from form_four_traffic_order ft "+
			   " inner join form_four f on f.form_four_Id=ft.form_four_Id "+
			   " inner join schedule s on s.schedule_id=f.schedule_number_id  "+
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id  "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='5' "+
			   " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
			   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
			   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
			   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
			   " where traffic_order_no='"+orderno+"' and  f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' ) d group by d.org_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("dista").addScalar("trip").addScalar("distadeadshift").addScalar("ot").addScalar("ot_sch").addScalar("org_name");
	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
    double Total_sch=0.0;
    double Total_kms=0.0;
    double Total_trip=0.0;
    double Total_uti=0.0;
    double Total_dead=0.0;
    double Total_ot_sch=0.0;
    double Total_ot_hr=0.0;
    String Total_uti1="";
    String Total_dead1="";

		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
     
       

        regionTypeAjaxString += "<tr><th>Depot</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
				      "<tr><th>No</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
				"</tr>";

        if(aliasToValueMapList.size()==0){
          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
               regionTypeAjaxString += "<tr>";
               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
              
               regionTypeAjaxString += "</tr>";
          	
          }else{
        
	
        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
		int j=i+1;
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("dista").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("trip").toString()+"</td>";
		double veh_uti=Double.parseDouble(list.get("dista").toString())/Double.parseDouble(list.get("tot_sch").toString());
		String veh_uti1=String.format("%.2f", veh_uti);
		regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot_sch").toString()+"</td>";
		int sec=Integer.parseInt(list.get("ot").toString());
		int hours = sec / 3600;
      int minutes = (sec%3600)/60;
      regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
		
      Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
      Total_kms+=Double.parseDouble(list.get("dista").toString());
      Total_trip+=Double.parseDouble(list.get("trip").toString());
      Total_uti+=veh_uti;
      Total_uti1=String.format("%.2f", Total_uti);
      Total_dead+=Double.parseDouble(list.get("distadeadshift").toString());
      Total_dead1=String.format("%.2f", Total_dead);
      Total_ot_sch+=Double.parseDouble(list.get("ot_sch").toString());
      Total_ot_hr+=hours;
		   regionTypeAjaxString +="</tr>";
	}
	    
	     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_sch+"</td>"+"<td align='right'><b>"+Total_kms+"</td><td align='right'><b>"+ Total_trip+"</td>" +
					"<td align='right'><b>"+Total_uti1+"</td><td align='right'><b>"+Total_dead1+"</td><td align='right'><b>"+Total_ot_sch+"</td><td align='right'><b>"+Total_ot_hr+"</td></tr>" +"\n";  
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
          }
	 
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	

		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	
	return null;
}

public String getScheduleAbstractDivisionWiseWeeklyReport(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
			//dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift),2) distadeadshift,(ot1+ot2) ot, "+
			   " (case when ot1+ot2!=0 THEN  count(schedule_number) ELSE 0 END) ot_sch,org_name,parent_id from( "+
			   " select traffic_order_no,sd.schedule_number,ft.form_four_Id,tot_sch,dista,trip,distadeadshift,TIME_TO_SEC(ot1) ot1, "+
			   " TIME_TO_SEC(ot2) ot2,org_name,parent_id from form_four_traffic_order ft "+
			   " inner join form_four f on f.form_four_Id=ft.form_four_Id "+
			   " inner join schedule s on s.schedule_id=f.schedule_number_id "+ 
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+ 
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id  "+
			   " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
			   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
			   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
			   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
			   " where traffic_order_no='"+orderno+"' and  f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' ) d group by d.parent_id";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("dista").addScalar("trip").addScalar("distadeadshift").addScalar("ot").addScalar("ot_sch").addScalar("org_name").addScalar("parent_id");
	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
    double Total_sch=0.0;
    double Total_kms=0.0;
    double Total_trip=0.0;
    double Total_uti=0.0;
    double Total_dead=0.0;
    double Total_ot_sch=0.0;
    double Total_ot_hr=0.0;
    String Total_uti1="";
    String Total_dead1="";
		
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
      
       

        regionTypeAjaxString += "<tr><th>Division</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
				      "<tr><th>Name</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
				"</tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th>Depot No</th><th>No Of SCH'S</th><th>Schedule KMS</th><th>Total Trips</th><th>CORONA Sch's</th><th>Schedule Kms</th><th>Total Trips</th><th>Volvo Sch's</th><th>Schedule Kms</th><th>Total Tips</th><th>Over All Veh.Uti.</th><th>Depot Wise Dead Kms</th></tr>" +
//				      "</thead><tbody>";
		 
		
        if(aliasToValueMapList.size()==0){
          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
               regionTypeAjaxString += "<tr>";
               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
              
               regionTypeAjaxString += "</tr>";
          	
          }else{
		
              
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
//		
 		String parent_id=list.get("parent_id").toString();
 		String division_name=getDivisionNames(parent_id);
		regionTypeAjaxString +="<td align='right'>"+division_name+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("dista").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("trip").toString()+"</td>";
		double veh_uti=Double.parseDouble(list.get("dista").toString())/Double.parseDouble(list.get("tot_sch").toString());
		String veh_uti1=String.format("%.2f", veh_uti);
		regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot_sch").toString()+"</td>";
		int sec=Integer.parseInt(list.get("ot").toString());
		int hours = sec / 3600;
      int minutes = (sec%3600)/60;
      regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
		
      Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
      Total_kms+=Double.parseDouble(list.get("dista").toString());
      Total_trip+=Double.parseDouble(list.get("trip").toString());
      Total_uti+=veh_uti;
      Total_uti1=String.format("%.2f", Total_uti);
      Total_dead+=Double.parseDouble(list.get("distadeadshift").toString());
      Total_dead1=String.format("%.2f", Total_dead);
      Total_ot_sch+=Double.parseDouble(list.get("ot_sch").toString());
      Total_ot_hr+=hours;
		   regionTypeAjaxString +="</tr>";
	}
	    
	     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_sch+"</td>"+"<td align='right'><b>"+Total_kms+"</td><td align='right'><b>"+ Total_trip+"</td>" +
					"<td align='right'><b>"+Total_uti1+"</td><td align='right'><b>"+Total_dead1+"</td><td align='right'><b>"+Total_ot_sch+"</td><td align='right'><b>"+Total_ot_hr+"</td></tr>" +"\n";  
			 regionTypeAjaxString += "</table></div> </div>  ";
	 
          }
	 
	
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	
	return null;
}

public String getDivisionNames(String parent_id){
	
	String divisionname="";
	Session session =null;
	try{
	String sql = " select org_name from org_chart where deleted_status=0 and org_chart_id="+parent_id+" and org_type_id=2  order by org_name";

	// sql += " order by " + COL_NAME + " " + DIR;
	Query query = HibernateUtil.getSession("").createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	if (aliasToValueMapList.size() > 0) {

		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			divisionname=rs.get("org_name").toString();
		}
	}
	HibernateUtil.closeSession();
	

	}catch(Exception e){
		e.printStackTrace();
	}

return divisionname;
}


}
