package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ScheduleWiseEarningAction {

	
	String path="";
	char ft = 15;
	String str="";
	String new_line = System.getProperty("line.separator");

	String c=" ";
	
	 public String startdate;
	    public String enddate;
	    private Map<Integer, String> divisionlist;
 		private Map<Integer, String> depotlist;
 		private String depotlist1;
 		private String divisionlist1;
 		 private List<Schedule> schedulelist;
 	    private String schedule;
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


	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double totalAmmount=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";


		
		
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		public String getScheduleWiseEarning()
		{

			double Totalvalues=0;
			int tTickettotal=0;
			double tTotalvalues=0;
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
			//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
				 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
					Query qry1=session1.createSQLQuery(sql1).
							addScalar("depotname")
							.addScalar("central_ip")
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
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","itishree","ItishreeN");
					 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				 
				 String sql2 = "SELECT ifnull(targetamount,0) as targetamount FROM scheduletargetamount WHERE scheduleid = '"+schedule+"'";
				 rs = stmt.executeQuery(sql2);
				 double targetamount = 0.0;
				 double totdis=0.0;
				 String queryyy="";
				 if (rs.next()) {
					 targetamount= Double.parseDouble(rs.getString("targetamount").toString());
				 }
					
                 String sql3 = "SELECT ifnull(sum(distance),'0.0')  as dista FROM `schedule_details` WHERE `schedule_number` = '"+schedule+"'";
                 rs = stmt.executeQuery(sql3);
                 if (rs.next()) {
                	 totdis= Double.parseDouble(rs.getString("dista").toString());
				 }
					//System.out.println("totdis"+totdis);
					int schedule1=Integer.parseInt(schedule);
					//System.out.println("schedule1"+schedule1);
					
					if (schedule1!=0) {
						queryyy = "s.schedule_id='" + schedule1 + "' and";
			        } else {
			            queryyy = "";
			        }

				 String sql = "SELECT dte,ename,token,ifnull(cancelkm,'0.0') as cancelkm,ifnull(extrakm,'0.0') as extrakm,sum(manualpassenger+manualdailypass+manualmonthlypass+manualluggage+etmpassenger+etmluggage+collection_amount)total," +
							"Waybill_No,snumber,sum(conbata) conbata,sum(conincentive) conincentive,sum(dribata) dribata,sum(driincentive) driincentive from " +
							"(SELECT wd.generated_Date AS dte,ifnull(block.manualpassenger,0) manualpassenger,ifnull(block.manualdailypass,0) manualdailypass, " +
							"ifnull(block.manualmonthlypass,0) manualmonthlypass,ifnull(block.manualluggage,0) manualluggage," +
							"sum(ifnull(px_total_amount,0)) etmpassenger, sum(ifnull(luggage_total_amount,0)) etmluggage,e.EMPLOYEE_NAME AS ename,e.TOKEN AS token," +
							"ifnull(gl.cancel_km,0)cancelkm,ifnull(gl.extra_km,0)extrakm,wd.Waybill_No,ifnull(wpc.collection_amount,0)collection_amount,s.schedule_number as snumber, " +
							"(ifnull(wd.cond_bata_amount,0)) conbata,(ifnull(wd.cond_incentive_amount,0)) conincentive," +
							"(ifnull(wd.driver_bata_amount,0)) dribata,(ifnull(wd.driver_incentive_amount,0)) driincentive FROM Waybill_Details wd " +
							"LEFT JOIN " +
							"(SELECT a1.waybill_cwa_block_master_id, " +
							"sum(if(ticket_type_manual_id IN (1), value, 0)) AS manualpassenger, sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass," +
							"sum(if(ticket_type_manual_id =3, value, 0)) AS manualmonthlypass, sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage " +
							" FROM waybill_cwa_block_master a1 " +
							"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id " +
							" GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 " +
							"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No " +
							"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
							" LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id  AND ticket_printed_flag ='Y' AND fare_type NOT IN ('NINC') " +
							"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id " +
							"LEFT JOIN ticket_type tt ON tt.ticket_type_id=t.ticket_type_short_code " +
							"AND tt.ticket_type_name NOT IN ('Line Checking', 'Penalty Ticket', 'Trip Status')" +
							" left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id " +
							"left join waybill_pending_collection wpc on wpc.waybill_no=wd.Waybill_No "+
							"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type" +
							" LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type " +
							"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " +
							" WHERE wd.Ticket_Audited_Date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59'" +
							" AND " +queryyy+ " (wd.status='AUDITED' OR wd.status='CLOSED')" +
							"GROUP BY wd.Waybill_No,s.schedule_number) a " +
							"GROUP BY a.Waybill_No HAVING total>0 order by dte,CAST(a.token as UNSIGNED)";
//			 System.out.println(sql);
			 rs = stmt.executeQuery(sql);

			     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Schedulewise Earnings Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
		      
	        
		        
		        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Date</th><th>Name</th><th>Token_No</th><th>Schedule No</th><th>Actual Earnings</th>" +
						"<th>Cond. Bata</th><th>Cond. Incentive</th><th>Dri. Bata</th><th>Dri. Incentive</th><th>Schedule KM</th><th>Cancelled KM</th><th>Extra KM</th><th>Operated KM</th><th>Target Earnings</th>"+"</tr></thead><tbody>";
				
				 int i=1;
					 while (rs.next()) {
						 
					    regionTypeAjaxString +="<tr>";
						//regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					    String extrakm  = rs.getString("extrakm").toString();
						double extrakm1 = Double.parseDouble(extrakm);
						//System.out.println("extrakm"+extrakm1);
						//String deviation = list.get("deviation_km").toString();
						//double deviation1 = Double.parseDouble(deviation);
						String cancelkm = rs.getString("cancelkm").toString();
						double cancelkm1 = Double.parseDouble(cancelkm);
						//System.out.println("cancelkm"+cancelkm1);
						double operatedkm = (extrakm1+totdis)-cancelkm1;
						regionTypeAjaxString +="<td align='right'>"+ i+++"</td>";
						regionTypeAjaxString +="<td align='right'>"+ common.getDateToPicker(rs.getString("dte").toString()) +"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("ename").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("token").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("snumber").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+ rs.getString("total").toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ rs.getString("conbata").toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ rs.getString("conincentive").toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ rs.getString("dribata").toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ rs.getString("driincentive").toString() +"</td>";
						
						regionTypeAjaxString +="<td align='right'>"+ totdis +"</td>";
						regionTypeAjaxString +="<td align='right'>"+cancelkm1+"</td>";
						regionTypeAjaxString +="<td align='right'>"+extrakm1+"</td>";
						regionTypeAjaxString +="<td align='right'>"+ operatedkm +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ targetamount +"</td>";
					
						regionTypeAjaxString +="</tr>";
						totalAmmount+= Double.parseDouble(rs.getString("total").toString());
					    //System.out.println("totalAmmount......"+totalAmmount);
							
					
					}
				    BigDecimal b2=new BigDecimal(totalAmmount);
					 MathContext mc = new MathContext(12);
					b2=b2.round(mc);
			     
				   regionTypeAjaxString +="<tr><td  colspan='5'><center><b>Month Totals</b></center></td><td align='center'><b>"+ b2+"</td></tr>" +"\n";  
				    regionTypeAjaxString += "</tbody></table></div> </div>"; 

			
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (Exception e) {
				
				e.printStackTrace();
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
		
		public List<Schedule> getScheduledataEarning() {
			List<Schedule> schedulelist = null;
			Session session = null;
			HttpServletRequest req = ServletActionContext.getRequest();
			//VtsDataDAO dao = VtsDataDAO.getInstance();
			try{
				int depotId = Integer.parseInt(req.getParameter("val"));
				
				List<String> l1 = getDepotWiseSchdNoId(depotId);
	    		List<String> l2 = getDepotWiseSchdNoName(depotId);
	    		String regionTypeAjaxString = "";
	    		regionTypeAjaxString += "<option value='0'>All</option>";
	    		for (int i = 0; i < l1.size(); i++) {
	    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
	    					+ ">" + l2.get(i).toString() + "</option>";
	    		}
	    		HttpServletResponse response = ServletActionContext.getResponse();
	    		PrintWriter out;
	    	
	    			out = response.getWriter();
	    			out.print(regionTypeAjaxString);
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
//	    		return null;
				
			
				return null;

		}
		
		public List getDepotWiseSchdNoId(int parentID) {
			List list = new ArrayList();
			String qry = "SELECT schedule_id,schedule_number  FROM  schedule where (status='ACTIVE' or status='NEW')"+
					" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and depot_id='"+parentID+"'";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_id").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		public List getDepotWiseSchdNoName(int parentID) {
			List list = new ArrayList();
			String qry = "SELECT schedule_id,schedule_number  FROM  schedule where (status='ACTIVE' or status='NEW')"+
						" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and depot_id='"+parentID+"'";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
	
}
