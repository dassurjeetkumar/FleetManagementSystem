package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class FormFourDetailsReport {
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> dutytypemap;
	public Map<Integer, String> daymap;
	public Map<Integer, String> getDaymap() {
		return daymap;
	}
	public void setDaymap(Map<Integer, String> daymap) {
		this.daymap = daymap;
	}
	public Map<Integer, String> getDutytypemap() {
		return dutytypemap;
	}
	public void setDutytypemap(Map<Integer, String> dutytypemap) {
		this.dutytypemap = dutytypemap;
	}
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
	    this.setDutytypemap(getdutytype());
	    this.setDaymap(daytype());
		return "success";
	}
	public Map<Integer, String> getdutytype() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		
		try {
			Query query = session
					.createSQLQuery("select shift_type_id,shift_type_name from `shift_type` where status='ACTIVE' and deleted_status='0'").addScalar("shift_type_id", Hibernate.INTEGER).addScalar("shift_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("shift_type_id").toString()),rs.get("shift_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	public Map<Integer, String> daytype() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		
		try {
			Query query = session
					.createSQLQuery("select form_four_type_id,form_four_type_name from `form_four_type` where status='ACTIVE' and deleted_status='0'").addScalar("form_four_type_id", Hibernate.INTEGER).addScalar("form_four_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("form_four_type_id").toString()),rs.get("form_four_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	public String getfoumfourdetails(){
		try {
			String regionTypeAjaxString = "";
			StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
	/*		Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);*/
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		int depot=Integer.parseInt(req.getParameter("depot"));
		int division=Integer.parseInt(req.getParameter("division"));
		int dutytype=Integer.parseInt(req.getParameter("dutytype"));
		String daytype=req.getParameter("daytype");
		String starttime=req.getParameter("starttime");
		String endtime=req.getParameter("endtime");
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
		 String sql="";
		 if(dutytype==3 || dutytype==5){
			 if(depot==0){
			    	sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,min(sd.start_time) as start_time,max(sd.end_time) as end_time,ff.spread_over_hours, "+
							 "ff.total_steering_time, "+
							 "ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as rest2,ifnull(sd.night_halt_location,'not avilable') as night_halt_location,ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date, "+
							 "ifnull(ff.effective_end_date,'not avilable') as effectenddate "+
							 "FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
							 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
							 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id "+
							 "WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' and oc.parent_id='"+division+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and "+
							 "s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND s.effective_end_date IS NULL and "+
							 "ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by s.schedule_number,sd.shift_type_id "+
							 "order by org_name";
			     }
			     else{ sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,min(sd.start_time) as start_time, max(sd.end_time) as end_time,ff.spread_over_hours, "+
						 "ff.total_steering_time, "+
						 "ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as "+ "rest2,ifnull(sd.night_halt_location,'not avilable') as night_halt_location,ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date, "+
						 "ifnull(ff.effective_end_date,'not avilable') as effectenddate "+
						 "FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
						 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
						 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id "+
						 "WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' and oc.org_chart_id='"+depot+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and "+
						 "s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND s.effective_end_date IS NULL and "+
						 "ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by s.schedule_number,sd.shift_type_id "+
						 "order by org_name";}
		 }else if(dutytype==2){
			 if(depot==0){
	             sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,ff.start_time, max(sd.end_time) as end_time,"+
	            		 "ff.spread_over_hours, ff.total_steering_time, ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as rest2,"+
	            		 "ifnull((select night_halt_location from schedule_details where night_halt=1 and form_four_id=ff.form_four_id),'not avilable') as night_halt_location,"+
	            		 "ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date,"+ 
	            		 "ifnull(ff.effective_end_date,'not avilable') as effectenddate FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
	            		 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+ 
	            		 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id "+ 
	            		 "left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' "+ 
	            		 "and oc.parent_id='"+division+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' "+ 
	            		 "AND s.effective_end_date IS NULL and ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by "+ "s.schedule_number,sd.shift_type_id order by org_name";
			 }else {
             sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,ff.start_time, max(sd.end_time) as end_time,"+
            		 "ff.spread_over_hours, ff.total_steering_time, ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as rest2,"+
            		 "ifnull((select night_halt_location from schedule_details where night_halt=1 and form_four_id=ff.form_four_id),'not avilable') as night_halt_location,"+
            		 "ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date,"+ 
            		 "ifnull(ff.effective_end_date,'not avilable') as effectenddate FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
            		 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+ 
            		 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id "+ 
            		 "left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' "+ 
            		 "and oc.org_chart_id='"+depot+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' "+ 
            		 "AND s.effective_end_date IS NULL and ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by "+ "s.schedule_number,sd.shift_type_id order by org_name";
			 }
		 }
		 else if(dutytype==78){
			 if(depot==0){	sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,ff.start_time,(select end_time from schedule_details where trip_number=number_of_trips and form_four_id =ff.form_four_id and s.status = 'NEW'  AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0')as end_time,ff.spread_over_hours, "+
					 "ff.total_steering_time, "+
					 "ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as "+ "rest2,ifnull(sd.night_halt_location,'not avilable') as night_halt_location,ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date, "+
					 "ifnull(ff.effective_end_date,'not avilable') as effectenddate "+
					 "FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
					 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
					 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id "+
					 "WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' and oc.parent_id='"+division+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and "+
					 "s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND s.effective_end_date IS NULL and "+
					 "ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by s.schedule_number,sd.shift_type_id "+
					 "order by org_name";}
			 else{
				 sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,ff.start_time,(select end_time from schedule_details where trip_number=number_of_trips and form_four_id =ff.form_four_id and s.status = 'NEW'  AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0')as end_time,ff.spread_over_hours, "+
						 					 "ff.total_steering_time, "+
						 					 "ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as "+ "rest2,ifnull(sd.night_halt_location,'not avilable') as night_halt_location,ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date, "+
						 					 "ifnull(ff.effective_end_date,'not avilable') as effectenddate "+
						 					 "FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
						 					 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
						 					 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id "+
						 					 "WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' and oc.org_chart_id='"+depot+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and "+
						 					 "s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND s.effective_end_date IS NULL and "+
						 					 "ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by s.schedule_number,sd.shift_type_id "+
						 					 "order by org_name";
			 }
		 }
		 else{
     if(depot==0){
    	sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,ff.start_time, max(sd.end_time) as end_time,ff.spread_over_hours, "+
				 "ff.total_steering_time, "+
				 "ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as "+ "rest2,ifnull(sd.night_halt_location,'not avilable') as night_halt_location,ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date, "+
				 "ifnull(ff.effective_end_date,'not avilable') as effectenddate "+
				 "FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
				 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
				 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id "+
				 "WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' and oc.parent_id='"+division+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and "+
				 "s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND s.effective_end_date IS NULL and "+
				 "ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by s.schedule_number,sd.shift_type_id "+
				 "order by org_name";
     }
     else{ sql="SELECT org_name,s.schedule_number,ff.schedule_number_name,ff.no_of_trips,schedule_type_name,sht.shift_type_name,ff.start_time, max(sd.end_time) as end_time,ff.spread_over_hours, "+
				 "ff.total_steering_time, "+
				 "ff.actual_km,ff.total_dead_km,ff.ot_hours,ifnull(ff.rest1,'no') as rest1,ifnull(ff.rest2,'no') as "+ "rest2,ifnull(sd.night_halt_location,'not avilable') as night_halt_location,ifnull(fto.traffic_order_no,'not avilable')as traffic_order,ff.effective_start_date, "+
				 "ifnull(ff.effective_end_date,'not avilable') as effectenddate "+
				 "FROM `schedule_details` sd inner join form_four ff on ff.form_four_id=sd.form_four_id "+
				 "inner join schedule s on s.schedule_id=sd.schedule_number inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
				 "inner join org_chart oc on s.depot_id=oc.org_chart_id inner join shift_type sht on st.schedule_type_id=sht.schedule_type_id left join  form_four_traffic_order fto on ff.form_four_id=fto.form_four_Id "+
				 "WHERE sd.deleted_status = '0' and ff.start_time between '"+starttime+":00' and '"+endtime+":00' and oc.org_chart_id='"+depot+"' and sd.shift_type_id='"+dutytype+"' and ff.form_four_name='"+daytype+"' and "+
				 "s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND s.effective_end_date IS NULL and "+
				 "ff.effective_end_date IS NULL AND ff.current_status = 'ACTIVE' AND ff.deleted_status = '0' group by s.schedule_number,sd.shift_type_id "+
				 "order by org_name";}}
		Query query = session1.createSQLQuery(sql);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Form-IV Details Report </br>Start Time Form:- "+starttime+" Start Time End:-"+endtime+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>Schedule Number</th><th>Schedule Number Name</th><th>No of Trips</th><th>Schedule Type Name</th><th>Shift Type Name</th><th>Start Time</th>" +
				"<th>End Time</th><th>Spread Hours</th><th>Steering Time</th><th>Actual km</th><th>Dead Km</th><th>Ot Hours</th>" +
				"<th>Rest 1</th><th>Rest 2</th><th>Halt Location</th><th>Traffic Order</th><th>Effect Start Date</th><th>Effect End Date</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		     
		        for (int i = 0; i < aliasToValueMapList.size(); i++) {
		        	int j=i+1;
		        	 Map<String, Object> list = aliasToValueMapList.get(i);
		        	
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("schedule_number").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("schedule_number_name").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("no_of_trips").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("schedule_type_name").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("shift_type_name").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("start_time").toString()  +"</td>");
			if(list.get("end_time")==null){
				regionTypeAjaxString1.append("<td align='right'>"+ ""  +"</td>");
			}else{
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("end_time").toString()  +"</td>");
			}
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("spread_over_hours").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_steering_time").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("actual_km").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_dead_km").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("ot_hours").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("rest1").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("rest2").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("night_halt_location").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("traffic_order").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("effective_start_date").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("effectenddate").toString() +"</td>");
		
			 regionTypeAjaxString1.append("</tr>");
					
					 }
		      
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;

	}

}
