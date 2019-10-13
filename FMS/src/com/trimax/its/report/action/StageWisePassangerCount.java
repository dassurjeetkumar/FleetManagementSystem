package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class StageWisePassangerCount {

	
	String path="";
	char ft = 15;
	String str="";
	public String startdate;
	public String enddate;
	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

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
			divisionlist = this.getDepotStageWise();
			return "success";
		}

		
		public String stageWisePassangerCountSubmitAction() throws SQLException
		{  
		Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			int shiftidchanged = 0;
			
			try {
			int shiftid = Integer.parseInt(req.getParameter("shift"));
			if(shiftid==2){
				shiftidchanged=1;
			}
			if(shiftid==3){
				shiftidchanged=2;
			}
			if(shiftid==4){
				shiftidchanged=1;
			}
			if(shiftid==5){
				shiftidchanged=2;
			}
			String tripno=req.getParameter("val");
			String depot=req.getParameter("depot");
			String waybill=req.getParameter("waybill");
			//routeorder
			String routeorder=req.getParameter("routeorder");
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			Common common=new Common();
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
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
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
				
			String sql="select ifnull(sum(px_count),'0') count from (SELECT `trip_no`,schedule_no, `route_no`,WAYBILL_NO,vehicle_no, `ticket_no`,ticket_date,ticket_time, " +
					"`ticket_from_stop_id`,bs.bus_stop_name fromstage, `ticket_from_stop_seq_no`,shift_no," +
					"(select route_order from route_point where route_id=t.route_id and bus_stop_id=ticket_from_stop_id and fare_stage='Y') from_order," +
					"(select route_order from route_point where route_id=t.route_id and bus_stop_id=ticket_till_stop_id and fare_stage='Y') till_order," +
					"`ticket_till_stop_id`,bs1.bus_stop_name till_stage,px_count, `px_total_amount`, `stage_count` " +
					"FROM `ticket` t inner join bus_stop bs on bs.bus_stop_id=ticket_from_stop_id inner join bus_stop bs1 on bs1.bus_stop_id=ticket_till_stop_id " +
					"WHERE WAYBILL_NO = '"+waybill+"' AND `ticket_no` != '0' and trip_no='"+tripno+"' and shift_no="+shiftidchanged+" " +
					"having  till_order >= "+routeorder+" ORDER BY ticket_no,`shift_no`, `trip_no`, `ticket_from_stop_seq_no`) A";
			
			
			 rs = stmt.executeQuery(sql);

		//	String filePath = "Ticketing/";

			//String fileName = "StageWisePassangerCount.txt";
			
//			regionTypeAjaxString1 =append
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'>"+depot+" </br>StageWise Passanger Count Report </br>/h4></div>");
//			     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+divisionlist+" </br>"+depotId+" </br>Operated And Not Operated Bag</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

//		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        
//		        String nwkr="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ __ _ _ _ _\n\n"
//		          +	  "                                     "+divisionlist+"                                                                          \n"
//	      		  +   "                                            "+depotId+"                                                                                  \n" 
//	      		  +   "                                    Operated And Not Operated Bag                                                               					 \n"
//	      		  +   "                                 From Date :-"+startdate+ "TO Date:- "+enddate+"                                                       \n "
//	      		  +   "                                                               Printed Date:-"+runDateTime+"               \n";
	        
		        
//		        regionTypeAjaxString1.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
//		        regionTypeAjaxString1.append("<td align='center' width='10%'><b>Schedule Type</b>&nbsp;&nbsp;&nbsp;</td><td align='center' width='20%'><b>Shift Type</b></td><td align='center' width='20%'><b>Total Schedules</b></td>" +
//						"<td align='center' width='10%'><b>Operated Schedules</b></td><td align='center' width='10%'><b>Not Operated Schedules</b></td>" +
//						"<td align='center' width='10%'><b>Audited Schedules</b></td><td align='center' width='10%'><b>Online Schedules</b></td>"+
//				"<td align='center' width='10%'><b>0 Audited Schedules</b></td><td align='center' width='10%'><b>Closed Schedules</b></td>" +
//				"<td align='center' width='10%'><b>Operated ETM Schedules</b></td></tr>");
			
			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<tbody><tr><td align='center' width='5%'><b>Total Passanger :</b> </td>");
			
		        
//				 
//				    String path = realpath + filePath + fileName;
//			        str+=ft+nwkr+add(headingprint,5);
			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
  					 while (rs.next()) {

//				regionTypeAjaxString +="<td align='right'>"+ rs.getString("sname") +"</td>";
				//regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("sname") +"</td>");
				regionTypeAjaxString1.append("<td align='center' width='2%'><b>"+rs.getString("count")+"</b></td></tr></tbody></table></div>");
  					 }
  					 PrintWriter out;

//			FileOutputStream FOS = new FileOutputStream(path);
//			PrintWriter PW = new PrintWriter(FOS);
			
		String p=str;

//		PW.println(p);
//		PW.close();
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

		
		
		static String add(String str1, int a1) {
			StringBuilder sb = new StringBuilder(str1);
			int m1 = str1.length();
			if (m1 >= a1) {
				str1.substring(0, a1 - 1);
			}
			a1 = a1 - m1;
			for (int i = 0; i <= a1; i++) {
				sb.append(" ");
			}
			String sb1 = sb.toString();
			return sb1;
		}
		
		static String addMoney(String str1, int a1) {
	        StringBuilder sb = new StringBuilder(str1);
	        StringBuilder sb2 = new StringBuilder();

	        //String sb1 =
	        int m1 = str1.length();
	        if (m1 >= a1) {
	            str1.substring(0, a1 - 1);
	            return str1;
	        }
	        a1 = a1 - m1;
	        for (int i = 0; i < a1; i++) {
	            sb2.append(" ");
	        }
	        sb2.append(sb);
	        String sb1 = sb2.toString();
	        return sb1;
	    }

	
		
		
		public Map<Integer, String> getDepotStageWise() {
			Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session
					.createQuery("from OrganisationChart where deleted_status=0 and org_type_id=3 order by org_name");
			try {
				List<OrganisationChart> list = query.list();
				for (OrganisationChart org : list) {
					resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				}
			} catch (Exception ex) {
			} finally {
				HibernateUtil.closeSession();
			}
			return resultMap;
		}

		public String getWaybillNo() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
		
			int parentId = Integer.parseInt(req.getParameter("val"));
			 String dat = req.getParameter("startdate");
			 Common comm = new Common();
			String dat1=comm. getDateFromPicker(dat);
	        		List<String> l1 = this.getWaybillId(parentId,dat1);
	        		List<String> l2 = this.getWaybillName(parentId,dat1);
	        		String regionTypeAjaxString = "";
//	        		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		public String getShiftNoForStage() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
		
			String waybill = req.getParameter("val");
			int  depotid = Integer.parseInt(req.getParameter("depot"));
			
			
	        		List<String> l1 = this.getShiftId(waybill,depotid);
	        		List<String> l2 = this.getShiftName(waybill,depotid);
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		
		public String getTripNoForStage() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
		
			String waybill = req.getParameter("val");
			int  shift = Integer.parseInt(req.getParameter("shift"));
			int  depotid = Integer.parseInt(req.getParameter("depot"));
			
			
	        		List<String> l1 = this.getTripId(waybill,shift,depotid);
	        		List<String> l2 = this.getTripName(waybill,shift,depotid);
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		public String getStageNo() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
		
			String waybill = req.getParameter("waybill");
			int  shift = Integer.parseInt(req.getParameter("shift"));
			int  depotid = Integer.parseInt(req.getParameter("depot"));
			int tripno = Integer.parseInt(req.getParameter("val"));
			
			
	        		List<String> l1 = this.getStageNoId(waybill,shift,depotid,tripno);
	        		List<String> l2 = this.getStageNoName(waybill,shift,depotid,tripno);
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		@SuppressWarnings("rawtypes")
		public List getWaybillId(int parentID,String dat1) {
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
			 Common common = new Common();
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
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
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
			String qry1 = "select waybill_No from Waybill_Details WHERE `generated_Date` = '"+dat1+"' AND `ETIM_Coll_Amt` > '0'";
//			System.out.println("qry1"+qry1);
			rs=stmt.executeQuery(qry1);
		
			 while(rs.next()){
						list.add(rs.getString("waybill_No"));
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
				
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List getWaybillName(int parentID,String dat1) {
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
					 System.out.println("connection........."+connection);
					 stmt = connection.createStatement();
				String qry1 = "select waybill_No from Waybill_Details WHERE `generated_Date` = '"+dat1+"' AND `ETIM_Coll_Amt` > '0'";
				rs=stmt.executeQuery(qry1);
				 while(rs.next()){
							list.add(rs.getString("waybill_No"));
					
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					return list;
				
		}
		
		@SuppressWarnings("rawtypes")
		public List getShiftId(String waybill,int depotid) {
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
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotid+"'";
			 Common common = new Common();
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
			String qry1 = "SELECT shift_type_id  FROM shift_type sf " +
					"inner join schedule_type st on st.schedule_type_id=sf.schedule_type_id " +
					"inner join Waybill_Details wd on wd.Schedult_Type=st.schedule_type_id " +
					"WHERE wd.waybill_No='"+waybill+"' AND sf.status = 'ACTIVE' AND sf.deleted_status = '0' ";
//			System.out.println("qry1"+qry1);
			rs=stmt.executeQuery(qry1);
		
			 while(rs.next()){
						list.add(rs.getString("shift_type_id"));
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
				
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List getShiftName(String waybill,int depotid) {
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
				//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotid+"'";
				 Common common = new Common();
					//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
					String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
					 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				
				 String qry1 = "SELECT  shift_type_name  FROM shift_type sf " +
							"inner join schedule_type st on st.schedule_type_id=sf.schedule_type_id " +
							"inner join Waybill_Details wd on wd.Schedult_Type=st.schedule_type_id " +
							"WHERE wd.waybill_No='"+waybill+"' AND sf.status = 'ACTIVE' AND sf.deleted_status = '0' ";
				rs=stmt.executeQuery(qry1);
				 while(rs.next()){
							list.add(rs.getString("shift_type_name"));
					
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					return list;
				
		}
		
		
		
		@SuppressWarnings("rawtypes")
		public List getTripId(String waybill,int shiftid,int depotid) {
          List list = new ArrayList();
          int shiftchangedid=0;
			if(shiftid==2){
				shiftchangedid=1;
			}
			if(shiftid==3){
				shiftchangedid=2;
			}
			if(shiftid==4){
				shiftchangedid=1;
			}
			if(shiftid==5){
				shiftchangedid=2;
			}
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
		//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotid+"'";
			 Common common = new Common();
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
			String qry1 = "SELECT trip_no FROM `ticket` " +
					"WHERE `waybill_no` = '"+waybill+"' AND `shift_no` = '"+shiftchangedid+"' and ticket_no !=0 group by trip_no ";
//			System.out.println("qry1"+qry1);
			rs=stmt.executeQuery(qry1);
		
			 while(rs.next()){
						list.add(rs.getString("trip_no"));
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
				
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List getTripName(String waybill,int shiftid,int depotid) {
			  List list = new ArrayList();
				
			  int shiftchangedid=0;
				if(shiftid==2){
					shiftchangedid=1;
				}
				if(shiftid==3){
					shiftchangedid=2;
				}
				if(shiftid==4){
					shiftchangedid=1;
				}
				if(shiftid==5){
					shiftchangedid=2;
				}
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
			//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotid+"'";
				 Common common = new Common();
					//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
					String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
					 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				
				 String qry1 = "SELECT trip_no FROM `ticket` " +
							"WHERE `waybill_no` = '"+waybill+"' AND `shift_no` = '"+shiftchangedid+"' and ticket_no !=0 group by trip_no ";
				rs=stmt.executeQuery(qry1);
				 while(rs.next()){
							list.add(rs.getString("trip_no"));
					
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					return list;
				
		}
	
		
		@SuppressWarnings("rawtypes")
		public List getStageNoId(String waybill,int shift,int depotid,int tripno) {
          List list = new ArrayList();
          int shiftchangedid=0;
			if(shift==2){
				shiftchangedid=1;
			}
			if(shift==3){
				shiftchangedid=2;
			}
			if(shift==4){
				shiftchangedid=1;
			}
			if(shift==5){
				shiftchangedid=2;
			}
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
			
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotid+"'";
			 Common common = new Common();
			//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
			String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
			 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
			String qry1 = "SELECT route_id FROM `ticket` WHERE `waybill_no` = '"+waybill+"' AND `shift_no` = '"+shiftchangedid+"' AND `trip_no` = '"+tripno+"' LIMIT 1";
//			System.out.println("qry1"+qry1);
			rs=stmt.executeQuery(qry1);
		       int routeid = 0;
			 while(rs.next()){
			routeid=Integer.parseInt(rs.getString("route_id"));
				
			}
			 
			 String qry2 = "SELECT rp.route_order route_order,bs.bus_stop_name bus_stop_name FROM `route_point` rp inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id" +
			 		" WHERE `route_id` = '"+routeid+"' AND rp.`fare_stage` = 'Y'";

//				System.out.println("qry2"+qry2);
				rs=stmt.executeQuery(qry2);
			      // int routeid = 0;
				 while(rs.next()){
				System.out.println("--------"+rs.getString("route_order"));
				//routeid=Integer.parseInt(rs.getString("route_id"));
						list.add(rs.getString("route_order"));
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
				
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List getStageNoName(String waybill,int shiftid,int depotid,int tripno) {
			  List list = new ArrayList();
				
			  int shiftchangedid=0;
				if(shiftid==2){
					shiftchangedid=1;
				}
				if(shiftid==3){
					shiftchangedid=2;
				}
				if(shiftid==4){
					shiftchangedid=1;
				}
				if(shiftid==5){
					shiftchangedid=2;
				}
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
				 Common common = new Common();
				//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotid+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				
				String qry1 = "SELECT route_id FROM `ticket` WHERE `waybill_no` = '"+waybill+"' AND `shift_no` = '"+shiftchangedid+"' AND `trip_no` = '"+tripno+"' LIMIT 1";
//				System.out.println("qry1"+qry1);
				rs=stmt.executeQuery(qry1);
			       int routeid = 0;
				 while(rs.next()){
				routeid=Integer.parseInt(rs.getString("route_id"));
					
				}
				 
				 String qry2 = "SELECT rp.route_order route_order,bs.bus_stop_name bus_stop_name FROM `route_point` rp inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id" +
				 		" WHERE `route_id` = '"+routeid+"' AND rp.`fare_stage` = 'Y'";

//					System.out.println("qry2"+qry2);
					rs=stmt.executeQuery(qry2);
				      // int routeid = 0;
					 while(rs.next()){
					//routeid=Integer.parseInt(rs.getString("route_id"));
							list.add(rs.getString("bus_stop_name"));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					return list;
		}
		
}
