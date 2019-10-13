package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class VehiclewiseKMPLReportITS {

	
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	 
	 private Map<Integer, String> depotlist;
	 private String drivertoken;
	 private Map<Integer, String> drivertokenlist;
	 public String divisionid;
		
		public String getDivisionid() {
			return divisionid;
		}
		public void setDivisionid(String divisionid) {
			this.divisionid = divisionid;
		}

		 public String depotid;
		 
	public String getDepotid() {
			return depotid;
		}
		public void setDepotid(String depotid) {
			this.depotid = depotid;
		}
	public String getDrivertoken() {
		return drivertoken;
	}

	public void setDrivertoken(String drivertoken) {
		this.drivertoken = drivertoken;
	}

	public Map<Integer, String> getDrivertokenlist() {
		return drivertokenlist;
	}

	public void setDrivertokenlist(Map<Integer, String> drivertokenlist) {
		this.drivertokenlist = drivertokenlist;
	}

	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
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

		int subtotalTickets=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
	
	


	public String execute() throws Exception {
		//this.setDepotlist(getDepotName());
		return "success";
	}


	
	@SuppressWarnings("finally")
public String vehiclewiseKMPLReportITS()
  {
	try {
		  
	      Date  ss1=new Date();
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		  String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		Session session1 = null;
		Transaction transaction  = null;
		
		String sql="";
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		  sql = "SELECT ifnull(SUM(operated_km),0) operated, ifnull(SUM(fuel),0) fuel," +
		 		"ifnull(SUM(operated_km)/SUM(fuel),0) kmpl " +
		 		"FROM `kmpl_details` WHERE `operated_date` between '" + date1 +"' and '" + date2 +"' ";

		 Query query = session1.createSQLQuery(sql).addScalar("operated").addScalar("fuel")
			 		.addScalar("kmpl");
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Vehicle Wise KMPL Report(Corporation)</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        
				        
				
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
						regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Corporation</b></td><td  align='center' width='10%'><b>Total Actual Operated KM</b></td><td align='center' width='10%'><b>Total HSD in Litres</b></td><td align='center' width='10%'><b>Average KMPL</b></td></tr>";

//					

						
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							int j=i+1;
							
							Map<String, Object> list = aliasToValueMapList.get(i);
							if(list.get("operated").toString().equals("0.0")){
								regionTypeAjaxString +="<tr><td colspan='5'><center><b>No Records Found</b></center></td></b></tr>";
							}else{
							regionTypeAjaxString +="<tr>";
							regionTypeAjaxString +="<td>"+j+"</td>";
							 regionTypeAjaxString +="<td><a href='#' onclick='jsFunction();'>"+"BMTC"+"</a></td>";
//							
							
							regionTypeAjaxString +="<td>"+list.get("operated").toString()+"</td>";
							
							regionTypeAjaxString +="<td>"+list.get("fuel").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("kmpl").toString()+"</td>";
							regionTypeAjaxString +="</tr>";
							}
						}
						
					
					 
					 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
//				
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
		
	public String getVehicleDivisionDetails(){
		String startdate1 = ServletActionContext.getRequest().getParameter("startdate");
		String enddate1 = ServletActionContext.getRequest().getParameter("enddate");
		//String divisionid = ServletActionContext.getRequest().getParameter("divisionid");
		setStartdate(startdate1);
		setEnddate(enddate1);
		//setDivisionid(divisionid);
		return "success";
	}
	private String startdate1;
	private String enddate1;
	
	public String getStartdate1() {
		return startdate1;
	}

	public void setStartdate1(String startdate1) {
		this.startdate1 = startdate1;
	}

	public String getEnddate1() {
		return enddate1;
	}

	public void setEnddate1(String enddate1) {
		this.enddate1 = enddate1;
	}

	public String getdivisiondetails1()
	  {
		try {
			  
		      Date  ss1=new Date();
		      Common common = new Common();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			//System.out.println("startdate"+startdate);
			//System.out.println("enddate"+enddate);
			  String date1=common.getDateFromPicker(startdate1);
			String date2=common.getDateFromPicker(enddate1);
			//System.out.println("date1===="+date1);
			//System.out.println("date2===="+date2);
			Session session1 = null;
			Transaction transaction  = null;
			String strtdate1=ServletActionContext.getRequest().getParameter("date1");
			String endate1=ServletActionContext.getRequest().getParameter("date2");
			String sql1="";
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			  sql1 = "SELECT count(*),org_name ,division_id,SUM(operated_km) operated, SUM(fuel) fuel,SUM(operated_km)/SUM(fuel) kmpl " +
			  		"FROM `kmpl_details` km inner join  org_chart org on org.org_chart_id=km.division_id " +
			  		"WHERE `operated_date` between '" + date1 +"' and '" + date2 +"' group by division_id";


			 Query query2 = session1.createSQLQuery(sql1).addScalar("org_name").addScalar("operated").addScalar("fuel")
				 		.addScalar("kmpl").addScalar("division_id");
			 query2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query2.list();
						String realpath = ServletActionContext.getRequest()
								.getRealPath("/");	
						
						     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>DivisionWise VehicleWise KMPL Report</br>From Date:- "+startdate1+" To Date:-"+ enddate1+"</h4></div>";

					        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
					        
					        
					        
					
					        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
							regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Division Name</b></td><td  align='center' width='10%'><b>Total Actual Operated KM</b></td><td align='center' width='10%'><b>Total HSD in Litres</b></td><td align='center' width='10%'><b>Average KMPL</b></td></tr>";

                           	for (int i = 0; i < aliasToValueMapList.size(); i++) {
								int j=i+1;
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								regionTypeAjaxString +="<td>"+j+"</td>";
								 regionTypeAjaxString +="<td><a href='#' onclick='jsFunction1("+list.get("division_id").toString()+");'>"+list.get("org_name").toString()+"</a></td>";
								
								regionTypeAjaxString +="<td>"+list.get("operated").toString()+"</td>";
								
								regionTypeAjaxString +="<td>"+list.get("fuel").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("kmpl").toString()+"</td>";

								regionTypeAjaxString +="</tr>";
							}
							
				
						 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
						HttpServletResponse response = ServletActionContext.getResponse();
						PrintWriter out;
						
						
						
							
//						
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
	
	public String getdepotvehicledetails(){
		String startdate1 = ServletActionContext.getRequest().getParameter("startdate1");
		String enddate1 = ServletActionContext.getRequest().getParameter("enddate1");
		String divisionid = ServletActionContext.getRequest().getParameter("divisionid");
		setStartdate(startdate1);
		setEnddate(enddate1);
		setDivisionid(divisionid);
		return "success";
	}
	
	
	public String getdepotvehicledetails1()
	  {
		try {
			  
			
		      Date  ss1=new Date();
		      Common common = new Common();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			//System.out.println("startdate"+startdate);
			//System.out.println("enddate"+enddate);
			  String date1=common.getDateFromPicker(startdate1);
			String date2=common.getDateFromPicker(enddate1);
			//System.out.println("date1===="+date1);
			//System.out.println("date2===="+date2);
			Session session1 = null;
			Transaction transaction  = null;
			String strtdate1=ServletActionContext.getRequest().getParameter("date1");
			String endate1=ServletActionContext.getRequest().getParameter("date2");
			String sql1="";
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			  sql1 ="SELECT count(*),depot_id,org_name,SUM(operated_km) operated, " +
			  		"SUM(fuel) fuel,SUM(operated_km)/SUM(fuel) kmpl " +
			  		"FROM `kmpl_details` km inner join org_chart og on og.org_chart_id=km.depot_id " +
			  		"WHERE `operated_date` between '" + date1 +"' and '" + date2 +"'  " +
			  		"and division_id='"+divisionid+"' group by depot_id;";



			 Query query2 = session1.createSQLQuery(sql1).addScalar("org_name").addScalar("operated").addScalar("fuel")
				 		.addScalar("kmpl").addScalar("depot_id");
			 query2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query2.list();
						String realpath = ServletActionContext.getRequest()
								.getRealPath("/");

						     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>DepotWise Vehiclewise KMPL Report</br>From Date:- "+startdate1+" To Date:-"+ enddate1+"</h4></div>";

					        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
					        
					        
					        
					
					        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
							regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Depot Name</b></td><td  align='center' width='10%'><b>Total Actual Operated KM</b></td><td align='center' width='10%'><b>Total HSD in Litres</b></td><td align='center' width='10%'><b>Average KMPL</b></td></tr>";
                     
							for (int i = 0; i < aliasToValueMapList.size(); i++) {
								int j=i+1;
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								regionTypeAjaxString +="<td>"+j+"</td>";
								 regionTypeAjaxString +="<td><a href='#' onclick='jsFunction3("+list.get("depot_id").toString()+");'>"+list.get("org_name").toString()+"</a></td>";
								
								regionTypeAjaxString +="<td>"+list.get("operated").toString()+"</td>";
								
								regionTypeAjaxString +="<td>"+list.get("fuel").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("kmpl").toString()+"</td>";
							
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
	
	
	
	
	
	public String getvehicledetailsname(){
		String startdate1 = ServletActionContext.getRequest().getParameter("startdate1");
		String enddate1 = ServletActionContext.getRequest().getParameter("enddate1");
		//String divisionid = ServletActionContext.getRequest().getParameter("divisionid");
		String depotid = ServletActionContext.getRequest().getParameter("depotid");
		//System.out.println("divisionid==="+startdate);
		setStartdate(startdate1);
		setEnddate(enddate1);
		//setDivisionid(divisionid);
		setDepotid(depotid);
		return "success";
	}
	
	
	public String getvehicledetailsname1()
	  {
		try {
			  
			
		      Date  ss1=new Date();
		      Common common = new Common();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			//System.out.println("startdate"+startdate);
			//System.out.println("enddate"+enddate);
			  String date1=common.getDateFromPicker(startdate1);
			String date2=common.getDateFromPicker(enddate1);
			//System.out.println("date1===="+date1);
			//System.out.println("date2===="+date2);
			Session session1 = null;
			Transaction transaction  = null;
			String strtdate1=ServletActionContext.getRequest().getParameter("date1");
			String endate1=ServletActionContext.getRequest().getParameter("date2");
			String sql1="";
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			  sql1 ="SELECT license_number,SUM(operated_km) operated, SUM(fuel) fuel,SUM(operated_km)/SUM(fuel) kmpl " +
			  		"FROM `kmpl_details` km left join vehicle v on v.vehicle_id=km.vehcile_id " +
			  		"WHERE `operated_date` between  '" + date1 +"' and '" + date2 +"'  and km.depot_id='"+ depotid+"' group by km.vehcile_id  order by kmpl";



			 Query query2 = session1.createSQLQuery(sql1).addScalar("license_number").addScalar("operated").addScalar("fuel")
				 		.addScalar("kmpl");
			 query2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query2.list();
						String realpath = ServletActionContext.getRequest()
								.getRealPath("/");

						     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>VehicleWise KMPL Report</br>From Date:- "+startdate1+" To Date:-"+ enddate1+"</h4></div>";

					        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
					        
					        
					        
					
					        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
							regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Vehicle Name</b></td><td  align='center' width='10%'><b>Total Actual Operated KM</b></td><td align='center' width='10%'><b>Total HSD in Litres</b></td><td align='center' width='10%'><b>Average KMPL</b></td></tr>";

//						
							

							for (int i = 0; i < aliasToValueMapList.size(); i++) {
								int j=i+1;
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								regionTypeAjaxString +="<td>"+j+"</td>";
								regionTypeAjaxString +="<td>"+list.get("license_number").toString()+"</td>";
						
								regionTypeAjaxString +="<td>"+list.get("operated").toString()+"</td>";
								
								regionTypeAjaxString +="<td>"+list.get("fuel").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("kmpl").toString()+"</td>";
						
								regionTypeAjaxString +="</tr>";
							}
							
								
							
						 
						 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";

						HttpServletResponse response = ServletActionContext.getResponse();
						PrintWriter out;
						
						
						
							
//						
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
