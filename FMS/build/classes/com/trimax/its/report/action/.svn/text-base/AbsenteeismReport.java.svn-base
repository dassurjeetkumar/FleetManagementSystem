package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class AbsenteeismReport {
	
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

	public String getAbsenteeismReport(){
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
	
		    String depot=req.getParameter("depotlist1");
		 
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			 
			 
			 
			 String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
			Query qry=session1.createSQLQuery(sql1).
					addScalar("dbname")
					.addScalar("ip")
					.addScalar("uname")
					.addScalar("password");
			
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();	
				String depotdb="";
				String depotIp="";
				String user="";
				String password="";
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("dbname").toString();
					 depotIp=aliasValue.get("ip").toString();
					 user=aliasValue.get("uname").toString();
					 password=aliasValue.get("password").toString();
				}
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
//          System.out.println("Connection established");

		 stmt = connection.createStatement();

		 String sql ="select token,name,Sum(no_ofAttendance),EMPLOYEE_DESIGNATION,((DATEDIFF('"+date2+"','"+date1+"')+1)-Sum(no_ofAttendance)) as absenties " +
		 		"from (select E.TOKEN token,E.EMPLOYEE_NAME name,E.EMPLOYEE_DESIGNATION EMPLOYEE_DESIGNATION,COUNT(EAD.employee_token_no) as no_ofAttendance " +
		 		"From employee E  " +
		 		"inner join employee_attendance_data EAD ON EAD.employee_token_no = E.EMPLOYEE_ID " +
		 		"where E.STATUS='ACTIVE' AND EAD.attendance = 'P' and  EAD.duty_date " +
		 		"between '"+date1+"'  and '"+date2+"' GROUP BY EAD.employee_token_no) a GROUP BY token";
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Absenteeism Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Name</th><th>Token No</th>" +
					"               <th>Designation</th><th>As On "+date1+" To "+date2+"</th></tr></thead><tbody>";
			
			 int i=1;
				 while (rs.next()) {
					 
				    regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("token").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("name").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("EMPLOYEE_DESIGNATION").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("absenties").toString()+"</td>" ;
					
					}
					
					   regionTypeAjaxString +="</tr>";
				
		          regionTypeAjaxString += "</tbody></table></div> </div> ";

		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	

}
