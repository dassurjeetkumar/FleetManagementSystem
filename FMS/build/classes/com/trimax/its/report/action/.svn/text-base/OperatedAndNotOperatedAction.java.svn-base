package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class OperatedAndNotOperatedAction extends ActionSupport {
	

	public String startdate;
	public String enddate;
	

	int subtotalTickets=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		public String getEnddate() {
		return enddate;
	}

	public String getStartdate() {
			return startdate;
		}
	private String depotlist1;
	public String divisionlist1;
    public String getDivisionlist1() {
		return divisionlist1;
	}


	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}
		public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

		public void setStartdate(String startdate) {
			this.startdate = startdate;
		}
		
		private Map<Integer, String> divisionlist;
		  


		public Map<Integer, String> getDivisionlist() {
				return divisionlist;
			}


			public void setDivisionlist(Map<Integer, String> divisionlist) {
				this.divisionlist = divisionlist;
			}
		

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		public String OperatedNotOperatedSchedules() throws SQLException
		{  
		Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			int totalSchedule=0;
			int totalOperated=0;
			int totalNotOperated=0;
			int totalAudited=0;
			int totalOnlineSchedule=0;
			int totalZeroAudited=0;
			int totalClosedSchedule=0;
			int totalETMOperated=0;
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
			CollectionReportDAO dao= new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			//String orgname=dao.getOrgName();
			//String depot=dao.getDepotName();
			String depotId=req.getParameter("depotlist1");
			String divisionlist=req.getParameter("division");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			Common common=new Common();
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
				 Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
				
			/* String sql="select sum(A.count)as total,sum(A.count1)as operated,sum(A.count2)as notopertaed,sum(A.count3)as audited,sum(A.count4)as closed," +
						"sum(A.count5)as online,sum(A.count6)as operatedetm,sum(A.count7)as oaudited,A.stnmae,A.sname,A.Schedult_Type,A.Shift_Type from " +
						"(select  count(Schedult_Type) as count,0 as count1,0 as count2,0 as count3,0 as count4,0 as count5,0 as count6,0 as count7," +
						"st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type,Shift_Type from Waybill_Details wd " +
						"left join shift_type st on st.shift_type_id=wd.Shift_Type left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type " +
						"where generated_Date between '"+date1+"' and '"+date2+"' and wd.Status!='INACTIVE' group by Shift_Type,Schedult_Type union all " +
						"select 0 as count,count(Schedult_Type) as count1,0 as count2,0 as count3,0 as count4,0 as count5,0 as count6,0 as count7," +
						"st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type,Shift_Type from Waybill_Details wd " +
						"left join shift_type st on st.shift_type_id=wd.Shift_Type left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type " +
						"where wd.Status in('AUDITED','closed','online') and (generated_Date between '"+date1+"' and '"+date2+"') " +
						"group by Shift_Type,Schedult_Type union all select 0 as count,0 as count1,count(Schedult_Type) as count2,0 as count3,0 as count4," +
						"0 as count5,0 as count6,0 as count7,st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type,Shift_Type from " +
						"Waybill_Details wd left join shift_type st on st.shift_type_id=wd.Shift_Type left join schedule_type sct on " +
						"sct.schedule_type_id=wd.Schedult_Type where wd.Status ='new' and (generated_Date between '"+date1+"' " +
						"and '"+date2+"') group by Shift_Type,Schedult_Type union all select 0 as count,0 as count1,0 as count2,count(Schedult_Type) as count3," +
						"0 as count4,0 as count5,0 as count6,0 as count7,st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type," +
						"Shift_Type from Waybill_Details wd left join shift_type st on st.shift_type_id=wd.Shift_Type " +
						"left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type where wd.Status in('AUDITED') and (ETIM_Coll_Amt!=0.00 or Bag_Coll_Amt!=0.00) and" +
						"(generated_Date between '"+date1+"' and '"+date2+"') group by Shift_Type,Schedult_Type union all select 0 as count,0 as count1," +
						"0 as count2,0 as count3,count(Schedult_Type) as count4,0 as count5,0 as count6,0 as count7,st.shift_type_name as stnmae," +
						"sct.schedule_type_name as sname,Schedult_Type,Shift_Type from Waybill_Details wd " +
						"left join shift_type st on st.shift_type_id=wd.Shift_Type left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type " +
						"where wd.Status in('closed') and (ETIM_Coll_Amt!=0.00 or Bag_Coll_Amt!=0.00) and (generated_Date between '"+date1+"' and '"+date2+"') group by Shift_Type,Schedult_Type " +
						"union all select 0 as count,0 as count1,0 as count2,0 as count3,0 as count4,count(Schedult_Type) as count5,0 as count6,0 as count7," +
						"st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type,Shift_Type from Waybill_Details wd " +
						"left join shift_type st on st.shift_type_id=wd.Shift_Type left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type " +
						"where wd.Status in('online') and (generated_Date between '"+date1+"' and '"+date2+"') group by Shift_Type,Schedult_Type union all " +
						"select 0 as count,0 as count1,0 as count2,0 as count3,0 as count4,0 as count5,count(Schedult_Type) as count6,0 as count7," +
						"st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type,Shift_Type from Waybill_Details wd " +
						"left join shift_type st on st.shift_type_id=wd.Shift_Type left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type " +
						"where wd.Status in('AUDITED','closed','online') and ETM_No!='' and(generated_Date between '"+date1+"' and '"+date2+"') " +
						"group by Shift_Type,Schedult_Type union all select 0 as count,0 as count1,0 as count2,0 as count3,0 as count4,0 as count5,0 as count6," +
						"count(Schedult_Type) as count7,st.shift_type_name as stnmae,sct.schedule_type_name as sname,Schedult_Type,Shift_Type " +
						"from Waybill_Details wd left join shift_type st on st.shift_type_id=wd.Shift_Type " +
						"left join schedule_type sct on sct.schedule_type_id=wd.Schedult_Type where wd.Status in('AUDITED','closed') and " +
						"ETIM_Coll_Amt=0.00 and Bag_Coll_Amt=0.00 and (generated_Date between '"+date1+"' and '"+date2+"') " +
						"group by Shift_Type,Schedult_Type) A group by A.stnmae,A.sname";*/
		
			 String sql="select ifnull(sdt1.schedule_type_name,'') sname,ifnull(a.shift_type_name,'') stnmae,count(*)*(DATEDIFF ('"+date2+"', '"+date1+"')+1) total," +
			 		"ifnull(operated,0)operated,((count(*)*(DATEDIFF ('"+date2+"', '"+date1+"')+1))-ifnull(Operated,0)) notopertaed, ifnull(audited,0)audited," +
			 		"ifnull(closed,0)closed,ifnull(Online,0)Online,ifnull(oaudited,0) oaudited,ifnull(operatedetm,0)operatedetm," +
			 		"ifnull(Schedult_Type,'')Schedult_Type,ifnull(Shift_Type,'')Shift_Type,s.schedule_type stype from (select sdt.schedule_type_id Schedult_Type,schedule_type_name,Shift_Type,shift_type_name," +
			 		"sum(if(wd.Status in ('AUDITED','Closed') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0,1,0)) Operated," +
			 		"sum(if(wd.Status = 'AUDITED' and (ETIM_Coll_Amt+Bag_Coll_Amt)>0,1,0)) Audited," +
			 		"sum(if(wd.Status = 'Closed' and (ETIM_Coll_Amt+Bag_Coll_Amt)>0,1,0)) closed ,sum(if(wd.Status = 'ONLINE',1,0)) Online," +
			 		"sum(if(wd.Status in ('AUDITED','Closed') and (ETIM_Coll_Amt+Bag_Coll_Amt)=0,1,0)) oaudited," +
			 		"sum(if(wd.Status in ('AUDITED','Closed') and (ETIM_Coll_Amt)>0,1,0)) operatedetm from " +
			 		"Waybill_Details wd inner join shift_type st on st.shift_type_id=wd.Shift_Type " +
			 		"inner join schedule_type sdt on st.schedule_type_id=sdt.schedule_type_id where (generated_Date between '"+date1+"' and '"+date2+"') group by Shift_Type)a " +
			 		"right join schedule s on a.Schedult_Type=s.schedule_type  " +
			 		"left join schedule_type sdt1 on sdt1.schedule_type_id=s.schedule_type " +
			 		"where s.status='NEW' and s.deleted_status=0 and s.current_status='CASE WORKER' " +
			 		"group by a.Shift_Type;";
			 
//			System.out.println("query"+sql);
			
			 rs = stmt.executeQuery(sql);

			
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'><h4>"+divisionlist+" </br>"+depotId+" </br>Operated And Not Operated Bag</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        
		        
		        regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
		        regionTypeAjaxString1.append("<td align='center' width='10%'><b>Schedule Type</b>&nbsp;&nbsp;&nbsp;</td><td align='center' width='20%'><b>Shift Type</b></td><td align='center' width='20%'><b>Total Schedules</b></td>" +
						"<td align='center' width='10%'><b>Operated Schedules</b></td><td align='center' width='10%'><b>Not Operated Schedules</b></td>" +
						"<td align='center' width='10%'><b>Audited Schedules</b></td><td align='center' width='10%'><b>Online Schedules</b></td>"+
				"<td align='center' width='10%'><b>0 Audited Schedules</b></td><td align='center' width='10%'><b>Closed Schedules</b></td>" +
				"<td align='center' width='10%'><b>Operated ETM Schedules</b></td></tr>");
			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
  					 while (rs.next()) {
				
				regionTypeAjaxString1.append("<tr>");
				
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("sname") +"</td>");
				if(rs.getString("stnmae")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
					
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs.getString("stnmae").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+");'>"+rs.getString("total")+"</a></td>");
				System.out.println("----"+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+");'>"+rs.getString("total"));
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction1("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("operated")+"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction2("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("notopertaed")+"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction3("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("audited")+"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction4("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("Online")+"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction5("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("oaudited")+"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction6("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("closed")+"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction7("+rs.getString("Schedult_Type")+","+rs.getString("Shift_Type")+")'>"+rs.getString("operatedetm")+"</a></td>");
				
				String sname = rs.getString("sname").toString();
				String count = rs.getString("total").toString();
				String count1 = rs.getString("operated").toString();
				String count2 = rs.getString("notopertaed").toString();
				String count3 = rs.getString("audited").toString();
				String count4 = rs.getString("closed").toString();
				String count5 = rs.getString("Online").toString();
				String count6 = rs.getString("oaudited").toString();
				String count7 = rs.getString("operatedetm").toString();
				totalSchedule +=Integer.parseInt(count);
				totalOperated +=Integer.parseInt(count1);
				totalNotOperated +=Integer.parseInt(count2);
				totalAudited +=Integer.parseInt(count3);
				totalOnlineSchedule +=Integer.parseInt(count5);
				totalZeroAudited +=Integer.parseInt(count6);
				totalClosedSchedule +=Integer.parseInt(count4);
				totalETMOperated +=Integer.parseInt(count7);
				regionTypeAjaxString1.append("</tr>");
			 }
  					 regionTypeAjaxString1.append("<tr><td><center><b>Totals</b></center></td><td></td><td align='center'><b>"+ totalSchedule+"</td><td align='center'><b>"+ totalOperated+"</td><td align='center'><b>"+ totalNotOperated+"</td><td align='center'><b>"+ totalAudited+"</td><td align='center'><b>"+ totalOnlineSchedule+"</td><td align='center'><b>"+ totalZeroAudited+"</td><td align='center'><b>"+ totalClosedSchedule+"</td><td align='center'><b>"+ totalETMOperated+"</td></tr>" +"\n");
			PrintWriter out;

			

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				// connection.close();

	            if (rs != null) {
	            	rs.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session1 !=null){
	            	 session1.close();
	             }
			}

			return null;
		}

		public String TotalSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Transaction tx=null;
			Connection connection=null;
			HttpServletRequest request = ServletActionContext.getRequest();
			
			try {
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				tx = session2.beginTransaction();
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
					
			/*String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
					"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
					"left join device d on d.device_id=wd.ETM_No " +
					"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
					"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
					"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
					"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
					"where (generated_Date between '"+date1+"' and '"+date2+"') and wd.Status!='INACTIVE' " +
					" and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"' ORDER BY CAST(bag_code AS SIGNED)";*/
			
			String sql="SELECT schedule_number FROM `schedule` WHERE `status` IN ('new','active') AND `current_status` IN ('APPROVED','CASE WORKER') AND `deleted_status` = '0'  and schedule_type='"+scheduletype+"'";
			 rs1 = stmt.executeQuery(sql);
		        System.out.println("total Schedule="+sql);
		        
			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid1'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append("<div id='header' style='display: ;'><div align='center'><h4>Details Of Total Schedules</h4></div>");

		        regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
						"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");

			        int j=0;
			        
			        while (rs1.next()) {
			        	regionTypeAjaxString1.append("<tr>");
				
				regionTypeAjaxString1.append("<td align='right'>"+ (++j) +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("schedule_number").toString() +"</td>");
//				if(rs1.getString("bag")==null){
//					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
//				}else{
//					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag")+"</td>");
//				}
//				if(rs1.getString("etm")==null){
//					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
//				}else{
//					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm")+"</td>");
//				}
//				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token")+"</td>");
//				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename")+"</td>");
//				
//				String schedule =rs1.getString("schedule").toString();
//				String token = rs1.getString("token").toString();
//				String ename = rs1.getString("ename").toString();
				
				
				regionTypeAjaxString1.append("</tr>");
			 }
regionTypeAjaxString1.append("</table></div> </div> </div>");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}


			return null;
		}

		public String OperatedSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			HttpServletRequest request = ServletActionContext.getRequest();
			
			try {
				
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
					
//			 String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
//						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
//						"left join device d on d.device_id=wd.ETM_No " +
//						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
//						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
//						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
//						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
//						"where wd.Status in('AUDITED','closed','online') and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			
			String sql=" select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
				"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
				"left join device d on d.device_id=wd.ETM_No " +
				"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
				"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
				"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
				"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
				"where wd.Status in('AUDITED','closed') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0 and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
		
			 rs1 = stmt.executeQuery(sql);
		        
		        
			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid2'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append("<div id='header' style='display: ;'><div align='center'><h4>Details Of Total Operated Schedules</h4></div>");
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
						"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");
		        
				 
			        int i=0;
			        while(rs1.next()){
			        	regionTypeAjaxString1.append("<tr>");
				
				regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("schedule").toString() +"</td>");
				if( rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("bag").toString()+"</td>");
				}
				if( rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("etm").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("token").toString()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("ename").toString()+"</td>");
				String schedule =  rs1.getString("schedule").toString();
				String token =  rs1.getString("token").toString();
				String ename =  rs1.getString("ename").toString();
				
				
				regionTypeAjaxString1.append("</tr>");
			 }
