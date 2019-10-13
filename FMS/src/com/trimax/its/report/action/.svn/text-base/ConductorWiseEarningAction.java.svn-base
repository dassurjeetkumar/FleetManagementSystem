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
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ConductorWiseEarningAction {
	
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
 		 private List<Employee> conductrtype;;
 	    private String coundvtorr;
	

	public List<Employee> getConductrtype() {
			return conductrtype;
		}


		public void setConductrtype(List<Employee> conductrtype) {
			this.conductrtype = conductrtype;
		}


		public String getCoundvtorr() {
			return coundvtorr;
		}


		public void setCoundvtorr(String coundvtorr) {
			this.coundvtorr = coundvtorr;
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

		
		public String getConductorWiseEarning()
		{

			double Totalvalues=0;
			int tTickettotal=0;
			double tTotalvalues=0;
			double totalamount=0;
			try {
				Connection connection=null;
				Statement stmt=null;
				Statement stmt1=null;
				Statement stmt2=null;
				Session session1 = null;
				ResultSet rs=null;
				ResultSet rs1=null;
				ResultSet rs2=null;
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
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","itishree","ItishreeN");
					 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				 String queryyy="";
				 
				 if (Integer.parseInt(coundvtorr)!=0) {
						queryyy = "and wd.conductor_Id='"+coundvtorr+"' ";
			        } else {
			            queryyy = "";
			        }
					String sql="select dte,snumber,dist,token,ename," +
					"sum(manualpassenger+manualdailypass+manualmonthlypass+manualluggage+etmpassenger+etmluggage+collection_amount)total,Waybill_No," +
					"sum(bata) bata ,sum(incentive) incentive " +
					"from (SELECT wd.generated_Date as dte,wd.waybil_Id as id," +
					"ifnull(block.manualpassenger,0) manualpassenger,ifnull(block.manualdailypass,0) manualdailypass," +
					"ifnull(block.manualmonthlypass,0) manualmonthlypass,ifnull(block.manualluggage,0) manualluggage," +
					"sum(ifnull(px_total_amount,0)) etmpassenger,sum(ifnull(luggage_total_amount,0)) etmluggage," +
					"s.schedule_number as snumber,e.TOKEN as token,e.EMPLOYEE_NAME as ename,s.schedule_id as dist," +
					"wd.Waybill_No,ifnull(wpc.collection_amount,0)collection_amount, " +
					"(ifnull(wd.cond_bata_amount,0)) bata,(ifnull(wd.cond_incentive_amount,0)) incentive FROM Waybill_Details wd " +
					"LEFT JOIN " +
					"(SELECT a1.waybill_cwa_block_master_id, sum(if(ticket_type_manual_id IN (1), value, 0)) AS " +
					"manualpassenger,sum(if(ticket_type_manual_id=2 , value, 0)) AS manualdailypass," +
					"sum(if(ticket_type_manual_id =3, value, 0)) AS manualmonthlypass," +
					"sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage FROM waybill_cwa_block_master a1 " +
					"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id " +
					"GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 " +
					"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No " +
					"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
					"LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id AND fare_type Not IN ('NINC')   AND ticket_printed_flag ='Y' " +
					"LEFT JOIN ticket_type tt ON tt.ticket_type_id=t.ticket_type_short_code  AND " +
					"tt.ticket_type_name NOT IN ('Line Checking', 'Penalty Ticket', 'Trip Status') " +
					"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id " +
					"left join waybill_pending_collection wpc on wpc.waybill_no=wd.Waybill_No "+
					"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type " +
					"LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type " +
					"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " +
					"WHERE wd.Ticket_Audited_Date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59' "+queryyy+" " +
					"and (wd.status='AUDITED' or wd.status='CLOSED') GROUP BY wd.Waybill_No,s.schedule_number) a group by " +
					" a.Waybill_No HAVING total>0  order by CAST(a.token as UNSIGNED)";
//			 System.out.println(sql);
			 rs = stmt.executeQuery(sql);

			     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Schedulewise Earnings Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
		      
	        
		    	regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>S.No.</th><th>Token No.</th><th>Conductor Name</th><th>Date</th><th>Schedule No</th><th>Scheduled Km</th><th>Target Earnings</th><th>Bata</th><th>Incentive</th><th>Actual Earnings</th>"+"</tr></thead><tbody>";

				 int i=1;
					 while (rs.next()) {
						 
					    regionTypeAjaxString +="<tr>";
						//regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					    regionTypeAjaxString +="<td>"+i+++"</td>";
						regionTypeAjaxString +="<td>"+rs.getString("token").toString()+"</td>";
						regionTypeAjaxString +="<td>"+rs.getString("ename").toString()+"</td>";
						regionTypeAjaxString +="<td>"+common.getDateToPicker(rs.getString("dte").toString())+"</td>";
						regionTypeAjaxString +="<td>"+rs.getString("snumber").toString()+"</td>";
						String sql11="SELECT sum(`distance`) as dista FROM `schedule_details` WHERE `schedule_number` = '"+rs.getString("dist").toString()+"'";
						stmt1 = connection.createStatement();
						rs1 = stmt1.executeQuery(sql11);
						 String count="";
						 if(rs1.next()){
							 count=rs1.getString("dista").toString();
						 }
						regionTypeAjaxString +="<td>"+count+"</td>";
						String sql12="SELECT ifnull(targetamount,0)tamount FROM `scheduletargetamount` WHERE `scheduleid` = '"+rs.getString("dist").toString()+"'";
						stmt2 = connection.createStatement();
						rs2 = stmt2.executeQuery(sql12);
						 String count1="";
						 if(rs2.next()){
							 count1=rs2.getString("tamount").toString();
						 }
						regionTypeAjaxString +="<td>"+count1+"</td>";
						regionTypeAjaxString +="<td>"+rs.getString("bata").toString()+"</td>";
						regionTypeAjaxString +="<td>"+rs.getString("incentive").toString()+"</td>";
						regionTypeAjaxString +="<td>"+rs.getString("total").toString()+"</td>";
						totalamount+=Double.parseDouble(rs.getString("total").toString());
						
						
						regionTypeAjaxString +="</tr>";
					}
					
						str+=" _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n";
					 
					  
						 BigDecimal b2=new BigDecimal(totalamount);
						 MathContext mc = new MathContext(12);
						b2=b2.round(mc);
				       
					    regionTypeAjaxString +="<tr><td colspan='9'><center><b>Total</b></center></td><td align='right'><b>"+ b2+"</td></tr>" +"\n";  
					    
				     
				 
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
		
		public List<Employee> getTokenNoForConductor() {
			List<Employee> tokenlist = null;
			Session session = null;
			HttpServletRequest req = ServletActionContext.getRequest();
			try{
				int depotId = Integer.parseInt(req.getParameter("val"));
				
				List<String> l1 = getTokenNoId(depotId);
	    		List<String> l2 = getTokenNoName(depotId);
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
		
		public List getTokenNoId(int parentID) {
			List list = new ArrayList();
			String qry = "SELECT `EMPLOYEE_ID`, `TOKEN` FROM `employee` WHERE status='Active'  and `EMPLOYEE_DESIGNATION` = 'Conductor' or `EMPLOYEE_DESIGNATION` = 'DriverConductor' and org_chart_id='"+parentID+"'";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("EMPLOYEE_ID").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		public List getTokenNoName(int parentID) {
			List list = new ArrayList();
			String qry = "SELECT `EMPLOYEE_ID`, `TOKEN` FROM `employee` WHERE status='Active'  and `EMPLOYEE_DESIGNATION` = 'Conductor' or `EMPLOYEE_DESIGNATION` = 'DriverConductor' and org_chart_id='"+parentID+"'";

			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("TOKEN").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}

}
