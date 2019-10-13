package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

public class STONE {
	 public String startdate;
	    public String enddate;
	 private List<Schedule> schedulelist;
	    private String schedule;
	    private Map<Integer, String> divisionlist;
	 		public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}


			private Map<Integer, String> depotlist;
	 		private String depotlist1;
	 		private String divisionlist1;
	    

	public String execute() throws Exception {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
divisionlist = vvt.getDivisionName();

return "success";
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
	
	public String getScheduleNo() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("val"));
		//String shiftid= req.getParameter("shiftid");
		try {
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
     		Map l1 = this.getScheduleId(parentId);
     		Set  ll2 = l1.keySet();
     		List l2=new ArrayList(ll2);
     		String regionTypeAjaxString = "";
     		regionTypeAjaxString += "<option value='0'>All</option>";
     		for (int i = 0; i < l1.size(); i++) {
     			regionTypeAjaxString += "<option value=" + l2.get(i).toString()
     					+ ">" + l1.get(l2.get(i)).toString() + "</option>";
     		
     		}
     		HttpServletResponse response = ServletActionContext.getResponse();
     		PrintWriter out;
     		
     			out = response.getWriter();
     			out.print(regionTypeAjaxString);
     		} catch (Exception e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
//     		return null;
//     	}
		
     		return null;
     }
	
	
	@SuppressWarnings("rawtypes")
	public Map getScheduleId(int parentID) {
 //  List list = new ArrayList();
		Map list=new LinkedHashMap();
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
					list.put(rs.getString("schedule_id"),rs.getString("scheduleNumber"));
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return list;
			
	}


	public String getSTTwoReport(){
		
		StringBuffer regionTypeAjaxString=new StringBuffer();
		 StringBuffer hedd=new StringBuffer();
		 StringBuffer medd=new StringBuffer();
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		Session	session12=null;
		ResultSet rs=null;
		Transaction transaction  = null;
		try {
	
		Common common = new Common();
		HttpServletRequest req=ServletActionContext.getRequest();
		
		String date1=common.getDateFromPicker(startdate);
		 //String enddate = req.getParameter("enddate");
		//String date2=common.getDateFromPicker(enddate);
	    String division1= divisionlist1;
	    String depot1= depotlist1;
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
 String runDateTime = sdf.format(ss1);
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	Date bday=this.subtractDays(sdf1.parse(date1));
 String schdid = req.getParameter("scheduleid");
 String sch_sub_qry="";
 if(!schdid.trim().equals("0")) {
	 sch_sub_qry=" and s.schedule_id='"+schdid+"' ";
 }
 String depot=req.getParameter("depotlist1");

 String dayname= req.getParameter("days");

	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	HibernateUtilVtu.rebuildSessionFactory();
	session12 = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");
	
	 String realpath = ServletActionContext.getRequest()
				.getRealPath("/");

	String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id =  '"+depot+"'";
//	String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
	 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
	 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

	 System.out.println("connection........."+connection);
	 stmt = connection.createStatement();

/*String sql="SELECT wd.waybill_no,license_number,dayname(wd.generated_Date) dayname,wd.generated_Date,DATE_FORMAT(wd.generated_Date,'%d-%m-%y')gdate,e.TOKEN cond,ee.TOKEN driver,s.schedule_number,schedule_type_name, " + 
		"r.route_number,bs1.bus_stop_name fromstop,bs2.bus_stop_name tostop,actual_km ,total_km, ifnull(stt.shift_type_id,0)shift_type_id,total_dead_km,no_of_trips," + 
		"(`ETIM_Coll_Amt`+ `Bag_Coll_Amt`)totrev ,bag_code,ifnull(targetamount,0)targetamount, ifnull(canceled_trips,0)canceled_trips," + 
		//"ifnull((dedicated_revenue+chartered_revenue),0)cdrev,"
		"ifnull(cancel_km,0)cancel_km " + 
		"ifnull((select group_concat(cancellation_name) cause from  logsheet_cancelled_trip lct  " + 
		"inner join cancellation_cause cc on cc.cancellation_id=lct.cause_for_cancellation " + 
		"where logsheet_id=gl.gen_logsheet_id),'')reason " + 
		"FROM `Waybill_Details` wd " + 
		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No " + 
		"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " + 
		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type " + 
		"inner join route r on r.route_id=ff.route_id " + 
		"inner join bus_stop bs1 on bs1.bus_stop_id=r.start_point_id " + 
		"inner join bus_stop bs2 on bs2.bus_stop_id=r.end_point_id " + 
		" inner join vehicle v on v.vehicle_id=wd.Vehicle_No " +
		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id " +
		"left JOIN employee ee ON ee.EMPLOYEE_ID=wd.driver_Id " +
		//"left join dedicated_charetered_revenue dcr on wd.generated_Date=dcr.month_year and s.schedule_number=dcr.Schedule_no " + 
		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " + 
		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id  " + 
		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id " + 
		" LEFT JOIN shift_type stt ON stt.shift_type_id=wd.Shift_Type  " +
		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and st.week_day=dayname(wd.generated_Date) " + 
		"WHERE  date(Ticket_Audited_Date) = '"+date1+"'  "+sch_sub_qry+"  and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) >0 group by wd.waybill_no,wd.schedule_No";*/

	 String sql="SELECT date(wd.Ticket_Audited_Date) as auditeddate,count(*),ff.form_four_id,wd.waybill_no,license_number,dayname(date(wd.Ticket_Audited_Date)) dayname,wd.generated_Date,DATE_FORMAT(wd.Ticket_Audited_Date,'%d-%m-%y')gdate,e.TOKEN cond,ee.TOKEN driver," + 
	 		"s.schedule_number,schedule_type_name, r.route_number,bs1.bus_stop_name fromstop,bs2.bus_stop_name tostop,ff.actual_km ,sdist total_km,strip no_of_trips, ifnull(stt.shift_type_id,0)shift_type_id," + 
	 		"total_dead_km,(`ETIM_Coll_Amt`+ `Bag_Coll_Amt`+etm_service_tax_amt+bag_service_tax_amt+pass_service_tax_amt+etm_card_amt+card_service_tax)totrev "
	 		+ ",bag_code,ifnull(targetamount,0)targetamount, " + 
	 		"ifnull(lct11.trip_no,0) canceled_trips,ifnull(lct11.canceled_trip_kms,0) cancel_km,ifnull(extra_trip_KM,0) extra_trip_KM " + 
	 		"FROM `Waybill_Details` wd INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No " + 
	 		"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type " + 
	 		" inner join route r on r.route_id=ff.route_id inner join bus_stop bs1 on bs1.bus_stop_id=r.start_point_id inner join bus_stop bs2 on bs2.bus_stop_id=r.end_point_id  " + 
	 		" inner join (select sum(sd.distance ) sdist,count(sd.trip_number) strip,sd.form_four_id,sd.schedule_number,if(sd.shift_type_id=3,2,sd.shift_type_id) shift_type_idd  from schedule_details sd  where  sd.deleted_status=0 group by sd.form_four_id,sd.schedule_number,shift_type_idd   ) innsd on innsd.form_four_id=ff.form_four_id and innsd.schedule_number=s.schedule_id and wd.Shift_Type=innsd.shift_type_idd "+
             "inner join vehicle v on v.vehicle_id=wd.Vehicle_No " + 
	 		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id left JOIN employee ee ON ee.EMPLOYEE_ID=wd.driver_Id " + 
	 		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " + 
	 		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id  " + 
	 		"left join (select ff.form_four_id,logsheet_date,ifnull(count(trip_no),0)trip_no,s.schedule_type,ifnull(sum(canceled_trip_kms),0)canceled_trip_kms,ifnull(sum(extra_trip_KM),0)extra_trip_KM FROM  logsheet_cancelled_trip_new  lct22 " + 
	 		"INNER JOIN form_four ff ON ff.form_four_id=lct22.form_four_id   " + 
	 		"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " + 
	 		"where  lct22.canceled_trip_kms !=0  "+sch_sub_qry+"  and logsheet_date ='"+date1+"'  group by lct22.form_four_id  ) lct11 on lct11.form_four_id=ff.form_four_id "
	 				+ " and  lct11.logsheet_date=date(wd.Ticket_Audited_Date) and  lct11.schedule_type=s.schedule_type "+
	 				// "and lct11.shift_type=wd.Shift_Type " + 
	 		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id  LEFT JOIN shift_type stt ON stt.shift_type_id=wd.Shift_Type  " + 
	 		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and st.week_day=dayname(wd.generated_Date) "
	 		+ " WHERE  date(Ticket_Audited_Date) = '"+date1+"'  "+sch_sub_qry+"     " + 
	 	//	"and s.schedule_id='6272'   " + 
	 		"and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) >0    group by wd.waybill_no,wd.schedule_No  "
	 		+ " union "+
	 	
	 		// cancel  for gs and ss and do
	 		"SELECT date(wd.Ticket_Audited_Date) as auditeddate,count(*),ff.form_four_id,wd.waybill_no,license_number,ifnull(dayname(date(wd.generated_Date)),'') dayname,wd.generated_Date, " + 
	 		"DATE_FORMAT(wd.Ticket_Audited_Date,'%d-%m-%y')gdate,e.TOKEN cond,ee.TOKEN driver,s.schedule_number,schedule_type_name, r.route_number, " + 
	 		"bs1.bus_stop_name fromstop,bs2.bus_stop_name tostop,ff.actual_km ,sdist total_km,strip no_of_trips, ifnull(stt.shift_type_id,0)shift_type_id,total_dead_km, " + 
	 		"(`ETIM_Coll_Amt`+ `Bag_Coll_Amt`+etm_service_tax_amt+bag_service_tax_amt+pass_service_tax_amt+etm_card_amt+card_service_tax)totrev ,bag_code,ifnull(targetamount,0)targetamount,  " + 
	 		"ifnull(lct11.trip_no,0) canceled_trips,ifnull(lct11.canceled_trip_kms,0) cancel_km,ifnull(extra_trip_KM,0) extra_trip_KM FROM `Waybill_Details` wd  " + 
	 		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " + 
	 		" inner join (select sum(sd.distance ) sdist,count(sd.trip_number) strip,sd.form_four_id,sd.schedule_number,if(sd.shift_type_id=3,2,sd.shift_type_id) shift_type_idd  from schedule_details sd  where  sd.deleted_status=0 group by sd.form_four_id,sd.schedule_number,shift_type_idd   ) innsd on innsd.form_four_id=ff.form_four_id and innsd.schedule_number=s.schedule_id and wd.Shift_Type=innsd.shift_type_idd "+
	 		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type inner join route r on r.route_id=ff.route_id  " + 
	 		"inner join bus_stop bs1 on bs1.bus_stop_id=r.start_point_id inner join bus_stop bs2 on bs2.bus_stop_id=r.end_point_id  inner join vehicle v on v.vehicle_id=wd.Vehicle_No  " + 
	 		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id left JOIN employee ee ON ee.EMPLOYEE_ID=wd.driver_Id LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id  " + 
	 		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id  left join (select ff.form_four_id,logsheet_date,ifnull(count(trip_no),0)trip_no,lct22.shift_type,ifnull(sum(canceled_trip_kms),0)canceled_trip_kms,ifnull(sum(extra_trip_KM),0)extra_trip_KM  " + 
	 		"FROM `logsheet_cancelled_trip_new` lct22 INNER JOIN form_four ff ON ff.form_four_id=lct22.form_four_id INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " + 
	 		"where  lct22.canceled_trip_kms !=0  "+sch_sub_qry+"   " + 
	 		"group by lct22.form_four_id  ) lct11 on lct11.form_four_id=ff.form_four_id and  lct11.logsheet_date=wd.generated_Date " + 
	 		"and lct11.shift_type=wd.Shift_Type left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id  LEFT JOIN shift_type stt ON stt.shift_type_id=wd.Shift_Type   " + 
	 		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and st.week_day=dayname(wd.generated_Date)  WHERE  generated_Date = '"+date1+"'  "+sch_sub_qry+"      "
	 				+ " and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) =0  " + 
	 		"and s.schedule_number not in ( " + 
	 		"SELECT s.schedule_number FROM `Waybill_Details` wd INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id   " + 
	 		"WHERE generated_Date = '"+date1+"'  "+sch_sub_qry+"    and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) >0  and s.schedule_type in (1,3,11) " + 
	 		"group by s.schedule_number) and sch_type.schedule_type_id in (1,3,11) " + 
	 		"group by  " + 
	 		"s.schedule_number " + 
	 		" union " + 
	 		//for night out and night service cancel
	 		"SELECT date(wd.Ticket_Audited_Date) as auditeddate,count(*),ff.form_four_id,wd.waybill_no,license_number,ifnull(dayname(date(wd.generated_Date)),'') dayname,wd.generated_Date, " + 
	 		"DATE_FORMAT(wd.Ticket_Audited_Date,'%d-%m-%y')gdate,e.TOKEN cond,ee.TOKEN driver,s.schedule_number,schedule_type_name, r.route_number, " + 
	 		"bs1.bus_stop_name fromstop,bs2.bus_stop_name tostop,ff.actual_km ,sdist total_km,strip no_of_trips, ifnull(stt.shift_type_id,0)shift_type_id,total_dead_km, " + 
	 		"(`ETIM_Coll_Amt`+ `Bag_Coll_Amt`+etm_service_tax_amt+bag_service_tax_amt+pass_service_tax_amt+etm_card_amt+card_service_tax)totrev ,bag_code,ifnull(targetamount,0)targetamount,  " + 
	 		"ifnull(lct11.trip_no,0) canceled_trips,ifnull(lct11.canceled_trip_kms,0) cancel_km,ifnull(extra_trip_KM,0) extra_trip_KM FROM `Waybill_Details` wd  " + 
	 		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " + 
	 		" inner join (select sum(sd.distance ) sdist,count(sd.trip_number) strip,sd.form_four_id,sd.schedule_number,if(sd.shift_type_id=3,2,sd.shift_type_id) shift_type_idd  from schedule_details sd  where  sd.deleted_status=0 group by sd.form_four_id,sd.schedule_number,shift_type_idd   ) innsd on innsd.form_four_id=ff.form_four_id and innsd.schedule_number=s.schedule_id and wd.Shift_Type=innsd.shift_type_idd "+
	 		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type inner join route r on r.route_id=ff.route_id  " + 
	 		"inner join bus_stop bs1 on bs1.bus_stop_id=r.start_point_id inner join bus_stop bs2 on bs2.bus_stop_id=r.end_point_id  inner join vehicle v on v.vehicle_id=wd.Vehicle_No  " + 
	 		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id left JOIN employee ee ON ee.EMPLOYEE_ID=wd.driver_Id LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id  " + 
	 		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id  left join (select ff.form_four_id,logsheet_date,ifnull(count(trip_no),0)trip_no,lct22.shift_type,ifnull(sum(canceled_trip_kms),0)canceled_trip_kms,ifnull(sum(extra_trip_KM),0)extra_trip_KM  " + 
	 		"FROM `logsheet_cancelled_trip_new` lct22 INNER JOIN form_four ff ON ff.form_four_id=lct22.form_four_id INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " + 
	 		"where  lct22.canceled_trip_kms !=0  "+sch_sub_qry+"  " + 
	 		"group by lct22.form_four_id  ) lct11 on lct11.form_four_id=ff.form_four_id and  lct11.logsheet_date=wd.generated_Date " + 
	 		"and lct11.shift_type=wd.Shift_Type left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id  LEFT JOIN shift_type stt ON stt.shift_type_id=wd.Shift_Type   " + 
	 		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and st.week_day=dayname(wd.generated_Date)  WHERE  generated_Date = '"+bday.toString()+"'       and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) =0  " + 
	 		"and s.schedule_number not in ( " + 
	 		"SELECT s.schedule_number FROM `Waybill_Details` wd INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id   " + 
	 		"WHERE generated_Date = '"+date1+"'  "+sch_sub_qry+"     and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) >0  and s.schedule_type in (2,4) " + 
	 		"group by s.schedule_number) and sch_type.schedule_type_id in (2,4) " + 
	 		"group by " + 
	 		"s.schedule_number"
	 		;
	 
	 System.out.println(sql);
	 rs = stmt.executeQuery(sql);
	 


	String filePath = "Ticketing/";

	String fileName = "STTWO.txt";
	 //String path = realpath + filePath + fileName;

	hedd .append("<div id='header'  style='display: none; '><table border='1'  style='width:100%'><tr ><td  align='center' colspan='20'>" + 
 		"<center><div >" + 
 		"		<font size='5'><span>ST--1</span></font></center><br><center><b><font size='3'>Depot-"+depotdb.substring(1, 3)+"</font></b>" + 
 		"</div></center>" + 
 		"</td></tr></table></div>");
 
     regionTypeAjaxString .append( "<div  overflow-y:scroll; display:block;'><div id='printid'><table border='1'  style='width:100%'   class='table table-striped table-bordered table-hover' id='sample_6' ;border-collapse: collapse;'></thead>");
     regionTypeAjaxString .append("<tr><td  align='center' ><b>SL.No</b></td><td  align='center' ><b>Duty Date</b></td><td  align='center' ><b>Bag No</b></td><td  align='center' ><b>Target <br>Revenue</b></td><td  align='center' ><b>Conductor <br>Revenue</b></td><td  align='center' ><b>Vehicle No</b></td>"
     		+ "<td  align='center' ><b>Sch No</b></td><td  align='center' ><b>Shift</b></td><td  align='center' ><b>Schedule<br> Kms</b></td><td  align='center' ><b>Schedule<br>Dead Kms</b></td><td  align='center' ><b>VTS<br> Kms</b></td><td  align='center' ><b>Odo<br> Kms</b></td>"
     		+ "<td  align='center' ><b>Sch Trips</b></td><td  align='center' ><b>Cancel Kms<br>(VTS)</b></td>" +
				"               <td  align='center' ><b>Non Track Kms<br>(VTS)</b></td> <td  align='center' ><b>Cancel <br> Kms<br>(Logsheet)</b></td>"
				+ "<td  align='center' ><b>Cancel Trips<br>(VTS)</b></td><td  align='center' ><b>Cancel Trips<br>(Logsheet)</b></td><td  align='center' ><b>Non Track<br>Trips</b></td>"
				+ "<td  align='center' ><b>Extra Kms</b></td><td  align='center'  ><b>C.Tok</b></td>" +
				 "<td  align='center' ><b>D.Tok</b></td></tr>");
		 String path = realpath + filePath + fileName;
	   
//     
		 int i=1;
		 double schtrip=0;
		 double lcankm=0;
		 double vcanceltrip=0;
		 double nontrackkms=0;
		 double nontracktrip=0;
		 
		 double tepkm=0;
		 double tkm=0;
		 double lcantrip=0;
		 double vckm=0;
		 double odokm=0;
		 double totrev=0;
		 double ekms=0;
		 double vkms=0;
		 double dead=0;
		 double totaltarget=0;
			 while (rs.next()) {
				 if(i==1) {
					 medd .append( "<div id='hedd'><table border='1' style='width:100%' class='table table-striped table-bordered table-hover' id='sample_6' ;border-collapse: collapse;'></thead>");
					 medd .append("<tr ><td  align='center' colspan='10' ><b>Audited Date:</b>"+rs.getString("gdate").toString()+"</td><td  align='center'  colspan='10'><b>Day:</b>"+rs.getString("dayname").toString()+"</td>"+"" );
					 //hedd .append("<tr ><td  align='center' colspan='4' ><b>Target Amount:</b>"+rs.getString("targetamount").toString()+"</td><td  align='center'  colspan='5'><b>Shift:</b>"+rs.getString("schedule_type_name").toString()+"</td>"+"" );
					// hedd .append("<tr ><td  align='center' colspan='4' ></td><td  align='center'  colspan='5'><b>Shift:</b>"+rs.getString("schedule_type_name").toString()+"</td>"+"" );
					// hedd .append("<tr ><td  align='center'  colspan='9'><b>From:</b>"+rs.getString("fromstop").toString()+"<b>            To:</b>"+rs.getString("tostop").toString()+"</td>"+"" );
				
					 medd .append("</tr></table></div>");
				 }
				 
				 Double fuel=0.0;
					Map m=null;
					try {
					/*	HibernateUtilVtu.rebuildSessionFactory();
						session12 = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");*/
					 m=getfueldatafromvts(session12,rs.getString("license_number").toString(),rs.getString("waybill_no"));
					 //fuel=(Double)m.get("fuel");
					}catch (Exception e) {
			e.printStackTrace();
					}
				 regionTypeAjaxString .append( "<tr>");
				 regionTypeAjaxString .append( "<td align='center'>"+i+++"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("generated_Date").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("bag_code").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("targetamount").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("totrev").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("license_number").toString()+"</td>");

				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("schedule_number").toString()+"</td>");
				 
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("schedule_type_name").toString()+"</td>");

				 regionTypeAjaxString .append( "<td align='center'>"+m.get("sch_dist").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("total_dead_km").toString()+"</td>");
	
				 
				 regionTypeAjaxString .append( "<td align='center'>"+m.get("vts_dist").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+m.get("odo_kms").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+m.get("sch_trips").toString()+"</td>");
				
/*				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("cdrev").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("totrev").toString()+"</td>");*/
				// regionTypeAjaxString .append( "<td align='center'>"+rs.getString("total_km").toString()+"/"+rs.getString("total_dead_km").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+m.get("cdist").toString()+"</td>");
				 

				 
				 
				 regionTypeAjaxString .append( "<td align='center'>"+m.get("non_trackkms").toString()+"</td>");
				 
				// regionTypeAjaxString .append( "<td align='center'>"+m.get("cancel").toString()+"</td>");
				 //regionTypeAjaxString .append( "<td align='center'>"+m.get("").toString()+"</td>");
				
				 regionTypeAjaxString .append( "<td align='center'>"+ rs.getString("cancel_km").toString() +"</td>");
				 
				 if(!m.get("ctrip").toString().trim().equals("0")) {
					regionTypeAjaxString.append("<td align='center'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getcanceltrips('"
							+ rs.getString("license_number").toString()
				            + "','"
				        
				           +rs.getString("waybill_no").toString()
				           + "','"
					        
				           +depotdb
							+ "') title='get cancel' id='alert_details"
						
							+ "'>"
							+ m.get("ctrip").toString()+""
							+ "</a>"+
					"</td>"); 
				 }else {
					regionTypeAjaxString .append( "<td align='center'>"+m.get("ctrip").toString()+"</td>");
				 }
				 
				 
				 
				 
				 //regionTypeAjaxString .append( "<td align='center'>"+rs.getString("canceled_trips").toString()+"</td>");
				 
				 
				 
				 if(!rs.getString("canceled_trips").toString().trim().equals("0")) {
						regionTypeAjaxString.append("<td align='center'>"
								+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getcanceltripslogsheet('"
								+ rs.getString("form_four_id").toString()
					            + "','"
					        
					           +rs.getString("auditeddate").toString()
					           + "','"
						        
					           +rs.getString("waybill_no").substring(0, 3)
								+ "') title='get cancel' id='alert_details" 
							
								+ "'>"
								+ rs.getString("canceled_trips").toString()+""
								+ "</a>"+
						"</td>"); 
					 }else {
						regionTypeAjaxString .append( "<td align='center'>"+rs.getString("canceled_trips").toString()+"</td>");
					 }
				 
					regionTypeAjaxString .append( "<td align='center'>"+m.get("non_tracktrip").toString()+"</td>");
				 
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("extra_trip_KM").toString() +"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("cond").toString() +"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("driver").toString() +"</td>");
			
					 regionTypeAjaxString .append( "</tr>");
					 
					 tkm +=Double.parseDouble(m.get("sch_dist").toString());
					 
					 dead +=Double.parseDouble(rs.getString("total_dead_km").toString());
					 vkms+=Double.parseDouble(m.get("vts_dist").toString());
					 schtrip +=Double.parseDouble(m.get("sch_trips").toString());
                
					 vckm +=Double.parseDouble(m.get("cdist").toString());
					 
					 odokm +=Double.parseDouble(m.get("odo_kms").toString());
					 
					 
					 vcanceltrip +=Double.parseDouble(m.get("ctrip").toString());
					 
				      nontrackkms +=Double.parseDouble(m.get("non_trackkms").toString());
				      lcankm +=Double.parseDouble(rs.getString("cancel_km").toString());
				      lcantrip +=Double.parseDouble(rs.getString("canceled_trips").toString());
				      
				     nontracktrip +=Double.parseDouble(m.get("non_tracktrip").toString());
				      
				      ekms+=Double.parseDouble(rs.getString("extra_trip_KM").toString());
				      
				      totaltarget +=Double.parseDouble(rs.getString("targetamount").toString());
				      totrev +=Double.parseDouble(rs.getString("totrev").toString());
				      //m.get("ctrip").toString()
			 }
			 regionTypeAjaxString .append( "<tr><td align='center'>Total</td><td align='center'></td><td align='center'></td>"
				 		+ "<td align='center'>"+ BigDecimal.valueOf(totaltarget).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 		+ "<td align='center'>"+ BigDecimal.valueOf(totrev).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 		+ "<td align='center'></td><td align='center'></td>"
			 		+ "<td align='center'></td><td align='center'>"+ BigDecimal.valueOf(tkm).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 				+ "<td align='center'>"+ BigDecimal.valueOf(dead).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 		 		+ "<td align='center'>"+ BigDecimal.valueOf(vkms).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 		 		
	+ "<td align='center'>"+ BigDecimal.valueOf(odokm).setScale(2,BigDecimal.ROUND_UP)+"</td>"	
	
			 				+ "<td align='center'>"+BigDecimal.valueOf(schtrip).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(vckm).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 								+ "<td align='center'>"+ BigDecimal.valueOf(nontrackkms).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(lcankm).setScale(2,BigDecimal.ROUND_UP)+"</td>"
										
			 								+ "<td align='center'>"+ BigDecimal.valueOf(vcanceltrip).setScale(0,BigDecimal.ROUND_UP)+"</td>"
			 									
			 								
			 						+ "<td align='center'>"+ BigDecimal.valueOf(lcantrip).setScale(0,BigDecimal.ROUND_UP)+"</td>"
			 						
	
	
+ "<td align='center'>"+ BigDecimal.valueOf(nontracktrip).setScale(0,BigDecimal.ROUND_UP)+"</td>"
			 								+ "<td align='center'>"+ BigDecimal.valueOf(ekms).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 				+ "<td align='center'></td><td align='center'></td></tr>");
				regionTypeAjaxString .append("</table></div></div>");
			 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;
				
				
				
					
					FileOutputStream FOS = new FileOutputStream(path);
					PrintWriter PW = new PrintWriter(FOS);
					
				PW.close();
					out = response.getWriter();
					out.print(hedd.append(medd).append(regionTypeAjaxString));
	}catch (Exception e) {
e.printStackTrace();	
}
	finally {
		
		session1.close();
		session12.close();
		HibernateUtilVtu.admin.close();
		
	}
		
		
		return null;
}
	public Map<String, Object> getfueldatafromvts(Session session,String vehicle,String waybillno) {
		 Double fuel=0.0;
		
			Map<String, Object> rs=null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
				


/*			     String sql="select WAYBILL_NO,ifnull(totvtsdist,0)totvtsdist,ifnull(candist,0)dist,"
				     		+ "ifnull(cantips,0) cancel from ( select WAYBILL_NO,count(*) cantips,round(sum(DISTANCE)) candist, " + 
				     		"(SELECT round(SUM(`DIST_TRAVELLED`),2) " + 
				     		"FROM `waybill_trip_details_new` " + 
				     		"WHERE `WAYBILL_NO` = '"+waybillno+"') totvtsdist " + 
				     		"from `waybill_trip_details_new` wd  " + 
				     		"where  `DIST_TRAVELLED` = 0  AND `etm_passenger_amount` = 0  AND `is_dread_trip` = 0  and `WAYBILL_NO` = '"+waybillno+"' " + 
				     		")a group by a.WAYBILL_NO ";*/
				String sql="select ifnull(WAYBILL_NO,0)WAYBILL_NO,ifnull(ctrip,0)ctrip,ifnull(cdist,0)cdist,ifnull(vts_dist,0)vts_dist,ifnull(sch_dist,0)sch_dist,ifnull(sch_trips,0)sch_trips," + 
						"ifnull(non_tracktrip,0)non_tracktrip,ifnull(non_trackkms,0)non_trackkms,ifnull(odo_kms,0)odo_kms from (" + 
						"SELECT WAYBILL_NO,sum(case when (TRIP_STATUS='a' or TRIP_STATUS='f' or  TRIP_STATUS='p') AND `is_dread_trip` = 0   then 1 else 0 end) ctrip," + 
						"(select sum(tot_dist) from schedulewise_dist_odometer where SCHEDULE_NO=wd.SCHEDULE_NO  and duty_dt=wd.duty_dt and duty_type_id=wd.duty_type_id ) odo_kms ,"+
						"round((sum(case when (TRIP_STATUS='a' or TRIP_STATUS='f') AND `is_dread_trip` = 0   then DISTANCE  else 0 end))+"
						+"(sum(case when TRIP_STATUS='p'  AND `is_dread_trip` = 0   then (DISTANCE -DIST_TRAVELLED) else 0 end)) ,0) cdist," + 
						"round(SUM(`DIST_TRAVELLED`),2) vts_dist,round(sum(DISTANCE),2) sch_dist,count(TRIP_NUMBER) sch_trips," + 
						"sum(case when TRIP_STATUS='z' or TRIP_STATUS='n' or TRIP_STATUS='y' then 1 else 0 end) non_tracktrip,round(sum(case when TRIP_STATUS='z' or TRIP_STATUS='n' then DISTANCE else 0 end),2) non_trackkms " + 
						"FROM `waybill_trip_details` wd WHERE `WAYBILL_NO` = '"+waybillno+"' ) a group by WAYBILL_NO";
				   
				   
					     
			System.out.println(sql);
			 Query query = session.createSQLQuery(sql);
			   // fuel=(Double)query.list().get(0);
		
		    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		    List<Map<String, Object>> aliasToValueMapList = query.list();
		    rs = aliasToValueMapList.get(0);
		    
			//System.out.println("fuel-----+"+fuel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return rs;
				
		
	}
	public String getcanceltrip() {
	HttpServletRequest req=ServletActionContext.getRequest();
		Session session=null;
		StringBuffer	 regionTypeAjaxString =new StringBuffer();
		
		try {
			 String vehicle = req.getParameter("vehicle");
			 String waybill = req.getParameter("waybill");
		HibernateUtilVtu.rebuildSessionFactory();
		session = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");
		
		 regionTypeAjaxString .append( "<div  overflow-y:scroll; display:block;'><div id='printid'><table border='1'    class='table table-striped table-bordered table-hover' id='sample_6' ;border-collapse: collapse;'></thead>");
	     regionTypeAjaxString .append("<tr><td  align='center' ><b>SL.No</b></td><td  align='center' ><b>Vehicle NO</b></td><td  align='center' ><b>Shift</b></td><td  align='center' ><b>Dist</b></td>"
	     		+ "<td  align='center' ><b>Trip</b></td><td  align='center' ><b>ETM ST</b>"+
					 "<td  align='center' ><b>ETM ET</b></td><td  align='center' ><b>CAUSE</b></td></tr>");
	  /*   String sql="SELECT `SCHEDULED_VEHICLE_NO`, `DISTANCE`, `TRIP_NUMBER`,ifnull(shift_type_name,'')shift_type_name,"
	     		+ " ifnull(ETM_START_TIME,'')ETM_START_TIME, ifnull(ETM_END_TIME,'')ETM_END_TIME  " + 
	     		"FROM `waybill_trip_details` "
	     		+ " left join shift_type st on st.shift_type_id=duty_type_id  " + 
	     		"WHERE `WAYBILL_NO` = '"+waybill+"' AND `SCHEDULED_VEHICLE_NO` = '"+vehicle+"' AND ACCUMULATED_DISTANCE =0 and etm_passenger_count =0 and is_dread_trip=0 order by TRIP_NUMBER";
	     */
	     /////new logic
	     String sql="SELECT `SCHEDULED_VEHICLE_NO`, round(sum(case when  TRIP_STATUS='p' then (DISTANCE-DIST_TRAVELLED)  else `DISTANCE` end),2) DISTANCE,"
	     		+ " `TRIP_NUMBER`,ifnull(shift_type_name,'')shift_type_name,if(TRIP_STATUS='a','Cancel','Partial') cause,"
		     		+ " ifnull(ETM_START_TIME,'')ETM_START_TIME, ifnull(ETM_END_TIME,'')ETM_END_TIME  " +
	     		"from `waybill_trip_details` wd  left join shift_type st on st.shift_type_id=duty_type_id  " + 
	     		//"where `DUTY_DT` between '2018-09-28' and '2018-10-01' "
	     		" WHERE `WAYBILL_NO` = '"+waybill+"' "
	     		+ " and (TRIP_STATUS='a' or TRIP_STATUS='f' or TRIP_STATUS='p')  AND `is_dread_trip` = 0   group by shift_type_name,TRIP_NUMBER " + 
	     		" order by shift_type_name,TRIP_NUMBER  ";
	     
/*		   String sql="SELECT `SCHEDULED_VEHICLE_NO`, `DISTANCE`, `TRIP_NUMBER`,ifnull(shift_type_name,'')shift_type_name,"
		     		+ " ifnull(ETM_START_TIME,'')ETM_START_TIME, ifnull(ETM_END_TIME,'')ETM_END_TIME  " +
		     		 "  from `waybill_trip_details` wd  left join shift_type st on st.shift_type_id=duty_type_id  WHERE WAYBILL_NO  =  '"+waybill+"'  "
		     		+ "and `ACCUMULATED_DISTANCE` = 0  AND `etm_passenger_amount` = '0' AND `is_dread_trip` = '0'  order by shift_type_name,TRIP_NUMBER ";*/
		   
		   
	     System.out.println(sql);
		 Query query = session.createSQLQuery(sql);
		   // fuel=(Double)query.list().get(0);
	
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();
 		for (int i = 0; i < aliasToValueMapList.size(); i++) {
 		  	int j=i+1;
       	 Map<String, Object> rs = aliasToValueMapList.get(i);
	    	 regionTypeAjaxString .append( "<tr>");
	         regionTypeAjaxString .append( "<td align='center'>"+ j+"</td>");
	     regionTypeAjaxString .append( "<td align='center'>"+ rs.get("SCHEDULED_VEHICLE_NO").toString() +"</td>");
		 regionTypeAjaxString .append( "<td align='center'>"+rs.get("shift_type_name").toString()+"</td>");
		 regionTypeAjaxString .append( "<td align='center'>"+rs.get("DISTANCE").toString()+"</td>");
		 regionTypeAjaxString .append( "<td align='center'>"+rs.get("TRIP_NUMBER").toString() +"</td>");
		 regionTypeAjaxString .append( "<td align='center'>"+rs.get("ETM_START_TIME").toString() +"</td>");
		 regionTypeAjaxString .append( "<td align='center'>"+rs.get("ETM_END_TIME").toString() +"</td>");
		 regionTypeAjaxString .append( "<td align='center'>"+rs.get("cause").toString() +"</td>");
			 regionTypeAjaxString .append( "</tr>");
	    }
		regionTypeAjaxString .append("</table></div></div>");
		}catch (Exception e) {
e.printStackTrace();
}
		finally {
			
			session.close();

			HibernateUtilVtu.admin.close();
			
		}try {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
		String filePath = "Ticketing/";

		String fileName = "STTWO.txt";
		 String path = realpath + filePath + fileName;
		 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);

			FileOutputStream FOS = new FileOutputStream(path);
			PrintWriter PW = new PrintWriter(FOS);
			
		PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
			

		}catch (Exception e) {
			e.printStackTrace();
		}
			return null;
	}
	
	
	
	
	public String getcanceltriplogsheet() {
		Connection connection=null;
		Statement stmt=null;
		HttpServletRequest req=ServletActionContext.getRequest();
			Session session=null;
			StringBuffer	 regionTypeAjaxString =new StringBuffer();
			
			try {
				 String date = req.getParameter("date");
				 String formfour = req.getParameter("formfour");
				 String depot = req.getParameter("depot");
			//	 String depot	 =(String)req.getSession().getAttribute("orgchartid");
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			

			String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depotname =  '"+depot+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
			Query qry1=session.createSQLQuery(sql1).
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
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

			 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			 
			 
			 
			 regionTypeAjaxString .append( "<div  overflow-y:scroll; display:block;'><div id='printid'><table border='1'    class='table table-striped table-bordered table-hover' id='sample_6' ;border-collapse: collapse;'></thead>");
		     regionTypeAjaxString .append("<tr><td  align='center' ><b>SL.No</b></td><td  align='center' ><b>Shift</b></td><td  align='center' ><b>Trip No</b></td><td  align='center' ><b>Cancel<br>Kms</b></td>"
		     		+ "<td  align='center' ><b>Cancel<br>Reason</b></td><td  align='center' ><b>Extra<br>Kms</b>"+
						 "<td  align='center' ><b>Extra<br>Reason</b></td></tr>");

		     
		/*	   String sql="SELECT `SCHEDULED_VEHICLE_NO`, `DISTANCE`, `TRIP_NUMBER`,ifnull(shift_type_name,'')shift_type_name,"
			     		+ " ifnull(ETM_START_TIME,'')ETM_START_TIME, ifnull(ETM_END_TIME,'')ETM_END_TIME  " +
			     		 "  from `waybill_trip_details` wd  left join shift_type st on st.shift_type_id=duty_type_id  WHERE WAYBILL_NO  =  '"+waybill+"'  "
			     		+ "and `ACCUMULATED_DISTANCE` = 0  AND `etm_passenger_amount` = '0' AND `is_dread_trip` = '0'  order by shift_type_name,TRIP_NUMBER ";*/
			   String sql="select ifnull(shift_type_name,'')shift,ifnull(trip_no,0)trip_no,ifnull(canceled_trip_kms,0)canceled_trip_kms,ifnull(cancellation_name,0)cancellation_name, " + 
			   		"ifnull(extra_trip_KM,0)extra_trip_KM,ifnull(extrakm_name,'')extrakm_name " + 
			   		"FROM `logsheet_cancelled_trip_new` lct22  " + 
			   		"left JOIN form_four ff ON ff.form_four_id=lct22.form_four_id  " + 
			   		"left JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " + 
			   		"left join cancellation_cause cc on cc.cancellation_id=lct22.cause_for_cancellation " + 
			   		"left join extrakm_cause ec on  ec.extrakm_id=lct22.extra_trip_reason " + 
			   		"left join shift_type st on lct22.shift_type=st.shift_type_id " + 
			   		"where (lct22.canceled_trip_kms !=0 || extra_trip_KM !=0)  and lct22.form_four_id='"+formfour+"' and lct22.logsheet_date='"+date+"' order by trip_no";
			   
		     System.out.println(sql);
		     ResultSet rs = stmt.executeQuery(sql);
/*			 Query query = session.createSQLQuery(sql);

		
		    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);*/
		    //List<Map<String, Object>> aliasToValueMapList1 = query.list();
	 		int i=0;
		     while (rs.next()) {
	 		  i++;
	       	 //Map<String, Object> rs = aliasToValueMapList1.get(i);
		    	 regionTypeAjaxString .append( "<tr>");
		         regionTypeAjaxString .append( "<td align='center'>"+ i+"</td>");
		     regionTypeAjaxString .append( "<td align='center'>"+ rs.getString("shift").toString() +"</td>");
			 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("trip_no").toString()+"</td>");
			 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("canceled_trip_kms").toString()+"</td>");
			 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("cancellation_name").toString() +"</td>");
			 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("extra_trip_KM").toString() +"</td>");
			 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("extrakm_name").toString() +"</td>");
		
				 regionTypeAjaxString .append( "</tr>");
		    }
			regionTypeAjaxString .append("</table></div></div>");
			}catch (Exception e) {
	e.printStackTrace();
	}
			finally {
				
				session.close();

				HibernateUtilVtu.admin.close();
				
			}try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			String filePath = "Ticketing/";

			String fileName = "STTWO.txt";
			 String path = realpath + filePath + fileName;
			 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);

				FileOutputStream FOS = new FileOutputStream(path);
				PrintWriter PW = new PrintWriter(FOS);
				
			PW.close();
				out = response.getWriter();
				out.print(regionTypeAjaxString);
				

			}catch (Exception e) {
				e.printStackTrace();
			}
				return null;
		}
	
	public static  Date subtractDays(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
				
		return cal.getTime();
	}
}