regionTypeAjaxString1.append("</table></div> </div> </div>");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}


			return null;
		}
		public String NotOperatedSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			HttpServletRequest request = ServletActionContext.getRequest();
			
			try {
				
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				
				
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 stmt = connection.createStatement();
				
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
				String sql="";	
//				String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
//						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
//						"left join device d on d.device_id=wd.ETM_No " +
//						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
//						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
//						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
//						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
//						"where wd.Status ='new' and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			
			 if(scheduletype.equalsIgnoreCase("3")){
				 sql="SELECT schedule_number FROM `schedule` WHERE `status` IN ('new','active') AND `current_status` IN ('APPROVED','CASE WORKER') AND `deleted_status` = '0'  " +
				 		" and schedule_type='"+scheduletype+"' and schedule_number not in(select sc.schedule_number as schedule from Waybill_Details wd  " +
				 				" inner join form_four ff on ff.form_four_id=wd.schedule_No inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
				 				" inner join shift_type st on st.shift_type_id=wd.Shift_Type where wd.Status in('AUDITED','closed') and " +
				 				" (ETIM_Coll_Amt+Bag_Coll_Amt)>0 and  (generated_Date between '"+date1+"' and '"+date2+"')  and Schedult_Type='"+scheduletype+"'  and wd.Shift_Type='"+shifttype+"') ";
		 }
			 else{
				 sql="SELECT schedule_number FROM `schedule` WHERE `status` IN ('new','active') AND `current_status` IN ('APPROVED','CASE WORKER') " +
						"AND `deleted_status` = '0'  and schedule_type='"+scheduletype+"' and schedule_number not in(select sc.schedule_number as schedule from Waybill_Details wd " +
						"inner join form_four ff on ff.form_four_id=wd.schedule_No inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
						"where wd.Status in('AUDITED','closed') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0 and " +
						"(generated_Date between '"+date1+"' and '"+date2+"')  and Schedult_Type='"+scheduletype+"')";
			 }
			rs1 = stmt.executeQuery(sql);
			System.out.println("query-----"+sql);

			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid3'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append(" <div id='header' style='display: ;'><div align='center'><h4>Details Of Not Operated Schedules</h4></div>");
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
					"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");
			
				
				        int i=0;
				        while(rs1.next()){
				        	
				        	regionTypeAjaxString1.append("<tr>");
				int j=i+1;
				regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
				
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("schedule_number").toString() +"</td>");
				/*if(rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag").toString()+"</td>");
				}
				if(rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm").toString()+"</td>");
				}*/
