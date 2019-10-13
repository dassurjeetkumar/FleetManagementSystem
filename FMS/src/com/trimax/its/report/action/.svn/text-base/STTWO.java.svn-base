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
import java.util.Date;
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

public class STTWO {
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
        		regionTypeAjaxString += "<option value='0'>select</option>";
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
//        		return null;
//        	}
		
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
		String date2=common.getDateFromPicker(enddate);
	    String division1= divisionlist1;
	    String depot1= depotlist1;
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
//    String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	
    String schdid = req.getParameter("scheduleid");
    String depot=req.getParameter("depotlist1");
    //String shiftid1= req.getParameter("shiftid");
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
	// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

	 System.out.println("connection........."+connection);
	 stmt = connection.createStatement();

/*String sql="SELECT wd.waybill_no,license_number,dayname(wd.generated_Date) dayname,wd.generated_Date,DATE_FORMAT(wd.generated_Date,'%d-%m-%y')gdate,TOKEN,s.schedule_number,schedule_type_name, " + 
		"r.route_number,bs1.bus_stop_name fromstop,bs2.bus_stop_name tostop,actual_km ,total_km, stt.shift_type_id,total_dead_km," + 
		"(`ETIM_Coll_Amt`+ `Bag_Coll_Amt`)totrev ,bag_code,ifnull(targetamount,0)targetamount, " + 
		"ifnull((dedicated_revenue+chartered_revenue),0)cdrev,ifnull(cancel_km,0)cancel_km, " + 
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
		"left join dedicated_charetered_revenue dcr on wd.generated_Date=dcr.month_year and s.schedule_number=dcr.Schedule_no " + 
		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " + 
		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id  " + 
		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id " + 
		" LEFT JOIN shift_type stt ON stt.shift_type_id=wd.Shift_Type  " +
		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and st.week_day=dayname(wd.generated_Date) " + 
		"WHERE  date(Ticket_Audited_Date)  between '"+date1+"' and '"+date2+"'  and s.schedule_id='"+schdid+"'  and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) >0 group by wd.waybill_no";
*/
	 String sql="SELECT wd.waybill_no,license_number,dayname(wd.generated_Date) dayname,wd.generated_Date, " + 
	 		"DATE_FORMAT(wd.generated_Date,'%d-%m-%y')gdate,e.TOKEN,ee.TOKEN as drivtoken,s.schedule_number,s.schedule_id,schedule_type_name, r.route_number,bs1.bus_stop_name fromstop, " + 
	 		"bs2.bus_stop_name tostop , stt.shift_type_id,(`ETIM_Coll_Amt`+ `Bag_Coll_Amt`+etm_service_tax_amt+bag_service_tax_amt+pass_service_tax_amt+etm_card_amt+card_service_tax)totrev ,bag_code,sdist total_km,strip no_of_trips, tdea total_dead_km," + 
	 		"ifnull(targetamount,0)targetamount, ifnull((dedicated_revenue+chartered_revenue),0)cdrev,ifnull(lct11.canceled_trip_kms,0)cancel_km,  " + 
	 		"ifnull((select group_concat(cancellation_name) cause from  logsheet_cancelled_trip_new lct  inner join cancellation_cause cc on cc.cancellation_id=lct.cause_for_cancellation  " + 
	 		"where lct.form_four_id=ff.form_four_id and logsheet_date=date(Ticket_Audited_Date) and lct.shift_type=wd.Shift_Type and  canceled_trip_kms >0 and extra_trip_KM =0  ),'')reason "+
	 		" FROM `Waybill_Details` wd INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No  " + 
	 		"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type  " + 
	 		" inner join (select sum(sd.distance ) sdist,count(sd.trip_number) strip,sd.form_four_id,sd.schedule_number,if(sd.shift_type_id=3,2,sd.shift_type_id) shift_type_idd,sum(case when is_dread_trip=1 then distance else 0 end) tdea  from schedule_details sd  where  sd.deleted_status=0 group by sd.form_four_id,sd.schedule_number,shift_type_idd   ) innsd on innsd.form_four_id=ff.form_four_id and innsd.schedule_number=s.schedule_id and wd.Shift_Type=innsd.shift_type_idd "+
	"left join (select ff.form_four_id,logsheet_date,ifnull(count(trip_no),0)trip_no,s.schedule_type,ifnull(sum(canceled_trip_kms),0)canceled_trip_kms,ifnull(sum(extra_trip_KM),0)extra_trip_KM,shift_type  " + 
	 		"FROM `logsheet_cancelled_trip_new` lct22 INNER JOIN form_four ff ON ff.form_four_id=lct22.form_four_id INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " + 
	 		"where    canceled_trip_kms >0 and extra_trip_KM =0   and s.schedule_id='"+schdid+"' and logsheet_date between '"+date1+"' and '"+date2+"'  group by lct22.form_four_id,logsheet_date  ) lct11 on lct11.form_four_id=ff.form_four_id and  lct11.logsheet_date=date(wd.Ticket_Audited_Date) "
	 				+ " and  lct11.schedule_type=s.schedule_type " + 
	 		"and lct11.shift_type=wd.Shift_Type " + 
	 		"inner join route r on r.route_id=ff.route_id inner join bus_stop bs1 on bs1.bus_stop_id=r.start_point_id inner join bus_stop bs2 on bs2.bus_stop_id=r.end_point_id   " + 
	 		"inner join vehicle v on v.vehicle_id=wd.Vehicle_No left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id  left JOIN employee ee ON ee.EMPLOYEE_ID=wd.driver_Id " + 
	 		"left join dedicated_charetered_revenue dcr on wd.generated_Date=dcr.month_year and s.schedule_number=dcr.Schedule_no  " + 
	 		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id   " + 
	 		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id  LEFT JOIN shift_type stt ON stt.shift_type_id=wd.Shift_Type   " + 
	 		"left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and st.week_day=dayname(wd.generated_Date)  " + 
	 		"WHERE  date(Ticket_Audited_Date)  between '"+date1+"' and '"+date2+"'  and s.schedule_id='"+schdid+"'  and (`ETIM_Coll_Amt`+ `Bag_Coll_Amt`) >0  " + 
	 		"group by wd.waybill_no";
	 System.out.println(sql);
	 rs = stmt.executeQuery(sql);
	 


