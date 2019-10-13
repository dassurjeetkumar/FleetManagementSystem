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

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CondWiseEarningReport {
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
		private Map<Integer, String> depotlist;
		private Map<Integer, String> cndtokenlist;
		private String ctoken;
		
		public Map<Integer, String> getCndtokenlist() {
			return cndtokenlist;
		}


		public void setCndtokenlist(Map<Integer, String> cndtokenlist) {
			this.cndtokenlist = cndtokenlist;
		}


		public String getCtoken() {
			return ctoken;
		}


		public void setCtoken(String ctoken) {
			this.ctoken = ctoken;
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

		public String divisionlist1;
		private String depotlist1;
	    private Map<Integer, String> divisionlist;
//	    private Map<Integer, String> divisionlist1;
	


//	public void setDivisionlist1(String divisionlist1) {
//			this.divisionlist1 = divisionlist1;
//		}
//
//
//	public Map<Integer, String> getDivisionlist1() {
//			return divisionlist1;
//		}
//
//
//		public void setDivisionlist1(Map<Integer, String> divisionlist1) {
//			this.divisionlist1 = divisionlist1;
//		}


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public String getDivisionlist1() {
		return divisionlist1;
	}


	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
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
	double mnthTotal=0.0;
	Double netTotal=0.0;
	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		public String AjaxCondWiseEarningReport()
		{
					Connection connection = null;
					 Statement stmt = null;
   			        ResultSet rs = null;
   			        String queryyy;
   			     DecimalFormat formate = new DecimalFormat("#.##"); 
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				   Session session1 = null;
					Transaction transaction  = null;
					Common common = new Common();
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					System.out.println("depotlist1"+depotlist1); 
					System.out.println("division id"+divisionlist1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					//String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
					String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
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
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
					 System.out.println("connection........."+connection);
					 stmt = connection.createStatement();
					 System.out.println("ctoken"+ctoken); 
					 System.out.println("connection........."+connection);
					 transaction = session1.beginTransaction();

					 Calendar cal = Calendar.getInstance();
				       

					 SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
					 Date startDate = format.parse(startdate);
					 Date endDateO = format.parse(enddate);
					 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM");
					 String startDate1 = fomat2.format(startDate).toString();
					 String endDate1 = fomat2.format(endDateO).toString();
					 
				
					 int tokenNo=Integer.parseInt(ctoken);
						queryyy = "SELECT DATE_FORMAT(dte,'%m-%Y') dte,ifnull(cancelkm,'0.0') as cancelkm,ifnull(extrakm,'0.0') as extrakm,sum(deadkm) as deadKm, "+
								     "sum(manualpassenger) as ticketRev,sum(manualdailypass) as dp,sum(manualmonthlypass) as mp,sum(manualluggage) as luggage,sum(dist) as schdKm ,sum(etmamount) as etmRev ,sum(bata) as bata, "+
								      "sum(incentive) as incn ,sum(misl) as misl from "+
								     "(SELECT wd.Ticket_Audited_Date AS dte,ifnull(block.manualpassenger,0) manualpassenger,ifnull(block.manualdailypass,0) manualdailypass, "+ 
								     "ifnull(block.manualmonthlypass,0) manualmonthlypass,ifnull(block.manualluggage,0) manualluggage, "+
								     "e.EMPLOYEE_NAME AS ename,e.TOKEN AS token,ifnull(gl.cancel_km,0)cancelkm,sum(conductor_Total_Bata+driver_Total_Bata)bata,  "+
								     "sum(driver_incentive_amount+cond_incentive_amount)incentive,sum(driver_other_deduction+cond_other_deduction)misl, "+
								     "sum(if(sd.is_dread_trip =1, (sd.distance), 0)) AS deadkm,"+
								      "ifnull(gl.extra_km,0)extrakm,sum(distance) as dist,ifnull(ETIM_Coll_Amt,0) etmamount FROM Waybill_Details wd LEFT JOIN (SELECT a1.waybill_cwa_block_master_id, sum(if(ticket_type_manual_id IN (1), "+
								      "value, 0)) AS manualpassenger, sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass,"+
								      "sum(if(ticket_type_manual_id =3, value, 0)) AS manualmonthlypass, sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage "+
								      "FROM waybill_cwa_block_master a1 INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id "+	  
								      "GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2  "+
								      "INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id "+  
								      "LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id "+ 
								      "INNER JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id "+ 
								      "INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type "+ 
								      "LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id "+ 
								      "inner join schedule_details sd on sd.form_four_id=wd.schedule_No "+
								      "WHERE wd.Ticket_Audited_Date BETWEEN '"+startDate1+"-01 00:00:00' and '"+endDate1+ "-31 23:59:59' AND  (wd.status='AUDITED'OR wd.status='CLOSED') AND e.EMPLOYEE_ID='"+tokenNo+"'GROUP BY wd.Waybill_No,e.EMPLOYEE_ID) a GROUP BY DATE_FORMAT(dte,'%Y-%m') ";
	
						rs = stmt.executeQuery(queryyy);
						 System.out.println("queryy is --"+queryyy);
					String filePath = "Report/";

					String fileName = "ConductorWiseReport.txt";
					  
		
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Conductorwise Earning Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        String nwkr=""    +   "                                   Conductorwise Earning Report   " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Month</th><th>Tckt Rev M</th><th>ETM Rev</th><th>Lug Rev</th><th>Dp Rev</th><th>Mp Rev</th><th>Schd Km</th><th>Extra Km</th><th>Dead Km</th><th>Cancel Km</th><th>Oprtd Km</th><th>Total Rev</th><th>Bata</th><th>Incn</th><th>Misc</th><th>Total Ded</th><th>Net</th>" +
								"                                                      "+"</tr></thead><tbody>";
						

						String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
								+add("SNo",3)+"|"+add("Month",10)+ "|"+add("Tckt Rev M",25)+ "|"+add("ETM Rev",7)+"|"+add("Lug Rev",14)+"|"+add("Dp Rev",10)+"|"+add("Mp Rev",11)+"|"+add("Schd Km",7)+"|"+add("Extra Km",10)+"|"+add("Dead Km",14)+"|"+add("Can Km",14)+"|"+add("Oprtd Km",14)+"|"+add("Tot Rev",14)+"|"+add("Batta",14)+"|"+
								add("Incn",14)+"|"+add("Misc",14)+"|"+add("Total Ded",14)+"|"+add("Net",14)+"|"
							 +" "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						
							
						 
						    String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);
						
					        HttpServletResponse response = ServletActionContext.getResponse();
					        int i=1;
	       					 while (rs.next()) {
	       					  
	 						    String date1=rs.getString("dte").toString();
	 						    regionTypeAjaxString +="<tr>";
	       						regionTypeAjaxString +="<td>"+i+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("dte")+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("ticketRev")+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("etmRev")+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("luggage")+"</td>"; 
	       						regionTypeAjaxString +="<td>"+rs.getString("dp")+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("mp")+"</td>"; 
	       						regionTypeAjaxString +="<td>"+rs.getString("schdKm")+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("extrakm")+"</td>";
	       						regionTypeAjaxString +="<td>"+rs.getString("deadKm")+"</td>";
	       						double dead=Double.parseDouble(rs.getString("deadKm"));
	       						regionTypeAjaxString +="<td>"+rs.getString("cancelkm")+"</td>";   
	       						double oprtdKm =Double.parseDouble(rs.getString("schdKm"))+Double.parseDouble(rs.getString("extrakm"))-Double.parseDouble(rs.getString("deadKm"))-Double.parseDouble(rs.getString("cancelkm"));
	       						oprtdKm = Double.valueOf(formate.format(oprtdKm));
	       						regionTypeAjaxString +="<td>"+oprtdKm+"</td>";
	       						double totalRev =  Double.parseDouble(rs.getString("ticketRev"))+Double.parseDouble(rs.getString("etmRev"))+
	           					Double.parseDouble(rs.getString("luggage"))+Double.parseDouble(rs.getString("dp"))+Double.parseDouble(rs.getString("mp"));
	                               regionTypeAjaxString +="<td>"+totalRev+"</td>";
	                               regionTypeAjaxString +="<td>"+rs.getString("bata")+"</td>"; 
	          						regionTypeAjaxString +="<td>"+rs.getString("incn")+"</td>"; 
	          						regionTypeAjaxString +="<td>"+rs.getString("misl")+"</td>";   
	                                double totalDeduc = Double.parseDouble(rs.getString("bata"))+Double.parseDouble(rs.getString("incn"))+Double.parseDouble(rs.getString("misl"));
	                                regionTypeAjaxString +="<td>"+totalDeduc+"</td>";
	                                netTotal= totalRev-totalDeduc;
	                                netTotal = Double.valueOf(formate.format(netTotal));
	                                regionTypeAjaxString +="<td>"+netTotal+"</td>";
	          						    regionTypeAjaxString +="</tr>";
	          						  DecimalFormat df = new DecimalFormat("####0.00");
	          						 mnthTotal +=netTotal;
	          						     
	          						mnthTotal = Double.valueOf(formate.format(mnthTotal));
    						    i++;

    					 }
	       					regionTypeAjaxString +="<tr><td  colspan='17'><center><b>Month Totals</b></center></td><td align='center'><b>"+ mnthTotal+"</td></tr>" +"\n";
//					        regionTypeAjaxString +="<tr><td colspan='18'><center><h4><b><span style='color:red'>No Records Found<b></span></h4></center></td></</tr>";
						   
					        //Connection Close
					        if (rs != null) {
  				            	rs.close();
  				            }
  				            
  				            if (stmt != null) {
  				            	stmt.close();
  				            }

  				            if (connection != null) {
  				                connection.close();
  				            }
					    
					        
					     
						   // regionTypeAjaxString += "</tbody></table></div><table><tr><td>Tckt Rev M</td><td>Ticket Revenur Manual</td></tr><td>Dp</td><td>Daily Pass</td></tr><tr><td>Mp</td><td>Monthly Pass</td></tr><tr><td>Ded</td><td>Deduction</td></tr></table>";
						    		                    
						  
					String p=str;

//					PW.println(p);
//					PW.close();
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
		
		
		public String getCondTokenNo() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			//String date = req.getParameter("selectedDate");
//			Common cm = new Common();
//			String formattedgivendate = cm.getDateFromPicker(date);
//			System.out.println("formattedgivendate" + formattedgivendate);
			List<String> l2 = dao.getCondTokenNoName(parentId);
			List<String> l1 = dao.getCondTokenNoId(parentId);
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