//				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token").toString()+"</td>");
//				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename").toString()+"</td>");	
//				String schedule = rs1.getString("schedule_number").toString();
//				String token = rs1.getString("token").toString();
//				String ename = rs1.getString("ename").toString();
				
				regionTypeAjaxString1.append("</tr>");
			 }
			regionTypeAjaxString1.append("</table></div> </div> ");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}

			return null;
		}

		public String AuditedSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 stmt = connection.createStatement();
				
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
		
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					
			String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
					"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
					"left join device d on d.device_id=wd.ETM_No " +
					"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
					"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
					"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
					"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
					"where wd.Status in('AUDITED') and (ETIM_Coll_Amt!=0.00 or Bag_Coll_Amt!=0.00) and " +
					"(generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			
			rs1=stmt.executeQuery(sql);
		        
			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid4'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");    
			regionTypeAjaxString1.append(" <div id='header' style='display: ;'><div align='center'><h4>Details Of Total Audited Schedules</h4></div>");    
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
					"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");
				
			        int i=0;
			        while(rs1.next()){
			        	regionTypeAjaxString1.append("<tr>");
				
				regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("schedule").toString() +"</td>");
				if(rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag").toString()+"</td>");
				}
				if(rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token").toString()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename").toString()+"</td>");
				
				String schedule = rs1.getString("schedule").toString();
				String token = rs1.getString("token").toString();
				String ename = rs1.getString("ename").toString();
				
				regionTypeAjaxString1.append("</tr>");
			 }
			regionTypeAjaxString1.append("</table></div> </div>  ");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}

			return null;
		}
		public String OnlineSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 stmt = connection.createStatement();
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
					
				String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
						"left join device d on d.device_id=wd.ETM_No " +
						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
						"where wd.Status in('online') and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			
			rs1=stmt.executeQuery(sql);
			
		        
			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid5'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append(" <div id='header' style='display: ;'><div align='center'><h4>Details Of Total Online Schedules</h4></div>");
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
					"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");
		        
		        
			        int i=0;
			        while(rs1.next()){
			        	regionTypeAjaxString1.append("<tr>");
				
			        	regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
			        	regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("schedule").toString() +"</td>");
				if(rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag").toString()+"</td>");
				}
				if(rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token").toString()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename").toString()+"</td>");
				
				String schedule = rs1.getString("schedule").toString();
				String token = rs1.getString("token").toString();
				String ename = rs1.getString("ename").toString();
				
				regionTypeAjaxString1.append("</tr>");
			 }
			regionTypeAjaxString1.append("</table></div> </div> ");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}

			return null;
		}
		public String OAuditedSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
		
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
					
			 String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
						"left join device d on d.device_id=wd.ETM_No " +
						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
						"where wd.Status in('AUDITED','closed') and ETIM_Coll_Amt=0.00 and Bag_Coll_Amt=0.00 " +
						"and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			
			rs1=stmt.executeQuery(sql);

			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid6'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append(" <div id='header' style='display: ;'><div align='center'><h4>Details Of 0 Audited Schedules</h4></div>");
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
					"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");
			
		      

			       int i=0;
			        while(rs1.next()){

			        	regionTypeAjaxString1.append("<tr>");

				regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("schedule").toString() +"</td>");
				if(rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag").toString()+"</td>");
				}
				if(rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token").toString()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename").toString()+"</td>");
				
				String schedule = rs1.getString("schedule").toString();
				String token =rs1.getString("token").toString();
				String ename = rs1.getString("ename").toString();
			
				regionTypeAjaxString1.append("</tr>");
			 }
			regionTypeAjaxString1.append("</table></div> </div> ");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}
			return null;
		}
		public String ClosedSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 stmt = connection.createStatement();
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
					
