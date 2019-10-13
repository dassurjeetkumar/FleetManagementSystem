package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;

public class ScheduleOperationRevenueReportAction extends ActionSupport{
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	 public String depotNo;
	 public String divisonNo;
	 public String servicetype;
	 private Map<Integer, String> servicetypelist;
	 public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public Map<Integer, String> getServicetypelist() {
		return servicetypelist;
	}

	public void setServicetypelist(Map<Integer, String> servicetypelist) {
		this.servicetypelist = servicetypelist;
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

	public String getDepotNo() {
		return depotNo;
	}

	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
	}

	public String getDivisonNo() {
		return divisonNo;
	}

	public void setDivisonNo(String divisonNo) {
		this.divisonNo = divisonNo;
	}

	
	char fl = 18;
		char f2 = 12;
		int pagi = 1;

		int FULL_LEN = 120;
		int HALF_LEN = 60;

		int subtotalTickets=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
	
	private Map<Integer, String> divisionlist;
	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	@Override
	public String execute() throws Exception {
		this.setDivisionlist(getDivisionName());
		this.setServicetypelist(getServicetylist());
		return "success";
	}

	private Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  " +
						"where parent_id ='1' and deleted_status=0 order by orgchart.org_name");
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
	private Map<Integer, String> getServicetylist() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from ServiceType servicetype  " +
						"where status ='ACTIVE' and deleted_status=0 ");
		try {
			List<ServiceType> list = query.list();
			for (ServiceType org : list) {
				resultMap.put(org.getService_type_id(), org.getService_type_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	@SuppressWarnings("finally")
public String AjaxSchedueloperationRevenueReport()
  {
	try {
		  
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
	
		HttpServletRequest req=ServletActionContext.getRequest();
		//String date1=common.getDateFromPicker(startdate);
		
	    String division1= divisonNo;
	    String depot1= depotNo;
	    Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
		 session1 = null;
		 transaction  = null;
		String service1= req.getParameter("servicetype");
	
  

	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 String realpath = ServletActionContext.getRequest()
				.getRealPath("/");
	// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotNo+"' AND division_id = '"+divisonNo+"'";
	 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotNo+"'";
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
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.154/its_live?zeroDateTimeBehavior=convertToNull",user,password);
		 System.out.println("connection........."+connection);
	 stmt = connection.createStatement();
	 String sql="";

		  sql="SELECT s.schedule_number schedule_number,schedule_type_name,bag_code,license_number,service_type_name,a.trips trips,a.dista dista,ifnull(gl.canceled_trips,0) ctrips," +
			 		"ifnull(gl.cancel_km,0)cancelkm,ifnull(cancellation_name,'')cancellation_name,ifnull(gl1.cancel_type,'') planedcancel," +
			 		"ifnull(cahrte,0) charte,e.TOKEN AS token,(ifnull(block.manualpassenger,0)+sum(ifnull(px_total_amount,0))" +
			 		"+ifnull(block.manualdailypass,0)+ifnull(block.manualluggage,0)) ticketrevenue,ifnull(c.sdead,'0.0') sdead " +
			 		"FROM Waybill_Details wd LEFT JOIN (SELECT a1.waybill_cwa_block_master_id, " +
			 		"sum(if(ticket_type_manual_id IN (1), value, 0)) AS manualpassenger, sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass," +
			 		"sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage  " +
			 		"FROM waybill_cwa_block_master a1 " +
			 		"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id  " +
			 		"GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 " +
			 		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
			 		"LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id  AND ticket_printed_flag ='Y' AND fare_type NOT IN ('NINC') " +
			 		"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id " +
			 		"left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id " +
			 		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id " +
			 		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type  " +
			 		"LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type  " +
			 		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " +
			 		"inner join vehicle v on v.vehicle_id=wd.Vehicle_No " +
			 		"LEFT JOIN gen_logsheet gl1 ON gl1.waybill_id=wd.waybil_Id and gl1.cancel_type='Planned' " +
			 		"inner join service_type sty on sty.service_type_id=wd.Service_Type " +
			 		"left join (SELECT ifnull(sum(distance),'0.0')  as dista,form_four_id,max(list_item_number) trips FROM `schedule_details`   " +
			 		"group by form_four_id)a " +
			 		"on a.form_four_id=wd.schedule_No " +
			 		"left join (SELECT ifnull(sum(distance),'0.0')  as cahrte,form_four_id FROM `schedule_details` WHERE  " +
			 		"`trip_type` IN (19,20) group by form_four_id)b " +
			 		"on b.form_four_id=wd.schedule_No " +
			 		"left join (SELECT ifnull(sum(distance),'0.0')  as sdead,form_four_id FROM `schedule_details` WHERE  " +
			 		"`trip_type`=3 group by form_four_id)c on c.form_four_id=wd.schedule_No " +
			 		"left join causewise_dead_km cdkm on cdkm.gen_logsheet_id=gl.gen_logsheet_id " +
			 		"WHERE wd.generated_Date BETWEEN '"+date1+"' and '"+date2+"' AND wd.Service_Type='"+service1+"' and  " +
			 		"(wd.status='AUDITED' OR wd.status='CLOSED') group by wd.waybil_Id order by CAST(token as UNSIGNED)";
		  rs = stmt.executeQuery(sql);
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Schedule Operation And Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        
				
				       
				        
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
						regionTypeAjaxString +="<td align='center' width='10%'><b>Sr.No.</b></td><td align='center' width='20%'><b>Vehicle No.</b></td>";
						regionTypeAjaxString +="<td align='center' width='20%'><b>Schedules</b></td><td align='center' width='20%'><b>Schedule Type</b></td>" +
								"<td align='center' width='20%'><b>Service Type</b></td><td align='center' width='10%'><b>Bag No.</b></td>" +
								"<td align='center' width='20%'><b>Schedule Trips</b></td><td align='center' width='10%'><b>Cancelled Trips</b></td>" +
								"<td align='center' width='20%'><b>Operated Trips</b></td><td align='center' width='10%'><b>Schedule Kms</b></td>" +
								"<td align='center' width='20%'><b>Cancelled Kms</b></td><td align='center' width='10%'><b>Reason for Cancellation</b></td>" +
								"<td align='center' width='20%'><b>Planned Cancellation = PC</b></td><td align='center' width='10%'><b>Effective Kms</b></td>" +
								"<td align='center' width='20%'><b>Cond Token No.</b></td><td align='center' width='10%'><b>Cond Revenue</b></td>" +
								"<td align='center' width='20%'><b>Chartered Revenue</b></td><td align='center' width='10%'><b>Dedicated Revenue</b></td>" +
								"<td align='center' width='20%'><b>Chartered Kms</b></td><td align='center' width='10%'><b>Dedicated Kms</b></td>" +
								"<td align='center' width='20%'><b>TOTAL Revenue</b></td><td align='center' width='10%'><b>EPKM= Total Revenue/Effective kms</b></td>" +
								"<td align='center' width='20%'><b>Total Dead Kms</b></td><td align='center' width='10%'><b>Gross Kms</b></td></tr>";
						

						 int i=1;
						 while (rs.next()) {
							
							regionTypeAjaxString +="<tr>";
							
								regionTypeAjaxString +="<td>"+i+++"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("license_number").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("schedule_number").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("schedule_type_name").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("service_type_name").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("bag_code").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("trips").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("ctrips").toString()+"</td>";
								int optrips=Integer.parseInt(rs.getString("trips").toString())-Integer.parseInt(rs.getString("ctrips").toString());
								regionTypeAjaxString +="<td>"+optrips+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("dista").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("cancelkm").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("cancellation_name").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("planedcancel").toString()+"</td>";
								double effkms=Double.parseDouble(rs.getString("dista").toString())-Double.parseDouble(rs.getString("cancelkm").toString());
								regionTypeAjaxString +="<td>"+effkms+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("token").toString()+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("ticketrevenue").toString()+"</td>";
								regionTypeAjaxString +="<td>0</td>";
								regionTypeAjaxString +="<td>0</td>";
								
								regionTypeAjaxString +="<td>"+rs.getString("charte").toString()+"</td>";
								regionTypeAjaxString +="<td>0.0</td>";
								int totrev=Integer.parseInt(rs.getString("ticketrevenue").toString());
								regionTypeAjaxString +="<td>"+totrev+"</td>";
								double epkm = totrev/effkms;
								epkm=Math.rint(epkm*100)/100;
								regionTypeAjaxString +="<td>"+epkm+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("sdead").toString()+"</td>";
								Double grosskm= epkm-Double.parseDouble(rs.getString("sdead").toString());
								grosskm=Math.rint(grosskm*100)/100;
								regionTypeAjaxString +="<td>"+grosskm+"</td>";
//								str+=""+add("",5)+"|"+add(date,20)+"|" + add(snumber, 20) +"|" + add(count,20)+"|" + add(count1,20)+"|" + add(total,20)+ "|"+"\n"; 
								regionTypeAjaxString +="</tr>";
							}
					     
					 
					 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";


					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
			
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (IOException e) {
						
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
}
