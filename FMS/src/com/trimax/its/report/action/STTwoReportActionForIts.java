package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

public class STTwoReportActionForIts {
	
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		//this.setSchedulelist(getSchedulelistdata());
		return "success";
	}
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
	 private List<Schedule> schedulelist;
	    private String schedule;
	    private Map<Integer, String> divisionlist;
	 		private Map<Integer, String> depotlist;
	 		private String depotlist1;
	 		private String divisionlist1;
	    
	public Map<Integer, String> getDivisionlist() {
				return divisionlist;
			}


			public void setDivisionlist(Map<Integer, String> divisionlist) {
				this.divisionlist = divisionlist;
			}


			public Map<Integer, String> getDepotlist() {
				return depotlist;
			}


			public void setDepotlist(Map<Integer, String> depotlist) {
				this.depotlist = depotlist;
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


	public List<Schedule> getSchedulelist() {
			return schedulelist;
		}


		public void setSchedulelist(List<Schedule> schedulelist) {
			this.schedulelist = schedulelist;
		}


	public String getSchedule() {
			return schedule;
		}


		public void setSchedule(String schedule) {
			this.schedule = schedule;
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

	public String getSTTwoReport(){
		try {
			Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			Common common = new Common();
			HttpServletRequest req=ServletActionContext.getRequest();
			String date1=common.getDateFromPicker(startdate);
			 String enddate = req.getParameter("enddate");
			String date2=common.getDateFromPicker(enddate);
		    String division1= divisionlist1;
		    String depot1= depotlist1;
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
//	    String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		
	    String schdid = req.getParameter("scheduleid");
	    String depot=req.getParameter("depotlist1");
	    String shiftid1= req.getParameter("shiftid");
	    String dayname= req.getParameter("days");
	   String subqry="";
	   String subqry1="";
	    if(!dayname.trim().equals("0")) {
	    	subqry="  and dayname(month_year)='"+dayname+"'";
	    	subqry1="  and dayname(wd.generated_Date)='"+dayname+"'";
	    }
	   
//	    String scheduletypeid = req.getParameter("scheduletypeid");
//	    int shiftid= Integer.parseInt(shiftid1);
//	    if(shiftid==3){
//	    	shiftid=2;
//	    }
//	 String qry="";
//	 
//	 if(!schdid.equalsIgnoreCase("0") && !shiftid1.equalsIgnoreCase("0")&& !scheduletypeid.equalsIgnoreCase("0")){
//		 qry="AND s.schedule_id='"+schdid+"' and STYPE.shift_type="+shiftid+"" ;
//		
//		}else if(!schdid.equalsIgnoreCase("0") && shiftid1.equalsIgnoreCase("0")&& scheduletypeid.equalsIgnoreCase("0")){
//			qry=" AND s.schedule_id='"+schdid+"'";
//		}else if(schdid.equalsIgnoreCase("0") && !shiftid1.equalsIgnoreCase("0")&& !scheduletypeid.equalsIgnoreCase("0")){
//			qry=" and STYPE.shift_type="+shiftid+" ";
//		}
//		
//		else if(!scheduletypeid.equalsIgnoreCase("0") && !shiftid1.equalsIgnoreCase("0") && !schdid.equalsIgnoreCase("0") ){
//			qry=" and shift_no ='"+shiftid+"'";
//			
//		}
//		else if(!scheduletypeid.equalsIgnoreCase("0") && shiftid1.equalsIgnoreCase("0") ){
//			qry=" and wd.Schedult_Type='"+scheduletypeid+"'";
//			
//		}
//		
//		else {
//			qry="";
//		}
	 
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
	//	String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
		String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
		Query qry1=session1.createSQLQuery(sql1).
				addScalar("depotname")
				.addScalar("central_ip",Hibernate.STRING)
				.addScalar("central_uname")
				.addScalar("central_pwd");
		
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
			}
			System.out.println(depotIp+depotdb);
		 Class.forName("com.mysql.jdbc.Driver");
		 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
		// connection = DriverManager.getConnection("jdbc:mysql://10.30.2.21:3306/bmtc_doa?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswarR");

		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		 String sql="";
		 
/*		 sql="SELECT wd.generated_Date,s.schedule_number,if(s.schedule_type='3',shift_type_name,schedule_type_name) schtypename,r.route_number route_number," +
			 		"bag_code,license_number,'' bustype,service_type_name,targetamount as target,ifnull(a.trips,0)trips,ifnull(a.dista,0)dista,ifnull(gl.canceled_trips,0) ctrips,ifnull(dr.dedicatedrev,0) dedicatedrev,ifnull(dr.chartedrev,0) chartedrev," +
			 		"ifnull(gl.cancel_km,0)cancelkm,ifnull(cancellation_name,'')cancellation_name,ifnull(gl1.cancel_type,'') planedcancel," +
			 		"ifnull(cahrte,0) charte,ifnull(gl.extra_km,0) extra_km,e.TOKEN AS token," +
			 		"round((ifnull(block.manualpassenger,0)+sum(ifnull(px_total_amount,0))),0) ticketrevenue,ifnull(block.manualdailypass,0) manualdailypass, " +
			 		"ifnull(block.manualluggage,0) manualluggage,ifnull(depot_dead_km,0) ddead,ifnull(bd_attend_km,0) bddead,ifnull(crew_change_km,0) crewc," +
			 		"ifnull(bank_counter_km,0) bankc,ifnull(vehicle_exchange_km,0) vcahnge,ifnull(gl.deviation_km,0)devication,ifnull(others,0) others," +
			 		"ifnull(cdkm.depot_cws,0)dcws,ifnull(call_bus,0) cbus,"+
			 		 "ifnull(c.sdead,0) sdead,e1.TOKEN as driver,ifnull(gl.fuel,0) fuel " +
			 		"FROM Waybill_Details wd LEFT JOIN (SELECT a1.waybill_cwa_block_master_id, " +
			 		"sum(if(ticket_type_manual_id IN (1), value, 0)) AS manualpassenger, sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass," +
			 		"sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage  " +
			 		"FROM waybill_cwa_block_master a1 " +
			 		"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id  " +
			 		"GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 " +
			 		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
			 		"inner join route r on r.route_id=ff.route_id  " +
			 		"LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id  AND ticket_printed_flag ='Y' AND fare_type NOT IN ('NINC')  " +
			 		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id " +
			 		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id " +
			 		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id" +
			 		" left JOIN employee e1 ON e1.EMPLOYEE_ID=wd.driver_Id " +
			 		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type  " +
			 		"LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type  " +
			 		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id  " +
			 		"inner join vehicle v on v.vehicle_id=wd.Vehicle_No " +
			 		"LEFT JOIN gen_logsheet gl1 ON gl1.waybill_id=wd.waybil_Id and gl1.cancel_type='Planned' " +
			 		"inner join service_type sty on sty.service_type_id=wd.Service_Type " +
			 		"left join (SELECT ifnull(sum(distance),'0.0')  as dista,form_four_id,max(list_item_number) trips FROM `schedule_details` " +
			 		"WHERE  " +
			 		"`schedule_number` = '"+schdid+"'   group by form_four_id)a " +
			 		"on a.form_four_id=wd.schedule_No   " +
			 		"left join (SELECT ifnull(dedicated_revenue,'0')  as dedicatedrev,ifnull(chartered_revenue,'0') as chartedrev,Schedule_no " +
			 		"FROM `dedicated_charetered_revenue` WHERE  `month_year` BETWEEN '"+date1+"' and '"+date2+"')dr on dr.Schedule_no=s.schedule_number " +
			 		"left join (SELECT ifnull(sum(distance),'0.0')  as cahrte,form_four_id FROM `schedule_details` WHERE  " +
			 		"`schedule_number` = '"+schdid+"' and `trip_type` IN (19,20) group by form_four_id)b " +
			 		"on b.form_four_id=wd.schedule_No " +
			 		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id "+
			 		//+ "and week_day=dayname(wd.generated_Date)  " +
			 		"left join (SELECT ifnull(sum(distance),'0.0')  as sdead,form_four_id FROM `schedule_details` WHERE  " +
			 		"`schedule_number` = '"+schdid+"' and `trip_type`=3 group by form_four_id)c on c.form_four_id=wd.schedule_No " +
			 		"left join causewise_dead_km cdkm on cdkm.gen_logsheet_id=gl.gen_logsheet_id " +
			 		"WHERE wd.generated_Date BETWEEN '"+date1+"' and '"+date2+"' AND s.schedule_id='"+schdid+"'  and " +
			 		"(wd.status='AUDITED' OR wd.status='CLOSED') group by wd.waybil_Id order by  wd.generated_Date,CAST(e.token as UNSIGNED)";
			  */
		 sql="SELECT dayname(wd.generated_Date) dayname,wd.generated_Date,s.schedule_number,if(s.schedule_type='3',shift_type_name,schedule_type_name) schtypename,r.route_number route_number,wd.Shift_Type," +
		 		"bag_code,license_number,'' bustype,service_type_name,targetamount as target,ifnull(a.trips,0)trips,ifnull(a.dista,0)dista,ifnull(gl.canceled_trips,0) ctrips,ifnull(dr.dedicatedrev,0) dedicatedrev,ifnull(dr.chartedrev,0) chartedrev," +
		 		"ifnull(gl.cancel_km,0)cancelkm,ifnull(cancellation_name,'')cancellation_name,ifnull(gl1.cancel_type,'') planedcancel," +
		 		"ifnull(cahrte,0) charte,ifnull(gl.extra_km,0) extra_km,e.TOKEN AS token," +
		 		//"round((ifnull(block.manualpassenger,0)+sum(ifnull(px_total_amount,0))),0) ticketrevenue,"
		 		"round((ifnull(block.manualpassenger,0)+ifnull((select sum(px_total_amount)  from ticket where  ticket_printed_flag ='Y' AND fare_type NOT IN ('NINC') and waybil_Id=wd.waybil_Id group by waybil_Id),0)),0)ticketrevenue ,"+
		 		 "ifnull(block.manualdailypass,0) manualdailypass, " +
		 		"ifnull(block.manualluggage,0) manualluggage,ifnull(depot_dead_km,0) ddead,ifnull(bd_attend_km,0) bddead,ifnull(crew_change_km,0) crewc," +
		 		"ifnull(bank_counter_km,0) bankc,ifnull(vehicle_exchange_km,0) vcahnge,ifnull(gl.deviation_km,0)devication,ifnull(others,0) others," +
		 		"ifnull(cdkm.depot_cws,0)dcws,ifnull(call_bus,0) cbus,"+
		 		 "ifnull(c.sdead,0) sdead,e1.TOKEN as driver,ifnull(gl.fuel,0) fuel " +
		 		"FROM Waybill_Details wd LEFT JOIN (SELECT a1.waybill_cwa_block_master_id, " +
		 		"sum(if(ticket_type_manual_id IN (1), value, 0)) AS manualpassenger, sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass," +
		 		"sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage  " +
		 		"FROM waybill_cwa_block_master a1 " +
		 		"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id  " +
		 		"GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 " +
		 		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
		 		"inner join route r on r.route_id=ff.route_id  " +
		 		//"LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id  AND ticket_printed_flag ='Y' AND fare_type NOT IN ('NINC')  " +
		 		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id " +
		 		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id " +
		 		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id" +
		 		" left JOIN employee e1 ON e1.EMPLOYEE_ID=wd.driver_Id " +
		 		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type  " +
		 		"LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type  " +
		 		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id  " +
		 		"inner join vehicle v on v.vehicle_id=wd.Vehicle_No " +
		 		"LEFT JOIN gen_logsheet gl1 ON gl1.waybill_id=wd.waybil_Id and gl1.cancel_type='Planned' " +
		 		"inner join service_type sty on sty.service_type_id=wd.Service_Type " +
		 		/*"left join (SELECT ifnull(sum(distance),'0.0')  as dista,shift_type_id,form_four_id,count(trip_number) trips FROM `schedule_details` " +
		 		"WHERE  " +
		 		"`schedule_number` = '"+schdid+"' AND `deleted_status` = '0'   group by form_four_id,shift_type_id)a " +*/
		 		"left join (SELECT ifnull(sum(distance),'0.0')  as dista,if(shift_type_id=2 || shift_type_id=3,2,shift_type_id)shift_type_i  ,form_four_id,count(trip_number) trips FROM `schedule_details`  " + 
		 		"WHERE  `schedule_number` = '"+schdid+"' AND `deleted_status` = '0'   group by form_four_id,shift_type_i)a "+
		 		"on a.form_four_id=wd.schedule_No  "+
		 		"and a.shift_type_i=wd.Shift_Type  " +
		 		"left join (SELECT ifnull(dedicated_revenue,'0')  as dedicatedrev,ifnull(chartered_revenue,'0') as chartedrev,Schedule_no " +
		 		"FROM `dedicated_charetered_revenue` WHERE  `month_year` BETWEEN '"+date1+"' and '"+date2+"' "+subqry+")dr on dr.Schedule_no=s.schedule_number " +
		 		"left join (SELECT ifnull(sum(distance),'0.0')  as cahrte,form_four_id FROM `schedule_details` WHERE  " +
		 		"`schedule_number` = '"+schdid+"' and `trip_type` IN (19,20) group by form_four_id)b " +
		 		"on b.form_four_id=wd.schedule_No " +
		 		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id "+
		 		//+ "and week_day=dayname(wd.generated_Date)  " +
		 		"left join (SELECT ifnull(sum(distance),'0.0')  as sdead,form_four_id FROM `schedule_details` WHERE  " +
		 		"`schedule_number` = '"+schdid+"' and `trip_type`=3 group by form_four_id)c on c.form_four_id=wd.schedule_No " +
		 		"left join causewise_dead_km cdkm on cdkm.gen_logsheet_id=gl.gen_logsheet_id " +
		 		"WHERE wd.generated_Date BETWEEN '"+date1+"' and '"+date2+"' "+subqry1+"  AND s.schedule_id='"+schdid+"'  and " +
		 		"(wd.status='AUDITED' OR wd.status='CLOSED') group by wd.waybil_Id having ticketrevenue >0 order by  wd.generated_Date,CAST(e.token as UNSIGNED)";
		 
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);
		 
//		 Query query = session1.createSQLQuery(sql)
//				 .addScalar("target").addScalar("ctrips").addScalar("cancelkm" , Hibernate.STRING)
//					.addScalar("cancellation_name").addScalar("planedcancel").addScalar("charte", Hibernate.STRING).addScalar("extra_km", Hibernate.STRING)
//					.addScalar("token").addScalar("manualdailypass").addScalar("crewc", Hibernate.STRING).addScalar("bankc", Hibernate.STRING)
//					.addScalar("vcahnge", Hibernate.STRING).addScalar("devication", Hibernate.STRING).addScalar("others", Hibernate.STRING).addScalar("dcws", Hibernate.STRING)
//					.addScalar("cbus", Hibernate.STRING).addScalar("sdead", Hibernate.STRING) .addScalar("generated_Date").addScalar("schedule_number").addScalar("schtypename")
//					.addScalar("route_number").addScalar("bag_code").addScalar("license_number").addScalar("bustype")
//					.addScalar("service_type_name").addScalar("trips").addScalar("dista", Hibernate.STRING).addScalar("driver").addScalar("ticketrevenue").addScalar("manualluggage")
//					.addScalar("ddead", Hibernate.STRING).addScalar("bddead", Hibernate.STRING).addScalar("fuel", Hibernate.STRING);
			
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String, Object>> aliasToValueMapList = query.list();
//
//		String ticketmanualname="";
//		String realpath = ServletActionContext.getRequest()
//				.getRealPath("/");
		
	
		String filePath = "Ticketing/";

		String fileName = "STTWO.txt";
		
		//     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> ST-2</br>From Date:- "+startdate+ " To Date:- "+enddate+" </h4></div>";
		   //  regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Depot-11</br> ST-2</br>From Date:- "+startdate+ " To Date:- "+enddate+" </h4></div>";

	       // regionTypeAjaxString +="<div align='left'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
			regionTypeAjaxString +="<tr><td rowspan='2' align='center' width='10%'><b>SL.No</b></td><td rowspan='2' align='center' width='10%'><b>Date</b></td><td rowspan='2' align='center' width='10%'><b>Day Name</b></td><td rowspan='2' align='center' width='20%'><b>Sch no</b></td><td rowspan='2' align='center' width='20%'><b>Status(N/O,GS,D/O(I&II Shift),NS)</b></td>" +
					"               <td rowspan='2' align='center' width='20%'><b>Route</b></td><td rowspan='2' align='center' width='20%'><b>Bag Number</b></td><td rowspan='2' align='center' width='20%' colspan='1'><b>Vehicle Number</b></td>" +
				//	"               <td rowspan='2' align='center' width='20%'><b>Type of bus</b></td>"
					 "<td rowspan='2' align='center' width='20%'><b>Type of service</b></td><td rowspan='2' align='center' width='20%'><b>Target</b></td>" +
					"<td rowspan='2' align='center' width='20%'><b>Operated/Not operated</b></td><td rowspan='1' align='center' width='20%' colspan='2'><b>Scheduled</b></td><td rowspan='1' align='center' width='20%' colspan='2'><b>Cancelled As <BR>Per Logsheet</b></td><td rowspan='1' align='center' width='20%' colspan='2'><b>Cancelled As <br>Per VTS</b></td>" +
					"<td rowspan='2' align='center' width='20%'><b>Reason for cancellation</b></td><td rowspan='1' align='center' width='20%' colspan='3'><b>Actual operated</b></td><td rowspan='2' align='center' width='20%'><b>Planned cancellation kms</b></td>" +
					"<td rowspan='2' align='center' width='20%'><b>Chartered kms</b></td><td rowspan='2' align='center' width='20%'><b>Extra kms</b></td><td rowspan='2' align='center' width='20%'><b>Eff Kms</b></td>" +
					"<td rowspan='2' align='center' width='20%'><b>Cr Token no</b></td><td rowspan='1' align='center' width='20%' colspan='7'><b>Revenue</b></td><td rowspan='2' align='center' width='20%'><b>EPKM</b></td><td rowspan='2' align='center' width='20%'><b>Cumulative EPKM</b></td><td rowspan='1' align='center' width='20%' colspan='11'><b>Cause-wise Dead Kms</b></td>" +
					"<td rowspan='2' align='center' width='20%'><b>Gross Kms</b></td><td rowspan='2' align='center' width='20%'><b>HSD As <br>Per LogSheet</b></td><td rowspan='2' align='center' width='20%'><b>HSD As<br>Per BPCL</b></td><td rowspan='2' align='center' width='20%'><b>KMPL</b></td><td rowspan='2' align='center' width='20%'><b>Driver Token No</b></td></tr>"+
					"<tr><td align='center' width='10%' ><b>Trips</b></td> <td align='center' width='10%'><b>KMS</b></td><td align='center' width='10%' ><b>Trips</b></td> <td align='center' width='10%'><b>KMS</b></td><td align='center' width='10%' ><b>Trips</b></td> <td align='center' width='10%'><b>KMS</b></td>" +
					"<td align='center' width='10%' ><b>Trips</b></td> <td align='center' width='10%' ><b>KMS</b></td><td align='center' width='10%' ><b>Cumulative KMS</b></td>" +
					"<td align='center' width='10%' ><b>Ticket rev</b></td><td align='center' width='10%' ><b>Daily Pass rev</b></td>" +
					"<td align='center' width='10%' ><b>Dedicated Rev</b></td><td align='center' width='10%' ><b>Chartered Rev</b></td>" +
					"<td align='center' width='10%' ><b>Luggage</b></td><td align='center' width='10%' ><b>Total Revenue</b></td><td align='center' width='10%' ><b>Cumulative Revenue</b></td>" +
					"<td align='center' width='10%' ><b>Scheduled dead</b></td> <td align='center' width='10%' ><b>Depot dead</b></td>" +
					"<td align='center' width='10%' ><b>Depot to CWS</b></td> <td align='center' width='10%' ><b>Call bus</b></td>" +
					"<td align='center' width='10%' ><b>B/D atttend</b></td> <td align='center' width='10%'><b>Crew change</b></td>" +
					"<td align='center' width='10%' ><b>Bank / Counter</b></td> <td align='center' width='10%'><b>Vehilcle exchange</b></td>" +
					"<td align='center' width='10%' ><b>Deviation</b></td> <td align='center' width='10%' ><b>others</b></td>" +
					"<td align='center' width='10%' ><b>Total Dead Kms</b></td> </tr>";
			//regionTypeAjaxString +="<td colspan='9'></td><td align='center' width='10%' ><b>Cr Remitted</b></td> <td align='center' width='10%'><b>Dedicated/Chartered</b></td><td align='center' width='10%'><b>Total Revenue</b></td></tr>";

			 String path = realpath + filePath + fileName;
		   
//	        
			 int i=1;
			 double cumklms=0;
			 double cumrev=0;
			 double cumepkm=0;
				 while (rs.next()) {
					 regionTypeAjaxString +="<tr>";
					 regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("generated_Date").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("dayname").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_number").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schtypename").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("route_number").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("bag_code").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("license_number").toString() +"</td>";
					//regionTypeAjaxString +="<td></td><td align='right'>"+ rs.getString("bustype").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("service_type_name").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("target").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>Operated</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("trips").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("dista").toString() +"</td>" ;
					
					
					Double fuel=0.0;
					Map m=null;
					try {
					 m=getfueldatafromvts(rs.getString("license_number").toString(),rs.getString("generated_Date").toString(),rs.getString("Shift_Type"));
					 fuel=(Double)m.get("fuel");
					}catch (Exception e) {
			e.printStackTrace();
					}
				/*	if(rs.getString("Shift_Type").trim().equals("4") || rs.getString("Shift_Type").trim().equals("5")) {
						fuel=fuel/2;	
					}*/
					
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("ctrips").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("cancelkm").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ m.get("cancel") +"</td>" ;
						regionTypeAjaxString +="<td align='right'>"+ m.get("dist") +"</td>" ;
					
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("cancellation_name").toString() +"</td>" ;
					
					//int trip=Integer.parseInt(rs.getString("trips").toString())-(Integer.parseInt(rs.getString("ctrips").toString()));
					int trip=Integer.parseInt(rs.getString("trips").toString())-(Integer.parseInt(m.get("cancel").toString()));
					
//					double kms=Double.parseDouble(rs.getString("dista").toString())-Double.parseDouble(rs.getString("cancelkm").toString());
					double kms=Double.parseDouble(rs.getString("dista").toString())-Double.parseDouble(m.get("dist").toString());

					
					regionTypeAjaxString +="<td align='right'>"+ trip +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ BigDecimal.valueOf(kms).setScale(2,BigDecimal.ROUND_UP)+"</td>" ;
					cumklms=cumklms+kms;
					regionTypeAjaxString +="<td align='right'>"+ BigDecimal.valueOf(cumklms).setScale(2,BigDecimal.ROUND_UP) +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("planedcancel").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("charte").toString() +"</td>" ;
					
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("extra_km").toString() +"</td>" ;
					double effkms=kms+Double.parseDouble(rs.getString("charte").toString())+Double.parseDouble(rs.getString("extra_km").toString());
					regionTypeAjaxString +="<td align='right'>"+BigDecimal.valueOf(effkms).setScale(2,BigDecimal.ROUND_UP) +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("token").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("ticketrevenue").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("manualdailypass").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("dedicatedrev").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("chartedrev").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("manualluggage").toString() +"</td>" ;
					int totrev=Integer.parseInt(rs.getString("manualdailypass").toString())+Integer.parseInt(rs.getString("manualluggage").toString())+Integer.parseInt(rs.getString("ticketrevenue").toString())+Integer.parseInt(rs.getString("dedicatedrev").toString())+Integer.parseInt(rs.getString("chartedrev").toString());
					regionTypeAjaxString +="<td align='right'>"+ totrev +"</td>" ;
					cumrev=cumrev+totrev;
					regionTypeAjaxString +="<td align='right'>"+ cumrev +"</td>" ;
					double empkm=totrev/effkms;
					empkm=Math.rint(empkm*100)/100;
					cumepkm=cumepkm+empkm;
					regionTypeAjaxString +="<td align='right'>"+ BigDecimal.valueOf(empkm).setScale(2,BigDecimal.ROUND_UP) +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+  BigDecimal.valueOf(cumepkm).setScale(2,BigDecimal.ROUND_UP) +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("sdead").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("ddead").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("dcws").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("cbus").toString() +"</td>" ;
				/*	regionTypeAjaxString +="<td align='right'>"+""+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+""+"</td>" ;*/
					
					
					/*regionTypeAjaxString +="<td align='right'>"+""+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+""+"</td>" ;*/
					
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("bddead").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("crewc").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("bankc").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("vcahnge").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("devication").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("others").toString() +"</td>" ;
					double totkms=Double.parseDouble(rs.getString("sdead").toString())+Double.parseDouble(rs.getString("ddead").toString())+
							Double.parseDouble(rs.getString("dcws").toString())+Double.parseDouble(rs.getString("cbus").toString())+
							Double.parseDouble(rs.getString("bddead").toString())+Double.parseDouble(rs.getString("crewc").toString())+
							Double.parseDouble(rs.getString("bankc").toString())+Double.parseDouble(rs.getString("vcahnge").toString())+
							Double.parseDouble(rs.getString("devication").toString())+Double.parseDouble(rs.getString("others").toString());
					regionTypeAjaxString +="<td align='right'>"+ totkms +"</td>" ;
				//	regionTypeAjaxString +="<td align='right'>--</td>" ;
					double grosskms=Math.round(effkms-totkms);
					regionTypeAjaxString +="<td align='right'>"+ grosskms +"</td>" ;
				
					
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("fuel").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+fuel +"</td>" ;

					//regionTypeAjaxString +="<td align='right'>"+ rs.getString("fuel").toString() +"</td>" ;
			/*		if(rs.getString("fuel").toString().equalsIgnoreCase("0") || rs.getString("fuel").toString().equalsIgnoreCase("")){
						regionTypeAjaxString +="<td align='right'>0</td>" ;
					}else{
						double kmpls=grosskms/Double.parseDouble(rs.getString("fuel").toString());
						kmpls=Math.rint(kmpls*100)/100;
					regionTypeAjaxString +="<td align='right'>"+ kmpls +"</td>" ;
							
					}*/
					
					if(fuel==null || fuel==0 ){
						regionTypeAjaxString +="<td align='right'>0</td>" ;
					}else{
						double kmpls=grosskms/fuel;
						kmpls=Math.rint(kmpls*100)/100;
					regionTypeAjaxString +="<td align='right'>"+ kmpls +"</td>" ;
							
					}
					
					
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("driver").toString() +"</td>" ;
					
					   regionTypeAjaxString +="</tr>";
				
				}
		
		 
		 regionTypeAjaxString += "</table></div> </div> ";

		 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		
			
			FileOutputStream FOS = new FileOutputStream(path);
			PrintWriter PW = new PrintWriter(FOS);
			
		PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getScheduleNo() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("val"));
		//String shiftid= req.getParameter("shiftid");
		
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getScheduleId(parentId);
        		List<String> l2 = this.getScheduleName(parentId);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='0'>select</option>";
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
//        		return null;
//        	}
		
        		return null;
        }
	
	
	@SuppressWarnings("rawtypes")
	public List getScheduleId(int parentID) {
      List list = new ArrayList();
		
      Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		HttpServletRequest req=ServletActionContext.getRequest();
		try {
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
		 Common common = new Common();
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
		String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
		Query qry=session1.createSQLQuery(sql1).
				addScalar("depotname")
				.addScalar("central_ip",Hibernate.STRING)
				.addScalar("central_uname")
				.addScalar("central_pwd");
		
		qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry.list();	
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
			}
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
		// connection = DriverManager.getConnection("jdbc:mysql://10.30.2.21:3306/bmtc_doa?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswarR");

		 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		
//		 String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule s " +
//			 		"inner join  schedule_type st on st.schedule_type_id=s.schedule_type " +
//			 		"inner join shift_type sft on sft.schedule_type_id=st.schedule_type_id  " +
//			 		"where (s.status='ACTIVE' or s.status='NEW') and (s.current_Status='APPROVED' or s.current_Status='CASE WORKER')  " +
//			 		"and s.deleted_Status=0 and sft.shift_type_id='"+shiftid+"'";
		 String qry1="SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
					" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
		System.out.println("qry1"+qry1);
		rs=stmt.executeQuery(qry1);
	
		 while(rs.next()){
					list.add(rs.getString("schedule_id"));
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return list;
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleName(int parentID) {
		  List list = new ArrayList();
			
          Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
				Query qry=session1.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip",Hibernate.STRING)
						.addScalar("central_uname")
						.addScalar("central_pwd");
				
				qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = qry.list();	
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
					}
				 Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
				// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
				 //connection = DriverManager.getConnection("jdbc:mysql://10.30.2.21:3306/bmtc_doa?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswarR");

				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
//			 String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule s " +
//				 		"inner join  schedule_type st on st.schedule_type_id=s.schedule_type " +
//				 		"inner join shift_type sft on sft.schedule_type_id=st.schedule_type_id  " +
//				 		"where (s.status='ACTIVE' or s.status='NEW') and (s.current_Status='APPROVED' or s.current_Status='CASE WORKER')  " +
//				 		"and s.deleted_Status=0 and sft.shift_type_id='"+shiftid+"'";
			 String qry1="SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
						" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
			rs=stmt.executeQuery(qry1);
			 while(rs.next()){
						list.add(rs.getString("scheduleNumber"));
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
			
	}
	

	public String getShiftType() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("depot"));
		String scheduletypeid= req.getParameter("scheduletypeid");
		 //String dat = req.getParameter("startdate");
		 //System.out.println("dat==="+dat);
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getShiftTypeId(parentId,scheduletypeid);
        		List<String> l2 = this.getShiftTypeName(parentId,scheduletypeid);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='0'>All</option>";
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
//        		return null;
//        	}
		
        		return null;
        }
	
	
	@SuppressWarnings("rawtypes")
	public List getShiftTypeId(int parentID,String scheduletypeid) {
      List list = new ArrayList();
		
      Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		HttpServletRequest req=ServletActionContext.getRequest();
		try {
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
		 Common common = new Common();
	//	String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
			Query qry=session1.createSQLQuery(sql1).
					addScalar("depotname")
					.addScalar("central_ip",Hibernate.STRING)
					.addScalar("central_uname")
					.addScalar("central_pwd");
			
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();	
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
				}
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

			 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();

		String qry1 = "select sf.shift_type_id shift_type_id,sf.shift_type_name shift_type_name from shift_type sf " +
				"inner join schedule_type st on sf.schedule_type_id=st.schedule_type_id " +
				"where sf.status='Active' and sf.deleted_status='0' " +
				"and st.schedule_type_id='"+scheduletypeid+"' " +
						"group by sf.shift_type_id";
		System.out.println("qry1"+qry1);
		rs=stmt.executeQuery(qry1);
	
		 while(rs.next()){
//		System.out.println("--------"+rs.getString("shift_type_id"));
					list.add(rs.getString("shift_type_id"));
//				System.out.println("++++++"+list.size());
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return list;
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getShiftTypeName(int parentID,String scheduletypeid) {
		  List list = new ArrayList();
			
          Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
				 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
					Query qry=session1.createSQLQuery(sql1).
							addScalar("depotname")
							.addScalar("central_ip",Hibernate.STRING)
							.addScalar("central_uname")
							.addScalar("central_pwd");
					
					qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = qry.list();	
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
						}
					 Class.forName("com.mysql.jdbc.Driver");
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
					 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

					 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();

			 String qry1 = "select sf.shift_type_id shift_type_id,sf.shift_type_name shift_type_name from shift_type sf " +
						"inner join schedule_type st on sf.schedule_type_id=st.schedule_type_id " +
						"where sf.status='Active' and sf.deleted_status='0'   " +
						"and st.schedule_type_id='"+scheduletypeid+"' " +
								"group by sf.shift_type_id";
			rs=stmt.executeQuery(qry1);
//		System.out.println("rs==="+rs);
			 while(rs.next()){
//			System.out.println("hiiii--------"+rs.getString("shift_type_name"));
						list.add(rs.getString("shift_type_name"));
//					System.out.println("++++++"+list.size());
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
			
	}
	public Map<String, Object> getfueldatafromvts(String vehicle,String fromDate,String shift) {
		 Double fuel=0.0;
			Session session1 = null;
			Map<String, Object> rs=null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
				HibernateUtilVtu.rebuildSessionFactory();
			session1 = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");
			int sh=Integer.parseInt(shift.trim());
			System.out.println("------------------"+sh);
			String sub="";
			String sub1="";
if(sh==4 || sh==5) {
	sub="and sdt.duty_type_id="+shift;
	sub1=" and wtd.duty_type_id=sdt.duty_type_id ";
}
/*String sql="select if(cancel !=0 and sch>=distt,(sch-distt),0)dist,cancel,fuel from( " + 
		"SELECT ifnull(`tot_dist`,0) distt,ifnull(sch_dist,0) sch,count(*) cancel,ifnull(fuel,0)fuel FROM waybill_trip_details wtd " + 
		"left join `schedulewise_dist_capture` sdt  on wtd.DUTY_DT=sdt.duty_dt and wtd.duty_type_id=sdt.duty_type_id  " + 
		"and SCHEDULED_VEHICLE_NO=sdt.vehicle_no and `ACCUMULATED_DISTANCE` = 0 and etm_passenger_amount=0 and	etm_passenger_count=0 and is_dread_trip=0 " + 
		"left join vehicle_fuel_consumption vfc on vfc.fuel_date=sdt.duty_dt and vfc.vehicle_no=sdt.vehicle_no " + 
		"WHERE sdt.vehicle_no ='"+vehicle+"' AND sdt.duty_dt  =  '"+fromDate+"'   and sdt.duty_type_id='"+shift+"' )a";*/

String sql="select if(cancel !=0 ,(sch-distt),0)dist,if(sch !=0 and distt !=0,cancel,0) cancel,fuel from( SELECT ifnull(`tot_dist`,0) distt,ifnull(sch_dist,0) sch,count(*) cancel, " + 
		"ifnull(fuel,0)fuel FROM waybill_trip_details wtd  " + 
		"left join `schedulewise_dist_capture` sdt  on wtd.DUTY_DT=sdt.duty_dt "+sub1+"   " + 
		"and SCHEDULED_VEHICLE_NO=sdt.vehicle_no and `ACCUMULATED_DISTANCE` = 0 and etm_passenger_amount=0 and	etm_passenger_count=0 and is_dread_trip=0  " + 
		"left join vehicle_fuel_consumption vfc on vfc.fuel_date=sdt.duty_dt and vfc.vehicle_no=sdt.vehicle_no WHERE sdt.vehicle_no ='"+vehicle+ "' AND sdt.duty_dt  =  '"+fromDate+ "'    "+sub+ ")a "; 
	
			System.out.println(sql);
			 Query query = session1.createSQLQuery(sql);
			   // fuel=(Double)query.list().get(0);
		
		    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		    List<Map<String, Object>> aliasToValueMapList = query.list();
		    rs = aliasToValueMapList.get(0);
		    
			System.out.println("fuel-----+"+fuel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				session1.close();
				HibernateUtilVtu.admin.close();
			}
				
				return rs;
				
		
	}
	public String getPerticularDepotForDepot() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        
        if(orgtypeid.equals("2")){
//        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
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
//        		return null;
//        	}
		
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
	

}
