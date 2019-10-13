package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

public class StOneItsDao {
	 public String startdate;
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getdepot() {

		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
     String orgchartid=(String)req.getSession().getAttribute("orgchartid");
     
     if(orgtypeid.equals("2")){
//     	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
     		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
     		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
     		String regionTypeAjaxString = "";
     		regionTypeAjaxString += "<option value='0'>------select------</option>";
     		for (int i = 0; i < l1.size(); i++) {
     			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
     					+ ">" + l2.get(i).toString() + "</option>";
     		}
     		HttpServletResponse response = ServletActionContext.getResponse();
     		PrintWriter out;
     		try {
     			out = response.getWriter();
     			out.print(regionTypeAjaxString);
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
//     		return null;
//     	}
		
     }
     
   
     else if(orgtypeid.equals("1") && orgchartid.equals("1")){
     	List<String> l1 = dao.getDepotId(parentId);
 		List<String> l2 = dao.getDepotName(parentId);
 		String regionTypeAjaxString = "";
 		regionTypeAjaxString += "<option value='0'>------select------</option>";
 		for (int i = 0; i < l1.size(); i++) {
 			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
 					+ ">" + l2.get(i).toString() + "</option>";
 		}
 		HttpServletResponse response = ServletActionContext.getResponse();
 		PrintWriter out;
 		try {
 			out = response.getWriter();
 			out.print(regionTypeAjaxString);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     }

     else {
     	List<String> l1 = dao.getDepotId(parentId,orgchartid);
 		List<String> l2 = dao.getDepotName(parentId,orgchartid);
 		String regionTypeAjaxString = "";
 		regionTypeAjaxString += "<option value='0'>------select------</option>";
 		for (int i = 0; i < l1.size(); i++) {
 			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
 					+ ">" + l2.get(i).toString() + "</option>";
 		}
 		HttpServletResponse response = ServletActionContext.getResponse();
 		PrintWriter out;
 		try {
 			out = response.getWriter();
 			out.print(regionTypeAjaxString);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     }
     
		return null;

	
	}
	public void getdatadao(String date,String div,String depot,String dateforhead) {
	/*	Common common = new Common();
		HttpServletRequest req=ServletActionContext.getRequest();
		
		String date=common.getDateFromPicker(startdate);
		
		String div=req.getParameter("division");
		String depot=req.getParameter("depot");*/
		//String date=req.getParameter("date");

		StringBuffer regionTypeAjaxString=new StringBuffer();
		 StringBuffer hedd=new StringBuffer();
		 StringBuffer medd=new StringBuffer();
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		Session	session12=null;
		ResultSet rs=null;
		
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	    
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
		String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id =  '"+depot+"'";
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
		Query qry1=session.createSQLQuery(sql1).
				addScalar("depotname")
				.addScalar("central_ip",Hibernate.STRING)
				.addScalar("central_uname")
				.addScalar("central_pwd");
		Calendar cal = Calendar.getInstance();
		String day=new SimpleDateFormat("EEEE").format(new SimpleDateFormat("yyyy-M-d").parse(date));

		qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry1.list();	
			String depotdb="";
			String depotIp="";
			String user="";
			String password="";
			for (int j = 0; j < aliasToValueMapList.size(); j++) {
				Map<String, Object> aliasValue = aliasToValueMapList.get(j);
				 depotdb=aliasValue.get("depotname").toString();
				 depotIp=aliasValue.get("central_ip").toString();
				 user=aliasValue.get("central_uname").toString();
				 password=aliasValue.get("central_pwd").toString();
				 
				 Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
				// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
				// connection = DriverManager.getConnection("jdbc:mysql://10.30.1.154:3306/bmtc_doa?zeroDateTimeBehavior=convertToNull&autoReconnect=true","doa","doa");

				 System.out.println("connection........."+connection);   
				 stmt = connection.createStatement();
				 String sqlforschdeule="select *,no_schedule-(plan_schedule+unplan_schedule) oper_sch from ( " + 
				 		"select '2018-12-13' date_report,count(distinct schedule_number) no_schedule,sum(if(cancel_type='Planned',1,0))plan_schedule,sum(if(cancel_type='Unplanned',1,0))unplan_schedule  from schedule s " + 
				 		"left join schedule_cancellation_cause scc on scc.schedule_id=s.schedule_id and '"+date+"' between from_date and to_date " + 
				 		"where s.deleted_status=0 and s.status='new' and s.current_status='CASE WORKER' )a;";
				 System.out.println(sqlforschdeule);
				 rs = stmt.executeQuery(sqlforschdeule);
				 int i=1;
					regionTypeAjaxString.append(" <div id='header'  style='display: none; '><table border='1'  style='width:100%'><tr ><td  align='center' colspan='4'>" + 
			 		"<div>" + 
			 		"		<font size='5'><span>ST--1</span></font></center><br><center><b><font size='3'>Depot-"+depotdb.substring(1, 3)+"</font></b>" + 
			 		"</div></center><br><center><b><font size='3'> Repot Date:-"+dateforhead+"</font></b></center>" + 
			 		"</td><right><b><font size='3'> Printed Date:-"+runDateTime+"</font></b></right></tr></table></div>");
					regionTypeAjaxString.append("<div class=''><table  border='1' style='width:100%'>");
					regionTypeAjaxString.append("<thead><tr ><th width='12%'> <center>Sno</center></th><th> <center>Schedules</center></th><th> <center>No Schedules</center></th><th></th>" +

								""+"</tr></thead><tbody>");
					if(rs.wasNull()) {
						 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Schedule(As per form V)</center></td><td><center>0</center></td><td></center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Actual Schedule</center></td><td><center>0</center></td><td></center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Cancelled Schedules(Planned)</center></td><td><center>0</center></td><td></center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Cancelled Schedules(Non-Planned)</center></td><td><center>0</center></td><td></center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Operated Schedules</center></td><td><center>0</center></td><td></center></td></tr>");

					}
				 while (rs.next()) {
				
					
					 //regionTypeAjaxString .append( "<div class='component'><table class='overflow-y' border='1'>");
					 //regionTypeAjaxString .append( "<th  align='center' colspan='4' ><font color='red'><b>Schdules</b></font></th>");
			
					 regionTypeAjaxString .append( "");
					 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Schedule(As per form V)</center></td><td><center>"+rs.getString("no_schedule").toString()+"</center></td><td></center></td></tr>");
					 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Actual Schedule</center></td><td><center>"+rs.getString("no_schedule").toString()+"</center></td><td></center></td></tr>");
					 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Cancelled Schedules(Planned)</center></td><td><center>"+rs.getString("plan_schedule").toString()+"</center></td><td></center></td></tr>");
					 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Cancelled Schedules(Non-Planned)</center></td><td><center>"+rs.getString("unplan_schedule").toString()+"</center></td><td></center></td></tr>");
					 regionTypeAjaxString .append( "<tr><td><center>"+i+++"</center></td><td><center>Operated Schedules</center></td><td><center>"+rs.getString("oper_sch").toString()+"</center></td><td></center></td></tr>");

					 /*	 regionTypeAjaxString .append( "<td><center>"+rs.getString("generated_Date").toString()+"</center></td>");
					 regionTypeAjaxString .append( "<td><center>"+rs.getString("bag_code").toString()+"</center></td>");
					 regionTypeAjaxString .append( "<td><center>"+rs.getString("totrev").toString()+"</center></td>");
					 regionTypeAjaxString .append( "<td><center>"+rs.getString("license_number").toString()+"</center></td>");

					 regionTypeAjaxString .append( "<td><center>"+rs.getString("schedule_number").toString()+"</center></td>");*/
					 
					// regionTypeAjaxString .append("</table></div>");
				 }
				 
				/* String sqlforcalcelledkms="select ifnull(cancellation_name,'Others')cancellation_name,count(trip_no) no_trip,sum(canceled_trip_kms) can_kms   " + 
				 		"from logsheet_cancelled_trip_new lcn left join cancellation_cause cc on cc.cancellation_id=lcn.cause_for_cancellation " + 
				 		"where canceled_trip_kms >0 and extra_trip_KM=0 and logsheet_date= '"+date+"' group by cancellation_name";*/
				 String sqlforcalcelledkms="select cancellation_name,sum(no_trip) no_trip,sum(can_kms) can_kms from (select cancellation_id,ifnull(cancellation_name,'Others')cancellation_name,count(trip_no) no_trip,ifnull(sum(canceled_trip_kms),0) can_kms  " + 
				 		"from cancellation_cause lcn left join logsheet_cancelled_trip_new cc on lcn.cancellation_id=cc.cause_for_cancellation and canceled_trip_kms >0 and extra_trip_KM=0  " + 
				 		"and logsheet_date=  '"+date+"' " + 
				 		"group by lcn.cancellation_name  " + 
				 		"union " + 
				 		"SELECT cancellation_id,reason,sum(no_of_trips) no_trip,sum(total_km) can_kms " + 
				 		"FROM `schedule_cancellation_cause` scc " + 
				 		"inner join form_four ff on ff.form_four_id=scc.form_four_id " + 
				 		"inner join cancellation_cause cc on cc.cancellation_name=scc.reason " + 
				 		"WHERE  '"+date+"'  between from_date and to_date and cancel_type='Unplanned'  " + 
				 		"group by reason)aa group by cancellation_id order by cancellation_id ";
				 
				 System.out.println(sqlforcalcelledkms);
				 rs = stmt.executeQuery(sqlforcalcelledkms);
				// int i=1;
					int i1=0;
					regionTypeAjaxString.append("<thead><tr ><th width='12%'> <center>Sno</center><th> <center>Cancelled kms(Cause-wise)</center></th><th > <center>Trips</center></th><th > <center>Kms</center></th>" +

									""+"</tr></thead><tbody>");
					double ckms=0.0;
					double ctrips=0.0;
				     while (rs.next()) {
			 		  i1++;
		
				    	 regionTypeAjaxString .append( "<tr>");
				         regionTypeAjaxString .append( "<td><center>"+ i1+"</center></td>");
				     regionTypeAjaxString .append( "<td><center>"+ rs.getString("cancellation_name").toString() +"</center></td>");
					 regionTypeAjaxString .append( "<td><center>"+rs.getString("no_trip").toString()+"</center></td>");
					 ctrips+=Double.parseDouble(rs.getString("no_trip").toString());
					 regionTypeAjaxString .append( "<td><center>"+rs.getString("can_kms").toString()+"</center></td>");
					 ckms+=Double.parseDouble(rs.getString("can_kms").toString());
				
						 regionTypeAjaxString .append( "</tr>");
				    }
				     regionTypeAjaxString .append( "<tr><td><center>Total</center></td><td></td><td><center>"+ctrips+"</center></td><td><center>"+BigDecimal.valueOf(ckms).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</center></td></tr>");
				   
				     
				      
				   
/*				     String sqlforkms="select *,ifnull((s_kms-(plan_cancle+totunpln_c_kms))+cc_extrakm+extrakm,0)  efftive_kms,ifnull(round((plan_cancle/s_kms)*100,2),0) plan_cankm_per,ifnull(round((totunpln_c_kms/s_kms)*100,2),0) unplan_cankm_per,"
				     		+ " ifnull(round((plan_trip/s_trip)*100,2),0) plan_cantrip_per,ifnull(round((unplan_cab_trips/s_trip)*100,2),0) unplan_cantrip_per,  " + 
				     		"ifnull((totunpln_c_kms+plan_cancle),0) tot_cancel_kms,ifnull((s_kms-(totunpln_c_kms+plan_cancle)),0)actual_oper_kms,ifnull((s_trip-tot_cancel_trips),0) atutal_opr_trips from ( " + 
				     		"select *,ifnull((plan_trip+unplan_cab_trips+uplan_trip),0) tot_cancel_trips,ifnull((s_trip-(plan_trip+unplan_cab_trips+uplan_trip)),0) oper_trips from ( " + 
				     		"select ifnull(sum(if(canceled_trip_kms >0 and extra_trip_KM =0,1,0)),0) unplan_cab_trips,ifnull(sum(if(canceled_trip_kms >0 and extra_trip_KM =0,canceled_trip_kms,0)),0) totunpln_c_kms, " + 
				     		"ifnull(sum(if(extra_trip_reason=2,extra_trip_KM,0)),0) cc_extrakm,ifnull(sum(if(extra_trip_reason !=2,extra_trip_KM,0)),0) extrakm,ifnull((SELECT count(`trip_number`) " + 
				     		"from form_four ff  inner join schedule s on ff.schedule_number_id = s.schedule_id inner  join weeklyChart wc on ff.form_four_id =  wc.form_four_id   " + 
				     		"inner join schedule_details sd on sd.form_four_id=ff.form_four_id and sd.schedule_number=s.schedule_id " + 
				     		"where  ff.deleted_status=0 AND ff.current_status='ACTIVE' AND s.status='NEW' and (ff.effective_end_date>= '2018-12-04' or ff.effective_end_date is null) "
				     		+ " AND s.current_status='CASE WORKER' and s.deleted_status=0 and wc.deleted_status=0 and wc.status='active' and "+day+"=1  " + 
				     		"and is_dread_trip=0),0)s_trip,ifnull((select sum(actual_km)s_kms " + 
				     		" from form_four ff  " + 
				     		"inner join schedule s on ff.schedule_number_id = s.schedule_id " + 
				     		"inner  join weeklyChart wc on ff.form_four_id =  wc.form_four_id  " + 
				     		"where  ff.deleted_status=0 AND ff.current_status='ACTIVE' AND s.status='NEW' and " + 
				     		"(ff.effective_end_date>= logsheet_date or ff.effective_end_date is null) " + 
				     		"AND s.current_status='CASE WORKER' and s.deleted_status=0 and wc.deleted_status=0 and wc.status='active' and "+day+"=1 ),0) s_kms,ifnull((select sum(total_km) " + 
				     		"from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id " + 
				     		"where logsheet_date between from_date and to_date and cancel_type='Planned'),0) plan_cancle,ifnull((select sum(no_of_trips) " + 
				     		"from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id " + 
				     		"where logsheet_date between from_date and to_date and cancel_type='Planned'),0) plan_trip, " + 
				     		
				     		
				     		
" ifnull((select sum(total_km) " + 
 		"from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id " + 
 		"where logsheet_date between from_date and to_date and cancel_type='Unplanned'),0) uplan_cancle,ifnull((select sum(no_of_trips) " + 
 		"from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id " + 
 		"where logsheet_date between from_date and to_date and cancel_type='Unplanned'),0) uplan_trip " + 
 		
 		
				     		"from ( " + 
				     		"SELECT `trip_no`, `canceled_trip_kms`, `extra_trip_KM`, `logsheet_date`, `extra_trip_reason` " + 
				     		"FROM `logsheet_cancelled_trip_new` " + 
				     		"WHERE `logsheet_date` = '"+date+"' ) a )b)c";*/
				     
				     String sqlforkms="select *,ifnull((s_kms-(plan_cancle+totunpln_c_kms+uplan_cancle))+cc_extrakm+extrakm,0)  efftive_kms,ifnull(round((plan_cancle/s_kms)*100,2),0) plan_cankm_per, " + 
				     		"ifnull(round(((totunpln_c_kms+uplan_cancle)/s_kms)*100,2),0) unplan_cankm_per, ifnull(round((plan_trip/s_trip)*100,2),0) plan_cantrip_per, " + 
				     		"ifnull(round(((unplan_cab_trips+uplan_trip)/s_trip)*100,2),0) unplan_cantrip_per,  ifnull((totunpln_c_kms+plan_cancle+uplan_cancle),0) tot_cancel_kms, " + 
				     		"ifnull((s_kms-(totunpln_c_kms+plan_cancle+uplan_cancle)),0)actual_oper_kms,ifnull((s_trip-tot_cancel_trips),0) atutal_opr_trips,"
				     		+ " (unplan_cab_trips+uplan_trip)totaluptrips,(uplan_cancle+totunpln_c_kms)totunplankms  " + 
				     		"from ( " + 
				     		"select *,ifnull((plan_trip+unplan_cab_trips+uplan_trip),0) tot_cancel_trips,ifnull((s_trip-(plan_trip+unplan_cab_trips+uplan_trip)),0) oper_trips  " + 
				     		"from ( select ifnull(sum(if(canceled_trip_kms >0 and extra_trip_KM =0,1,0)),0) unplan_cab_trips, " + 
				     		"ifnull(sum(if(canceled_trip_kms >0 and extra_trip_KM =0,canceled_trip_kms,0)),0) totunpln_c_kms, ifnull(sum(if(extra_trip_reason=2,extra_trip_KM,0)),0) cc_extrakm, " + 
				     		"ifnull(sum(if(extra_trip_reason !=2,extra_trip_KM,0)),0) extrakm,ifnull((SELECT count(`trip_number`) from form_four ff   " + 
				     		"inner join schedule s on ff.schedule_number_id = s.schedule_id inner  join weeklyChart wc on ff.form_four_id =  wc.form_four_id    " + 
				     		"inner join schedule_details sd on sd.form_four_id=ff.form_four_id and sd.schedule_number=s.schedule_id where  ff.deleted_status=0 AND ff.current_status='ACTIVE'  " + 
				     		"AND s.status='NEW' and (ff.effective_end_date>=logsheet_date or ff.effective_end_date is null)  AND s.current_status='CASE WORKER' and s.deleted_status=0  " + 
				     		"and wc.deleted_status=0 and wc.status='active' and Wednesday=1  and is_dread_trip=0),0)s_trip,ifnull((select sum(actual_km)s_kms  from form_four ff   " + 
				     		"inner join schedule s on ff.schedule_number_id = s.schedule_id inner  join weeklyChart wc on ff.form_four_id =  wc.form_four_id   " + 
				     		"where  ff.deleted_status=0 AND ff.current_status='ACTIVE' AND s.status='NEW' and (ff.effective_end_date>= logsheet_date or ff.effective_end_date is null)  " + 
				     		"AND s.current_status='CASE WORKER' and s.deleted_status=0 and wc.deleted_status=0 and wc.status='active' and  "+day+"=1 ),0) s_kms,ifnull((select sum(total_km)  " + 
				     		"from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id  " + 
				     		"where logsheet_date between from_date and to_date and cancel_type='Planned' and reason !='0'),0) plan_cancle,ifnull((select sum(no_of_trips) from form_four ff  " + 
				     		"inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id where logsheet_date between from_date and to_date  " + 
				     		"and cancel_type='Planned' and reason !='0'),0) plan_trip,  ifnull((select sum(total_km) from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id  " + 
				     		"and scc.form_four_id=ff.form_four_id where logsheet_date between from_date and to_date and cancel_type='Unplanned' and reason !='0'),0) uplan_cancle,ifnull((select sum(no_of_trips)  " + 
				     		"from form_four ff inner join schedule_cancellation_cause scc on scc.schedule_id=ff.schedule_number_id and scc.form_four_id=ff.form_four_id  " + 
				     		"where logsheet_date between from_date and to_date and cancel_type='Unplanned' and reason !='0'),0) uplan_trip from ( SELECT `trip_no`, `canceled_trip_kms`, `extra_trip_KM`, `logsheet_date`,  " + 
				     		"`extra_trip_reason` FROM `logsheet_cancelled_trip_new` WHERE `logsheet_date` ='"+date+"' ) a )b)c";
					 System.out.println(sqlforkms);
					 rs = stmt.executeQuery(sqlforkms);
					 int i2=1;
					 double effkms=0.0;
					 double extra=0.0;
					 double ccextra=0.0;
						regionTypeAjaxString.append("<thead><tr ><th> <center>Sno</center></th><th> <center>Cancellation Type</center></th><th> <center>No Trips</center></th><th><center>KMS</center></th>" +

										""+"</tr></thead><tbody>");
						if(rs.wasNull()) {
							regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Scheduled (As per form V)</center></td><td><center>0</center></td><td><center>0</center></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Cancelled</center></td><td><center>0</center></td><td><center>0</center></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Operated</center></td><td><center>0</center></td><td><center>0</center></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Cancelled Schedules(Planned)</center></td><td><center>0</center></td><td><center>0</center></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Cancelled Schedules(Cause-wise)</center></td><td><center>0</center></td><td><center>0</center></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Extra kms && CC Kms</center></td><td><center></center></td><td><center>0&&0</center></td></tr>");
							 
							 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>% of Cancellation Plan && Non-Plan</center></td><td><center>0&&0</center></td><td><center>0&&0</center></td></tr>");
						}
					 while (rs.next()) {
						//	regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1' style='width:100%'>");
					/*		regionTypeAjaxString.append("<thead><tr ><th> <center>Sno</center></th><th> <center>Cancellation Type</center></th><th> <center>No Trips</center></th><th><center>KMS</center></th>" +

										""+"</tr></thead><tbody>");*/
						
							effkms +=Double.parseDouble(rs.getString("actual_oper_kms").toString());
							System.out.println(effkms);
							extra +=Double.parseDouble(rs.getString("extrakm").toString());
							System.out.println(extra);
							ccextra +=Double.parseDouble(rs.getString("cc_extrakm").toString());
							System.out.println(ccextra);
						 regionTypeAjaxString .append( "");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Scheduled (As per form V)</center></td><td><center>"+rs.getString("s_trip").toString()+"</center></td><td><center>"+rs.getString("s_kms").toString()+"</center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Cancelled</center></td><td><center>"+rs.getString("tot_cancel_trips").toString()+"</center></td><td><center>"+rs.getString("tot_cancel_kms").toString()+"</center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Operated</center></td><td><center>"+rs.getString("atutal_opr_trips").toString()+"</center></td><td><center>"+rs.getString("actual_oper_kms").toString()+"</center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Cancelled Schedules(Planned)</center></td><td><center>"+rs.getString("plan_trip").toString()+"</center></td><td><center>"+rs.getString("plan_cancle").toString()+"</center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Cancelled Schedules(Cause-wise)</center></td><td><center>"+rs.getString("totaluptrips").toString()+"</center></td><td><center>"+rs.getString("totunplankms").toString()+"</center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>Extra kms</center></td><td><center></center></td><td><center>"+rs.getString("extrakm").toString()+"</center></td></tr>");
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>CC Kms</center></td><td><center></center></td><td><center>"+rs.getString("cc_extrakm").toString()+"</center></td></tr>");

						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>% of Cancellation Plan</center></td><td><center>"+rs.getString("plan_cantrip_per").toString()+"</center></td><td><center>"+rs.getString("plan_cankm_per").toString()+"</center></td></tr>");
						 
						 regionTypeAjaxString .append( "<tr><td><center>"+i2+++"</center></td><td><center>% of Non-Plan</center></td><td><center>"+rs.getString("unplan_cantrip_per").toString()+"</center></td><td><center>"+rs.getString("unplan_cankm_per").toString()+"</center></td></tr>");


					 }
					 
					 String  sqlforrevenue="select sum(rev) rev,revtype   from ( " + 
					 		"select if(ticket_manual.ticket_type_manual_name='Normal','Ticket',ticket_manual.ticket_type_manual_name) as revtype,sum(ifnull(block.value,0)) as rev from Waybill_Details wd   " + 
					 		"inner join waybill_cwa_block_master cwa  on cwa.waybill_cwa_block_master_id=wd.param2   " + 
					 		"inner join Waybill_cwa_receipt_details block  on block.waybill_cwa_block_master_id = cwa.waybill_cwa_block_master_id 	 " + 
					 		"inner join denomination_type_manual denoType ON block.denomination_type_manual_id = denoType.denomination_type_manual_id 	 " + 
					 		"inner join ticket_type_manual ticket_manual on	block.ticket_type_manual_id=ticket_manual.ticket_type_manual_id    " + 
					 		"where  wd.Ticket_Audited_Date between '"+date+" 00:00:00' and '"+date+" 23:59:59' and block.value!=0  " + 
					 		"and ticket_manual.ticket_type_manual_id in (1,2) group by ticket_manual.ticket_type_manual_id  " + 
					 		"union " + 
					 		"select revtype,sum(rev) rev from ( " + 
					 		"select sum(px_total_amount) rev,if(ticket_type_short_code !=9,'Ticket','Pass - Daily') revtype from Waybill_Details wd inner join ticket t on t.waybil_Id=wd.waybil_Id " + 
					 		"where  " + 
					 		"wd.Ticket_Audited_Date between  '"+date+" 00:00:00' and '"+date+" 23:59:59' group by ticket_type_short_code " + 
					 		" " + 
					 		") a group by revtype ) b group by revtype order by revtype desc ";
					 	/*	"union  " + 
					 		"SELECT ifnull(sum(chartered_revenue),0) rev,'Chartered' revtype " + 
					 		"FROM `dedicated_charetered_revenue` " + 
					 		"WHERE `month_year` =  '"+date+"' group by revtype " + 
					 		"union  " + 
					 		"SELECT ifnull(sum(dedicated_revenue),0) rev,'dedicated' revtype " + 
					 		"FROM `dedicated_charetered_revenue` " + 
					 		"WHERE `month_year` = '"+date+"' group by revtype";*/
					 
					 System.out.println(sqlforrevenue);
					 rs = stmt.executeQuery(sqlforrevenue);
					 int i3=1;
					 double epkms=effkms+extra+ccextra;
						double totrev=0.0;
						//double ctrips=0.0;
				   		//regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1' style='width:100%'>");
						regionTypeAjaxString.append("<thead><tr ><th> <center>Sno</center></th><th> <center>Revenue Type</center></th><th> <center>Revenue</center></th><th><center></center></th>" +

									""+"</tr></thead><tbody>");
						if(rs.wasNull()) {
						 	 regionTypeAjaxString .append( "<tr>");
					         regionTypeAjaxString .append( "<td><center>"+ i3+"</center></td>");
					     regionTypeAjaxString .append( "<td><center>0</center></td>");
						 regionTypeAjaxString .append( "<td><center>0</center></td>");
						
				
						 regionTypeAjaxString .append( "<td><center></center></td>");
						 //ckms+=Double.parseDouble(rs.getString("can_kms").toString());
					
							 regionTypeAjaxString .append( "</tr>");
							 
							 
						}
					     while (rs.next()) {
					    	 
					 
								
				 		  i3++;
			
					    	 regionTypeAjaxString .append( "<tr>");
					         regionTypeAjaxString .append( "<td><center>"+ i3+"</center></td>");
					     regionTypeAjaxString .append( "<td><center>"+ rs.getString("revtype").toString() +"</center></td>");
						 regionTypeAjaxString .append( "<td><center>"+rs.getString("rev").toString()+"</center></td>");
						 totrev+=Double.parseDouble(rs.getString("rev").toString());
				
						 regionTypeAjaxString .append( "<td><center></center></td>");
						 //ckms+=Double.parseDouble(rs.getString("can_kms").toString());
					
							 regionTypeAjaxString .append( "</tr>");
					    }
					 //    double rev=0.00;
						    List< Map<String, Object>> chat=getcharted(depot,date,i3);
					     
					      System.out.println(chat.size());
							for (int l = 0; l< chat.size(); l++) {
						 		  i3++;
						 		 Map<String, Object> rss1 = chat.get(l);
						 		 System.out.println(rss1);
							    	 regionTypeAjaxString .append( "<tr>");
							         regionTypeAjaxString .append( "<td><center>"+ i3+"</center></td>");
							     regionTypeAjaxString .append( "<td><center>"+ rss1.get("revtype").toString() +"</center></td>");
								 regionTypeAjaxString .append( "<td><center>"+rss1.get("rev").toString()+"</center></td>");
								 totrev+=Double.parseDouble(rss1.get("rev").toString());
						
								 regionTypeAjaxString .append( "<td><center></center></td>");
								
							
									 regionTypeAjaxString .append( "</tr>");
							}
							
							
							
							
					     regionTypeAjaxString .append( "<tr><td><center>Total</center></td><td></td><td><center>"+totrev+"</center></td><td><center></center></td></tr>");
					     regionTypeAjaxString .append( "<tr><td><center>EPKM</center></td><td></td><td><center>"+new BigDecimal((totrev/epkms)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</center></td><td><center></center></td></tr>");

					     
					     
					     String sqlfordeadkms="select *,depot_dead_km+bd_attend_km+crew_change_km+bank_counter_km+vehicle_exchange_km+others+depot_cws+sch_dead_kms+call_bus total from ( " + 
					     		"SELECT ifnull( SUM(`depot_dead_km`),0) depot_dead_km, ifnull(SUM(`bd_attend_km`),0) bd_attend_km, " + 
					     		"ifnull(SUM(`crew_change_km`),0) crew_change_km,ifnull( SUM(`bank_counter_km`),0) bank_counter_km, ifnull(SUM(`vehicle_exchange_km`),0) vehicle_exchange_km, " + 
					     		"ifnull(SUM(`others`),0) others,ifnull( SUM(`depot_cws`),0) depot_cws,ff.dead_kms sch_dead_kms,ff.call_bus " + 
					     		"FROM `gen_logsheet` gl inner join causewise_dead_km cdk on cdk.gen_logsheet_id=gl.gen_logsheet_id " + 
					     		"join (SELECT SUM(if(trip_type = 3,`distance`,0)) dead_kms,SUM(if(trip_type = 18,`distance`,0)) call_bus " + 
					     		"FROM `schedule_details` sd "
					     		
					     		+ "INNER JOIN form_four ff ON ff.form_four_id=sd.form_four_id "
					     		+ " INNER JOIN schedule s ON s.schedule_id=sd.schedule_number " + 
					     		 " inner join  `Waybill_Details` wd on wd.schedule_No=ff.form_four_id "+
					     		"WHERE  sd.deleted_status = '0' and trip_type in (3,18) and date(wd.Ticket_Audited_Date)='"+date+"' and (ETIM_Coll_Amt+Bag_Coll_Amt)>0 ) ff  " + 
					     		"WHERE `gen_logsheet_date` = '"+date+"' )a";
					     
					     
						 System.out.println(sqlfordeadkms);
						 rs = stmt.executeQuery(sqlfordeadkms);
						 int i4=1;
						 double dead=0.0;
							regionTypeAjaxString.append("<thead><tr ><th> <center>Sno</center></th><th> <center>Dead Type</center></th><th> <center>Kms</center></th><th><center></center></th>" +

											""+"</tr></thead><tbody>");
							
							if(rs.wasNull()) {
							
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Scheduled Dead</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Call Bus</center></td><td><center>0</center></td><td></td></tr>");

							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Depot Dead</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Depot Cws</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>BD</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Crew Change</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Bank Counter</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Vehicle Exchange</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Others</center></td><td><center>0</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>Total</center></td><td></td><td><center>0</center></td><td></td></tr>");

							}
							 
						 while (rs.next()) {
							//	regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1' style='width:100%'>");
							
							
							 
							 regionTypeAjaxString .append( "");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Scheduled Dead</center></td><td><center>"+rs.getString("sch_dead_kms").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Call Bus</center></td><td><center>"+rs.getString("call_bus").toString()+"</center></td><td></td></tr>");

							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Depot Dead</center></td><td><center>"+rs.getString("depot_dead_km").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Depot Cws</center></td><td><center>"+rs.getString("depot_cws").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>BD</center></td><td><center>"+rs.getString("bd_attend_km").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Crew Change</center></td><td><center>"+rs.getString("crew_change_km").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Bank Counter</center></td><td><center>"+rs.getString("bank_counter_km").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Vehicle Exchange</center></td><td><center>"+rs.getString("vehicle_exchange_km").toString()+"</center></td><td></td></tr>");


							 regionTypeAjaxString .append( "<tr><td><center>"+i4+++"</center></td><td><center>Others</center></td><td><center>"+rs.getString("others").toString()+"</center></td><td></td></tr>");
							 regionTypeAjaxString .append( "<tr><td><center>Total</center></td><td></td><td><center>"+rs.getString("total").toString()+"</center></td><td></td></tr>");

							 dead+=Double.parseDouble(rs.getString("total").toString());


						 }
						 double gross=epkms-dead;
						 
						 
						 String  sqlforfuel="SELECT ifnull(SUM(`fuel`),0) fuel " + 
						 		"FROM `gen_logsheet` gl " + 
						 		"inner join Waybill_Details wd on wd.waybil_id=gl.waybill_id " + 
						 		"WHERE date(Ticket_Audited_Date)='"+date+"'";
						 System.out.println(sqlforfuel);
					ResultSet	 rss = stmt.executeQuery(sqlforfuel);
					double logsheetfuel=0.00;
					while(rss.next()){
System.out.println(rss.getString("fuel").toString());
						  logsheetfuel=Double.parseDouble(rss.getString("fuel").toString());
					}
						 int i5=1;
						 double hsd=getfuel(date,depotdb);
							regionTypeAjaxString.append("<thead><tr ><th> <center>Sno</center></th><th> <center>Fuel</center></th><th> <center></center></th><th><center></center></th>" +

												""+"</tr></thead><tbody>");
							
							if(hsd==0 && logsheetfuel !=0 ) {
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>Gross Kms</center></td><td><center>"+gross+"</center></td><td></td></tr>");
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>HSD Consumed(Liters)</center></td><td><center>"+logsheetfuel+"(Logsheet)</center></td><td><center>0(BPCL)</center></td></tr>");
								 double logsheetkmpl=(gross/logsheetfuel)*100;
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>KMPL</center></td><td><center>"+new BigDecimal(logsheetkmpl).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"(Logsheet)</center></td><td><center>0(BPCL)</center></td></tr>");
								
							} else if(hsd !=0 && logsheetfuel ==0 ){
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>Gross Kms</center></td><td><center>"+new BigDecimal(gross).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</center></td><td></td></tr>");
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>HSD Consumed(Liters)</center></td><td><center>0(Logsheet)</center></td><td><center>"+hsd+"(BPCL)</center></td></tr>");
								double kmpl=(gross/hsd);
							//	double logsheetkmpl=(gross/logsheetfuel);
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>KMPL</center></td><td><center>0(Logsheet)</center></td><td><center>"+new BigDecimal(kmpl).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"(BPCL)</center></td></tr>");
								
							}
							else {
			
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>Gross Kms</center></td><td><center>"+new BigDecimal(gross).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</center></td><td></td></tr>");
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>HSD Consumed(Liters)</center></td><td><center>"+logsheetfuel+"(Logsheet)</center></td><td><center>"+hsd+"(BPCL)</center></td></tr>");
								double kmpl=(gross/hsd);
								double logsheetkmpl=(gross/logsheetfuel);
								 regionTypeAjaxString .append( "<tr><td><center>"+i5+++"</center></td><td><center>KMPL</center></td><td><center>"+new BigDecimal(logsheetkmpl).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"(Logsheet)</center></td><td><center>"+new BigDecimal(kmpl).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"(BPCL)</center></td></tr>");
								


							 }
						 
						 
					     
				     regionTypeAjaxString .append("</table></div>");
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		}catch (Exception e) {
                      e.printStackTrace();
                }/*	finally{
        			if(session!=null){
        				session.close();
        			}
        		}*/
	}
/*	public String getDataDao() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String div=req.getParameter("division");
		String depot=req.getParameter("depot");
		return null;
	}*/
	public Double getfuel(String date,String depot) {
		Double  fuel=0.0;
	
		HibernateUtilVtu.rebuildSessionFactory();
		Session session = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");
		try {
		String sql="SELECT ifnull(SUM(`fuel`),0) fuel FROM `vehicle_fuel_consumption` WHERE `fuel_date` = '"+date+"' AND `depot_name` = 'Depot-"+depot.substring(1, 3)+"' ";
		
		System.out.println(sql);
		 Query query = session.createSQLQuery(sql);
		   // fuel=(Double)query.list().get(0);
	
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List< Map<String, Object>> aliasToValueMapList= query.list();
		Map<String, Object> rs=aliasToValueMapList.get(0);
	  fuel=(Double) rs.get("fuel");
		return fuel;
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		
		HibernateUtilVtu.closeSession();
	}
		return fuel;
}
	public List getcharted(String depot,String date,int sl) {
		List l=null;
double rev=0.00;
String s= "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		try{
String sql="SELECT 'Charted' revtype,ifnull(sum(`kms_per_day`+ `rate/kms`),0) rev FROM `chartedrevenue` WHERE 	'"+date+"' between from_date and `to_date` and depot="+depot+" " + 
		"union " + 
		"SELECT 'Dedicated' revtype,ifnull(sum(`kms_per_day`+ `rate/kms`),0) rev FROM `dedicatedrev` WHERE 		'"+date+"' between from_date and `to_date` and depot="+depot+" " + 
		"union  " + 
		"SELECT 'CC' revtype,ifnull(sum(rent+ot_or_it+gst),0) rev FROM `casualcontractrevenue`  	WHERE date(departure_date) = 	'"+date+"' and depot_no="+depot+" " + 
		"";
			Query query=session.createSQLQuery(sql);
	
			  query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			    List< Map<String, Object>> aliasToValueMapList= query.list();
				l=aliasToValueMapList;
			 

		}catch (Exception e) {
		
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	
		
	
	
		return l;
		}

}
