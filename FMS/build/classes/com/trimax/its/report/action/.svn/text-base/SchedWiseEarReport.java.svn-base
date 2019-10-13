	package com.trimax.its.report.action;

	import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
	import java.text.SimpleDateFormat;
    import java.util.ArrayList;
import java.util.Calendar;
	import java.util.Date;
	import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts2.ServletActionContext;
	import org.hibernate.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
    import org.hibernate.transform.Transformers;
	
import com.trimax.its.common.Common;
    import com.trimax.its.transport.model.Schedule;
    import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

	import org.hibernate.Hibernate;
	import org.hibernate.Session;
import org.hibernate.Transaction;


	public class SchedWiseEarReport {
		
		String str="";
		String path="";
		char ft = 15;
	
	 public String startdate;
	    public String enddate;
	    public String ctoken;
	    
	    public String getCtoken() {
			return ctoken;
		}


		public void setCtoken(String ctoken) {
			this.ctoken = ctoken;
		}


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

		
		private String depotlist1;
		public String divisionlist1;
	    public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


		private Map<Integer, String> divisionlist;
	  


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}

		
	double totalAmmount=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";

	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
//			System.out.println("division........"+divisionlist);	
			//this.setSchedulelist(getSchedulelistdata());
			return "success";
		}

		
		
		public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}

		double mnthTotal=0.0;
		Double netTotal=0.0;
		public String AjaxSchedEarReport()
		{
					 Connection connection = null;
					 Statement stmt = null;
				        Statement stmt1 = null;
				        ResultSet rs1 = null;
				        DecimalFormat formate = new DecimalFormat("#.##"); 
					try {
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
					Session session1 = null;
					Transaction transaction  = null;
					String queryyy;
					Common common = new Common();
//					String date1=common.getDateFromPicker(startdate);
//					String date2=common.getDateFromPicker(enddate);
//					System.out.println("start date is"+date1);
//					System.out.println("end date is"+date2);
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					//String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
					 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
//						String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
						 stmt1 = connection.createStatement();
						 transaction = session1.beginTransaction();

						 Calendar cal = Calendar.getInstance();
					       

						 SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
						 Date startDate = format.parse(startdate);
						 Date endDateO = format.parse(enddate);
						 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM");
						 String startDate1 = fomat2.format(startDate).toString();
						 String endDate1 = fomat2.format(endDateO).toString();
							int scheduleId=Integer.parseInt(ctoken);
							System.out.println("schedule id is---"+scheduleId);
							if (scheduleId!=0) {
								queryyy = "SELECT DATE_FORMAT(dte,'%m-%Y') dte,ifnull(cancelkm,'0.0') as cancelkm,ifnull(extrakm,'0.0') as extrakm,sum(deadkm) as deadKm,sum(manualpassenger) as ticketRev," +
										"sum(manualdailypass) as dpRev,sum(manualmonthlypass) as mpRev,sum(manualluggage) as luggage,sum(dist) as schdKm,sum(etmamount) as etmRev,sum(bata) as bata,sum(incentive) as incn," +
										"sum(misl) as misl from (SELECT wd.Ticket_Audited_Date AS dte,ifnull(block.manualpassenger,0) manualpassenger," +
										"ifnull(block.manualdailypass,0) manualdailypass, ifnull(block.manualmonthlypass,0) manualmonthlypass," +
										"ifnull(block.manualluggage,0) manualluggage, e.EMPLOYEE_NAME AS ename,e.TOKEN AS token,ifnull(gl.cancel_km,0)cancelkm," +
										"sum(conductor_Total_Bata+driver_Total_Bata)bata,sum(driver_incentive_amount+cond_incentive_amount)incentive," +
										"sum(driver_other_deduction+cond_other_deduction)misl,sum(if(sd.is_dread_trip =1, (sd.distance), 0)) AS deadkm," +
										"ifnull(gl.extra_km,0)extrakm,sum(distance) as dist,ifnull(ETIM_Coll_Amt,0) etmamount FROM Waybill_Details wd LEFT JOIN (SELECT a1.waybill_cwa_block_master_id," +
										"sum(if(ticket_type_manual_id IN (1),value, 0))AS manualpassenger, sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass," +
										"sum(if(ticket_type_manual_id =3, value, 0)) AS manualmonthlypass, sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage FROM waybill_cwa_block_master a1 " +
										"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id GROUP BY a1.waybill_cwa_block_master_id) block" +
										" ON block.waybill_cwa_block_master_id = wd.param2 INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id  " +
										"LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id INNER JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type" +
										" LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " +
										"inner join schedule_details sd on sd.form_four_id=wd.schedule_No WHERE wd.Ticket_Audited_Date BETWEEN '"+startDate1+"-01 00:00:00' " +
										"and '"+endDate1+ "-31 23:59:59' AND  (wd.status='AUDITED'OR wd.status='CLOSED') AND s.schedule_id='"+scheduleId+"' GROUP BY wd.Waybill_No,s.schedule_number) a " +
										"GROUP BY DATE_FORMAT(dte,'%Y-%m') ";
					
								                     
//						queryyy = "s.schedule_id='" + scheduleID + "' and";
			        } else {
			            queryyy = "";
			        }
							 rs1 = stmt1.executeQuery(queryyy);
							 System.out.println("queryy is --"+queryyy);		
							String filePath = "Report/";

							String fileName = "ScheduleWiseEarningReport.txt";
					   
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Schedulewise Earning Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        String nwkr=""    +   "                                   ScheduleWise Earning    " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr.No.</th><th>Month</th><th>Tckt Rev M</th><th>ETM Rev</th><th>Lug Rev</th><th>Dp Rev</th><th>Mp Rev</th><th>Schd KM</th><th>Extra KM</th><th>Dead KM</th><th>Can KM</th><th>Oprtd KM</th><th>Tot Rev</th><th>Bata</th><th>Incn</th><th>Misc</th><th>Tot Ded</th><th>Net</th>"+"</tr></thead><tbody>";
						
						
//						 regionTypeAjaxString +="<tr><td colspan='18'><center><h4><b><span style='color:red'>No Records Found<b></span></h4></center></td></</tr>";
						   
//						    regionTypeAjaxString += "</tbody></table></div>"; 
						    
						    String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
									+add("SNo",3)+"|"+add("Tckt Rev M",7)+"|"+add("ETM Rev",14)+"|"+add("Lug Rev",10)+"|"+add("Dp Rev",11)+"|"+add("Mp Rev",7)+"|"+
									add("Schd KM",10)+"|"+add("Extra KM",14)+"|"+add("Dead KM",14)+"|"+add("Can KM",14)+"|"+add("Oprtd KM",14)+"|"+add("Tot Rev",14)+"|"
									+add("Batta",14)+"|"+add("Incn",14)+"|"+add("Misc",14)+"|"+add("Tot Ded",14)+"|"+add("Net",14)+
								 " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
							
							String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);
						  
						  
                            HttpServletResponse response = ServletActionContext.getResponse();
//                            int tcktRev=0;
                            int i=1;
       					 while (rs1.next()) {
       					  
 						    String date1=rs1.getString("dte").toString();
 						    System.out.println("date1"+date1);
 						    
       						 regionTypeAjaxString +="<tr>";
       						 regionTypeAjaxString +="<td>"+i+"</td>";
       						 regionTypeAjaxString +="<td>"+rs1.getString("dte")+"</td>";
//       					 regionTypeAjaxString +="<td>"+rs1.getString("date")+"</td>";
       						 regionTypeAjaxString +="<td>"+rs1.getString("ticketRev")+"</td>";
       						 regionTypeAjaxString +="<td>"+rs1.getString("etmRev")+"</td>";
       						 regionTypeAjaxString +="<td>"+rs1.getString("luggage")+"</td>"; 
       						 regionTypeAjaxString +="<td>"+rs1.getString("dpRev")+"</td>";
       						 regionTypeAjaxString +="<td>"+rs1.getString("mpRev")+"</td>"; 
       					     regionTypeAjaxString +="<td>"+rs1.getString("schdKm")+"</td>";
       					     regionTypeAjaxString +="<td>"+rs1.getString("extrakm")+"</td>";
       				         regionTypeAjaxString +="<td>"+rs1.getString("deadKm")+"</td>";
       						 double dead=Double.parseDouble(rs1.getString("deadKm"));
       						 regionTypeAjaxString +="<td>"+rs1.getString("cancelkm")+"</td>";   	
//       						regionTypeAjaxString +="<td>"+0+"</td>";
       						 double oprtdKm =Double.parseDouble(rs1.getString("schdKm"))+Double.parseDouble(rs1.getString("extrakm"))-Double.parseDouble(rs1.getString("deadKm"))-Double.parseDouble(rs1.getString("cancelkm"));
       						regionTypeAjaxString +="<td>"+oprtdKm+"</td>"; 
       					    double totalRev =  Double.parseDouble(rs1.getString("ticketRev"))+Double.parseDouble(rs1.getString("etmRev"))+
       						Double.parseDouble(rs1.getString("luggage"))+Double.parseDouble(rs1.getString("dpRev"))+Double.parseDouble(rs1.getString("mpRev"));
                            regionTypeAjaxString +="<td>"+totalRev+"</td>";
       						regionTypeAjaxString +="<td>"+rs1.getString("bata")+"</td>"; 
       						regionTypeAjaxString +="<td>"+rs1.getString("incn")+"</td>"; 
       						regionTypeAjaxString +="<td>"+rs1.getString("misl")+"</td>";   
                            double totalDeduc = Double.parseDouble(rs1.getString("bata"))+Double.parseDouble(rs1.getString("incn"))+Double.parseDouble(rs1.getString("misl"));
                            totalDeduc = Double.valueOf(formate.format(totalDeduc));
                            regionTypeAjaxString +="<td>"+totalDeduc+"</td>";
                            netTotal= totalRev-totalDeduc;
                             regionTypeAjaxString +="<td>"+netTotal+"</td>";
       						  regionTypeAjaxString +="</tr>";
       						 mnthTotal +=netTotal;
       						mnthTotal = Double.valueOf(formate.format(mnthTotal));
       					   i++;
       					 }

       					regionTypeAjaxString +="<tr><td  colspan='17'><center><b>Month Totals</b></center></td><td align='center'><b>"+ mnthTotal+"</td></tr>" +"\n";
//       						regionTypeAjaxString +="<tr><td colspan='18'><center><h4><b><span style='color:red'>No Records Found<b></span></h4></center></td></</tr>";  
       					// connection.close();

  				            if (rs1 != null) {
  				            	rs1.close();
  				            }
  				            
  				            if (stmt1 != null) {
  				            	stmt1.close();
  				            }

  				            if (connection != null) {
  				                connection.close();
  				            }
//  						FileOutputStream FOS = new FileOutputStream(path);
//  						PrintWriter PW = new PrintWriter(FOS);
//  						String p=str;
//  						PW.println(p);
//  						PW.close();
					PrintWriter out;
					//System.out.println("we are in ajax code................"+regionTypeAjaxString);
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					return null;
			}
		
		
		
		