//			 String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
//						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
//						"left join device d on d.device_id=wd.ETM_No " +
//						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
//						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
//						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
//						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
//						"where wd.Status in('closed') and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			
			 String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
						"left join device d on d.device_id=wd.ETM_No " +
						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
						"where wd.Status in('closed') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0 and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			 
			rs1=stmt.executeQuery(sql);
			
			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid7'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append(" <div id='header' style='display: ;'><div align='center'><h4>Details Of Closed Schedules</h4></div>");
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
					"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");
			        int i=0;
			        while(rs1.next()){
			        	regionTypeAjaxString1.append("<tr>");

				regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");

				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("schedule").toString() +"</td>");
				if(rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag").toString()+"</td>");
				}
				if(rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token").toString()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename").toString()+"</td>");
				String schedule = rs1.getString("schedule").toString();
				String token = rs1.getString("token").toString();
				String ename = rs1.getString("ename").toString();
				
				regionTypeAjaxString1.append("</tr>");
			 }
			regionTypeAjaxString1.append("</table></div> </div> ");
			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}
			return null;
		}
		public String OperatedETMSchedules() throws SQLException
		{
			Statement stmt=null;
			Session session2 = null;
			ResultSet rs1=null;
			Connection connection=null;
			
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String depotId=request.getParameter("depotlist1");
				String divisionlist=request.getParameter("division");
				Common common=new Common();
				session2 = HibernateUtil.getSession("hibernate.cfg.xml");
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' AND division_id = '"+divisionlist+"'";
				Query qry=session2.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip")
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
				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			String scheduletype=request.getParameter("scheduletyp");
			String shifttype=request.getParameter("shifttype");
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
					
			 String sql="select d.device_serial_number as etm,e.EMPLOYEE_NAME as ename,e.token as token," +
						"sc.schedule_number as schedule,tm.bag_code as bag from Waybill_Details wd " +
						"left join device d on d.device_id=wd.ETM_No " +
						"inner join employee e on e.EMPLOYEE_ID=wd.conductor_id " +
						"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
						"inner join schedule sc on sc.schedule_id=ff.schedule_number_id " +
						"left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No " +
						"where wd.Status in('AUDITED','closed') and ETIM_Coll_Amt>0 and ETM_No!=''" +
						" and (generated_Date between '"+date1+"' and '"+date2+"') and Schedult_Type='"+scheduletype+"' and Shift_Type='"+shifttype+"'";
			 
			
			rs1=stmt.executeQuery(sql);
			
		        
			regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid8'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
			regionTypeAjaxString1.append(" <div id='header' style='display: ;'><div align='center'><h4>Details Of Operated ETM Schedules</h4></div>");
			regionTypeAjaxString1.append("<td align='center' width='10%'><b>SNo</b></td><td align='center' width='10%'><b>Schedule No</b></td><td align='center' width='20%'><b>Bag No</b></td><td align='center' width='20%'><b>ETM No</b></td>" +
					"<td align='center' width='10%'><b>Conductor Token No</b></td><td align='center' width='10%'><b>Conductor Name</b></td></tr>");					
				 
					        int i=0;
			        while(rs1.next()){
			        	regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs1.getString("schedule").toString() +"</td>");
				if(rs1.getString("bag")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bag").toString()+"</td>");
				}
				if(rs1.getString("etm")==null){
					regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				}else{
					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("etm").toString()+"</td>");
				}
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("token").toString()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("ename").toString()+"</td>");
				
				String schedule = rs1.getString("schedule").toString();
				String token = rs1.getString("token").toString();
				String ename = rs1.getString("ename").toString();
				
				regionTypeAjaxString1.append("</tr>");
			 }
			regionTypeAjaxString1.append("</table></div> </div> ");

			 
			 HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				// connection.close();

	            if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stmt != null) {
	            	stmt.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(session2 !=null){
	            	 session2.close();
	             }
			}
			return null;
		}
		

	}