package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class SeniorcitizenETMticketconsumptionreport extends ActionSupport{
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	 public String depotNo;
	 public String divisonNo;
	 
	 
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
	
	public String getVehicle() {
	HttpServletRequest req = ServletActionContext.getRequest();
	
	int parentId = Integer.parseInt(req.getParameter("val"));
	String regionTypeAjaxString = "";
	
    	List<String> l1 = getVehicleId(parentId);
		List<String> l2 = getVehicleName(parentId);
		
		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
	public List getVehicleId(int orgchart_id) {
		List list = new ArrayList();
		String qry = "select vehicle_id from vehicle where deleted_status=0 and status='ACTIVE' and " +
				"org_chart_id="+orgchart_id+" order by vehicle_id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("vehicle_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	public List getVehicleName(int orgchart_id) {
		List list = new ArrayList();
		String qry = "select license_number from vehicle where deleted_status=0 and status='ACTIVE' and " +
				"org_chart_id="+orgchart_id+" order by vehicle_id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("license_number").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	@SuppressWarnings("finally")
public String AjaxSeniorcitizenETMticketconsumptionreport()
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
		
		
		String querytype ="";
		if(depotNo.equalsIgnoreCase("0") && divisonNo.equalsIgnoreCase("0") ){
			querytype="";
		}
		else if(depotNo.equalsIgnoreCase("0") && !divisonNo.equalsIgnoreCase("0")){
			querytype="  and division_id='"+divisonNo+"'  ";
			
		}else {
			querytype=" and depot_id='"+depotNo+"' and division_id='"+divisonNo+"'";
		}
	    
		String sql="SELECT pass_day,denom,sum(total_tickets) as tick,sum(total_value)as val"+ " FROM daily_pass_consumption "+
    	"where dat between '"+date1+"' and '"+date2+"' "+querytype+" and pass_day ='SC'  group by pass_day,denom order by CAST(denom AS UNSIGNED)";
                             
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session1.createSQLQuery(sql).addScalar("pass_day")
		 		.addScalar("denom").addScalar("tick").addScalar("val");
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		
//					String realpath = ServletActionContext.getRequest()
//							.getRealPath("/");
//		
//			
//					String filePath = "Reports/";
//
//					String fileName = "EarlyArrivalSummary.txt";
					
		
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Senior citizen ETM ticket consumption report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
					     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
					     
					   
					    
				        
				        String nwkr="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
				          +	  "                                     "+""+"                                                                          \n"
			      		  +   "                                            "+""+"                                                                                  \n" 
			      		  +   "                                   Senior citizen ETM ticket consumption report      " +
			      		 "  \n                                  From Date:- "+startdate+" To Date:-"+ enddate+"    \n"                                           
			      		  +   "                                                               Run Date:-"+runDateTime+"               \n";
//				        regionTypeAjaxString +="<div align='center'><b>Range Of Conductors:</b></div>"+"<br>";
				       
				        
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:80%;border-collapse: collapse;'></thead>";
//						regionTypeAjaxString +="<td align='center' width='10%'>Sr NO<td align='center' width='40%'>Denomination</td>";
//						regionTypeAjaxString +="<td align='center' width='50%'>No.Of Tckts<td align='center' width='60%'>Total Rev</tr>";

//						String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n" 
//								+add("",5)+"|"+add("Date",20)+ "|"+add("Schedule No",20)+ "|"+add("Scheduled Km",20)+"|"+add("Target Earnings",20)+"|"+add("Actual Earnings",20)+"|"+"\n"
//						 		+ " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n";
//						
//							
						 
	//					    String path = realpath + filePath + fileName;
		//			        str+=ft+nwkr+add(headingprint,5);
double total=0;double ticketotal=0;
                		for (int i = 0; i < aliasToValueMapList.size(); i++) {
						
						regionTypeAjaxString +="<tr>";
						Map<String, Object> list = aliasToValueMapList.get(i);
						int j=i+1;
						regionTypeAjaxString +="<td>"+j+"</td>";
					//	regionTypeAjaxString +="<td>"+list.get("pass_day").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("denom").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("tick").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("val").toString()+"</td>";
						total+=Double.parseDouble(list.get("val").toString());
						ticketotal+=Double.parseDouble(list.get("tick").toString());
					}
                		BigDecimal b2=new BigDecimal(total);
						 MathContext mc = new MathContext(12);
						b2=b2.round(mc);
                		regionTypeAjaxString +="<tr><td colspan='2'>Total</td><td>"+ticketotal+"</td><td>"+b2+"</td></tr>";  
						 //   regionTypeAjaxString +="<tr><td colspan='4'><center><b>Grand Total</b></center></td><td align='right'><b>"+ ""+"</td></tr>" +"\n";  
						    
					     
					 
					 regionTypeAjaxString += "</table></div> </div>  " ;
//					 str+= ""+add("",5)+"|" + add("Total", 90) + "|" +  add(String.valueOf(totalamount),20)+ "|"+"\n";
//					 
				//	 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
				//	 System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
					 HttpServletResponse response = ServletActionContext.getResponse();
						PrintWriter out;
					
					
						
//						FileOutputStream FOS = new FileOutputStream(path);
//						PrintWriter PW = new PrintWriter(FOS);
//						
//			            	System.out.println(str);
//					String p=str;
//					System.out.println("realpath="+path);
//					System.out.println("string..@@"+p);
//
//					PW.println(p);
//					PW.close();
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
		}}
	