//		public List<Schedule> getSchedulelistdata() {
//			List<Schedule> schedulelist = null;
//			Session session = null;
//			HttpServletRequest req = ServletActionContext.getRequest();
//			int parentId = Integer.parseInt(req.getParameter("val"));
//			try{
//			session = HibernateUtil.getSession("hibernate.cfg.xml");
//			String sql="SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
//					" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 where depot_id='"+parentId+"'";
//	         Query query = session.createSQLQuery(sql)
//					.addScalar("schedule_id", Hibernate.INTEGER)
//					.addScalar("scheduleNumber", Hibernate.STRING);
//			query.setResultTransformer(Transformers.aliasToBean(Schedule.class));
//			schedulelist = new ArrayList<Schedule>();
//			List<Object> det = query.list();
//			Schedule initialDevice = new Schedule();
//			initialDevice.setScheduleNumber("ALL");
//			initialDevice.setSchedule_id(0);
//			schedulelist.add(initialDevice);
//
//			for (int i = 0; i < det.size(); i++) {
//
//				Schedule device = (Schedule) det.get(i);
//				schedulelist.add(device);
//			}
//			}catch(Exception e){
//				e.printStackTrace();
//			}finally{
//				if(session!=null){
//					session.close();
//				}
//				return schedulelist;
//			}
//		}
		
	
		
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
		
		
		public String getDepotWiseSchdNo() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			//String date = req.getParameter("selectedDate");
//			Common cm = new Common();
//			String formattedgivendate = cm.getDateFromPicker(date);
//			System.out.println("formattedgivendate" + formattedgivendate);
			List<String> l2 = dao.getDepotWiseSchdNoName(parentId);
			List<String> l1 = dao.getDepotWiseSchdNoId(parentId);
			String regionTypeAjaxString = "<option value='0'>----Select----</option>";

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

			return null;

		}
		
		
		
			 }





	
	
	