	String filePath = "Ticketing/";
	 StringBuffer hedd=new StringBuffer();
	 StringBuffer medd=new StringBuffer();
	String fileName = "STTWO.txt";
	 //String path = realpath + filePath + fileName;

	hedd .append("<div id='header'  style='display: none; '><table border='1'  style='width:100%'><tr ><td  align='center' colspan='15'> " + 
    		"<center><div > " + 
    		"		<font size='5'><span>ST--2</span></font></center><br><center><b><font size='3'>Depot-"+depotdb.substring(1, 3)+"</font></b> " + 
    		"</div></center> " + 
    		"</td></tr><tr><td align='center'colspan='8'><center><b><font size='2'><label>From Date: </label></font><font size='2'><b>"+startdate+"</font></b></center></td> " + 
    		" " + 
    		"<td align='center'colspan='7'><center><b><font size='2'><label>To Date: </label></font><font size='2'><b>"+enddate+"</font></b></center></td></tr></table></div>");
    
        regionTypeAjaxString .append( "<div  overflow-y:scroll; display:block;'><div id='printid'><table border='1'  style='width:100%'   class='table table-striped table-bordered table-hover' id='sample_6' ;border-collapse: collapse;'></thead>");
        regionTypeAjaxString .append("<tr><td  align='center' ><b>SL.No</b></td><td  align='center' ><b>Duty<br>Date</b></td><td  align='center' ><b>Day Name</b></td>"
        		+ "<td  align='center' ><b>Vehicle<br>No</b></td><td  align='center' ><b>Driver<br>Token No</b></td><td  align='center' ><b>Conductor<br>Token No</b></td>"
        		+ "<td  align='center' ><b>Charted/Dedicated<br>Revenue</b></td>" +
				"               <td  align='center' ><b>Conductor<br>Revenue</b></td> <td  align='center' ><b>Sch Kms/<br>Dead Kms</b></td><td  align='center' ><b>Cancel<br> Kms<br>As per VTS</b></td>"
				+ "<td  align='center' ><b>Cancel Kms<br>As Per Logsheet </b></td><td  align='center'  ><b>Cancel<br>Reason</b></td>" +
				 "<td  align='center' ><b>EPKM</b></td><td  align='center' ><b>HSD</b></td><td  align='center' ><b>KMPL</b></td></tr>");
		 String path = realpath + filePath + fileName;
	   
//        
		 int i=1;
		 double totrev=0;
		 double cdrev=0;
		 double cancelklms=0;
		 double tepkm=0;
		 double tkmpl=0;
		 double tkm=0;
		 double tdkm=0;
		 double vckm=0;
		 double fuel=0;
			 while (rs.next()) {
				 if(i==1) {
					 medd .append( "<div id='hedd'><table border='1' style='width:100%' class='table table-striped table-bordered table-hover' id='sample_6' ;border-collapse: collapse;'></thead>");
					 medd .append("<tr ><td  align='center' colspan='8' ><b>Bag No:</b>"+rs.getString("bag_code").toString()+"</td><td  align='center'  colspan='7'><b>Sch No:</b>"+rs.getString("schedule_number").toString()+"</td>"+"" );
					 medd .append("<tr ><td  align='center' colspan='8' ><b>Target Amount:</b>"+rs.getString("targetamount").toString()+"</td><td  align='center'  colspan='7'><b>Shift:</b>"+rs.getString("schedule_type_name").toString()+"</td>"+"" );
					// hedd .append("<tr ><td  align='center' colspan='4' ></td><td  align='center'  colspan='5'><b>Shift:</b>"+rs.getString("schedule_type_name").toString()+"</td>"+"" );
					 medd .append("<tr ><td  align='center'  colspan='15'><b>From:</b>"+rs.getString("fromstop").toString()+"<b>            To:</b>"+rs.getString("tostop").toString()+"</td>"+"" );
				
					 medd .append("</tr></table></div>");
				 }
				 
				 //Double fuel=0.0;
					Map m=null;
					try {
					/*	HibernateUtilVtu.rebuildSessionFactory();
						session12 = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");*/
						 m=getfueldatafromvts(session12,rs.getString("license_number").toString(),rs.getString("waybill_no").toString());
					// fuel=(Double)m.get("fuel");
					}catch (Exception e) {
			e.printStackTrace();
					}
				 regionTypeAjaxString .append( "<tr>");
				 regionTypeAjaxString .append( "<td align='center'>"+i+++"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("gdate").toString()+"</td>");
				 
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("dayname").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("license_number").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("drivtoken").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("TOKEN").toString()+"</td>");
		
				 double rr=0.00;
				 try {
				 rr=getcharted(depot,rs.getString("generated_Date").toString(),rs.getString("schedule_id").toString(),rs.getString("license_number").toString());
				 }catch (Exception e) {
					e.printStackTrace();
				}
				 
				 regionTypeAjaxString .append( "<td align='center'>"+ BigDecimal.valueOf(rr).setScale(2,BigDecimal.ROUND_UP) +"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("totrev").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("total_km").toString()+"/"+rs.getString("total_dead_km").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+m.get("dist").toString()+"</td>");
				 //regionTypeAjaxString .append( "<td align='center'>"+m.get("").toString()+"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+ rs.getString("cancel_km").toString() +"</td>");
				 regionTypeAjaxString .append( "<td align='center'>"+rs.getString("reason").toString() +"</td>");
				
				 
					double empkm=Double.parseDouble(rs.getString("totrev").toString())/(Double.parseDouble(rs.getString("total_km").toString())-Double.parseDouble(rs.getString("cancel_km").toString()));
					empkm=Math.rint(empkm*100)/100;
					tepkm +=empkm;
					regionTypeAjaxString .append("<td align='center'>"+ BigDecimal.valueOf(empkm).setScale(2,BigDecimal.ROUND_UP) +"</td>") ;
					
					 regionTypeAjaxString .append( "<td align='center'>"+m.get("fuel").toString()+"</td>");
					 
					 double kmpl=0.00;
					 if(Double.parseDouble(m.get("fuel").toString()) !=0) {
						 kmpl=(Double.parseDouble(rs.getString("total_km").toString())-Double.parseDouble(m.get("dist").toString()))/Double.parseDouble(m.get("fuel").toString());
						kmpl=Math.rint(empkm*100)/100;
						tkmpl +=kmpl;
					 }
						regionTypeAjaxString .append("<td align='center'>"+ BigDecimal.valueOf(kmpl).setScale(2,BigDecimal.ROUND_UP) +"</td>") ;
						
					 
					 regionTypeAjaxString .append( "</tr>");
					 totrev +=Double.parseDouble(rs.getString("totrev").toString());
				
				//	 cdrev +=Double.parseDouble(rs.getString("cdrev").toString());
					 cdrev +=rr;
					 cancelklms +=Double.parseDouble(rs.getString("cancel_km").toString());
					 vckm +=Double.parseDouble(m.get("dist").toString());
					 tkm +=Double.parseDouble(rs.getString("total_km").toString());
					 tdkm +=Double.parseDouble(rs.getString("total_dead_km").toString());
					 fuel +=Double.parseDouble(m.get("dist").toString());
			 }
			 regionTypeAjaxString .append( "<tr><td align='center'>Total</td><td align='center'></td><td align='center'></td>"
			 		+ "<td align='center'></td><td align='center'></td>"
			 		+ "<td align='center'></td><td align='center'>"+ BigDecimal.valueOf(cdrev).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 				+ "<td align='center'>"+BigDecimal.valueOf(totrev).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(tkm).setScale(2,BigDecimal.ROUND_UP)+"/"+BigDecimal.valueOf(tdkm).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(vckm).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(cancelklms).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 				+ "<td align='center'></td><td align='center'>"+ BigDecimal.valueOf(tepkm).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(fuel).setScale(2,BigDecimal.ROUND_UP)+"</td>"
			 						+ "<td align='center'>"+ BigDecimal.valueOf(tkmpl).setScale(2,BigDecimal.ROUND_UP)+"</td></tr>");
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
				


/*String sql="select if(cancel !=0 ,(sch-distt),0)dist,if(sch !=0 and distt !=0,cancel,0) cancel,fuel from( SELECT ifnull(`tot_dist`,0) distt,ifnull(sch_dist,0) sch,count(*) cancel, " + 
		"ifnull(fuel,0)fuel FROM waybill_trip_details wtd  " + 
		"left join `schedulewise_dist_capture` sdt  on wtd.DUTY_DT=sdt.duty_dt  and wtd.duty_type_id=sdt.duty_type_id " + 
		"and SCHEDULED_VEHICLE_NO=sdt.vehicle_no and `ACCUMULATED_DISTANCE` = 0 and etm_passenger_amount=0 and	etm_passenger_count=0 and is_dread_trip=0  " + 
		"left join vehicle_fuel_consumption vfc on vfc.fuel_date=sdt.duty_dt and vfc.vehicle_no=sdt.vehicle_no WHERE sdt.vehicle_no ='"+vehicle+ "' AND sdt.WAYBILL_NO  = '"+waybillno+ "'  and is_dread_trip=0 )a"; 
*/
				
				String sql="select WAYBILL_NO,ifnull(candist,0)dist,ifnull(cantips,0) cancel,ifnull(fuel,0)fuel  from  " + 
						"( select WAYBILL_NO,count(*) cantips,round(sum(case when  TRIP_STATUS='p' then (DISTANCE-DIST_TRAVELLED)  else `DISTANCE` end),2) candist,fuel    " + 
						"from `waybill_trip_details` wd   " + 
						"left join vehicle_fuel_consumption vfc on vfc.fuel_date=wd.DUTY_DT and vfc.vehicle_no=wd.SCHEDULED_VEHICLE_NO " + 
						"where  (TRIP_STATUS='a' or TRIP_STATUS='f' or TRIP_STATUS='p')  AND `is_dread_trip` = 0  and `WAYBILL_NO` ='"+waybillno+"' )a  " + 
						"group by a.WAYBILL_NO";
				
				
			System.out.println(sql);
			 Query query = session.createSQLQuery(sql);
			   // fuel=(Double)query.list().get(0);
		
		    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		    List<Map<String, Object>> aliasToValueMapList = query.list();
		    rs = aliasToValueMapList.get(0);
		    
			System.out.println("fuel-----+"+fuel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return rs;
				
		
	}
	public Double getcharted(String depot,String date,String schedule,String vehicle) {
		List l=null;
double rev=0.00;
String s= "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		try{
String sql="select sum(rev) tot from (SELECT 'Charted' revtype,ifnull(sum(`kms_per_day`+ `rate/kms`),0) rev FROM `chartedrevenue` "
		+ "WHERE 	'"+date+"' between from_date and `to_date` and depot="+depot+" and schedule_no='"+schedule+"' " + 
		"union " + 
		"SELECT 'Dedicated' revtype,ifnull(sum(`kms_per_day`+ `rate/kms`),0) rev FROM `dedicatedrev` WHERE 		'"+date+"' between from_date and `to_date` and depot="+depot+" and schedule_no='"+schedule+"' " + 
		"union  " + 
		"SELECT 'CC' revtype,ifnull(sum(rent+ot_or_it+gst),0) rev FROM `casualcontractrevenue`  	"
		+ "WHERE date(departure_date) = 	'"+date+"' and depot_no="+depot+" and vehicle_no='"+vehicle+"' " + 
		")a";
			Query query=session.createSQLQuery(sql);
	
			  query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			    List< Map<String, Object>> aliasToValueMapList= query.list();
			    rev=Double.parseDouble(aliasToValueMapList.get(0).get("tot").toString());
			 

		}catch (Exception e) {
		
			e.printStackTrace();
		
		}return rev;
}
	}