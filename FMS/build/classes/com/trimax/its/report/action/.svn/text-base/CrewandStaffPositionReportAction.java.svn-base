package com.trimax.its.report.action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;


public class CrewandStaffPositionReportAction extends ActionSupport{
		String path="";
		char ft = 15;
		String str="";
		String c=" ";
		 public String startdate;
		 public String enddate;
		 public String depotNo;
		 public String divisonNo;
		 public String time;
		 
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

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
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
		@SuppressWarnings("finally")
	public String AjaxCrewandStaffPosition()
	  {
		try {
			  
		      Date  ss1=new Date();
		      Common common = new Common();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			
			Session session1 = null;
			Transaction transaction  = null;
			
			String sql="select employee_type_name,sum(driver) Drivers,sum(conductor) Conductors,sum(driverconductor) DriverCumConductor," +
					"sum(driver+conductor+driverconductor) total,sum(Mechanic) Mechanics,sum(Others) Others," +
					"sum(driver+conductor+driverconductor+Mechanic+Others) grandtotal from (select et.employee_type_name," +
					"if(e.WORKING_DESIGNATION=1,count(*),0) driver, 0 as conductor, 0 as driverconductor,0 as Mechanic,0 as Others from employee e " +
					"inner join designation_type dt on e.WORKING_DESIGNATION=dt.designation_type_id right join employee_type et on " +
					"e.EMPLOYEE_TYPE_NAME = et.employee_id where e.STATUS='ACTIVE' and e.WORKING_DESIGNATION=1 group by et.EMPLOYEE_TYPE_NAME " +
					"union select et.employee_type_name,0 as driver,if(e.WORKING_DESIGNATION=2,count(*),0) conductor, 0 as driverconductor," +
					"0 as Mechanic,0 as Others from employee e inner join designation_type dt on e.WORKING_DESIGNATION=dt.designation_type_id " +
					"right join employee_type et on e.EMPLOYEE_TYPE_NAME = et.employee_id where e.STATUS='ACTIVE' and e.WORKING_DESIGNATION=2 " +
					"group by et.EMPLOYEE_TYPE_NAME union select et.employee_type_name,0 as driver,0 as conductor," +
					"if(e.WORKING_DESIGNATION=16,count(*),0) driverconductor,0 as Mechanic,0 as Others from employee e " +
					"inner join designation_type dt on e.WORKING_DESIGNATION=dt.designation_type_id " +
					"right join employee_type et on e.EMPLOYEE_TYPE_NAME = et.employee_id where e.STATUS='ACTIVE' and e.WORKING_DESIGNATION=16 " +
					"group by et.EMPLOYEE_TYPE_NAME union select et.employee_type_name,0 as driver,0 as conductor, 0 as driverconductor," +
					"if(e.WORKING_DESIGNATION=17,count(*),0) Mechanic,0 as Others from employee e inner join designation_type dt on " +
					"e.WORKING_DESIGNATION=dt.designation_type_id right join employee_type et on e.EMPLOYEE_TYPE_NAME = et.employee_id where " +
					"e.STATUS='ACTIVE' and e.WORKING_DESIGNATION=17 group by et.EMPLOYEE_TYPE_NAME union " +
					"select et.employee_type_name,0 as driver,0 as conductor, 0 as driverconductor,0 as Mechanic," +
					"if(e.WORKING_DESIGNATION=18,count(*),0) Others from employee e " +
					"inner join designation_type dt on e.WORKING_DESIGNATION=dt.designation_type_id " +
					"right join employee_type et on e.EMPLOYEE_TYPE_NAME = et.employee_id where e.STATUS='ACTIVE' and e.WORKING_DESIGNATION=18 " +
					"group by et.EMPLOYEE_TYPE_NAME)a group by a.employee_type_name";
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 Query query = session1.createSQLQuery(sql).addScalar("employee_type_name").addScalar("Drivers")
				 		.addScalar("Conductors").addScalar("DriverCumConductor").addScalar("total")
				 		.addScalar("Mechanics").addScalar("Others").addScalar("grandtotal");
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
						String realpath = ServletActionContext.getRequest()
								.getRealPath("/");
			
				
						String filePath = "Reports/";
	
						String fileName = "CrewandStaffPositionReport.txt";
						
						     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Crew and Staff Position Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

					        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
					        
					        
					        
					        
					        String nwkr="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
					          +	  "                                     "+""+"                                                                          \n"
				      		  +   "                                            "+""+"                                                                                  \n" 
				      		  +   "                                   Crew and Staff Position Report       " +
				      		 "  \n                                  From Date:- "+startdate+" To Date:-"+ enddate+"    \n"                                           
				      		  +   "                                                               Run Date:-"+runDateTime+"               \n";
//					        regionTypeAjaxString +="<div align='center'><b>Range Of Conductors:</b></div>"+"<br>";
					       
					        
					        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
					        regionTypeAjaxString +="<td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Particulars</b></td><td align='center' width='20%'><b>Drivers</b></td>";
							regionTypeAjaxString +="<td align='center' width='20%'><b>Conductors</b></td><td align='center' width='20%'><b>Driver Cum Conductors</b></td>" +
									"<td align='center' width='20%'><b>Total</b></td><td align='center' width='10%'><b>Mechanic</b></td>" +
									"<td align='center' width='20%'><b>Others</b></td><td align='center' width='10%'><b>Grand Total</b></td></tr>";
							

//							String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n" 
//									+add("",5)+"|"+add("Date",20)+ "|"+add("Schedule No",20)+ "|"+add("Scheduled Km",20)+"|"+add("Target Earnings",20)+"|"+add("Actual Earnings",20)+"|"+"\n"
//							 		+ " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n";
//							
//								
							 
		//					    String path = realpath + filePath + fileName;
			//			        str+=ft+nwkr+add(headingprint,5);

							for (int i = 0; i < aliasToValueMapList.size(); i++) {
								
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								regionTypeAjaxString +="<td>"+list.get("employee_type_name").toString()+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								regionTypeAjaxString +="<td>"+list.get("Drivers").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("Conductors").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("DriverCumConductor").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("total").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("Mechanics").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("Others").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("grandtotal").toString()+"</td>";

//								
//								str+=""+add("",5)+"|"+add(date,20)+"|" + add(snumber, 20) +"|" + add(count,20)+"|" + add(count1,20)+"|" + add(total,20)+ "|"+"\n"; 
								regionTypeAjaxString +="</tr>";
							}
//							
								
							 
							  
						    
						       
							  //  regionTypeAjaxString +="<tr><td colspan='4'><center><b>Grand Total</b></center></td><td align='right'><b>"+ ""+"</td></tr>" +"\n";  
							    
						     
						 
						 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div>";
//						 str+= ""+add("",5)+"|" + add("Total", 90) + "|" +  add(String.valueOf(totalamount),20)+ "|"+"\n";
//						 
					//	 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
					//	 System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));

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


